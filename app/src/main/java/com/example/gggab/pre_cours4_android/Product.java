package com.example.gggab.pre_cours4_android;

/**
 * Created by gggab on 2018-01-23.
 */

public class Product {
    private String name;
    private Double price;

    public Product(){}

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
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
}
