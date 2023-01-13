package com.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Order {
   private int orderNumber;
    private String customerName;
    private LocalDate date;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    // following optionscan calculate
    private BigDecimal materialCost;
    private BigDecimal materialCostPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;

    public Order (int orderNumber){
        this.orderNumber = orderNumber;
    }

 public Order() {

 }

 public LocalDate getDate() {
  return date;
 }

 public void setDate(LocalDate date) {
  this.date = date;
 }

 public BigDecimal getMaterialCostPerSquareFoot() {
  return materialCostPerSquareFoot;
 }

 public void setMaterialCostPerSquareFoot(BigDecimal materialCostPerSquareFoot) {
  this.materialCostPerSquareFoot = materialCostPerSquareFoot;
 }

 public int getOrderNumber() {
  return orderNumber;
 }

 public void setOrderNumber(int orderNumber) {
  this.orderNumber = orderNumber;
 }

 public String getCustomerName() {
  return customerName;
 }

 public void setCustomerName(String customerName) {
  this.customerName = customerName;
 }

 public String getState() {
  return state;
 }

 public void setState(String state) {
  this.state = state;
 }

 public BigDecimal getTaxRate() {
  return taxRate;
 }

 public void setTaxRate(BigDecimal taxRate) {
  this.taxRate = taxRate;
 }

 public String getProductType() {
  return productType;
 }

 public void setProductType(String productType) {
  this.productType = productType;
 }

 public BigDecimal getArea() {
  return area;
 }

 public void setArea(BigDecimal area) {
  this.area = area;
 }

 public BigDecimal getLaborCostPerSquareFoot() {
  return laborCostPerSquareFoot;
 }

 public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
  this.laborCostPerSquareFoot = laborCostPerSquareFoot;
 }

 public BigDecimal getMaterialCost() {
  return materialCost;
 }

 public void setMaterialCost(BigDecimal materialCost) {
  this.materialCost = materialCost;
 }

 public BigDecimal getLaborCost() {
  return laborCost;
 }

 public void setLaborCost(BigDecimal laborCost) {
  this.laborCost = laborCost;
 }

 public BigDecimal getTax() {
  return tax;
 }

 public void setTax(BigDecimal tax) {
  this.tax = tax;
 }

 public BigDecimal getTotal() {
  return total;
 }

 public void setTotal(BigDecimal total) {
  this.total = total;
 }

 @Override
 public boolean equals(Object o) {
  if (this == o) return true;
  if (o == null || getClass() != o.getClass()) return false;
  Order order = (Order) o;
  return orderNumber == order.orderNumber && customerName.equals(order.customerName) && state.equals(order.state) && taxRate.equals(order.taxRate) && productType.equals(order.productType) && area.equals(order.area) && materialCostPerSquareFoot.equals(order.materialCostPerSquareFoot) && laborCostPerSquareFoot.equals(order.laborCostPerSquareFoot) && materialCost.equals(order.materialCost) && laborCost.equals(order.laborCost) && tax.equals(order.tax) && total.equals(order.total);
 }

 @Override
 public int hashCode() {
  return Objects.hash(orderNumber, customerName, state, taxRate, productType, area, materialCostPerSquareFoot, laborCostPerSquareFoot, materialCost, laborCost, tax, total);
 }

 @Override
 public String toString() {
  return "Order{" +
          "orderNumber=" + orderNumber +
          ", customerName='" + customerName + '\'' +
          ", state='" + state + '\'' +
          ", taxRate=" + taxRate +
          ", productType='" + productType + '\'' +
          ", area=" + area +
          ", costPerSquareFoot=" + materialCostPerSquareFoot +
          ", laborCostPerSquareFoot=" + laborCostPerSquareFoot +
          ", materialCost=" + materialCost +
          ", laborCost=" + laborCost +
          ", tax=" + tax +
          ", total=" + total +
          '}';
 }
}

