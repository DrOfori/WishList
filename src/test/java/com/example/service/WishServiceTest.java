package com.example.service;

import com.example.model.Wish;
import com.example.repository.WishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WishServiceTest {

    // Falsk repository: gemmer ikke i en database, men husker det, den fik
    static class FakeWishRepository extends WishRepository {
        Wish saved;
        Wish updated;

        FakeWishRepository() {
            super(null);
        }

        @Override
        public void save(Wish wish) {
            saved = wish;
        }

        @Override
        public void update(Wish wish) {
            updated = wish;
        }
    }

    private FakeWishRepository repository;
    private WishService service;

    @BeforeEach
    void setUp() {
        repository = new FakeWishRepository();
        service = new WishService(repository);
    }

    // ---------- Navn ----------

    @Test
    void createWish_emptyName_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createWish(1, "", null, null));
    }

    @Test
    void createWish_nullName_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createWish(1, null, null, null));
    }

    @Test
    void createWish_onlySpacesInName_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createWish(1, "     ", null, null));
    }

    @Test
    void createWish_nameTooLong_throws() {
        String tooLong = "a".repeat(101);
        assertThrows(IllegalArgumentException.class,
                () -> service.createWish(1, tooLong, null, null));
    }

    @Test
    void createWish_nameExactlyMaxLength_isAccepted() {
        String maxLength = "a".repeat(100);
        service.createWish(1, maxLength, null, null);
        assertNotNull(repository.saved);
    }

    // ---------- Pris ----------

    @Test
    void createWish_priceNotANumber_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createWish(1, "Bog", "abc", null));
    }

    @Test
    void createWish_negativePrice_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createWish(1, "Bog", "-5", null));
    }

    @Test
    void createWish_priceWithComma_isConvertedToDecimal() {
        service.createWish(1, "Bog", "99,50", null);
        assertEquals(99.5, repository.saved.getPrice(), 0.001);
    }

    @Test
    void createWish_emptyPrice_isAllowedAndNull() {
        service.createWish(1, "Bog", "", null);
        assertNull(repository.saved.getPrice());
    }

    // ---------- Link ----------

    @Test
    void createWish_linkWithoutHttp_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createWish(1, "Bog", null, "www.example.com"));
    }

    @Test
    void createWish_javascriptLink_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createWish(1, "Bog", null, "javascript:alert(1)"));
    }

    @Test
    void createWish_httpsLink_isAccepted() {
        service.createWish(1, "Bog", null, "https://www.example.com");
        assertEquals("https://www.example.com", repository.saved.getLink());
    }

    @Test
    void createWish_emptyLink_isAllowedAndNull() {
        service.createWish(1, "Bog", null, "");
        assertNull(repository.saved.getLink());
    }

    // ---------- Gyldigt ønske ----------

    @Test
    void createWish_validInput_savesTrimmedNameAndWishlistId() {
        service.createWish(7, "  Høretelefoner  ", "999", "https://example.com");

        assertNotNull(repository.saved);
        assertEquals("Høretelefoner", repository.saved.getName());
        assertEquals(7, repository.saved.getWishlistId());
        assertEquals(999.0, repository.saved.getPrice(), 0.001);
    }

    // ---------- Redigering ----------

    @Test
    void updateWish_emptyName_throwsAndDoesNotUpdate() {
        assertThrows(IllegalArgumentException.class,
                () -> service.updateWish(3, "", null, null));
        assertNull(repository.updated);
    }

    @Test
    void updateWish_validInput_setsIdAndUpdates() {
        service.updateWish(3, "Ny titel", "50", null);

        assertNotNull(repository.updated);
        assertEquals(3, repository.updated.getId());
        assertEquals("Ny titel", repository.updated.getName());
    }
}