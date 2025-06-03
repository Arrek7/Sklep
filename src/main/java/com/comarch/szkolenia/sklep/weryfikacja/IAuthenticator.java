package com.comarch.szkolenia.sklep.weryfikacja;

public interface IAuthenticator {
    void authenticate(String login, String password);
    String hashedPassword(String password);
}
