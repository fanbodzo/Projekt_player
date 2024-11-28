package utils;

import users.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserLoader {
    public List<User> loadUsersFromFile(String filePath) {
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true; // Flaga do pomijania pierwszej linii
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Pomijamy pierwszą linię
                    continue;
                }
                String[] userData = line.split(",");
                if (userData.length >= 4) {
                    User newUser = new User(userData[0].trim(), userData[1].trim(), userData[2].trim(), userData[3].trim(), userData[4].trim());
                    users.add(newUser);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }

        return users;
    }
}