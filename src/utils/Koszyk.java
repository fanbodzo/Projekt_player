package utils;

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

        kupButton = new JButton("Kup");
        wsteczButton = new JButton("Wstecz");
        podsumowanieArea = new JTextArea();
        podsumowanieArea.setEditable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(kupButton);
        buttonPanel.add(wsteczButton);

        JPanel podsumowaniePanel = new JPanel();
        podsumowaniePanel.setLayout(new BorderLayout());
        podsumowaniePanel.add(new JScrollPane(podsumowanieArea), BorderLayout.CENTER);

        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(podsumowaniePanel, BorderLayout.CENTER);
    }

    public void dodajFilm(Film film) {
        if (!czyFilmJestWKoszyku(film)) {
            filmyWKoszyku.add(film);
            JOptionPane.showMessageDialog(this, film.getTytul() + " dodany do koszyka.", "Sukces", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, film.getTytul() + " jest już w koszyku!", "Informacja", JOptionPane.INFORMATION_MESSAGE);
        }
        zaktualizujPodsumowanie();
    }

    private boolean czyFilmJestWKoszyku(Film film) {
        return filmyWKoszyku.stream()
                .anyMatch(f -> f.getTytul().equals(film.getTytul()));
    }

    public void usunFilm(Film film) {
        filmyWKoszyku.remove(film);
        zaktualizujPodsumowanie();
    }

    public List<Film> getFilmy() {
        return filmyWKoszyku;
    }

    private void zaktualizujPodsumowanie() {
        double cenaRazem = 0;
        StringBuilder sb = new StringBuilder();
        for (Film film : filmyWKoszyku) {
            sb.append(film.getTytul()).append("\n");
            sb.append("Cena: ").append(String.format("%.2f", film.getCena())).append(" PLN\n\n");
            cenaRazem += film.getCena();
        }
        sb.append("Łączna cena: ").append(String.format("%.2f", cenaRazem)).append(" PLN");

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
