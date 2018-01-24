package com.example.gggab.pre_cours4_android;

public class Product {
    private String name;
    private Double price;

    Product() {
    }

    Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Double getPrice() {
        return price;
    }
}
