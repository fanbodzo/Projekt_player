package gui;

import users.User;
import utils.ComponentStyle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.UUID;

public class RegisterForm implements ComponentStyle {
    private JPanel contentPane;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField emailField;
    private JTextField nameField;
    private JButton registerButton;
    private JButton backButton;
    private boolean registrationSuccessful = false;

    public RegisterForm() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        setBackgroundDefault(contentPane);

        // Tworzenie komponentów
        loginField = new JTextField(20);
        passwordField = new JPasswordField(20);
        confirmPasswordField = new JPasswordField(20);
        emailField = new JTextField(20);
        nameField = new JTextField(20);
        registerButton = new JButton("Zarejestruj się");
        backButton = new JButton("Powrót");

        // Stylizacja przycisków
        setPrimaryButtonStyle(registerButton);
        setPrimaryButtonStyle(backButton);

        // Ustawienia layoutu
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dodawanie komponentów
        addComponent("Login:", loginField, gbc, 0);
        addComponent("Hasło:", passwordField, gbc, 1);
        addComponent("Potwierdź hasło:", confirmPasswordField, gbc, 2);
        addComponent("Email:", emailField, gbc, 3);
        addComponent("Imię:", nameField, gbc, 4);

        // Przyciski
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(BackgroundDefaultColor);
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        gbc.gridy = 5;
        gbc.gridwidth = 2;
        contentPane.add(buttonPanel, gbc);

        // Obsługa rejestracji
        registerButton.addActionListener(e -> {
            if (validateForm()) {
                registerUser();
            }
        });
    }

    private void addComponent(String labelText, JComponent component, GridBagConstraints gbc, int y) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));

        gbc.gridx = 0;
        gbc.gridy = y;
        contentPane.add(label, gbc);

        gbc.gridx = 1;
        contentPane.add(component, gbc);
    }

    private boolean validateForm() {
        if (loginField.getText().trim().isEmpty() ||
                new String(passwordField.getPassword()).trim().isEmpty() ||
                emailField.getText().trim().isEmpty() ||
                nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(contentPane,
                    "Wszystkie pola muszą być wypełnione!",
                    "Błąd walidacji",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!new String(passwordField.getPassword())
                .equals(new String(confirmPasswordField.getPassword()))) {
            JOptionPane.showMessageDialog(contentPane,
                    "Hasła muszą być identyczne!",
                    "Błąd walidacji",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!emailField.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(contentPane,
                    "Nieprawidłowy format adresu email!",
                    "Błąd walidacji",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void registerUser() {
        String userId = UUID.randomUUID().toString();
        User newUser = new User(
                userId,
                loginField.getText().trim(),
                new String(passwordField.getPassword()).trim(),
                emailField.getText().trim(),
                nameField.getText().trim(),
                false
        );

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/users.txt", true));
            writer.newLine();
            writer.write(String.format("%s,%s,%s,%s,%s,%s",
                    newUser.getUserId(),
                    newUser.getLogin(),
                    newUser.getPassword(),
                    newUser.getEmail(),
                    newUser.getName(),
                    "false"
            ));
            writer.flush();
            writer.close();

            registrationSuccessful = true;
            JOptionPane.showMessageDialog(contentPane,
                    "Rejestracja zakończona sukcesem!",
                    "Sukces",
                    JOptionPane.INFORMATION_MESSAGE);

            // Automatyczny powrót do logowania
            if (registrationSuccessful) {
                backButton.doClick();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(contentPane,
                    "Błąd podczas rejestracji: " + e.getMessage(),
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public boolean isRegistrationSuccessful() {
        return registrationSuccessful;
    }
}