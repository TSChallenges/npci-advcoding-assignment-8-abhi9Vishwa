package com.mystore.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Product name cannot be null")
    @NotBlank(message = "Product name cannot be blank")
    private String name;

    @NotNull(message = "Category cannot be null")
    @NotBlank(message = "Category cannot be blank")
    private String category;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "100.0", message = "Please don't add any product with price lesser than 100")
    @DecimalMax(value = "50000.0", message = "This platform doesn't allow high priced products. Prices must be <= 50000")
    private Double price;

    @NotNull(message = "Stock quantity cannot be null")
    @Min(value = 10, message = "Stock quantity must be at least 10")
    @Max(value = 500, message = "Stock quantity must not exceed 500")
    private Integer stockQuantity;

    public Product() {
    }

    public Product(Integer id, String name, String category, Double price, Integer stockQuantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
