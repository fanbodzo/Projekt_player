package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

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
    private JEditorPane tekstSubskrypcjiLabel;
    private JButton SfinalizujZakupButton;
    private JPanel daneDoPremiumPanel;
    private JButton kupPremiumButton;

    public KupPremiumStrefa() {
        tekstSubskrypcjiLabel.setContentType("text/html"); // Ustaw typ zawartości
        wypiszSzczegolySubskrypcji(); // Wywołanie metody
        SfinalizujZakupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sprawdzPoprawnoscDanych()) {
                    JOptionPane.showMessageDialog(kupPremiumPanel, "Zakup zrealizowany pomyślnie!", "Sukces", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(kupPremiumPanel, "Wprowadzone dane są nieprawidłowe. Sprawdź formularz i spróbuj ponownie.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getKupPremiumPanel() {
        return kupPremiumPanel;
    }

    public JButton getKupPremiumButton() {
        return kupPremiumButton;
    }

    public void wypiszSzczegolySubskrypcji() {
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
    /**
     * Metoda sprawdzająca poprawność danych w formularzu.
     *
     * @return true, jeśli dane są prawidłowe, false w przeciwnym razie
     */
    private boolean sprawdzPoprawnoscDanych() {
        // Walidacja adresu e-mail
        if (!czyEmailJestPoprawny(eMailTextField.getText())) {
            return false;
        }

        // Walidacja numeru karty kredytowej
        if (!czyNumerKartyJestPoprawny(numerKartyTextField.getText())) {
            return false;
        }

        // Walidacja imienia i nazwiska
        if (!czyImieNazwiskoJestPoprawne(imieNazwiskoTextField.getText())) {
            return false;
        }

        // Walidacja pola adresu (ulica, numer domu, kod pocztowy, miasto)
        if (adresUlicaTextField.getText().isEmpty() ||
                adresNumerDomuTextField.getText().isEmpty() ||
                adresKodPocztowyTextField.getText().isEmpty() ||
                AdresMiastoTextField.getText().isEmpty()) {
            return false;
        }

        // Jeśli wszystkie pola są poprawne
        return true;
    }

    /**
     * Sprawdza poprawność adresu e-mail.
     *
     * @param email adres e-mail
     * @return true, jeśli adres jest poprawny, false w przeciwnym razie
     */
    private boolean czyEmailJestPoprawny(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"; // Prosty regex dla e-maila
        return Pattern.matches(emailRegex, email);
    }

    /**
     * Sprawdza poprawność numeru karty kredytowej.
     *
     * @param numerKarty numer karty kredytowej
     * @return true, jeśli numer karty jest poprawny, false w przeciwnym razie
     */
    private boolean czyNumerKartyJestPoprawny(String numerKarty) {
        // Numer karty powinien składać się z 16 cyfr
        String kartaRegex = "\\d{16}";
        return Pattern.matches(kartaRegex, numerKarty);
    }

    /**
     * Sprawdza poprawność imienia i nazwiska.
     *
     * @param imieNazwisko imię i nazwisko
     * @return true, jeśli imię i nazwisko zawiera tylko litery i spacje, false w przeciwnym razie
     */
    private boolean czyImieNazwiskoJestPoprawne(String imieNazwisko) {
        String imieNazwiskoRegex = "^[A-Za-zÀ-ÿ]+(\\s[A-Za-zÀ-ÿ]+)+$"; // Imię i nazwisko (przynajmniej dwa człony)
        return Pattern.matches(imieNazwiskoRegex, imieNazwisko);
    }
}
