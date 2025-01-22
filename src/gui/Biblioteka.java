package gui;

import utils.ComponentStyle;
import utils.Film;
import utils.Koszyk;
import users.User;
import utils.FrameLoader;

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
    private Koszyk koszyk;
    private static final String DOMYSLNY_FOLDER = "Filmy";
    private FrameLoader frameLoader;


    public Biblioteka(String folderFilmy, Koszyk koszyk , FrameLoader frameLoader) {
        if (koszyk == null) {
            throw new IllegalArgumentException("Koszyk nie może być null");
        }
        this.koszyk = koszyk;
        this.frameLoader = frameLoader;


        // Inicjalizacja głównego panelu (contentPane)
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setBackgroundDefault(contentPane);

        // Inicjalizacja panelu na filmy
        filmy = new JPanel();
        setBackgroundDefault(filmy);
        filmy.setLayout(new GridLayout(2, 8, 10, 10)); // Automatyczna liczba wierszy, odstępy 10px

        // Inicjalizacja przycisku powrotu
        powrotButton = new JButton("Powrót");
        setPrimaryButtonStyle(powrotButton);

        // Pobieranie i dodawanie filmów
        List<Film> listaFilmow = wczytajFilmy(folderFilmy);
        User loggedInUser = this.frameLoader.getLoggedInUser();


        for (Film film : listaFilmow) {
            File filmFolder = new File(folderFilmy, film.getTytul()); // Zakładając, że nazwa folderu to tytuł filmu
            boolean maDostep = czyUzytkownikMaDostepDoFilmu(loggedInUser, filmFolder, film);

            JButton button = new JButton(film.getTytul());
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);

            // Ładowanie ikony
            ImageIcon icon = scaleIcon(film.getSciezkaIkony(), 200, 200);
            if (icon != null) {
                button.setIcon(icon);
            } else {
                button.setIcon(scaleIcon("sciezka_do_placeholdera/brak_ikony.png", 120, 120));
            }

            // Ustawianie koloru i akcji przycisku w zależności od dostępu
            if (maDostep) {
                setButtonColor(button, Color.GREEN);
                button.addActionListener(e -> otworzLoadingScreen());
            } else {
                setButtonColor(button, new Color(199, 61, 230, 98));
                button.addActionListener(e -> wyswietlSzczegolyFilmu(film));
            }

            // Przyciski "Dodaj do koszyka"
            JButton dodajDoKoszykaButton = new JButton("Dodaj do koszyka (" + String.format("%.2f", film.getCena()) + " PLN)");
            dodajDoKoszykaButton.addActionListener(e -> {
                double cenaDlaUzytkownika = film.getCenaDlaUzytkownika(loggedInUser); // Pobierz cenę dla użytkownika
                film.setCena(cenaDlaUzytkownika);
                koszyk.dodajFilm(film);
            });
            setButtonColor(dodajDoKoszykaButton, new Color(199, 61, 230, 98));

            // Panel z filmem i przyciskiem
            JPanel filmPanel = new JPanel();
            setBackgroundDefault(filmPanel);
            filmPanel.setLayout(new BorderLayout());
            filmPanel.add(button, BorderLayout.NORTH);
            filmPanel.add(dodajDoKoszykaButton, BorderLayout.SOUTH);

            // Dodanie panelu filmu do głównego panelu
            filmy.add(filmPanel);
        }

        // Dodanie komponentów do głównego panelu
        contentPane.add(powrotButton, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(filmy), BorderLayout.CENTER); // Dodanie JScrollPane dla lepszej nawigacji

        // Dodanie contentPane do głównego komponentu
        setLayout(new BorderLayout());
        add(contentPane);
    }

    private List<Film> wczytajFilmy(String folderFilmy) {
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

                    double cena = obliczCene(podfolder);
                    File cenaPlik = new File(podfolder, "cena.txt");
                    if (cenaPlik.exists()) {
                        try {
                            cena = Double.parseDouble(new String(Files.readAllBytes(cenaPlik.toPath())).trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Nieprawidłowy format ceny w folderze: " + podfolder.getName());
                        }
                    }

                    if (ikona != null) {
                        filmy.add(new Film(tytul, sciezkaWideo, ikona.getAbsolutePath(), opis, tagi, cena));
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

    private void otworzLoadingScreen() {
        JFrame loadingFrame = new JFrame("Loading");
        ImageIcon loadingIcon = new ImageIcon("resources/loading_projket.gif");
        JLabel loadingLabel = new JLabel(loadingIcon);
        loadingFrame.add(loadingLabel);
        loadingFrame.pack();
        loadingFrame.setLocationRelativeTo(null);
        loadingFrame.setVisible(true);

        // Opcjonalnie, zamknij loadingFrame po pewnym czasie lub po zakończeniu ładowania filmu
        // Możesz użyć Timer lub innego mechanizmu do zarządzania widocznością
    }

    private boolean czyUzytkownikMaDostepDoFilmu(User user, File folderFilmu, Film film) {
        File userFile = new File(folderFilmu, "user.txt");
        if (!userFile.exists()) {
            System.out.println("Plik user.txt nie istnieje dla filmu: " + film.getTytul());
            return false;
        }

        try {
            List<String> lines = Files.readAllLines(userFile.toPath());
            String userLogin = user.getLogin();
            System.out.println("Sprawdzanie dostępu dla użytkownika: " + userLogin);
            for (String line : lines) {
                System.out.println("Porównywanie z linią: " + line.trim());
                if (line.trim().equalsIgnoreCase(userLogin)) { // Użycie equalsIgnoreCase dla większej elastyczności
                    System.out.println("Dostęp przyznany dla użytkownika: " + userLogin + " do filmu: " + film.getTytul());
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Błąd podczas odczytu pliku user.txt dla filmu: " + film.getTytul());
            e.printStackTrace();
        }
        return false;
    }

    private double obliczCene(File podfolder) {
        double cena = 0.0;
        File cenaPlik = new File(podfolder, "cena.txt");

        if (cenaPlik.exists()) {
            try {
                cena = Double.parseDouble(new String(Files.readAllBytes(cenaPlik.toPath())).trim());
            } catch (IOException e) {
                System.err.println("Błąd podczas odczytu pliku z ceną w folderze: " + podfolder.getName());
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.err.println("Nieprawidłowy format ceny w folderze: " + podfolder.getName());
            }
        }

        return cena;
    }


    private File znajdzPlikIkony(File folder) {
        File[] pliki = folder.listFiles();
        if (pliki == null) return null;
        for (File plik : pliki) {
            if (plik.isFile() && (plik.getName().toLowerCase().endsWith(".png") || plik.getName().toLowerCase().endsWith(".jpg") || plik.getName().toLowerCase().endsWith(".jpeg"))) {
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
                "Tytuł: " + film.getTytul() + "\n" +
                        "Opis: " + film.getOpis() + "\n" +
                        "Tagi: " + film.getTagi() + "\n" +
                        "Cena: " + String.format("%.2f PLN", film.getCena()),
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
