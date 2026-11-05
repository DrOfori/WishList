package com.example.model;

public class Wish {
    private int id;
    private String name;
    private Double price;   // Double (ikke double), så prisen kan være tom (null)
    private String link;
    private int wishlistId;

    public Wish() {
    }

    public Wish(int id, String name, Double price, String link, int wishlistId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.link = link;
        this.wishlistId = wishlistId;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public Double getPrice() { return price; }
    public String getLink() { return link; }
    public int getWishlistId() { return wishlistId; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(Double price) { this.price = price; }
    public void setLink(String link) { this.link = link; }
    public void setWishlistId(int wishlistId) { this.wishlistId = wishlistId; }
}
