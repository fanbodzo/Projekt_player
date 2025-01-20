package gui;

import utils.ComponentStyle;

import javax.swing.*;

public class Koszyk implements ComponentStyle {
    private JPanel contentPane;
    private JButton wsteczButton;
    private JButton kupButton;
    private JPanel ListaFilmow;

    public Koszyk() {
        setBackgroundDefault(contentPane);
        setPrimaryButtonStyle(wsteczButton);
        setPrimaryButtonStyle(kupButton);
        setBackgroundDefault(ListaFilmow);
    }

    public JPanel getContentPane() {
        return contentPane;
    }
    public JButton getWsteczButton() {return wsteczButton;}
    public JButton getKupButton() {return kupButton;}
    public JPanel getListaFilmow() {return ListaFilmow;}
}
