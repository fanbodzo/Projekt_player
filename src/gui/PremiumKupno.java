package gui;

import javax.swing.*;
import java.awt.*;

public class PremiumKupno {

    private JPanel premiumPanel;
    private JLabel headerLabel;
    private JPanel benefitsPanel;
    private JButton buyButton;
    private JButton powrotButton;

    public PremiumKupno() {
        premiumPanel = new JPanel(new BorderLayout()); // Tworzymy panel z układem BorderLayout

        // Nagłówek
        JLabel headerLabel = new JLabel("Strefa Premium", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 22));
        premiumPanel.add(headerLabel, BorderLayout.NORTH);

        // Korzyści premium
        JPanel benefitsPanel = new JPanel();
        benefitsPanel.setLayout(new BoxLayout(benefitsPanel, BoxLayout.Y_AXIS));;
        benefitsPanel.add(new JLabel("Filmy w lepszych cenach! Już wkrótce wiele więcej korzyści"));
        JButton buyButton = new JButton("Kup Premium");

        // Obsługa przycisku "Kup Premium"
        buyButton.addActionListener(e -> JOptionPane.showMessageDialog(null,
                "Dziękujemy za zakup Premium! Miłego korzystania."));
        benefitsPanel.add(buyButton);

        premiumPanel.add(benefitsPanel, BorderLayout.CENTER);

        // Przycisk "Powrót"
        JButton powrotButton = new JButton("Powrót do Mojego Konta");
        powrotButton.addActionListener(e -> {
            // Wracamy do formularza "Moje Konto" (logika obsługiwana w FrameLoader)
            JOptionPane.showMessageDialog(null, "Powrót do Mojego Konta");
        });
        premiumPanel.add(powrotButton, BorderLayout.SOUTH);
    }

    // Getter zwracający panel dla strefy premium
    public JPanel getPremiumPanel() {
        return premiumPanel;
    }
}