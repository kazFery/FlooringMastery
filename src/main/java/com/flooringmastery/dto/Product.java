package com.flooringmastery.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private String productId;
    private String productType;
    private BigDecimal materialCostPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;

    public Product (String productId){
        this.productId = productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Product() {
    }

    public BigDecimal getMaterialCostPerSquareFoot() {
        return materialCostPerSquareFoot;
    }

    public void setMaterialCostPerSquareFoot(BigDecimal materialCostPerSquareFoot) {
        this.materialCostPerSquareFoot = materialCostPerSquareFoot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId.equals(product.productId) && productType.equals(product.productType) && materialCostPerSquareFoot.equals(product.materialCostPerSquareFoot) && laborCostPerSquareFoot.equals(product.laborCostPerSquareFoot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productType, materialCostPerSquareFoot, laborCostPerSquareFoot);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productType='" + productType + '\'' +
                ", costPerSquareFoot=" + materialCostPerSquareFoot +
                ", laborCostPerSquareFoot=" + laborCostPerSquareFoot +
                '}';
    }
}
