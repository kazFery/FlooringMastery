package com.flooringmastery.dao;

import com.flooringmastery.dto.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductsDaoStubImpl implements ProductsDao{

    List<Product> products = new ArrayList<>();
    @Override
    public Product getProduct(String productType) throws DataPersistenceException {
        Product pro = null;
      if ( productType.equalsIgnoreCase("Carpet")){
          Product product1 = new Product("1");
          product1.setProductType("Carpet");
          product1.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
          product1.setMaterialCostPerSquareFoot(new BigDecimal("2.25"));
          pro=  product1;
      }
      if ( productType.equalsIgnoreCase("Laminate")){
          Product product2 = new Product("2");
          product2.setProductType("Laminate");
          product2.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
          product2.setMaterialCostPerSquareFoot(new BigDecimal("1.75"));
          pro = product2;
      }
      return pro;
    }

    @Override
    public List<Product> loadProducts() throws DataPersistenceException {

        Product product1 = new Product("1");
        product1.setProductType("Carpet");
        product1.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        product1.setMaterialCostPerSquareFoot(new BigDecimal("2.25"));
        products.add(product1);

        Product product2 = new Product("2");
        product2.setProductType("Laminate");
        product2.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        product2.setMaterialCostPerSquareFoot(new BigDecimal("1.75"));
        products.add(product2);
        return products;
    }
}
