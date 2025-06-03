package com.comarch.szkolenia.sklep.core;

import com.comarch.szkolenia.sklep.database.IProductRepository;
import com.comarch.szkolenia.sklep.database.IUserRepository;
import com.comarch.szkolenia.sklep.database.ProductRepository;
import com.comarch.szkolenia.sklep.database.UserRepository;
import com.comarch.szkolenia.sklep.exceptions.BuyProductException;
import com.comarch.szkolenia.sklep.exceptions.FailedAuthenticationException;
import com.comarch.szkolenia.sklep.gui.GUI;
import com.comarch.szkolenia.sklep.gui.IGUI;
import com.comarch.szkolenia.sklep.model.User;
import com.comarch.szkolenia.sklep.weryfikacja.Authenticator;
import com.comarch.szkolenia.sklep.weryfikacja.IAuthenticator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Core implements ICore {
    private final IProductRepository productRepository;
    private final IAuthenticator authenticator;
    private final IUserRepository userRepository;
    private final IGUI gui;

    private boolean authenticateUser() {
        String option = gui.ChooseOption();
        if (option.equals("1")) {
            boolean result;
            int counter = 0;
            do {
                User user = gui.readCredentials();
                try {
                    authenticator.authenticate(user.getLogin(), user.getPassword());
                    result = true;
                } catch (FailedAuthenticationException e) {
                    result = false;
                    gui.showIncorrectCredentials();
                }
                counter++;
            } while (!result && counter < 3);

            return result;

        } else if (option.equals("2")) {
            User newUser = gui.readUserCommonData();
            if (userRepository.userExist(newUser.getLogin())) {
                gui.showRegistrationResult(false);
                return authenticateUser();

            } else {
                userRepository.addUser(newUser);
                gui.showRegistrationResult(true);
                return authenticateUser();
            }
        } else {
            gui.showWrongChoose();
            return authenticateUser();
        }
    }
@Override
    public void start() {
        boolean run = authenticateUser();
        while (run) {
            switch (gui.showMenuAndReadChoose()) {
                case "1":
                    gui.showProduct();
                    break;
                case "2":
                    if(Authenticator.currentUserRole == User.Role.ADMIN) {
                        addProduct();
                    } else {
                        try {
                            productRepository.buyProduct(gui.readId(), gui.enterQuantity());
                            gui.showResult(true);
                        } catch (BuyProductException e) {
                            gui.showResult(false);
                        }
                    }
                    break;
                case "3":
                    run = authenticateUser();
                    break;
                case "4":
                    run = false;
                    break;
                default:
                    gui.showWrongChoose();
                    break;
            }
        }
    }
        private void addProduct() {
            productRepository.addProduct(gui.readProductCommonData());
        }
}
