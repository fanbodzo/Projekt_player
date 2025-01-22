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
    public JButton getPowrotButton() {
        return powrotButton;
    }
    public JButton getPremiumButton() {return premiumButton;}
    public JPanel getContentPane() {
        return contentPane;
    }
    public JButton getMojeDaneButton() {return mojeDaneButton;}
}
