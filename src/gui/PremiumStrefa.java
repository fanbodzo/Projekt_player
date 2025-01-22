package gui;

import utils.ComponentStyle;

import javax.swing.*;

public class PremiumStrefa implements ComponentStyle {
    private JPanel premiumPanel;
    private JPanel premiumStrefaNaglowek;
    private JLabel premiumNaglowek;
    private JButton kupPremiumButton;
    private JEditorPane opisPremiumLabel;
    private JPanel panel2;
    private JButton powrótZPremiumButton;

    public JPanel getPremiumPanel() {
        return premiumPanel;
    }

    public PremiumStrefa() {
        setBackgroundDefault(premiumPanel);
        setPrimaryButtonStyle(premiumButton);
        setPrimaryButtonStyle(kupPremiumButton);
        setPrimaryButtonStyle(powrótZPremiumButton);
        setBackgroundDefault(premiumStrefaNaglowek);
        setLabelStyle(premiumNaglowek);
        setBackgroundDefault(panel2);
        setEditorPaneStyle(opisPremiumLabel);

        opisPremiumLabel.setContentType("text/html"); // Ustaw typ zawartości
        wypiszSzczegolyPremium(); // Wywołanie metody
    }
    public void wypiszSzczegolyPremium() {
        String opisPremium = "<html>"
                + "<b>Player Premium - tylko 20 zł miesięcznie!</b><br>"
                + "Oglądaj filmy taniej dzięki naszej ekskluzywnej subskrypcji.<br>"
                + "Ciesz się setkami filmów w niższych cenach.<br>"
                + "Dołącz do Player Premium już dziś!"
                + "</html>";
        opisPremiumLabel.setText(opisPremium);
    }
    public JButton getPremiumButton() {
        return premiumButton;
    }

    public JButton getPowrotZPremiumButton() {return powrótZPremiumButton;}
    public JButton getKupPremiumButton() {
        return kupPremiumButton;
    }

    private JButton premiumButton;
}
