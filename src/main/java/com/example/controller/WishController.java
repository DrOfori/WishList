package com.example.controller;
import com.example.model.Wish;
import com.example.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WishController {

        private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

        // CREATE
        @PostMapping("/wishlists/{wishlistId}/wishes")
        public String createWish(@PathVariable("wishlistId") int wishlistId,
        @RequestParam("name") String name,
        @RequestParam(value = "price", required = false) String price,
        @RequestParam(value = "link", required = false) String link,
        RedirectAttributes redirectAttributes) {
        try {
            wishService.createWish(wishlistId, name, price, link);
        } catch (IllegalArgumentException e) {
            // Flash-attributter overlever én redirect, så siden kan vise fejlen og det indtastede
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("formName", name);
            redirectAttributes.addFlashAttribute("formPrice", price);
            redirectAttributes.addFlashAttribute("formLink", link);
        }
        return "redirect:/wishlists/" + wishlistId;
    }

        // UPDATE
        @GetMapping("/wishes/{id}/edit")
        public String showEditForm(@PathVariable("id") int id, Model model) {
        Wish wish = wishService.getWishById(id);
        if (wish == null) {
            return "redirect:/wishlists";
        }
        fillEditModel(model, wish.getId(), wish.getWishlistId(),
                wish.getName(),
                wish.getPrice() == null ? "" : String.valueOf(wish.getPrice()),
                wish.getLink() == null ? "" : wish.getLink());
        return "edit-wish";
    }

        @PostMapping("/wishes/{id}/edit")
        public String updateWish(@PathVariable("id") int id,
        @RequestParam("name") String name,
        @RequestParam(value = "price", required = false) String price,
        @RequestParam(value = "link", required = false) String link,
        Model model) {
        Wish existing = wishService.getWishById(id);
        if (existing == null) {
            return "redirect:/wishlists";
        }
        try {
            wishService.updateWish(id, name, price, link);
            return "redirect:/wishlists/" + existing.getWishlistId();
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            fillEditModel(model, id, existing.getWishlistId(), name, price, link);
            return "edit-wish";
        }
    }

        // DELETE
        @PostMapping("/wishes/{id}/delete")
        public String deleteWish(@PathVariable("id") int id) {
        Wish wish = wishService.getWishById(id);
        if (wish == null) {
            return "redirect:/wishlists";   // allerede slettet, så ingen fejlside
        }
        wishService.deleteWish(id);
        return "redirect:/wishlists/" + wish.getWishlistId();
    }

        private void fillEditModel(Model model, int wishId, int wishlistId,
        String name, String price, String link) {
        model.addAttribute("wishId", wishId);
        model.addAttribute("wishlistId", wishlistId);
        model.addAttribute("name", name);
        model.addAttribute("price", price);
        model.addAttribute("link", link);
    }
    }


