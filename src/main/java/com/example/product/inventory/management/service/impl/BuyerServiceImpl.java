package com.example.product.inventory.management.service.impl;

import com.example.product.inventory.management.model.Buyer;
import com.example.product.inventory.management.repository.BuyerRepository;
import com.example.product.inventory.management.service.BuyerService;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceImpl implements BuyerService{

    private final BuyerRepository buyerRepository;

    public BuyerServiceImpl(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public Buyer saveBuyer(Buyer buyer) {
        return buyerRepository.save(buyer);
    }
}
