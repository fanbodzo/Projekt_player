package gui;

import utils.ComponentStyle;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class KupPremiumStrefa implements ComponentStyle {
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
    private JLabel adresRozliczeniowyLabel;
    private JButton powrotZKupPremiumButton;
    private JButton kupPremiumButton;
    private Runnable przejdzDoMainPage;
    private static String currentUsername;

    public KupPremiumStrefa() {
        setBackgroundDefault(kupPremiumPanel);
        setBackgroundDefault(daneDoPremiumPanel);
        setPrimaryButtonStyle(kupPremiumButton);
        setPrimaryButtonStyle(SfinalizujZakupButton);
        setTextFieldStyle(eMailTextField);
        setTextFieldStyle(numerKartyTextField);
        setTextFieldStyle(imieNazwiskoTextField);
        setTextFieldStyle(adresUlicaTextField);
        setTextFieldStyle(adresNumerDomuTextField);
        setTextFieldStyle(adresKodPocztowyTextField);
        setTextFieldStyle(AdresMiastoTextField);
        setTextFieldStyle(kartaMMRRTextField);
        setTextFieldStyle(kartaKodCVCTextField);
        setLabelStyle(emailKupujacegoLabel);
        setLabelStyle(infoKartaLabel);
        setLabelStyle(ImieNazwiskoLabel);
        setLabelStyle(adresRozliczeniowyLabel);
        setComboBoxStyle(adresKrajComboBox);
        setEditorPaneStyle( tekstSubskrypcjiLabel);
        setPrimaryButtonStyle(powrotZKupPremiumButton);


        tekstSubskrypcjiLabel.setContentType("text/html");
        wypiszSzczegolySubskrypcji();
        SfinalizujZakupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sprawdzPoprawnoscDanych()) {
                    JOptionPane.showMessageDialog(kupPremiumPanel, "Zakup zrealizowany pomyślnie!", "Sukces", JOptionPane.INFORMATION_MESSAGE);
                    if (currentUsername != null && !currentUsername.isEmpty()) {
                        ustawPremiumDlaZalogowanegoUzytkownika();
                    }
                    if (przejdzDoMainPage != null) {
                        przejdzDoMainPage.run();
                    }
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
    public JButton getPowrotZKupPremiumButton() {return powrotZKupPremiumButton;}

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
    public void setPrzejdzDoMainPage(Runnable przejdzDoMainPage) {
        this.przejdzDoMainPage = przejdzDoMainPage;
    }
    public void ustawPremiumDlaZalogowanegoUzytkownika() {
        String filePath = "data/users.txt";
        List<String> wszystkieLinie = new ArrayList<>();

        try {
            wszystkieLinie = Files.readAllLines(Paths.get(filePath));

            boolean znalezionoUzytkownika = false;

            for (int i = 0; i < wszystkieLinie.size(); i++) {
                String linia = wszystkieLinie.get(i);

                // sprawdza czy nazwa uzytkownika znajduje się w pliku
                String[] dane = linia.split(",");
                if (dane.length >= 2 && dane[1].equals(currentUsername)) {
                    System.out.println("Znaleziono użytkownika: " + currentUsername);

                    if (dane.length == 6) {
                        dane[5] = "true";
                    }

                    wszystkieLinie.set(i, String.join(",", dane));
                    znalezionoUzytkownika = true;
                    break;
                }
            }

            if (znalezionoUzytkownika) {
                Files.write(Paths.get(filePath), wszystkieLinie);
                System.out.println("Zaktualizowano status PREMIUM dla użytkownika: " + currentUsername);
            } else {
                JOptionPane.showMessageDialog(
                        kupPremiumPanel,
                        "Nie znaleziono użytkownika w pliku!",
                        "Błąd",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    kupPremiumPanel,
                    "Wystąpił błąd podczas aktualizacji pliku użytkowników.",
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    /**
     * metody na poprawnosc danych
     */
    private boolean sprawdzPoprawnoscDanych() {
        if (!czyEmailJestPoprawny(eMailTextField.getText())) {
            return false;
        }

        if (!czyNumerKartyJestPoprawny(numerKartyTextField.getText())) {
            return false;
        }

        if (!czyImieNazwiskoJestPoprawne(imieNazwiskoTextField.getText())) {
            return false;
        }

        if (adresUlicaTextField.getText().isEmpty() ||
                adresNumerDomuTextField.getText().isEmpty() ||
                adresKodPocztowyTextField.getText().isEmpty() ||
                AdresMiastoTextField.getText().isEmpty()) {
            return false;
        }

        return true;
    }

    /**
     *poprawnosc adresu e-mail
     */
    private boolean czyEmailJestPoprawny(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"; // Prosty regex dla e-maila
        return Pattern.matches(emailRegex, email);
    }

    /**
     *poprawnosc karty na 16 cyfr
     */
    private boolean czyNumerKartyJestPoprawny(String numerKarty) {
        String kartaRegex = "\\d{16}";
        return Pattern.matches(kartaRegex, numerKarty);
    }

    /**
     * poprawnosc imienia i nazwiska

     */
    private boolean czyImieNazwiskoJestPoprawne(String imieNazwisko) {
        String imieNazwiskoRegex = "^[A-Za-zÀ-ÿ]+(\\s[A-Za-zÀ-ÿ]+)+$"; // Imię i nazwisko (przynajmniej dwa człony)
        return Pattern.matches(imieNazwiskoRegex, imieNazwisko);
    }
}
