package com.comarch.szkolenia.sklep.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Product {
    private int id;
    private String type;
    private String brand;
    private double price;
    private int quantity;

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.getId())
                .append(" - ")
                .append(this.getType())
                .append(" ")
                .append(this.getBrand())
                .append(" ")
                .append(this.getPrice())
                .append("zł ")
                .append(this.getQuantity())
                .append("szt")
                .toString();
    }
}
