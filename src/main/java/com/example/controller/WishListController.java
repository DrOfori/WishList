package com.example.controller;

import com.example.repository.WishlistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishListController {

    @GetMapping("/wishlist")
            public String wishlist(){
    return "wishlists.html";
    }

    private final WishlistRepository repository;

    public WishListController(WishlistRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/wishlists")
    public String all() {
        return repository.findAll().toString();
    }
}