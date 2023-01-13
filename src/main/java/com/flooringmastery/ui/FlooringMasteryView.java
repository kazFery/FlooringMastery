package com.flooringmastery.ui;

import com.flooringmastery.dto.Order;
import com.flooringmastery.dto.Product;
import com.flooringmastery.dto.Tax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;


@Component
public class FlooringMasteryView {

    @Autowired
    private  UserIO io ;

    public int printMenuAndGetSelection() {
        io.print("******************************");
        io.print("* << Flooring Program>>");
        io.print("* 1. Display Orders");
        io.print("* 2. Add an Order");
        io.print("* 3. EDIT an Order");
        io.print("* 4. remove an Order");
        io.print("* 5. Export All Data");
        io.print("* 6. Quit");
        io.print("******************************");

        return io.readInt("Please select from the above choices.", 0, 7);
    }

    public LocalDate getOrderDate() {
        return io.readDate("Please enter order's Date");
    }

//    public LocalDate getOrderDate(LocalDate min) {
//        boolean validDate=true;
//        LocalDate currentDate = LocalDate.now();
//
//        LocalDate inputDate;
//        LocalDate otherFormatInputDate;
//        do {
//            inputDate = getOrderDate();
//            otherFormatInputDate = LocalDate.parse(inputDate.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//            if (otherFormatInputDate.isBefore(currentDate)){
//                validDate = false;
//                displayDateValidationError();
//            }
//        } while (!validDate);
//        return inputDate;
//    }

    // get customerName and validate it

    public String getCustomerName(boolean edit, String oldName) {
        boolean validName=true;
        String customerName="";
        do {
            customerName = io.readString(edit? "Please enter new Customer Name: (" + oldName + ")" : "Please enter Customer Name: " );
            if (!customerName.matches("^[A-Za-z0-9., ]+$") && (customerName.isBlank() && !edit)) {
                validName = false;
                displayCustomerNameValidationError();
            }
        } while (!validName);
        if (customerName.trim() == "" && edit)
            customerName = oldName;
        return customerName;
    }


    // get order number
    public int getOrderNumber() {
        return  io.readInt("Please enter Order Number: ");
    }
    // get product name and validate it

    public String getProductType(List<Product> productList, boolean edit, String oldproductType) {
        String productType="";
        boolean validProductType=true;

        do {
            productType = io.readString(edit? "Please enter new Product Type: (" + oldproductType + ")" : "Please enter Product Type: ");
            String finalProductType = productType;
            if (productType.trim()=="" && edit)
                break;
            validProductType = true;
            if (productList.stream().noneMatch((p) -> finalProductType.equalsIgnoreCase(p.getProductType()) )){
                validProductType = false;
                displayProductTypeValidationError();
                displayProductList(productList);
            }
        } while (!validProductType);
        if(productType.trim() == "" && edit){
            productType = oldproductType;
        }
        return productType;
    }

    // get area and validate it
    public BigDecimal getArea()  {
        boolean validArea=true;
        BigDecimal area;
        do {
            area = io.readBigDecimal("Please enter Area (sq ft): ");
            if (area.compareTo(new BigDecimal("100")) < 0) {
                validArea = false;
                displayAreaValidationError();
            }
        } while (!validArea);

        return area;
    }

