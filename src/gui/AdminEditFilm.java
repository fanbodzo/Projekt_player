package gui;

import utils.ComponentStyle;

import javax.swing.*;
import java.awt.*;

public class AdminEditFilm implements ComponentStyle {
    private JPanel contentPane;
    private JButton wybierzFilmButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton zapiszButton;
    private JButton anulujButton;
    private JButton edytujOkladkeButton;

    public AdminEditFilm() {
        setBackgroundDefault(contentPane);
        setPrimaryButtonStyle(wybierzFilmButton);
        setPrimaryButtonStyle(anulujButton);
        setPrimaryButtonStyle(zapiszButton);
        setButtonColor(edytujOkladkeButton,new Color(230, 110, 61));
    }

    public JButton getAnulujButton() {
        return anulujButton;
    }

    public JPanel getContentPane() {
        return contentPane;
    }
}
