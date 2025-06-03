package com.comarch.szkolenia.sklep.database;

import com.comarch.szkolenia.sklep.model.User;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository implements IUserRepository {
    private final Map<String, User> users = new HashMap<>();
    private final String DB_FILE = "users.txt";

    public UserRepository() {
        this.users.put("admin", new User("admin",
                "73e448ae60c818c23ede44ee35be02b3", User.Role.ADMIN));
        this.users.put("arek", new User("arek",
                "0be7f8602e8c857cb0586fd8bd9d8e7e", User.Role.USER));

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(DB_FILE))) {
            String line;
            while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                String[] parametr = line.split(";");
                User user = new User(parametr[0], parametr[1], User.Role.valueOf(parametr[2]));
                this.users.put(user.getLogin(), user);
            }
        }catch (IOException e) {
            System.out.println("baza nie działa !!");
        }
    }
    @Override
    public User findUser(String login) {
        return this.users.get(login);
    }
    @Override
    public void addUser (User user) {
        this.users.put(user.getLogin(), user);
    }
    @Override
    public boolean userExist (String login) {
        return this.users.containsKey(login);
    }
    @Override
    public void persist(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(DB_FILE))) {
            for(User u : this.users.values()) {
                writer.write(u.convertToDatabaseLine());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Nie działa zapisywanie !!");
        }
    }
}
