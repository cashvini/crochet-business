package org.example.crochetbusiness.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class ProdcutRequest {

    @NotBlank(message = "Name can not be blank")
    private String name;
    @PositiveOrZero(message = "Price can not be negative")
    private double price;
    @PositiveOrZero(message = "Stock value can not be negative")
    private int stockQuantity;

    public ProdcutRequest(String name, double price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}


