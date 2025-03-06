package com.example.product.inventory.management.controller;

import com.example.product.inventory.management.model.Product;
import com.example.product.inventory.management.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PutMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addOrUpdateProduct(product);
    }

    @GetMapping("/{productId}/stockInHand")
    public int getStock(@PathVariable String productId) {
        return productService.getStock(productId);
    }
}
