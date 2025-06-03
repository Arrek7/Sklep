package com.comarch.szkolenia.sklep.database;

import com.comarch.szkolenia.sklep.model.User;

public interface IUserRepository {
    User findUser(String login);
    void addUser (User user);
    boolean userExist (String login);
    void persist();
}
