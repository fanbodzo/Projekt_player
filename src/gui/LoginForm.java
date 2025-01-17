package gui;

import users.User;
import utils.UserLoader;
import utils.ComponentStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class LoginForm implements ComponentStyle {
    private JPanel contentPane;
    private JButton loginButton;
    private JButton registerButton; // Nowy przycisk
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private boolean loginSuccessful = false;
    private boolean accountTypeLoggedIn = false;

    public LoginForm() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        setBackgroundDefault(contentPane);

        // Inicjalizacja komponentów
        usernameLabel = new JLabel("Login:");
        passwordLabel = new JLabel("Hasło:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Zaloguj się");
        registerButton = new JButton("Zarejestruj się");

        // Ustawienia czcionek
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Ustawienia kolorów tekstu
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);

        // Stylizacja przycisków
        setPrimaryButtonStyle(loginButton);
        setPrimaryButtonStyle(registerButton);

        // Layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dodawanie komponentów
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(usernameLabel, gbc);

        gbc.gridx = 1;
        contentPane.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(passwordLabel, gbc);

        gbc.gridx = 1;
        contentPane.add(passwordField, gbc);

        // Panel przycisków
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(BackgroundDefaultColor);
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        contentPane.add(buttonPanel, gbc);

        // Logika logowania
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserLoader userLoader = new UserLoader();
                List<User> users = userLoader.loadUsersFromFile("data/users.txt");

                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                for (User user : users) {
                    if (user.getLogin().equals(username) && user.getPassword().equals(password)) {
                        loginSuccessful = true;
                        if (isAdminLogin(user.getLogin())) {
                            accountTypeLoggedIn = true;
                        }
                        break;
                    }
                }
                if (!loginSuccessful) {
                    JOptionPane.showMessageDialog(contentPane,
                            "Nieprawidłowy login lub hasło",
                            "Błąd logowania",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    public boolean getLoginConfirmation() {
        return loginSuccessful;
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public boolean isAdminLogin(String login) {
        String[] adminLogins = {"admin1", "admin2", "admin3"};
        return Arrays.asList(adminLogins).contains(login);
    }

    public boolean getAccountTypeLoggedIn() {
        return accountTypeLoggedIn;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
}