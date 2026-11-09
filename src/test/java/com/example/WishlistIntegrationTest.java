package com.example;

import com.example.model.Wish;
import com.example.model.Wishlist;
import com.example.service.WishService;
import com.example.service.WishlistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional   // hver test rulles tilbage bagefter, så testene ikke påvirker hinanden
class WishlistIntegrationTest {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private WishService wishService;

    private Wishlist findByName(String name) {
        return wishlistService.getAllWishlists().stream()
                .filter(w -> w.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Test
    void createWishlist_isStoredInDatabase() {
        wishlistService.createWishlist("Integrationstest-liste");

        Wishlist found = findByName("Integrationstest-liste");

        assertNotNull(found);
        assertTrue(found.getId() > 0);
        assertNotNull(found.getCreatedAt());
    }

    @Test
    void deleteWishlist_removesItFromDatabase() {
        wishlistService.createWishlist("Skal slettes");
        Wishlist created = findByName("Skal slettes");
        assertNotNull(created);

        wishlistService.deleteWishlist(created.getId());

        assertNull(findByName("Skal slettes"));
    }

    @Test
    void renameWishlist_changesNameInDatabase() {
        wishlistService.createWishlist("Gammelt navn");
        Wishlist created = findByName("Gammelt navn");

        wishlistService.renameWishlist(created.getId(), "Nyt navn");

        assertNull(findByName("Gammelt navn"));
        assertNotNull(findByName("Nyt navn"));
    }

    @Test
    void createWish_isStoredWithPriceAndLink() {
        wishlistService.createWishlist("Liste med ønsker");
        Wishlist list = findByName("Liste med ønsker");

        wishService.createWish(list.getId(), "Høretelefoner", "999,50", "https://example.com");

        List<Wish> wishes = wishService.getWishesByWishlistId(list.getId());
        assertEquals(1, wishes.size());
        assertEquals("Høretelefoner", wishes.get(0).getName());
        assertEquals(999.5, wishes.get(0).getPrice(), 0.001);
        assertEquals("https://example.com", wishes.get(0).getLink());
    }

    @Test
    void createWish_withInvalidLink_isNotStored() {
        wishlistService.createWishlist("Liste til fejltest");
        Wishlist list = findByName("Liste til fejltest");

        assertThrows(IllegalArgumentException.class,
                () -> wishService.createWish(list.getId(), "Bog", null, "javascript:alert(1)"));

        assertTrue(wishService.getWishesByWishlistId(list.getId()).isEmpty());
    }

    @Test
    void deleteWish_removesOnlyThatWish() {
        wishlistService.createWishlist("Liste til sletning");
        Wishlist list = findByName("Liste til sletning");
        wishService.createWish(list.getId(), "Ønske A", null, null);
        wishService.createWish(list.getId(), "Ønske B", null, null);

        Wish first = wishService.getWishesByWishlistId(list.getId()).get(0);
        wishService.deleteWish(first.getId());

        List<Wish> remaining = wishService.getWishesByWishlistId(list.getId());
        assertEquals(1, remaining.size());
        assertEquals("Ønske B", remaining.get(0).getName());
    }

    @Test
    void deleteWishlist_alsoDeletesItsWishes() {
        wishlistService.createWishlist("Cascade-test");
        Wishlist list = findByName("Cascade-test");
        wishService.createWish(list.getId(), "Ønske", null, null);
        Wish wish = wishService.getWishesByWishlistId(list.getId()).get(0);

        wishlistService.deleteWishlist(list.getId());

        assertNull(wishService.getWishById(wish.getId()));
        assertTrue(wishService.getWishesByWishlistId(list.getId()).isEmpty());
    }
}