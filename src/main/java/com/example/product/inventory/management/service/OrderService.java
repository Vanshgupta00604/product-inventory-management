package com.example.product.inventory.management.service;

import com.example.product.inventory.management.model.Order;

import java.util.List;

public interface OrderService {
    String placeOrder(String productId, String buyerId, int quantity);
    List<Order> getAllOrders();
}
