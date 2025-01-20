package utils;

import utils.Film;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Koszyk extends JPanel {
    private List<Film> filmyWKoszyku;
    private JButton kupButton;
    private JButton wsteczButton;
    private JTextArea podsumowanieArea;

    public Koszyk() {
        this.filmyWKoszyku = new ArrayList<>();
        this.setLayout(new BorderLayout());

        // Tworzymy przyciski
        kupButton = new JButton("Kup");
        wsteczButton = new JButton("Wstecz");
        podsumowanieArea = new JTextArea();
        podsumowanieArea.setEditable(false);

        // Dodajemy do panelu
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(kupButton);
        buttonPanel.add(wsteczButton);

        // Panel do podsumowania
        JPanel podsumowaniePanel = new JPanel();
        podsumowaniePanel.setLayout(new BorderLayout());
        podsumowaniePanel.add(new JScrollPane(podsumowanieArea), BorderLayout.CENTER);

        // Dodajemy przyciski i podsumowanie
        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(podsumowaniePanel, BorderLayout.CENTER);
    }

    // Dodajemy film do koszyka
    public void dodajFilm(Film film) {
        filmyWKoszyku.add(film);
        zaktualizujPodsumowanie();
    }

    // Usuwamy film z koszyka
    public void usunFilm(Film film) {
        filmyWKoszyku.remove(film);
        zaktualizujPodsumowanie();
    }

    // Metoda do pobierania listy filmów w koszyku
    public List<Film> getFilmy() {
        return filmyWKoszyku; // Poprawione: zwrócenie filmyWKoszyku
    }

    // Zaktualizuj podsumowanie koszyka
    private void zaktualizujPodsumowanie() {
        double cenaRazem = 0;
        StringBuilder sb = new StringBuilder();
        for (Film film : filmyWKoszyku) {
            sb.append(film.getTytul()).append("\n");
            sb.append("Cena: ").append(film.getCena()).append(" PLN\n\n");
            cenaRazem += film.getCena();
        }
        sb.append("Łączna cena: ").append(cenaRazem).append(" PLN");

        podsumowanieArea.setText(sb.toString());
    }

    public List<Film> getFilmyWKoszyku() {
        return filmyWKoszyku;
    }

    public JButton getKupButton() {
        return kupButton;
    }

    public JButton getWsteczButton() {
        return wsteczButton;
    }
}
