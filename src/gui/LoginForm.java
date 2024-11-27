package gui;

import utils.UserLoader;
import users.User;

import javax.swing.*;
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
        //nie wiem nie wyswietla sie nic nie wiem o co chodzi a sie juz tak nameczylem z tym wsyzskim i naszukalem
        // na niecie ze mam dosc poprstu na dzis

        UserLoader userLoader = new UserLoader();
        List<User> dane = userLoader.loadUsersFromFile("data/users.txt");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                for(User user : dane){
                    if(user.getLogin().equals(username) && user.getPassword().equals(String.valueOf(password))){
                        JOptionPane.showMessageDialog(contentPane, "udalo sie zalogowac");
                    }else{
                        JOptionPane.showMessageDialog(contentPane, "niepoprawne dane nie udalo sie zalogowac");
                    }

                }
            }
        });
    }
}
