package gui;

import utils.ComponentStyle;
import javax.swing.*;


public class MainPageUser implements ComponentStyle{
    private JPanel contentPane;
    private JButton filmyButton;
    private JButton koszykButton;
    private JButton mojeKontoButton;
    private JLabel regulaminLabel;

    public MainPageUser() {
        setPrimaryButtonStyle(filmyButton);
        setPrimaryButtonStyle(koszykButton);
        setPrimaryButtonStyle(mojeKontoButton);
        setBackgroundDefault(contentPane);

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

}
