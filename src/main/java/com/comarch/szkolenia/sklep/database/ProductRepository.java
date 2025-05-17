package com.comarch.szkolenia.sklep.database;

import com.comarch.szkolenia.sklep.model.Product;
import lombok.Getter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private final Map<Integer, Product> products;
    @Getter
    private final static ProductRepository instance = new ProductRepository();

    private ProductRepository() {
        this.products = new HashMap<>();
        this.products.put(1, new Product(1,"T-Shirt", "Nike",50,2));
        this.products.put(2, new Product(2,"Spodnie", "Wrangler",100,1));
        this.products.put(3, new Product(3,"Kurtka", "Columbia",150,5));
        this.products.put(4, new Product(4,"Buty", "Nike",70,10));
    }

    public void addProduct(Product product) {
        this.products.put(product.getId(), product);
    }

    public boolean buyProduct(int id, int quantity) {
        Product product = this.products.get(id);

        if(product != null && quantity <= product.getQuantity()) {
            product.setQuantity(product.getQuantity() - quantity);
            return true;
        }
        return false;
    }

    public Collection<Product> getProduct() {
        return this.products.values();
    }

}