    // get state and validate it
    public String getState(List<Tax> taxList, boolean edit, String oldState) {
        String state;
        boolean validState = true;
        do {
            state =  io.readString(edit? "please enter  new State code: ("+oldState+")" : "please enter State code: ");
            if (state.trim()=="" && edit)
                break;
            String finalState = state;
            boolean notInAbbState = taxList.stream().noneMatch((s) -> finalState.equalsIgnoreCase(s.getStateAbbreviation()));
            boolean notInNameState =  taxList.stream().noneMatch((s) -> finalState.equalsIgnoreCase(s.getStateName()));
            validState = true;
            if (notInAbbState)  {
                if (!notInNameState){
                    continue;}
                validState = false;
                displayStateValidationError();
                displayStatesList(taxList);
            }
        } while (!validState);
        if(state.trim() == "" && edit){
            state = oldState;
        }
        return state;
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void  displayOrdersList(List<Order> ordersList) {

        if (ordersList != null) {
            Collections.sort(ordersList, new Comparator<Order>() {
                @Override
                public int compare(Order lhs, Order rhs) {
                    // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                    return Integer.compare(lhs.getOrderNumber(), rhs.getOrderNumber());
                }
            });
            for (Order currentOrder : ordersList) {
                String orderInfo = String.format("#%s : %s, %s, %s, %s, %s, %s, %s, %s, %s, %s",
                        Integer.toString(currentOrder.getOrderNumber()),
                        currentOrder.getCustomerName(),
                        currentOrder.getState(),
                        currentOrder.getTaxRate().setScale(2).toPlainString(),
                        currentOrder.getProductType(),
                        currentOrder.getArea().setScale(2).toPlainString(),
                        currentOrder.getMaterialCostPerSquareFoot().setScale(2).toPlainString(),
                        currentOrder.getLaborCostPerSquareFoot().setScale(2).toPlainString(),
                        currentOrder.getMaterialCost().setScale(2).toPlainString(),
                        currentOrder.getTax().setScale(2).toPlainString(),
                        currentOrder.getTotal().setScale(2).toPlainString()
                );
                io.print(orderInfo);
            }
        }
    }

    public void  displayStatesList(List<Tax> taxList) {
        io.print("*** State List  ***");
        for (Tax currentTax : taxList) {
            String taxInfo = String.format(" %s, %s",
                    currentTax.getStateAbbreviation(),
                    currentTax.getStateName());

            io.print(taxInfo);
        }
    }

    public void  displayProductList(List<Product> productsList) {
        io.print("*** ProductType, CostPerSquareFoot, LaborCostPerSquareFoot  ***");
        for (Product currentProduct : productsList) {
            String orderInfo = String.format(" %s, %s, %s",
                    currentProduct.getProductType(),
                    currentProduct.getLaborCostPerSquareFoot().setScale(2).toPlainString(),
                    currentProduct.getMaterialCostPerSquareFoot().setScale(2).toPlainString());

            io.print(orderInfo);
        }
    }

    public String displayPlaceOrderMessage(){
        return    io.readString("Do you want to place your order? (Y/N): ");

    }

    public String displayRemoveConfirmationMessage(){
        return    io.readString("Are you sure you want to remove your order? (Y/N): ");
    }


    public void displayRemoveSuccessBanner() {
        io.print("Your order removred sccessfully");
    }

    public void displayExitBanner() {
        io.print("Your are done. See you next time");
    }

    public void displayRemoveOrderBanner() {
        io.print("******  Romove your order  ********");
    }

    public void displayEditSuccessBanner() {
        io.print("Your order edited successfully");
    }

    public void displayEditOrderBanner() {
        io.print("******  Edit your order  ********");
    }

    public void displayCreateSuccessBanner() {
        io.print("Your order added successfully");
    }

    public void displayCreateOrdertBanner() {
        io.print("******  Add your order  ********");
    }

    public void displayListofOrdersBanner() {
        io.print("****** List of orders  ********");
    }

    public void displayExportDataBanner() {
        io.print("******  Data exported in Backup folder  ********");
    }

    public void displayDateValidationError() {
        io.print("your date must be in future");
    }

    public void displayCustomerNameValidationError() {
        io.print("Customer Name must not be blank and is limited to characters [a-z][0-9] as well as periods and comma characters");
    }

    public void displayStateValidationError() {
        io.print("Please select a valid state.");
    }

    public void displayProductTypeValidationError() {
        io.print("Please select a valid product type.");
    }

    public void displayAreaValidationError() {
        io.print("The area must mor the 100 sf.");
    }

    public LocalDate inputDate() {
        return io.readDate("Please enter a date. (MM/DD/YYYY)");   }

    public LocalDate inputNewOrderDate() {
        return io.readDate("Please enter a date. (MM/DD/YYYY)", LocalDate.now());   }


    public void displayDateBanner(LocalDate dateChoice) {
        System.out.printf("=== Orders on %s ===\n", dateChoice);
    }

    public void displayDateOrders(List<Order> ordersByDate) {
        for (Order o : ordersByDate) {
            io.print(o.getOrderNumber() + " | " + o.getCustomerName() + " | "
                    + io.formatCurrency(o.getTotal()));
        }
    }

    public void displayContinue() {
        io.readString("Please hit enter to continue.");
    }

    public BigDecimal inputArea(boolean edit , BigDecimal oldArea) {
        if (edit){
            return io.readBigDecimal("Please enter the new area of your project "
                    + "in square feet. (" + oldArea + ")", 2, new BigDecimal("100") );
        }
        else{
            return io.readBigDecimal("Please enter the area of your project "
                    + "in square feet.", 2, new BigDecimal("100") );
        }

    }

    public void displayOrederNotFoundMessage(int orderNumber) {
        String message = "Order Number " + orderNumber + " Not Found.";
        io.print(message);
    }
}
