package com.example.product.inventory.management.service.impl;

import com.example.product.inventory.management.exception.InsufficientStockException;
import com.example.product.inventory.management.exception.ResourceNotFoundException;
import com.example.product.inventory.management.service.OrderService;
import com.example.product.inventory.management.model.Buyer;
import com.example.product.inventory.management.model.Order;
import com.example.product.inventory.management.model.Product;
import com.example.product.inventory.management.repository.BuyerRepository;
import com.example.product.inventory.management.repository.OrderRepository;
import com.example.product.inventory.management.repository.ProductRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final BuyerRepository buyerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, BuyerRepository buyerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.buyerRepository = buyerRepository;
    }

    @Transactional
    public String placeOrder(String productId, String buyerId, int quantity) {
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));

        Buyer buyer = buyerRepository.findByBuyerId(buyerId)
                .orElseThrow(() -> new ResourceNotFoundException("Buyer not found with ID: " + buyerId));

        if (product.getStockQuantity() < quantity) {
            throw new InsufficientStockException("Not enough stock available for product ID: " + productId);
        }

        product.setStockQuantity(product.getStockQuantity() - quantity);
        productRepository.save(product);

        Order order = new Order();
        order.setProduct(product);
        order.setBuyer(buyer);
        order.setQuantityOrdered(quantity);
        orderRepository.save(order);

        return "Order placed successfully!";
    }

    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAllOrders();
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("No orders found");
        }
        return orders;
    }
}
