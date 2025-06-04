package com.comarch.szkolenia.sklep.gui;
import com.comarch.szkolenia.sklep.model.Product;
import com.comarch.szkolenia.sklep.model.User;

public interface IGUI{
    String showMenuAndReadChoose();

    void showProduct();
    int readId();
    int enterQuantity();
    void showResult(boolean result);
    void showWrongChoose();
    String ChooseOption();
    User readCredentials();
    void showIncorrectCredentials();
    Product readProductCommonData();
    User readUserCommonData();
    void showRegistrationResult(boolean result);
    void showAddResult (boolean result);
    String readUserToChangeRole();
    void showChangeRoleResult(String login, User.Role newRole);
    void showUserNotFound();
    }
