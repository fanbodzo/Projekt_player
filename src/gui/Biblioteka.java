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

        // Dodawanie przycisków dla każdego filmu
        for (Film film : filmy) {
            JButton button = new JButton(film.getNazwa());
            button.setIcon(new ImageIcon(film.getSciezkaIkony())); // Dodanie ikony filmu
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            button.setHorizontalTextPosition(SwingConstants.CENTER);

            // Obsługa kliknięcia w przycisk
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (film.maFilm()) {
                        // Jeśli film ma plik MP4, odtwórz go
                        odtworzFilm(film.getSciezkaVideo());
                    } else {
                        // Wyświetl komunikat, jeśli brak filmu
                        JOptionPane.showMessageDialog(Biblioteka.this, "Ten film nie posiada wideo!");
                    }
                }
            });

            add(button); // Dodanie przycisku do GUI
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
}