package gui;

import javax.swing.*;

public class Regulamin {
    private JPanel regulaminPanel;
    private JLabel regulaminLabel;
    private JLabel regulaminTrescLabel;
    private JButton powrotRegulaminButton;

    public Regulamin() {
        // Ładowanie treści regulaminu podczas inicjalizacji klasy.
        ustawRegulamin();
    }

    /**
     * Zwraca treść regulaminu w formacie tekstowym.
     *
     * @return String z treścią regulaminu
     */
    private String getRegulaminTresc() {
        return """
                Regulamin użytkowania Playera
                
                § 1 Postanowienia ogólne
                1. Niniejszy regulamin określa zasady korzystania z aplikacji/playera.
                2. Regulamin dotyczy zarówno użytkowników bez subskrypcji Premium, jak i tych, którzy wykupili plan "Player Premium".
                3. Korzystanie z aplikacji oznacza akceptację niniejszego regulaminu.
                4. Regulamin jest dostępny w każdej chwili na stronie aplikacji/playera.

                § 2 Warunki użytkowania
                1. Użytkownicy bez Premium:
                   a) Użytkownicy mogą korzystać z podstawowych funkcji aplikacji bez opłat.
                   b) Treści dostępne bez subskrypcji Premium mogą zawierać reklamy.
                   c) Dostęp do wybranych materiałów i funkcji może być ograniczony dla użytkowników bez Premium.

                2. Użytkownicy "Player Premium":
                   a) Subskrypcja "Player Premium" daje dostęp do dodatkowych funkcji, takich jak:
                      - Brak reklam,
                      - Niższe ceny za filmy,
                      - Lepsza jakość odtwarzania (jeśli dotyczy).
                   b) Koszt subskrypcji wynosi 20,00 PLN miesięcznie, w tym podatek VAT (23%).
                   c) Subskrypcja jest płatna z góry za ustawiony okres rozliczeniowy.
                   d) Użytkownik zostaje poinformowany o szczegółach płatności przed jej realizacją.

                § 3 Płatności (dla użytkowników Premium)
                1. Użytkownik "Player Premium" zobowiązuje się do podania prawidłowych danych, w tym:
                   - Adresu e-mail,
                   - Imienia i nazwiska,
                   - Prawidłowych danych płatniczych.
                2. Brak możliwości obciążenia karty lub podanie nieprawidłowych danych może uniemożliwić aktywację lub utrzymanie subskrypcji.
                3. Użytkownik może wybrać automatyczne odnawianie subskrypcji.
                4. Faktury elektroniczne za subskrypcję są dostępne na żądanie.

                § 4 Zasady korzystania z treści
                1. Użytkownik ma prawo korzystać z udostępnionych treści wyłącznie na własny użytek.
                2. Zabrania się kopiowania, udostępniania lub publikowania materiałów z aplikacji bez zgody ich właściciela.
                3. Usługodawca zastrzega sobie prawo do ograniczenia niektórych funkcji w ramach wybranych planów.

                § 5 Prawa i obowiązki użytkownika
                1. Użytkownicy powinni przestrzegać zasad współżycia społecznego w ramach korzystania z funkcji aplikacji.
                2. Każdy użytkownik odpowiada za dane wprowadzone podczas rejestracji.
                3. Zabrania się korzystania z aplikacji do celów niezgodnych z prawem.

                § 6 Rezygnacja i anulowanie subskrypcji (dla użytkowników Premium)
                1. Użytkownik może zrezygnować z subskrypcji Premium w każdym momencie.
                2. Rezygnacja skutkuje wyłączeniem funkcji Premium po zakończeniu aktualnego okresu rozliczeniowego.
                3. Zwrot wpłaconych środków nie jest możliwy za okresy już opłacone.

                § 7 Problemy techniczne i odpowiedzialność
                1. Usługodawca nie ponosi odpowiedzialności za:
                   - Przerwy w działaniu z przyczyn niezależnych (np. awarie dostawców zewnętrznych),
                   - Podanie błędnych danych przez użytkownika.
                2. Problemy techniczne można zgłaszać poprzez formularz kontaktowy dostępny w aplikacji.

                § 8 Zmiany w regulaminie
                1. Regulamin może być zmieniony w dowolnym momencie z ważnych przyczyn (np. prawnych, technicznych).
                2. Informacja o zmianach zostanie przesłana użytkownikom na podany adres e-mail.
                3. Kontynuowanie korzystania z aplikacji po zmianie regulaminu oznacza akceptację nowej wersji.

                § 9 Postanowienia końcowe
                1. W sprawach nieuregulowanych niniejszym regulaminem mają zastosowanie obowiązujące przepisy prawa polskiego.
                2. Usługodawca zastrzega sobie prawo do weryfikacji przestrzegania regulaminu przez użytkowników.
                3. Wszelkie spory będą rozstrzygane przez sądy właściwe dla siedziby usługodawcy.
                """;
    }

    /**
     * Ustawia treść regulaminu w elemencie JLabel.
     */
    private void ustawRegulamin() {
        if (regulaminTrescLabel != null) {
            regulaminTrescLabel.setText("<html>" + getRegulaminTresc().replace("\n", "<br>") + "</html>");
        }
    }

    public JButton getPowrotRegulaminButton() {
        return powrotRegulaminButton;
    }
    public JPanel getRegulaminPanel() {
        return regulaminPanel;
    }
}