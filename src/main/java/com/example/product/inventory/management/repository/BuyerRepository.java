package com.example.product.inventory.management.repository;

import com.example.product.inventory.management.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    Optional<Buyer> findByBuyerId(String buyerId);
}
