package gui;

import utils.ComponentStyle;
import utils.Film; // Import modelu Film z pakietu utils
import utils.FilmLoader; // Import loadera FilmLoader z pakietu utils

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class Biblioteka extends JPanel implements ComponentStyle {
    private JPanel contentPane;
    private JButton powrotButton; // Dodajemy przycisk "Powrót"
    // Domyślna ścieżka do folderu z filmami
    private static final String DOMYSLNY_FOLDER = "Filmy";

    private ImageIcon scaleIcon(String iconPath, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(iconPath);
        if (originalIcon.getIconWidth() > 0 && originalIcon.getIconHeight() > 0) { // Pewność, że ikona istnieje
            Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } else {
            System.err.println("Nie udało się załadować ikony: " + iconPath);
            return null; // W razie problemów zwracamy null
        }
    }
    // Konstruktor bez argumentów – używa domyślnej wartości
    public Biblioteka() {
        this(DOMYSLNY_FOLDER); // Wywołanie przeciążonego konstruktora z domyślną wartością
    }

    // Konstruktor z argumentem – umożliwia zmianę ścieżki
    public Biblioteka(String folderFilmy) {
        // Dynamiczne wczytywanie listy filmów z folderu
        List<utils.Film> filmy = FilmLoader.wczytajFilmy(folderFilmy);

        // Layout siatki dla przycisków filmów
        int columns = 4; // Liczba kolumn
        setLayout(new GridLayout(0, columns, 10, 10)); // rows = 0 -> automatyczne dostosowanie liczby wierszy

        for (Film film : filmy) {
            JButton button = new JButton(film.getNazwa());

            // Ścieżka do ikony
            String sciezkaIkony = film.getSciezkaIkony();

            if (sciezkaIkony != null && !sciezkaIkony.isEmpty()) {
                ImageIcon scaledIcon = scaleIcon(sciezkaIkony, 120, 120); // Skalowanie ikony
                if (scaledIcon != null) {
                    button.setIcon(scaledIcon); // Ustaw ikonę tylko jeśli się załadowała
                } else {
                    System.err.println("Nie udało się załadować poprawnej ikony dla: " + film.getNazwa());
                    button.setIcon(scaleIcon("sciezka_do_placeholdera/brak_ikony.png", 120, 120)); // Placeholder
                }
            } else {
                System.err.println("Ścieżka ikony jest pusta dla: " + film.getNazwa());
                button.setIcon(scaleIcon("sciezka_do_placeholdera/brak_ikony.png", 120, 120));
            }

            // Ustawienia tekstu i layoutu przycisku
            button.setHorizontalTextPosition(SwingConstants.CENTER); // Wyśrodkowanie tekstu
            button.setVerticalTextPosition(SwingConstants.BOTTOM);   // Tekst pod ikoną

            // Dodaj przycisk do panelu
            this.add(button);
        }

        setVisible(true);
    }

    /**
     * Metoda do odtwarzania filmu (np. otwarcie pliku .mp4 w domyślnym odtwarzaczu wideo systemowym)
     */
    private void odtworzFilm(String sciezkaVideo) {
        try {
            Desktop.getDesktop().open(new File(sciezkaVideo));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Nie udało się odtworzyć filmu: " + sciezkaVideo);
            ex.printStackTrace();
        }
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    // Getter dla przycisku "Powrót"
    public JButton getPowrotButton() {
        return powrotButton;
    }
}