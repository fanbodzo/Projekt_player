package gui;

import users.User;
import utils.UserLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginForm {
    private JPanel contentPane;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JLabel usernameLabel;

    public LoginForm() {

        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(3, 2, 10, 10)); // Ustawienie układu

        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        // Dodanie komponentów do panelu
        contentPane.add(usernameLabel);
        contentPane.add(usernameField);
        contentPane.add(passwordLabel);
        contentPane.add(passwordField);
        contentPane.add(new JLabel()); // Pusty label dla wyrównania
        contentPane.add(loginButton);
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserLoader userLoader = new UserLoader();
                List<User> users = userLoader.loadUsersFromFile("data/users.txt");

                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                for (User user : users) {
                    if (user.getLogin().equals(username) && user.getPassword().equals(String.valueOf(password))) {
                        JOptionPane.showMessageDialog(contentPane, "Login successful!");
                        return;
                    }
                }

                JOptionPane.showMessageDialog(contentPane, "Invalid username or password.");
            }
        });
    }
    public JPanel getContentPane() {
        return contentPane;
    }
}