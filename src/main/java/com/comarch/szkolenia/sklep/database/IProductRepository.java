package com.comarch.szkolenia.sklep.database;

import com.comarch.szkolenia.sklep.model.Product;
import com.comarch.szkolenia.sklep.model.User;

import java.util.Collection;

public interface IProductRepository {
    void addProduct(Product product);
    boolean buyProduct(int id, int quantity);
    Collection<Product> getProduct();
    void persist();
}
