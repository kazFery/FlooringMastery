package com.flooringmastery.service;

import com.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;



import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;


import static org.junit.jupiter.api.Assertions.*;

public class FloorServiceImplTest {

    private FloorService service;

    public FloorServiceImplTest() {

       ApplicationContext  applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

       service = applicationContext.getBean(FloorServiceImpl.class);

//        service = appCtx.getBean(VendingMachineServiceLayerImpl.class);
//        OrdersDao ordersDao = new OrdersDaoStubImpl();
//        ProductsDao productsDao = new ProductsDaoFileImpl();
//        StatesDao statesDao = new StatesDaoFileImpl();
//        AuditDao auditDao = new AuditDaoStubImpl();
//
//        service = new FloorServiceImpl(ordersDao, productsDao, statesDao, auditDao);
    }

    /**
     * Test of getOrders method, of class FloorServiceImpl.
     */
    @Test
    public void testGetOrders() throws Exception {

        assertEquals(1, service.getOrders(LocalDate.of(2023, 04, 30)).size());

        try {
            List<Order> orders = service.getOrders(LocalDate.of(2022, 01, 01));
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {
        }
    }

    /**
     * Test of getOrder method, of class FloorServiceImpl.
     */
    @Test
    public void testGetOrder() throws Exception {
        Order order = service.getOrder(LocalDate.of(2023, 04, 30), 1);
        assertNotNull(order);

        try {
            order = service.getOrder(LocalDate.of(2023, 04, 30), 0);
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {
        }

        try {
            service.getOrder(LocalDate.of(2023, 01, 01), 1);
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {
        }

    }

    /**
     * Test of calculateOrder method, of class FloorServiceImpl.
     */
    @Test
    public void testCalculateOrder() throws Exception {

        Order order1 = new Order();
        order1.setCustomerName("Place LLC");
        order1.setState("WA");
        order1.setProductType("Laminate");
        order1.setArea(new BigDecimal("100"));

        Order order2 = new Order();
        order2.setCustomerName("Place LLC");
        order2.setState("WA");
        order2.setProductType("Laminate");
        order2.setArea(new BigDecimal("100"));

        Order order3 = new Order();
        order3.setCustomerName("Place LLC");
        order3.setState("WA");
        order3.setProductType("wood");
        order3.setArea(new BigDecimal("100"));

        assertEquals(service.calculateOrder(order1), service.calculateOrder(order2));

        try {
            service.calculateOrder(order3);
            fail("ERROR: We do not sell that product.");
        } catch (ProductValidationException e) {
        }

        order1.setCustomerName("");

        try {
            service.calculateOrder(order1);
            fail("Expected OrderValidationException was not thrown.");
        } catch (OrderValidationException e) {
        }

        order1.setCustomerName("Place LLC");
        order1.setState("");

        try {
            service.calculateOrder(order1);
            fail("Expected OrderValidationException was not thrown.");
        } catch (OrderValidationException e) {
        }

        order1.setState("WA");
        order1.setProductType("");

        try {
            service.calculateOrder(order1);
            fail("Expected OrderValidationException was not thrown.");
        } catch (OrderValidationException e) {
        }

        order1.setProductType("Wood");
        order1.setArea(new BigDecimal("0"));

        try {
            service.calculateOrder(order1);
            fail("Expected OrderValidationException was not thrown.");
        } catch (OrderValidationException e) {
        }

        order1.setArea(new BigDecimal("100"));
        order1.setState("MN");

        try {
            service.calculateOrder(order1);
            fail("Expected StateValidationException was not thrown.");
        } catch (StateValidationException e) {
        }

        order1.setState("WA");
        order1.setProductType("Glass");

        try {
            service.calculateOrder(order1);
            fail("Expected ProductValidationException was not thrown.");
        } catch (ProductValidationException e) {
        }

    }

    /**
     * Test of addOrder method, of class FloorServiceImpl.
     */
    @Test
    public void testAddOrder() throws Exception {

        Order order = new Order();
        order.setCustomerName("Place LLC");
        order.setState("WA");
        order.setProductType("Wood");
        order.setArea(new BigDecimal("100"));
        service.addOrder(order);

        assertEquals(order, service.addOrder(order));

    }

    /**
     * Test of compareOrders method, of class FloorServiceImpl.
     */
    @Test
    public void testCompareOrders() throws Exception {

        Order savedOrder = service.getOrder(LocalDate.of(2023, 04, 30), 1);

        Order editedOrder = new Order();
        editedOrder.setCustomerName("Peanut Butter LLC");

        Order updatedOrder = service.compareOrders(savedOrder, editedOrder);

        assertEquals(updatedOrder, savedOrder);

    }

    /**
     * Test of editOrder method, of class FloorServiceImpl.
     */
    @Test
    public void testEditOrder() throws Exception {

        Order savedOrder = service.getOrder(LocalDate.of(2023, 04, 30), 1);
        assertNotNull(savedOrder);

        try {
            savedOrder = service.getOrder(LocalDate.of(2023, 04, 30), 0);
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {
        }

    }

    /**
     * Test of removeOrder method, of class FloorServiceImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {

        Order removedOrder = service.getOrder(LocalDate.of(2023, 04, 30), 1);
        assertNotNull(removedOrder);

        try {
            removedOrder = service.getOrder(LocalDate.of(2023, 04, 30), 0);
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {
        }

    }

}