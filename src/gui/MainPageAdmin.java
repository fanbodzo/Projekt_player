package gui;

import utils.ComponentStyle;
import javax.swing.*;

public class MainPageAdmin implements ComponentStyle{
    private JPanel contentPane;
    private JButton wylogujButton;
    private JButton dodajFilmButton;

    public MainPageAdmin() {
        setPrimaryButtonStyle(wylogujButton);
        setPrimaryButtonStyle(dodajFilmButton);
        setBackgroundDefault(contentPane);
    }

    public JButton getWylogujButton() {
        return wylogujButton;
    }
    public JPanel getContentPane() {
        return contentPane;
    }
    public JButton getDodajFilmButton() {return dodajFilmButton;}
}
