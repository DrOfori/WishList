package com.example.repository;
import com.example.model.Wishlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class WishlistRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishlistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Wishlist> rowMapper = (rs, rowNum) -> {
        Wishlist wishlist = new Wishlist();
        wishlist.setId(rs.getInt("id"));
        wishlist.setName(rs.getString("name"));
        wishlist.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return wishlist;
    };

    public List<Wishlist> findAll() {
        return jdbcTemplate.query("SELECT * FROM wishlist", rowMapper);
    }

    public Wishlist findById(int id) {
        List<Wishlist> result = jdbcTemplate.query(
                "SELECT * FROM wishlist WHERE id = ?", rowMapper, id);
        return result.isEmpty() ? null : result.get(0);
    }

    public void save(Wishlist wishlist) {
        jdbcTemplate.update("INSERT INTO wishlist (name) VALUES (?)", wishlist.getName());
    }

    public void update(int id, String name) {
        jdbcTemplate.update("UPDATE wishlist SET name = ? WHERE id = ?", name, id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM wishlist WHERE id = ?", id);
    }
}