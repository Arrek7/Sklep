package com.comarch.szkolenia.sklep.database;

import com.comarch.szkolenia.sklep.model.Product;

import java.util.Collection;

public interface IProductRepository {
    void addProduct(Product product);
    void buyProduct(int id, int quantity);
    Collection<Product> getProduct();
    void persist();
}
