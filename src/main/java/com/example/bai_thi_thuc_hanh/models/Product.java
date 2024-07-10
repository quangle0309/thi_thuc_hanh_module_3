package com.example.bai_thi_thuc_hanh.models;

public class Product {
    private int id;
    private String name;
    private double price;
    private int discountId;
    private int inventory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discount) {
        this.discountId = discount;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}
