package gui;

import utils.ComponentStyle;
import javax.swing.*;

public class MainPageAdmin implements ComponentStyle{
    private JPanel contentPane;
    private JButton wylogujButton;

    public MainPageAdmin() {
        setPrimaryButtonStyle(wylogujButton);
        setBackgroundDefault(contentPane);
    }

    public JButton getWylogujButton() {
        return wylogujButton;
    }
    public JPanel getContentPane() {
        return contentPane;
    }
}
