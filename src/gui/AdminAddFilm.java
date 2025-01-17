package gui;

import utils.ComponentStyle;

import javax.swing.*;

public class AdminAddFilm implements ComponentStyle {
    private JPanel contentPane;
    private JTextField textField1;
    private JTextField textField2;
    private JButton dodajButton;

    public AdminAddFilm() {
        setBackgroundDefault(contentPane);
        setPrimaryButtonStyle(dodajButton);
    }
    public JPanel getContentPane() {
        return contentPane;
    }
}
