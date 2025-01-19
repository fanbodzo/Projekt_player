package gui;

import utils.ComponentStyle;
import utils.Film;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Biblioteka extends JPanel implements ComponentStyle {
    private JPanel contentPane;
    private JButton powrotButton;
    private JPanel filmy;
    private static final String DOMYSLNY_FOLDER = "Filmy";

    public Biblioteka() {
        this(DOMYSLNY_FOLDER);
    }

    public Biblioteka(String folderFilmy) {
        // Inicjalizacja głównego panelu (contentPane)
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setBackgroundDefault(contentPane);
        // Inicjalizacja panelu na filmy
        filmy = new JPanel();
        setBackgroundDefault(filmy);
        int columns = 8; // Liczba kolumn w siatce
        filmy.setLayout(new GridLayout(2, columns, 10, 10)); // Automatyczna liczba wierszy, odstępy 10px

        // Inicjalizacja przycisku powrotu
        powrotButton = new JButton("Powrót");
        setPrimaryButtonStyle(powrotButton);
        // Pobieranie i dodawanie filmów
        List<Film> listaFilmow = wczytajFilmy(folderFilmy);

        for (Film film : listaFilmow) {
            JButton button = new JButton(film.getTytul());
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);

            // Ładowanie ikony
            ImageIcon icon = scaleIcon(film.getSciezkaIkony(), 120, 120);
            if (icon != null) {
                button.setIcon(icon);
            } else {
                // Jeśli nie uda się załadować ikony, ustaw placeholder
                button.setIcon(scaleIcon("sciezka_do_placeholdera/brak_ikony.png", 120, 120));
            }

            // Dodanie akcji do przycisku
            button.addActionListener(e -> wyswietlSzczegolyFilmu(film));

            // Dodanie przycisku do panelu filmów
            setButtonColor( button , new Color(199, 61, 230, 98));
            filmy.add(button);
        }

        // Dodanie komponentów do głównego panelu
        contentPane.add(powrotButton, BorderLayout.NORTH);
        contentPane.add(filmy, BorderLayout.CENTER);

        // Dodanie contentPane do głównego komponentu
        setLayout(new BorderLayout());
        add(contentPane);
    }

    // Pozostałe metody pozostają bez zmian
    private List<Film> wczytajFilmy(String folderFilmy) {
        // ... (kod pozostaje bez zmian)
        List<Film> filmy = new ArrayList<>();
        File folder = new File(folderFilmy);

        if (!folder.exists() || !folder.isDirectory()) {
            JOptionPane.showMessageDialog(this, "Folder z filmami nie istnieje!", "Błąd", JOptionPane.ERROR_MESSAGE);
            return filmy;
        }

        for (File podfolder : folder.listFiles()) {
            if (podfolder.isDirectory()) {
                try {
                    String tytul = new String(Files.readAllBytes(new File(podfolder, "tytul.txt").toPath())).trim();
                    String opis = new String(Files.readAllBytes(new File(podfolder, "opis.txt").toPath())).trim();
                    String tagi = new String(Files.readAllBytes(new File(podfolder, "tagi.txt").toPath())).trim();
                    File ikona = znajdzPlikIkony(podfolder);
                    String sciezkaWideo = podfolder.getAbsolutePath() + "/film.mp4";

                    if (ikona != null) {
                        filmy.add(new Film(tytul, sciezkaWideo, ikona.getAbsolutePath(), opis, tagi));
                    } else {
                        System.err.println("Brak pliku ikony w folderze: " + podfolder.getName());
                    }
                } catch (IOException e) {
                    System.err.println("Błąd odczytu danych z folderu: " + podfolder.getName());
                    e.printStackTrace();
                }
            }
        }
        return filmy;
    }

    private File znajdzPlikIkony(File folder) {
        for (File plik : folder.listFiles()) {
            if (plik.isFile() && (plik.getName().endsWith(".png") || plik.getName().endsWith(".jpg") || plik.getName().endsWith(".jpeg"))) {
                return plik;
            }
        }
        return null;
    }

    private ImageIcon scaleIcon(String iconPath, int width, int height) {
        if (iconPath == null || iconPath.isEmpty()) {
            return null;
        }

        ImageIcon originalIcon = new ImageIcon(iconPath);
        if (originalIcon.getIconWidth() > 0 && originalIcon.getIconHeight() > 0) {
            Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        }
        return null;
    }

    private void wyswietlSzczegolyFilmu(Film film) {
        JOptionPane.showMessageDialog(this,
                "Tytuł: " + film.getTytul() + "\nOpis: " + film.getOpis() + "\nTagi: " + film.getTagi(),
                "Szczegóły filmu",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public JButton getPowrotButton() {
        return powrotButton;
    }
}