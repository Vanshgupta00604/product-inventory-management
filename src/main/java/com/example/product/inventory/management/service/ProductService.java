package com.example.product.inventory.management.service;

import com.example.product.inventory.management.model.Product;

public interface ProductService {
    Product addOrUpdateProduct(Product product);
    int getStock(String productId);
}
