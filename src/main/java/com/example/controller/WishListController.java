package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WishListController {

    @GetMapping("/wishlist")
            public String wishlist(){
    return "wishlists.html";
    }
}