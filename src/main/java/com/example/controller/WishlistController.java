package com.example.controller;

import com.example.model.Wishlist;
import com.example.service.WishService;
import com.example.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WishlistController {

    private final WishlistService wishlistService;
    private final WishService wishService;

    public WishlistController(WishlistService wishlistService, WishService wishService) {
        this.wishlistService = wishlistService;
        this.wishService = wishService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/wishlists";
    }

    // READ: alle lister
    @GetMapping("/wishlists")
    public String showWishlists(Model model) {
        model.addAttribute("wishlists", wishlistService.getAllWishlists());
        return "wishlists";
    }

    // READ: én liste med sine ønsker
    @GetMapping("/wishlists/{id}")
    public String showWishlist(@PathVariable("id") int id, Model model) {
        Wishlist wishlist = wishlistService.getWishlistById(id);
        if (wishlist == null) {
            return "redirect:/wishlists";
        }
        model.addAttribute("wishlist", wishlist);
        model.addAttribute("wishes", wishService.getWishesByWishlistId(id));
        return "wishlist";
    }

    // CREATE
    @GetMapping("/wishlists/new")
    public String showCreateForm() {
        return "create-wishlist";
    }

    @PostMapping("/wishlists")
    public String createWishlist(@RequestParam("name") String name, Model model) {
        try {
            wishlistService.createWishlist(name);
            return "redirect:/wishlists";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("name", name);
            return "create-wishlist";
        }
    }

    // UPDATE (omdøb)
    @GetMapping("/wishlists/{id}/edit")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Wishlist wishlist = wishlistService.getWishlistById(id);
        if (wishlist == null) {
            return "redirect:/wishlists";
        }
        model.addAttribute("wishlistId", id);
        model.addAttribute("name", wishlist.getName());
        return "edit-wishlist";
    }

    @PostMapping("/wishlists/{id}/edit")
    public String renameWishlist(@PathVariable("id") int id,
                                 @RequestParam("name") String name,
                                 Model model) {
        try {
            wishlistService.renameWishlist(id, name);
            return "redirect:/wishlists";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("wishlistId", id);
            model.addAttribute("name", name);
            return "edit-wishlist";
        }
    }

    // DELETE
    @PostMapping("/wishlists/{id}/delete")
    public String deleteWishlist(@PathVariable("id") int id) {
        wishlistService.deleteWishlist(id);
        return "redirect:/wishlists";
    }
}