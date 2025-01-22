package gui;

import utils.ComponentStyle;
import javax.swing.*;


public class MainPageUser implements ComponentStyle{
    private JPanel contentPane;
    private JButton filmyButton;
    private JButton koszykButton;
    private JButton mojeKontoButton;
    private JPanel filmyPanel;
    private JButton regulaminButton;

    public MainPageUser() {
        setPrimaryButtonStyle(filmyButton);
        setPrimaryButtonStyle(koszykButton);
        setPrimaryButtonStyle(mojeKontoButton);
        setPrimaryButtonStyle(regulaminButton);
        setBackgroundDefault(contentPane);
        setBackgroundDefault(filmyPanel);

    }
    // przekazuje przycisk co jest chyba efektywniejsze
    public JButton getMojeKontoButton() {
        return mojeKontoButton;
    }
    public JPanel getContentPane() {
        return contentPane;
    }
    public JButton getBibliotekaButton() {
        return filmyButton;
    }
    public JButton getKoszykButton() {
        return koszykButton;
    }
    public JButton getRegulaminButton() {return regulaminButton;}
    public JPanel getFilmyPanel() {return filmyPanel;}

}
