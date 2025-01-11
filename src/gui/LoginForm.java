package gui;

import users.User;
import utils.UserLoader;
import utils.ComponentStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginForm implements ComponentStyle {
    private JPanel contentPane;  // Nazwa musi być dokładnie taka sama jak w pliku .form
    private JButton loginButton;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private boolean loginSuccessful = false;

    public LoginForm() {
        //zmiana wykorzystujemy metode na bazowe tlo z intefejsu
        setBackgroundDefault(contentPane);

        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));

        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));

        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        // wykorzystujemy metode na bazowy kolor przycisku
        setPrimaryButtonStyle(loginButton);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserLoader userLoader = new UserLoader();
                List<User> users = userLoader.loadUsersFromFile("data/users.txt");

                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                for (User user : users) {
                    if (user.getLogin().equals(username) && user.getPassword().equals(password)) {
                        // w komentarzu bo zawadza tylko to powaidomienie i jest brzydkie
//                        JOptionPane.showMessageDialog(contentPane, "Login successful! Welcome " + user.getLogin());
                        loginSuccessful = true;
                        break;
                    }
                }
                if (!loginSuccessful) {
                    // dodalem tytul i jaki jest to typ waidomosci co daje taka fajna ikonke
                    JOptionPane.showMessageDialog(contentPane, "Nieprawidlowy login lub haslo","blad logowania", JOptionPane.WARNING_MESSAGE);
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
}