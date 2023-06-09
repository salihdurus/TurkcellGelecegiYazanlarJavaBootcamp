package com.gy.ecommerce.business.abstracts;

import com.gy.ecommerce.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product getById(int id);

    Product add(Product product);

    Product update(int id, Product product);

    void delete(int id);
}