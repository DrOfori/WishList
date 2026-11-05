package com.example.service;
import com.example.model.Wishlist;
import com.example.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WishlistService  {

    private static final int MAX_NAME_LENGTH = 100;

    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<Wishlist> getAllWishlists() {
        return wishlistRepository.findAll();
    }

    public Wishlist getWishlistById(int id) {
        return wishlistRepository.findById(id);
    }

    public void createWishlist(String name) {
        Wishlist wishlist = new Wishlist();
        wishlist.setName(validateName(name));
        wishlistRepository.save(wishlist);
    }

    public void renameWishlist(int id, String name) {
        wishlistRepository.update(id, validateName(name));
    }

    public void deleteWishlist(int id) {
        wishlistRepository.delete(id);
    }

    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Navnet må ikke være tomt");
        }
        String cleanName = name.trim();
        if (cleanName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Navnet må højst være " + MAX_NAME_LENGTH + " tegn");
        }
        return cleanName;
    }
}
