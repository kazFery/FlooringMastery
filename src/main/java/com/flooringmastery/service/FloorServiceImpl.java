package com.flooringmastery.service;

import com.flooringmastery.dao.*;
import com.flooringmastery.dto.Order;
import com.flooringmastery.dto.Product;
import com.flooringmastery.dto.Tax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class FloorServiceImpl implements FloorService {
    @Autowired
    private OrderDao ordersDao;
    @Autowired
    private ProductsDao productsDao;
    @Autowired
    private TaxesDao taxesDao;
    @Autowired
    private ExportDao exportDao;
    @Autowired
    private AuditDao auditDao;

    public FloorServiceImpl(OrderDao ordersDao, ProductsDao productsDao, TaxesDao taxesDao, AuditDao auditDao) {
        this.ordersDao = ordersDao;
        this.productsDao = productsDao;
        this.taxesDao = taxesDao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Order> getOrders(LocalDate chosenDate) throws InvalidOrderNumberException,
            DataPersistenceException {
        List<Order> ordersByDate = ordersDao.getOrders(chosenDate);
        if (!ordersByDate.isEmpty()) {
            return ordersByDate;
        } else {
            throw new InvalidOrderNumberException("ERROR: No orders "
                    + "exist on that date.");
        }
    }

    @Override
    public Order getOrder(LocalDate chosenDate, int orderNumber) throws
            DataPersistenceException, InvalidOrderNumberException {
        List<Order> orders = getOrders(chosenDate);
        Order chosenOrder = orders.stream()
                .filter(o -> o.getOrderNumber() == orderNumber)
                .findFirst().orElse(null);
        if (chosenOrder != null) {
            return chosenOrder;
        } else {
            throw new InvalidOrderNumberException("ERROR: No orders with that number "
                    + "exist on that date.");
        }
    }

    @Override
    public Order calculateOrder(Order order) throws DataPersistenceException,
            OrderValidationException, StateValidationException, ProductValidationException {

        validateOrder(order);
        calculateTax(order);
        calculateMaterial(order);
        calculateTotal(order);

        return order;

    }

    private void calculateTax(Order order) throws DataPersistenceException,
            StateValidationException {
        //Set state information in order.
        Tax chosenState = taxesDao.getState(order.getState());
        if (chosenState == null) {
            throw new StateValidationException("ERROR: SWG Corp does not "
                    + "serve that state.");
        }
        order.setState(chosenState.getStateAbbreviation());
        order.setTaxRate(chosenState.getTaxRate());
    }

    private void calculateMaterial(Order o) throws DataPersistenceException,
            ProductValidationException {
        //Set product information in order.
        Product chosenProduct = productsDao.getProduct(o.getProductType());
        if (chosenProduct == null) {
            throw new ProductValidationException("ERROR: We do not sell that "
                    + "product.");
        }
        o.setProductType(chosenProduct.getProductType());
        o.setMaterialCostPerSquareFoot(chosenProduct.getMaterialCostPerSquareFoot());
        o.setLaborCostPerSquareFoot(chosenProduct.getLaborCostPerSquareFoot());
    }

    private void calculateTotal(Order order) {
        //Calculate other order fields.
        order.setMaterialCost(order.getMaterialCostPerSquareFoot().multiply(order.getArea())
                .setScale(2, RoundingMode.HALF_UP));
        order.setLaborCost(order.getLaborCostPerSquareFoot().multiply(order.getArea())
                .setScale(2, RoundingMode.HALF_UP));
        order.setTax(order.getTaxRate().divide(new BigDecimal("100.00"))
                .multiply((order.getMaterialCost().add(order.getLaborCost())))
                .setScale(2, RoundingMode.HALF_UP));
        order.setTotal(order.getMaterialCost().add(order.getLaborCost()).add(order.getTax()));
    }

    private void validateOrder(Order order) throws OrderValidationException {
        String message = "";
        if (order.getCustomerName().trim().isEmpty() || order.getCustomerName() == null) {
            message += "Customer name is required.\n";
        }
        if (order.getState().trim().isEmpty() || order.getState() == null) {
            message += "State is required.\n";
        }
        if (order.getProductType().trim().isEmpty() || order.getProductType() == null) {
            message += "Product type is required.\n";
        }
        if (order.getArea().compareTo(BigDecimal.ZERO) == 0 || order.getArea() == null) {
            message += "Area square footage is required.";
        }
        if (!message.isEmpty()) {
            throw new OrderValidationException(message);
        }
    }

    @Override
    public Order addOrder(Order order) throws DataPersistenceException {
        auditDao.writeAuditEntry("Order #"
                + order.getOrderNumber() + " for date "
                + order.getDate() + " ADDED.");
        ordersDao.addOrder(order);

        return order;
    }

    @Override
    public Order compareOrders(Order savedOrder, Order editedOrder)
            throws DataPersistenceException, StateValidationException,
            ProductValidationException {

        //This will only update the already saved order's fields
        if (editedOrder.getCustomerName() == null
                || editedOrder.getCustomerName().trim().equals("")) {
            //No change
        } else {
            savedOrder.setCustomerName(editedOrder.getCustomerName());
        }

        if (editedOrder.getState() == null
                || editedOrder.getState().trim().equals("")) {
        } else {
            savedOrder.setState(editedOrder.getState());
            calculateTax(savedOrder);
        }

        if (editedOrder.getProductType() == null
                || editedOrder.getProductType().equals("")) {
        } else {
            savedOrder.setProductType(editedOrder.getProductType());
            calculateMaterial(savedOrder);
        }

        if (editedOrder.getArea() == null
                || (editedOrder.getArea().compareTo(BigDecimal.ZERO)) == 0) {
        } else {
            savedOrder.setArea(editedOrder.getArea());
        }

        calculateTotal(savedOrder);

        return savedOrder;
    }

    @Override
    public Order editOrder(Order updatedOrder) throws DataPersistenceException,
            InvalidOrderNumberException {
        updatedOrder = ordersDao.editOrder(updatedOrder);
        if (updatedOrder != null) {
                return updatedOrder;
        } else {
            throw new InvalidOrderNumberException("ERROR: No orders with that number "
                    + "exist on that date.");
        }
    }

    @Override
    public void removeOrder(Order removedOrder) throws DataPersistenceException,
            InvalidOrderNumberException {
        ordersDao.removeOrder(removedOrder.getDate(), removedOrder.getOrderNumber());

    }

    @Override
    public void exportData() throws IOException {
        exportDao.exportData();
    }

    @Override
    public Product getProduct(String productType) throws DataPersistenceException {
        return productsDao.getProduct(productType);
    }

    @Override
    public List<Product> loadProducts() throws DataPersistenceException {
        return productsDao.loadProducts();
    }

    @Override
    public Tax getState(String stateAbbr) throws DataPersistenceException {
        return taxesDao.getState(stateAbbr);
    }

    @Override
    public List<Tax> loadStates() throws DataPersistenceException {
        return taxesDao.loadStates();
    }

}
