package com.comarch.szkolenia.sklep.database;

import com.comarch.szkolenia.sklep.model.User;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<String, User> users = new HashMap<>();
    @Getter
    private final static UserRepository instance = new UserRepository();

    private UserRepository() {
        this.users.put("admin", new User("admin",
                "73e448ae60c818c23ede44ee35be02b3", User.Role.ADMIN));
        this.users.put("arek", new User("arek",
                "0be7f8602e8c857cb0586fd8bd9d8e7e", User.Role.USER));
    }

    public User findUser(String login) {
        return this.users.get(login);
    }

    public void addUser (User user) {
        this.users.put(user.getLogin(), user);
    }
    public boolean userExist (String login) {
        return this.users.containsKey(login);
    }
}
