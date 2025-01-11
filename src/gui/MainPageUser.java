package gui;

import utils.ComponentStyle;
import javax.swing.*;


public class MainPageUser implements ComponentStyle{
    private JPanel contentPane;
    private JButton filmyButton;
    private JButton koszykButton;
    private JButton mojeKontoButton;

    public MainPageUser() {
        setPrimaryButtonStyle(filmyButton);
        setPrimaryButtonStyle(koszykButton);
        setPrimaryButtonStyle(mojeKontoButton);
        setBackgroundDefault(contentPane);
    }
    public JPanel getContentPane() {
        return contentPane;
    }
}
