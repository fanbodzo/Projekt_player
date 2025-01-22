package gui;

import users.User;
import utils.ComponentStyle;
import gui.LoginForm;

import javax.swing.*;
import java.util.List;

public class MojeDane implements ComponentStyle {
    private JPanel contentPane;
    private JLabel wysweitlanaNazwaUzytkownikaLabel;
    private JLabel loginLabel;
    private JLabel emailLabel;
    private JLabel statusPremiumLabel;
    private JButton powrot;
    private JLabel text1;
    private JLabel text2;
    private JLabel text3;
    private JLabel text4;

    public MojeDane() {
        setPrimaryButtonStyle(powrot);
        setBackgroundDefault(contentPane);
        setLabelStyle(wysweitlanaNazwaUzytkownikaLabel);
        setLabelStyle(loginLabel);
        setLabelStyle(emailLabel);
        setLabelStyle(statusPremiumLabel);
        setLabelStyle(text1);
        setLabelStyle(text2);
        setLabelStyle(text3);
        setLabelStyle(text4);
    }

    public void mojeDaneHandler(User user) {

        text1.setText("Login: " + user.getLogin());
        text2.setText("Nazwa: " + user.getName());
        text3.setText("Email: " + user.getEmail());
        text4.setText(user.isPremium() ? "Status: Premium" : "Status: Standard");
    }

    public JButton getPowrot() {
        return powrot;
    }

    public JPanel getContentPane() {
        return contentPane;
    }
}
