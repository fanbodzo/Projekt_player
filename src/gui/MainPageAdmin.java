package gui;

import utils.ComponentStyle;
import javax.swing.*;

public class MainPageAdmin implements ComponentStyle{
    private JPanel contentPane;
    private JButton wylogujButton;
    private JButton dodajFilmButton;
    private JButton edytujFilmButton;
    private JButton wysweitlLogiButton;

    public MainPageAdmin() {
        setPrimaryButtonStyle(wylogujButton);
        setPrimaryButtonStyle(dodajFilmButton);
        setPrimaryButtonStyle(edytujFilmButton);
        setPrimaryButtonStyle(wysweitlLogiButton);
        setBackgroundDefault(contentPane);
    }

    public JButton getWylogujButton() {
        return wylogujButton;
    }
    public JPanel getContentPane() {
        return contentPane;
    }
    public JButton getDodajFilmButton() {return dodajFilmButton;}
    public JButton getEdytujFilmButton() {return edytujFilmButton;}
}
