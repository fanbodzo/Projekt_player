package gui;

import utils.ComponentStyle;
import javax.swing.*;


public class MainPageUser implements ComponentStyle{
    private JPanel contentPane;
    private JButton filmyButton;
    private JButton koszykButton;
    private JButton mojeKontoButton;
    private JPanel welcomePanel;
    private JButton regulaminButton;
    private JLabel welcomeLabel;
    private JLabel playerLabel;

    public MainPageUser() {
        setPrimaryButtonStyle(filmyButton);
        setPrimaryButtonStyle(koszykButton);
        setPrimaryButtonStyle(mojeKontoButton);
        setPrimaryButtonStyle(regulaminButton);
        setBackgroundDefault(contentPane);
        setBackgroundDefault(welcomePanel);

        ustawPowitanie();

    }
    private String getPowitanieTresc() {
        return """
            Witaj w naszej aplikacji!
            
            Korzystaj z pełni możliwości naszej platformy:
            - Oglądaj filmy w bibliotece,
            - Zarządzaj swoimi zakupami w koszyku,
            - Sprawdź szczegóły swojego konta,
            - Odkryj wyjątkowe promocje i nowości!
            
            Dziękujemy, że jesteś z nami i miłego dnia!
            """;
    }

    /**
     * powitanie
     */
    private void ustawPowitanie() {
        if (welcomeLabel != null) {
            welcomeLabel.setText("<html>" + getPowitanieTresc().replace("\n", "<br>") + "</html>");
        }
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
    public JPanel getWelcomePanel() {return welcomePanel;}

}
