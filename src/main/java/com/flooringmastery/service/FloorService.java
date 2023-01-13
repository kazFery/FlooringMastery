package com.flooringmastery.service;


import com.flooringmastery.dao.DataPersistenceException;
import com.flooringmastery.dto.Order;
import com.flooringmastery.dto.Product;
import com.flooringmastery.dto.Tax;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface FloorService {

    List<Order> getOrders(LocalDate dateChoice) throws InvalidOrderNumberException,
            DataPersistenceException;

    Order calculateOrder(Order order) throws DataPersistenceException,
            OrderValidationException, StateValidationException, ProductValidationException;

    Order getOrder(LocalDate dateChoice, int orderNumber) throws
            DataPersistenceException, InvalidOrderNumberException;

    Order addOrder(Order order) throws DataPersistenceException;

    Order compareOrders(Order savedOrder, Order editedOrder)
            throws DataPersistenceException, StateValidationException,
            ProductValidationException;

    Order editOrder(Order updatedOrder) throws DataPersistenceException,
            InvalidOrderNumberException;

    void removeOrder(Order removedOrder) throws DataPersistenceException,
            InvalidOrderNumberException;

    void exportData() throws IOException;

    Product getProduct(String productType) throws DataPersistenceException;

    List<Product> loadProducts() throws DataPersistenceException;

    Tax getState(String stateAbbr) throws DataPersistenceException;

    List<Tax> loadStates() throws DataPersistenceException;



}
