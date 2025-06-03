package com.comarch.szkolenia.sklep.database;

import com.comarch.szkolenia.sklep.model.Product;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProductRepository implements IProductRepository {
    private final Map<Integer, Product> products;
    private final String DB_FILE = "vehicles.txt";

    private ProductRepository() {
        this.products = new HashMap<>();
        this.products.put(1, new Product(1,"T-Shirt", "Nike",50,2));
        this.products.put(2, new Product(2,"Spodnie", "Wrangler",100,1));
        this.products.put(3, new Product(3,"Kurtka", "Columbia",150,5));
        this.products.put(4, new Product(4,"Buty", "Nike",70,10));

        try(BufferedReader bufferedReader = new BufferedReader((new FileReader(DB_FILE)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                String[] parameters = line.split(";");
                Product product = new Product(Integer.parseInt(parameters[1]), parameters[2], parameters[2],
                        Integer.parseInt(parameters[4]), Integer.parseInt(parameters[5]));
                this.products.put(product.getId(), product);
            }
        }catch (IOException e) {
            System.out.println("Nie działa plik");
        }
    }
    @Override
    public void addProduct(Product product) {
       if (this.products.containsKey(product.getId())){
           Product existProduct = this.products.get(product.getId());
           existProduct.setQuantity(existProduct.getQuantity() + product.getQuantity());
       } else {
           this.products.put(product.getId(), product);
       }
    }
    @Override
    public boolean buyProduct(int id, int quantity) {
        Product product = this.products.get(id);

        if(product != null && quantity <= product.getQuantity()) {
            product.setQuantity(product.getQuantity() - quantity);
            return true;
        }
        return false;
    }
    @Override
    public Collection<Product> getProduct() {
        return this.products.values();
    }
    @Override
    public void persist() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(DB_FILE))) {
            for (Product p : getProduct()) {
                writer.write(p.convertToDatabaseLine());
                writer.newLine();
            }
        }catch (IOException e) {
            System.out.println("Nie działa zapisywanie !!");
        }
    }
}
