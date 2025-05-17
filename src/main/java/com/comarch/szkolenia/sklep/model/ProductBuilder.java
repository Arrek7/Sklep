package com.comarch.szkolenia.sklep.model;

public class ProductBuilder {
    private int id;
    private String type;
    private String brand;
    private int price;
    private int quantity;

    public ProductBuilder id (int id) {
        this.id = id;
        return this;
    }

    public ProductBuilder type (String type) {
        this.type = type;
        return this;
    }

    public ProductBuilder brand (String brand) {
        this.brand = brand;
        return this;
    }

    public ProductBuilder price (int price) {
        this.price = price;
        return this;
    }

    public ProductBuilder quantity (int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Product build() {
        return new Product(this.id, this.type, this.brand, this.price, this.quantity);
    }

}
