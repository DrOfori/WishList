package com.example.service;

import com.example.model.Wish;
import com.example.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {

    private static final int MAX_NAME_LENGTH = 100;
    private static final int MAX_LINK_LENGTH = 2048;
    private static final double MAX_PRICE = 99_999_999.99;

    private final WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }
    public List<Wish> getWishesByWishlistId(int wishlistId) {
        return wishRepository.findByWishlistId(wishlistId);
    }

    public Wish getWishById(int id) {
        return wishRepository.findById(id);
    }

    public void createWish(int wishlistId, String name, String price, String link) {
        Wish wish = buildValidatedWish(name, price, link);
        wish.setWishlistId(wishlistId);
        wishRepository.save(wish);
    }

    public void updateWish(int id, String name, String price, String link) {
        Wish wish = buildValidatedWish(name, price, link);
        wish.setId(id);
        wishRepository.update(wish);
    }

    public void deleteWish(int id) {
        wishRepository.delete(id);
    }

    private Wish buildValidatedWish(String name, String price, String link) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Navnet på ønsket må ikke være tomt");
        }
        String cleanName = name.trim();
        if (cleanName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Navnet må højst være " + MAX_NAME_LENGTH + " tegn");
        }

        Wish wish = new Wish();
        wish.setName(cleanName);
        wish.setPrice(parsePrice(price));
        wish.setLink(validateLink(link));
        return wish;
    }

    private Double parsePrice(String price) {
        if (price == null || price.trim().isEmpty()) {
            return null;
        }
        double value;
        try {
            value = Double.parseDouble(price.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Prisen skal være et tal");
        }
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Prisen skal være et tal");
        }
        if (value < 0) {
            throw new IllegalArgumentException("Prisen må ikke være negativ");
        }
        if (value > MAX_PRICE) {
            throw new IllegalArgumentException("Prisen er for høj");
        }
        return value;
    }

    private String validateLink(String link) {
        if (link == null || link.trim().isEmpty()) {
            return null;
        }
        String cleanLink = link.trim();
        if (cleanLink.length() > MAX_LINK_LENGTH) {
            throw new IllegalArgumentException("Linket er for langt");
        }
        String lower = cleanLink.toLowerCase();
        if (!lower.startsWith("http://") && !lower.startsWith("https://")) {
            throw new IllegalArgumentException("Linket skal starte med http:// eller https://");
        }
        return cleanLink;
    }
}