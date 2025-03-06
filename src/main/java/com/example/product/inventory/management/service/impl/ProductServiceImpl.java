package com.example.product.inventory.management.service.impl;

import com.example.product.inventory.management.exception.ResourceNotFoundException;
import com.example.product.inventory.management.repository.ProductRepository;
import com.example.product.inventory.management.model.Product;
import com.example.product.inventory.management.service.ProductService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addOrUpdateProduct(Product product) {
        Optional<Product> existingProduct = productRepository.findByProductId(product.getProductId());

        if (existingProduct.isPresent()) {
            Product prod = existingProduct.get();
            prod.setStockQuantity(prod.getStockQuantity() + product.getStockQuantity());
            return productRepository.save(prod);
        }

        return productRepository.save(product);
    }

    public int getStock(String productId) {
        return productRepository.findByProductId(productId)
                .map(Product::getStockQuantity)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
    }
}
