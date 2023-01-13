package com.flooringmastery.dao;


import com.flooringmastery.dto.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoImplTest {

    OrderDao testOrderDao;

    @BeforeEach
    void setUp() {
        List<Order> orders = new ArrayList<>();
        String testFile = "Orders_06012013.txt";
        // Use the FileWriter to quickly blank the file
        testOrderDao = new OrderDaoImpl(testFile);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getOrders() throws DataPersistenceException {

        LocalDate inputDate = LocalDate.ofEpochDay(05/01/2022);
        Order order1 = new Order();

        order1.setOrderNumber(1);
        order1.setCustomerName("Ada Lovelace");
        order1.setState("CA");
        order1.setTax(BigDecimal.valueOf(25.00));
        order1.setProductType("Tile");
        order1.setArea(BigDecimal.valueOf(249.00));
        order1.setLaborCostPerSquareFoot(BigDecimal.valueOf(3.50));
        order1.setLaborCostPerSquareFoot(BigDecimal.valueOf(4.15));
        order1.setMaterialCost(BigDecimal.valueOf(871.15));
        order1.setLaborCost(BigDecimal.valueOf(1033.35));
        order1.setTax(BigDecimal.valueOf(476.21));
        order1.setTotal(BigDecimal.valueOf(2381.06));
        List<Order> orders  = testOrderDao.getOrders(inputDate);

        orders.add(order1);
        assertTrue(order1.getCustomerName().equals(orders.get(0).getCustomerName()));

    }

    @Test
    void removeOrder() throws DataPersistenceException {
        Order order1 = new Order();

        order1.setOrderNumber(1);
        order1.setCustomerName("Ada Lovelace");
        order1.setState("CA");
        order1.setTax(BigDecimal.valueOf(25.00));
        order1.setProductType("Tile");
        order1.setArea(BigDecimal.valueOf(249.00));
        order1.setLaborCostPerSquareFoot(BigDecimal.valueOf(3.50));
        order1.setLaborCostPerSquareFoot(BigDecimal.valueOf(4.15));
        order1.setMaterialCost(BigDecimal.valueOf(871.15));
        order1.setLaborCost(BigDecimal.valueOf(1033.35));
        order1.setTax(BigDecimal.valueOf(476.21));
        order1.setTotal(BigDecimal.valueOf(2381.06));

        LocalDate inputDate = LocalDate.ofEpochDay(05/01/2022);
        List<Order> orders  = testOrderDao.getOrders(inputDate);
        orders.add(order1);
        orders.remove(order1);
        assertEquals(0, testOrderDao.getOrders(inputDate).size());


    }

    @Test
    void addOrder() throws DataPersistenceException {

        Order order1 = new Order();

        order1.setOrderNumber(1);
        order1.setCustomerName("Ada Lovelace");
        order1.setState("CA");
        order1.setTax(BigDecimal.valueOf(25.00));
        order1.setProductType("Tile");
        order1.setArea(BigDecimal.valueOf(249.00));
        order1.setLaborCostPerSquareFoot(BigDecimal.valueOf(3.50));
        order1.setLaborCostPerSquareFoot(BigDecimal.valueOf(4.15));
        order1.setMaterialCost(BigDecimal.valueOf(871.15));
        order1.setLaborCost(BigDecimal.valueOf(1033.35));
        order1.setTax(BigDecimal.valueOf(476.21));
        order1.setTotal(BigDecimal.valueOf(2381.06));

        LocalDate inputDate = LocalDate.ofEpochDay(05/01/2022);
        List<Order> orders  = testOrderDao.getOrders(inputDate);
        orders.add(order1);
        assertTrue(order1.getCustomerName().equals(orders.get(0).getCustomerName()));


    }

    @Test
    void editOrder() throws DataPersistenceException {

        Order order1 = new Order();

        order1.setOrderNumber(1);
        order1.setCustomerName("Ada Lovelace");
        order1.setState("CA");
        order1.setTaxRate(new BigDecimal("25.00"));
        order1.setTax(new BigDecimal("476.21"));
        order1.setProductType("Tile");
        order1.setArea(new BigDecimal("249.00"));
        order1.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        order1.setMaterialCost(new BigDecimal("871.15"));
        order1.setLaborCost(new BigDecimal("1033.35"));
        order1.setTotal(new BigDecimal("2381.06"));
        order1.setDate(LocalDate.parse("06/01/2013", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        order1.setMaterialCostPerSquareFoot(new BigDecimal("3.50"));

        LocalDate inputDate=null;

        try {
            inputDate = LocalDate.parse("06/01/2013", DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        } catch (DateTimeParseException e) {
            System.out.println("error");
        }

        List<Order> orders  = testOrderDao.getOrders(inputDate);
        orders.add(order1);
        Order order2 = new Order();
        order2.setOrderNumber(1);
        order2.setCustomerName("Hani");
        order2.setState("CA");
        order2.setTaxRate(new BigDecimal("25.00"));
        order2.setTax(new BigDecimal("476.21"));
        order2.setProductType("Tile");
        order2.setArea(new BigDecimal("249.00"));
        order2.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        order2.setMaterialCost(new BigDecimal("871.15"));
        order2.setLaborCost(new BigDecimal("1033.35"));
        order2.setTotal(new BigDecimal("2381.06"));
        order2.setDate(LocalDate.parse("06/01/2013", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        order2.setMaterialCostPerSquareFoot(new BigDecimal("3.50"));

        testOrderDao.editOrder(order2);

        assertTrue(order2.getCustomerName().equals(orders.get(0).getCustomerName()));


    }
}