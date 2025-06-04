package com.comarch.szkolenia.sklep.database;

import com.comarch.szkolenia.sklep.exceptions.BuyProductException;
import com.comarch.szkolenia.sklep.model.Product;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProductRepository implements IProductRepository {
    private final Map<Integer, Product> products;
    private final String DB_FILE = "product.txt";

    public ProductRepository() {
        this.products = new HashMap<>();

        try(BufferedReader bufferedReader = new BufferedReader((new FileReader(DB_FILE)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                String[] parameters = line.split(";");
                Product product = new Product(Integer.parseInt(parameters[0]), parameters[1], parameters[2],
                        Double.parseDouble(parameters[3]), Integer.parseInt(parameters[4]));
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
    public void buyProduct(int id, int quantity) {
        Product product = this.products.get(id);

        if(product == null) {
            throw new BuyProductException("Nie ma produktu o podanym ID");

        } if (quantity > product.getQuantity()) {

            throw new BuyProductException("Brak wystarczajacej ilości produktu");
        }
        product.setQuantity(product.getQuantity()-quantity);
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
