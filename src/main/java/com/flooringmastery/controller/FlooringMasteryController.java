package com.flooringmastery.controller;



import com.flooringmastery.dao.DataPersistenceException;
import com.flooringmastery.dto.Order;
import com.flooringmastery.dto.Product;
import com.flooringmastery.dto.Tax;
import com.flooringmastery.service.*;
import com.flooringmastery.ui.FlooringMasteryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Controller
public class FlooringMasteryController {

    @Autowired
    FlooringMasteryView view;

    @Autowired
    FloorService service;


    public void run() throws InvalidOrderNumberException, DataPersistenceException {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {

            while (keepGoing) {
                try {
                    menuSelection = getMenuSelection();

                    switch (menuSelection) {
                        case 1:
                            listOrders();
                            break;
                        case 2:
                            createOrder();
                            break;
                        case 3:
                            orderEdit();
                            break;
                        case 4:
                            orderRemove();
                            break;
                        case 5:
                            dataExport();
                            break;
                        case 6:
                            exit();
                            keepGoing = false;
                            break;
                        default:
                            unknownCommand();
                    }
                }catch(InvalidOrderNumberException e){
                    view.displayErrorMessage(e.getMessage());
                    keepGoing = true;
                }

            }
        } catch (DataPersistenceException | IOException | StateValidationException | OrderValidationException | ProductValidationException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }




    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }


    // 1. Display Orders
    private void listOrders () throws DataPersistenceException, InvalidOrderNumberException {

        LocalDate dateChoice = view.inputDate();
        List<Order> ordersList = service.getOrders(dateChoice);
        // A methode in view to display the ordersList:
        view.displayDateBanner(dateChoice);
        //view.displayDateOrders(service.getOrders(dateChoice));
        view.displayOrdersList(service.getOrders(dateChoice));
        view.displayContinue();
    }

    // 2. Add an Order

    private void createOrder () throws DataPersistenceException, StateValidationException, OrderValidationException, ProductValidationException {
        // A methode in view to display a banner
        view.displayCreateOrdertBanner();

        //from engage: To add an order will query the user for OrderDate, CustomerName,State,
        // ProductType, Area

        //get order date and  validate
        LocalDate inputDate =view.inputNewOrderDate();

        //get Customer name and  validate
        String customerName = view.getCustomerName(false,"");

        // get  state and validate
        List<Tax> states = service.loadStates();
        String state = view.getState(states, false,"");

        //get Product type  and validate
        List<Product>  productList = service.loadProducts();
        String productType = view.getProductType(productList, false,"");

        //get Area  and validate
        BigDecimal area = view.inputArea(false,BigDecimal.ZERO);

        Order newOrder= new Order();
        newOrder.setDate(inputDate);
        newOrder.setArea(area);
        newOrder.setProductType(productType);
        newOrder.setCustomerName(customerName);
        newOrder.setState(state);

        String userResponse = view.displayPlaceOrderMessage();
        if(userResponse.equalsIgnoreCase("y") || userResponse.equalsIgnoreCase("yes")){
            service.addOrder(service.calculateOrder(newOrder));
            // A methode in view to display a message
            view.displayCreateSuccessBanner();
            view.displayContinue();
        }

    }

    //3. Edit an order
    private void orderEdit () throws DataPersistenceException, InvalidOrderNumberException {
        view.displayEditOrderBanner();
        LocalDate date = view.getOrderDate();
        int orderNumber = view.getOrderNumber();
        Order myOrder = service.getOrder(date,orderNumber);
        if (myOrder != null) {
            //get Customer name and  validate
            String customerName = view.getCustomerName(true,myOrder.getCustomerName());
            myOrder.setCustomerName(customerName);

            // get  state and validate
            List<Tax> states = service.loadStates();
            String state = view.getState(states, true,myOrder.getState());
            myOrder.setState(state);

            //get Product type  and validate
            List<Product>  productList = service.loadProducts();
            String productType = view.getProductType(productList, true, myOrder.getProductType());
            myOrder.setProductType(productType);

            //get Area  and validate
            BigDecimal newArea = view.inputArea(true, myOrder.getArea());
            myOrder.setArea(newArea);

            service.editOrder(myOrder);
            // A methode in view to display a message
            view.displayEditSuccessBanner();
        }
        else
            view.displayOrederNotFoundMessage(orderNumber);
    }


    //4. Remove an Order
    private void orderRemove () throws DataPersistenceException, InvalidOrderNumberException {
        // A methode in view to display a banner
        view.displayRemoveOrderBanner();
        int inputOrderNumber = view.getOrderNumber();
        LocalDate date = view.getOrderDate();

        Order myOrder = service.getOrder(date,inputOrderNumber);

        String userResponse = view.displayRemoveConfirmationMessage();
        if(userResponse.equalsIgnoreCase("y")) {
            service.removeOrder(myOrder);
            // A methode in view to display a message
            view.displayRemoveSuccessBanner();
        }
    }


    // 5. Export all data
    private void dataExport() throws IOException {
        service.exportData();
    }

    //6. Exit
    private void exit () throws DataPersistenceException {

        // A methode in view to display a message to exit
        view.displayExitBanner();
    }

    private void unknownCommand () {
        view.displayUnknownCommandBanner();
    }


}
