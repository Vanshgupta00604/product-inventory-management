package com.example.product.inventory.management.controller;

import com.example.product.inventory.management.service.OrderService;
import com.example.product.inventory.management.model.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{productId}/buyer/{buyerId}/order")
    public String placeOrder(@PathVariable String productId, @PathVariable String buyerId, @RequestParam int quantity) {
        return orderService.placeOrder(productId, buyerId, quantity);
    }
    @GetMapping("/order")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}