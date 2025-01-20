package gui;

import utils.Film;
import utils.Koszyk;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Biblioteka extends JPanel {
    private JPanel contentPane;
    private JButton powrotButton;
    private JPanel filmy;
    private Koszyk koszyk;
    private static final String DOMYSLNY_FOLDER = "Filmy";

    public Biblioteka() {
        this(DOMYSLNY_FOLDER);
    }

    public Biblioteka(String folderFilmy) {
        // Inicjalizacja głównego panelu (contentPane)
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        filmy = new JPanel();
        filmy.setLayout(new GridLayout(2, 8, 10, 10)); // Automatyczna liczba wierszy, odstępy 10px

        // Inicjalizacja przycisku powrotu
        powrotButton = new JButton("Powrót");
        powrotButton.setBackground(new Color(199, 61, 230, 98));

        // Inicjalizacja koszyka
        koszyk = new Koszyk(); // Tutaj tworzysz nowy obiekt Koszyk

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
                button.setIcon(scaleIcon("sciezka_do_placeholdera/brak_ikony.png", 120, 120));
            }

            // Dodanie akcji do przycisku
            button.addActionListener(e -> wyswietlSzczegolyFilmu(film));

            // Przyciski "Dodaj do koszyka"
            JButton dodajDoKoszykaButton = new JButton("Dodaj do koszyka");
            dodajDoKoszykaButton.addActionListener(e -> {
                koszyk.dodajFilm(film); // Wywołanie metody dodajFilm z klasy Koszyk
                JOptionPane.showMessageDialog(this, film.getTytul() + " dodany do koszyka!");
            });

            // Panel z filmem i przyciskiem
            JPanel filmPanel = new JPanel();
            filmPanel.setLayout(new BorderLayout());
            filmPanel.add(button, BorderLayout.NORTH);
            filmPanel.add(dodajDoKoszykaButton, BorderLayout.SOUTH);

            // Dodanie panelu filmu do głównego panelu
            filmy.add(filmPanel);
        }

        // Dodanie komponentów do głównego panelu
        contentPane.add(powrotButton, BorderLayout.NORTH);
        contentPane.add(filmy, BorderLayout.CENTER);

        // Dodanie contentPane do głównego komponentu
        setLayout(new BorderLayout());
        add(contentPane);
    }

    private List<Film> wczytajFilmy(String folderFilmy) {
        // Zwracamy przykładową listę filmów - tutaj musisz dodać kod do wczytywania filmów z folderu
        return List.of(
                new Film("Film 1", "sciezka_video_1.mp4", "sciezka_ikony_1.png", "Opis 1", "Tagi 1", 19.99),
                new Film("Film 2", "sciezka_video_2.mp4", "sciezka_ikony_2.png", "Opis 2", "Tagi 2", 24.99),
                new Film("Film 3", "sciezka_video_3.mp4", "sciezka_ikony_3.png", "Opis 3", "Tagi 3", 14.99)
        );
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
                "Tytuł: " + film.getTytul() + "\nOpis: " + film.getOpis() + "\nTagi: " + film.getTagi() + "\nCena: " + film.getCena() + " PLN",
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
