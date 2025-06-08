package com.comarch.szkolenia.sklep.database;

import com.comarch.szkolenia.sklep.gui.GUI;
import com.comarch.szkolenia.sklep.gui.IGUI;
import com.comarch.szkolenia.sklep.model.User;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepository implements IUserRepository {
    private final IGUI gui;
    private final Map<String, User> users = new HashMap<>();
    private final String DB_FILE = "users.txt";

    public UserRepository(GUI gui) {
        this.gui = gui;
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
    @Override
    public void changeUserRole () {
        String login = gui.readUserToChangeRole();
        User user = findUser(login);
        if (user == null){
            gui.showUserNotFound();
            return;

        }
        User.Role newRole = (user.getRole() == User.Role.ADMIN) ? User.Role.USER : User.Role.ADMIN;
        user.setRole(newRole);
        persist();
        gui.showChangeRoleResult(login, newRole);
    }
}
