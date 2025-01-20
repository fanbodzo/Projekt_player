package gui;

import javax.swing.*;

public class KupPremiumStrefa {
    private JPanel kupPremiumPanel;
    private JTextField eMailTextField;
    private JTextField numerKartyTextField;
    private JTextField imieNazwiskoTextField;
    private JTextField adresUlicaTextField;
    private JLabel emailKupujacegoLabel;
    private JLabel infoKartaLabel;
    private JTextField kartaMMRRTextField;
    private JTextField kartaKodCVCTextField;
    private JLabel ImieNazwiskoLabel;
    private JTextField adresNumerDomuTextField;
    private JTextField adresKodPocztowyTextField;
    private JTextField AdresMiastoTextField;
    private JComboBox adresKrajComboBox;
    private JLabel tekstSubskrypcjiLabel;
    private JButton kupPremiumButton;

    public JPanel getKupPremiumPanel() {
        return kupPremiumPanel;
    }

    public JButton getKupPremiumButton() {
        return kupPremiumButton;
    }


    public void wypiszSzczegolySubskrypcji() {
        // Szczegóły subskrypcji
        String tekstSubskrypcji = "<html>"
                + "Subskrybuj Player Premium za 20 zł miesięcznie!<br>"
                + "<br>"
                + "Cena podstawowa: 16,26 zł<br>"
                + "VAT (23%): 3,74 zł<br>"
                + "<b>Łączna kwota do zapłaty: 20,00 zł</b><br>"
                + "<br>"
                + "Dołącz teraz i ciesz się nieograniczonym dostępem!"
                + "</html>";
        tekstSubskrypcjiLabel.setText(tekstSubskrypcji);
    }
}
