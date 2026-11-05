package com.example.repository;
import com.example.model.Wish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class WishRepository  {

    private final JdbcTemplate jdbcTemplate;

    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Wish> rowMapper = (rs, rowNum) -> {
        Wish wish = new Wish();
        wish.setId(rs.getInt("id"));
        wish.setName(rs.getString("name"));
        double price = rs.getDouble("price");
        wish.setPrice(rs.wasNull() ? null : price);   // wasNull: var prisen tom i databasen?
        wish.setLink(rs.getString("link"));
        wish.setWishlistId(rs.getInt("wishlist_id"));
        return wish;
    };

    public List<Wish> findByWishlistId(int wishlistId) {
        return jdbcTemplate.query(
                "SELECT * FROM wish WHERE wishlist_id = ? ORDER BY id", rowMapper, wishlistId);
    }

    public Wish findById(int id) {
        List<Wish> result = jdbcTemplate.query(
                "SELECT * FROM wish WHERE id = ?", rowMapper, id);
        return result.isEmpty() ? null : result.get(0);
    }

    public void save(Wish wish) {
        jdbcTemplate.update(
                "INSERT INTO wish (name, price, link, wishlist_id) VALUES (?, ?, ?, ?)",
                wish.getName(), wish.getPrice(), wish.getLink(), wish.getWishlistId());
    }

    public void update(Wish wish) {
        jdbcTemplate.update(
                "UPDATE wish SET name = ?, price = ?, link = ? WHERE id = ?",
                wish.getName(), wish.getPrice(), wish.getLink(), wish.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM wish WHERE id = ?", id);
    }
}
