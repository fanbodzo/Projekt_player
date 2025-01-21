package gui;

import utils.ComponentStyle;

import javax.swing.*;
import java.awt.*;

public class MojeKonto implements ComponentStyle {
    private JPanel contentPane;
    private JButton mojeDaneButton;
    private JButton powrotButton;
    private JButton wylogujButton;
    private JButton premiumButton;

    public MojeKonto() {
        setPrimaryButtonStyle(mojeDaneButton);
        setPrimaryButtonStyle(powrotButton);
        setPrimaryButtonStyle(wylogujButton);
        setButtonColor(premiumButton,new Color(221, 183, 26));
        setBackgroundDefault(contentPane);
    }

    public JButton getWylogujButton() {
        return wylogujButton;
    }
    // do przemyslenia czy to ma sens bo ten powrot bedziemy stosowac wiele razy jeszcze
    // a takie cos ma zasotsoawnie do jednego formularza hmmm
    public JButton getPowrotButton() {
        return powrotButton;
    }
    public JButton getPremiumButton() {return premiumButton;}


    public JPanel getContentPane() {
        return contentPane;
    }
}
