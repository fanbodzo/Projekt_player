package gui;

import utils.ComponentStyle;

import javax.swing.*;

public class MojeKonto implements ComponentStyle {
    private JPanel contentPane;
    private JButton mojeDaneButton;
    private JButton powrotButton;
    private JButton wylogujButton;

    public MojeKonto() {
        setPrimaryButtonStyle(mojeDaneButton);
        setPrimaryButtonStyle(powrotButton);
        setPrimaryButtonStyle(wylogujButton);
        setBackgroundDefault(contentPane);
    }

    public JButton getWyologujButton() {
        return wylogujButton;
    }
    // do przemyslenia czy to ma sens bo ten powrot bedziemy stosowac wiele razy jeszcze
    // a takie cos ma zasotsoawnie do jednego formularza hmmm
    public JButton getPowrotButton() {
        return powrotButton;
    }


    public JPanel getContentPane() {
        return contentPane;
    }
}
