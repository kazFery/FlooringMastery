package com.flooringmastery.dao;

import com.flooringmastery.dto.Order;
import com.flooringmastery.dto.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductsDaoFileImplTest {

    ProductsDao testProductDao;


    @Test
    void getProduct() throws DataPersistenceException {
        testProductDao = new ProductsDaoFileImpl();
        List<Product> products  = testProductDao.loadProducts();

        Product product1 = new Product();
        product1.setProductType("Carpet");
        product1.setMaterialCostPerSquareFoot(new BigDecimal("2.25"));
        product1.setLaborCostPerSquareFoot(new BigDecimal("2.10"));

        products.add(product1);

        assertTrue(product1.getProductType().equals(testProductDao.getProduct("Carpet").getProductType()));

    }

    @Test
    void loadProducts() throws DataPersistenceException {
        testProductDao = new ProductsDaoFileImpl();
        List<Product> products  = testProductDao.loadProducts();

        Product product1 = new Product();
        product1.setProductType("Carpet");
        product1.setMaterialCostPerSquareFoot(new BigDecimal("2.25"));
        product1.setLaborCostPerSquareFoot(new BigDecimal("2.10"));

        assertTrue(product1.getProductType().equals(products.get(0).getProductType()));
    }
    }


