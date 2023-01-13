
package com.flooringmastery.dao;


import com.flooringmastery.dto.Product;

import java.util.List;

public interface ProductsDao {

    Product getProduct(String productType) throws DataPersistenceException;

    List<Product> loadProducts() throws DataPersistenceException;

}
