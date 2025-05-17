package com.comarch.szkolenia.sklep.gui;

import com.comarch.szkolenia.sklep.database.ProductRepository;
import com.comarch.szkolenia.sklep.model.Constants;
import com.comarch.szkolenia.sklep.model.Product;
import com.comarch.szkolenia.sklep.model.User;
import com.comarch.szkolenia.sklep.weryfikacja.Authenticator;
import lombok.Getter;

import java.util.Scanner;

public class GUI {
    private final Scanner scanner = new Scanner(System.in);
    @Getter
    private final static GUI instance = new GUI();

    public GUI() {
    }

    public String showMenuAndReadChoose() {
        System.out.println("1. Lista produktów");
        System.out.println(Authenticator.currentUserRole == User.Role.ADMIN ?
                "2. Dodaj produkt" : "2. Kup produkt");
        System.out.println("3. Wyloguj");
        System.out.println("4. Exit");

        return scanner.nextLine();
    }

    public void showProduct() {
        for(Product product : ProductRepository.getInstance().getProduct()) {
            if (product.getQuantity() > 0) {
                System.out.println(product);
            }
        }
    }

    public int readId() {
        System.out.println("Podaj ID produktu");
        return Integer.parseInt(scanner.nextLine());
    }

    public int enterQuantity() {
        System.out.println("Podaj ilość");
        return Integer.parseInt(scanner.nextLine());
    }

    public void showResult(boolean result) {
        System.out.println(result ? "kupiono !!" : "Brak produktu !!");
    }

    public void showWrongChoose() {
        System.out.println("Zły wybór !! Wybierz ponownie !!");
    }

    public String ChooseOption() {
        System.out.println("1. Zaloguj");
        System.out.println("2. Zarejestruj");

        return scanner.nextLine();
    }

    public User readCredentials() {
        System.out.println("Podaj login:");
        String login = scanner.nextLine();
        System.out.println("Podaj hasło:");
        return new User(login, scanner.nextLine());
    }

    public void showIncorrectCredentials() {
        System.out.println(" Niepoprawne dane");
    }

    public Product readProductCommonData() {
        System.out.println("ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Type:");
        String type = scanner.nextLine();
        System.out.println("Brand:");
        String brand = scanner.nextLine();
        System.out.println("Price");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Quantity:");
        int quantity = Integer.parseInt(scanner.nextLine());

        return new Product(id, type, brand, price, quantity);
    }

    public User readUserCommonData() {
        System.out.println("Podaj login: ");
        String login = scanner.nextLine();
        System.out.println("Podaj hasło: ");
        String password = scanner.nextLine();

        return new User(login,password);
    }

    public void showRegistrationResult(boolean result) {
        System.out.println(result ? "Rejestracja udana" : "Użytkownik już istnieje");
    }
}
