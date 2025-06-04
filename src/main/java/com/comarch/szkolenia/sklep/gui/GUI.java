package com.comarch.szkolenia.sklep.gui;

import com.comarch.szkolenia.sklep.database.IProductRepository;
import com.comarch.szkolenia.sklep.model.Product;
import com.comarch.szkolenia.sklep.model.User;
import com.comarch.szkolenia.sklep.weryfikacja.Authenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
@Primary
public class GUI implements IGUI {
    private final Scanner scanner;
    private final IProductRepository productRepository;
    private final Authenticator authenticator;

    @Override
    public String showMenuAndReadChoose() {
        System.out.println("1. Lista produktów");
        System.out.println(Authenticator.currentUserRole == User.Role.ADMIN ?
                "2. Dodaj produkt" : "2. Kup produkt");
        if(Authenticator.currentUserRole == User.Role.ADMIN) {
            System.out.println("3. Zmień role użytkownika");
            System.out.println("4. Wyloguj");
            System.out.println("5. Exit");
        }else{
            System.out.println("3. Wyloguj");
            System.out.println("4. Exit");
        }

        return scanner.nextLine();
    }
    @Override
    public void showProduct() {
        for(Product product : this.productRepository.getProduct()) {
            if (product.getQuantity() > 0) {
                System.out.println(product);
            }
        }
    }
    @Override
    public int readId() {
        System.out.println("Podaj ID produktu");
        return Integer.parseInt(scanner.nextLine());
    }
    @Override
    public int enterQuantity() {
        System.out.println("Podaj ilość");
        return Integer.parseInt(scanner.nextLine());
    }
    @Override
    public void showResult(boolean result) {
        System.out.println(result ? "kupiono !!" : "Brak produktu !!");
    }
    @Override
    public void showWrongChoose() {
        System.out.println("Zły wybór !! Wybierz ponownie !!");
    }
    @Override
    public String ChooseOption() {
        System.out.println("1. Zaloguj");
        System.out.println("2. Zarejestruj");

        return scanner.nextLine();
    }
    @Override
    public User readCredentials() {
        System.out.println("Podaj login:");
        String login = scanner.nextLine();
        System.out.println("Podaj hasło:");
        return new User(login, scanner.nextLine());
    }
    @Override
    public void showIncorrectCredentials() {
        System.out.println(" Niepoprawne dane");
    }
    @Override
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
    @Override
    public User readUserCommonData() {
        System.out.println("Podaj login: ");
        String login = scanner.nextLine();
        System.out.println("Podaj hasło: ");
        String password = scanner.nextLine();
        String hashedPassword =
                authenticator.hashedPassword(password);

        return new User(login,hashedPassword, User.Role.USER);
    }
    @Override
    public void showRegistrationResult(boolean result) {
        System.out.println(result ? "Rejestracja udana" : "Użytkownik już istnieje");
    }
    @Override
    public void showAddResult (boolean result) {
        System.out.println( result ? "Zwiękoszono ilość produktu" : "Produkt juz istnieje");
    }
    @Override
    public String readUserToChangeRole() {
        System.out.println("Podaj login użytkownika któremu chcesz zmienić role: ");
        return scanner.nextLine();
    }
    @Override
    public void showChangeRoleResult(String login, User.Role newRole) {
        System.out.println("Rola użytkownika " + login + " została zmieniona na: " + newRole);
    }
    @Override
    public void showUserNotFound() {
        System.out.println("Użytkownik nie istnieje!");
    }
}
