package com.example.service;

import com.example.model.Wishlist;
import com.example.repository.WishlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WishlistServiceTest {

    static class FakeWishlistRepository extends WishlistRepository {
        Wishlist saved;
        Integer updatedId;
        String updatedName;
        Integer deletedId;

        FakeWishlistRepository() {
            super(null);
        }

        @Override
        public void save(Wishlist wishlist) {
            saved = wishlist;
        }

        @Override
        public void update(int id, String name) {
            updatedId = id;
            updatedName = name;
        }

        @Override
        public void delete(int id) {
            deletedId = id;
        }
    }

    private FakeWishlistRepository repository;
    private WishlistService service;

    @BeforeEach
    void setUp() {
        repository = new FakeWishlistRepository();
        service = new WishlistService(repository);
    }

    @Test
    void createWishlist_emptyName_throwsAndDoesNotSave() {
        assertThrows(IllegalArgumentException.class, () -> service.createWishlist(""));
        assertNull(repository.saved);
    }

    @Test
    void createWishlist_nullName_throws() {
        assertThrows(IllegalArgumentException.class, () -> service.createWishlist(null));
    }

    @Test
    void createWishlist_onlySpaces_throws() {
        assertThrows(IllegalArgumentException.class, () -> service.createWishlist("    "));
    }

    @Test
    void createWishlist_nameTooLong_throws() {
        String tooLong = "a".repeat(101);
        assertThrows(IllegalArgumentException.class, () -> service.createWishlist(tooLong));
    }

    @Test
    void createWishlist_validName_savesTrimmedName() {
        service.createWishlist("  Jul 2026  ");

        assertNotNull(repository.saved);
        assertEquals("Jul 2026", repository.saved.getName());
    }

    @Test
    void renameWishlist_emptyName_throwsAndDoesNotUpdate() {
        assertThrows(IllegalArgumentException.class, () -> service.renameWishlist(1, ""));
        assertNull(repository.updatedId);
    }

    @Test
    void renameWishlist_validName_updatesWithTrimmedName() {
        service.renameWishlist(5, "  Fødselsdag ");

        assertEquals(5, repository.updatedId);
        assertEquals("Fødselsdag", repository.updatedName);
    }

    @Test
    void deleteWishlist_callsRepositoryWithId() {
        service.deleteWishlist(9);
        assertEquals(9, repository.deletedId);
    }
}