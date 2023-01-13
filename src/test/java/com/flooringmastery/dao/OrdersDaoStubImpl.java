package com.flooringmastery.dao;

import com.flooringmastery.dto.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrdersDaoStubImpl implements OrderDao {

    private Order onlyOrder;
    private List<Order> ordersList = new ArrayList<>();

    public OrdersDaoStubImpl() {

        onlyOrder = new Order();
        onlyOrder.setDate(LocalDate.parse("04302023",
                DateTimeFormatter.ofPattern("MMddyyyy")));
        onlyOrder.setOrderNumber(1);
        onlyOrder.setCustomerName("Coolest Company");
        onlyOrder.setState("TX");
        onlyOrder.setTaxRate(new BigDecimal("6.00"));
        onlyOrder.setProductType("Laminate");
        onlyOrder.setArea(new BigDecimal("100"));
        onlyOrder.setMaterialCostPerSquareFoot(new BigDecimal("1.75"));
        onlyOrder.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        onlyOrder.setMaterialCost(onlyOrder.getMaterialCostPerSquareFoot()
                .multiply(onlyOrder.getArea()).setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setLaborCost(onlyOrder.getLaborCostPerSquareFoot().multiply(onlyOrder.getArea())
                .setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setTax(onlyOrder.getTaxRate().divide(new BigDecimal("100.00"))
                .multiply((onlyOrder.getMaterialCost().add(onlyOrder.getLaborCost())))
                .setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setTotal(onlyOrder.getMaterialCost().add(onlyOrder.getLaborCost())
                .add(onlyOrder.getTax()));

        ordersList.add(onlyOrder);

    }



    @Override
    public List<Order> getOrders(LocalDate dateChoice) throws DataPersistenceException {
        if (dateChoice.equals(onlyOrder.getDate())) {
            return ordersList;
        } else {
            //Should return an empty list like the dao does.
            return new ArrayList<>();
        }
    }

    @Override
    public void removeOrder(LocalDate date, int orderNumber) throws DataPersistenceException {
        if (date.equals(onlyOrder.getDate()) && Integer.toString(orderNumber).equals(orderNumber)) {

            ordersList.remove(onlyOrder);
        }
    }


    @Override
    public Order addOrder(Order o) throws DataPersistenceException {
        ordersList.add(o);
        return o;
    }

    @Override
    public Order editOrder(Order editedOrder) throws DataPersistenceException {
        if (editedOrder.getOrderNumber() == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        } else {
            return null;
        }
    }



}
