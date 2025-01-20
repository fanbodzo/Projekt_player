package gui;

import utils.Koszyk;
import utils.Film;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class KoszykPanel extends JPanel {
    private Koszyk koszyk; // Obiekt Koszyk
    private JPanel panelFilmow;
    private JButton kupButton;
    private JButton wsteczButton;

    public KoszykPanel(Koszyk koszyk) {
        this.koszyk = koszyk;
        initComponents();  // Metoda inicjalizująca komponenty

        setLayout(new BorderLayout());  // Ustawiamy layout

        // Tworzymy panel do wyświetlania filmów
        panelFilmow = new JPanel();
        panelFilmow.setLayout(new BoxLayout(panelFilmow, BoxLayout.Y_AXIS)); // Ustawiamy układ dla panelu filmów

        // Dodanie filmów do panelu
        List<Film> filmy = koszyk.getFilmy();
        for (Film film : filmy) {
            panelFilmow.add(new JLabel(film.getTytul()));  // Dodanie tytułu filmu jako etykieta
        }

        add(panelFilmow, BorderLayout.CENTER);
        add(kupButton, BorderLayout.SOUTH);  // Przyciski "Kup" na dole
        add(wsteczButton, BorderLayout.NORTH);  // Przyciski "Wstecz" na górze
    }

    // Inicjalizacja komponentów GUI
    private void initComponents() {
        // Przyciski "Kup" i "Wstecz"
        kupButton = new JButton("Kup");
        wsteczButton = new JButton("Wstecz");
    }
}
