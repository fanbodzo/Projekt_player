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



        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserLoader userLoader = new UserLoader();
                List<User> users = userLoader.loadUsersFromFile("data/users.txt");

                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                boolean loginSuccessful = false;
                for (User user : users) {
                    if (user.getLogin().equals(username) && user.getPassword().equals(password)) {
                        JOptionPane.showMessageDialog(contentPane, "Login successful! Welcome " + user.getLogin());
                        loginSuccessful = true;
                        break;
                    }

                }
                if(!loginSuccessful){ JOptionPane.showMessageDialog(contentPane, "Invalid username or password.");}
            }
        });
    }
    public JPanel getContentPane() {
        return contentPane;
    }
}