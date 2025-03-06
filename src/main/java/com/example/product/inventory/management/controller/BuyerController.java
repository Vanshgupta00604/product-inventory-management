package com.example.product.inventory.management.controller;

import com.example.product.inventory.management.model.Buyer;
import com.example.product.inventory.management.service.BuyerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/buyer")
public class BuyerController {

    private final BuyerService buyerService;

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @PutMapping
    public Buyer addBuyer(@RequestBody Buyer buyer) {
        return buyerService.saveBuyer(buyer);
    }
}