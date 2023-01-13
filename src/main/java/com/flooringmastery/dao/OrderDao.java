package com.flooringmastery.dao;


import com.flooringmastery.dto.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderDao {

    List<Order> getOrders(LocalDate dateChoice) throws DataPersistenceException;
    void removeOrder(LocalDate date, int orderNumber)throws DataPersistenceException;;
    Order addOrder(Order o) throws DataPersistenceException;
    Order editOrder(Order editedOrder) throws DataPersistenceException;

    //Order removeOrder(Order o) throws DataPersistenceException;
}
