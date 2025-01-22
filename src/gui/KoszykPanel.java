package gui;

import utils.ComponentStyle;
import utils.Koszyk;
import utils.Film;

import javax.swing.*;
import java.awt.*;

public class KoszykPanel extends JPanel implements ComponentStyle {
    private JPanel contentPane;
    private Koszyk koszyk;
    private JPanel filmyPanel;
    private JButton kupButton;
    private JButton wsteczButton;
    private JScrollPane scrollPane;
    private JFrame parentFrame;
    private MainPageUser mainPageUserPanel;
    private JLabel sumaLabel;

    public KoszykPanel(Koszyk koszyk, JFrame parentFrame, MainPageUser mainPageUserPanel) {
        this.koszyk = koszyk;
        this.parentFrame = parentFrame;
        this.mainPageUserPanel = mainPageUserPanel;

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackgroundDefault(contentPane);
        setLayout(new BorderLayout());
        add(contentPane);

        initComponents();
        layoutComponents();
        updateKoszyk();
    }

    private void initComponents() {
        filmyPanel = new JPanel();
        filmyPanel.setLayout(new GridLayout(0, 8, 10, 10));
        setBackgroundDefault(filmyPanel);

        scrollPane = new JScrollPane(filmyPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        kupButton = new JButton("Kup");
        wsteczButton = new JButton("Wstecz");
        sumaLabel = new JLabel("Suma: 0.00 PLN");

        setPrimaryButtonStyle(kupButton);
        setPrimaryButtonStyle(wsteczButton);
        setLabelStyle(sumaLabel);

        wsteczButton.addActionListener(e -> {
            parentFrame.setContentPane(mainPageUserPanel.getContentPane());
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        kupButton.addActionListener(e -> {
            if (koszyk.getFilmy().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Koszyk jest pusty!", "Błąd", JOptionPane.ERROR_MESSAGE);
            } else {
                KoszykPodsumowanie podsumowanie = new KoszykPodsumowanie(koszyk, parentFrame, mainPageUserPanel);
                parentFrame.setContentPane(podsumowanie);
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
    }

    private void layoutComponents() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setBackgroundDefault(buttonPanel);
        buttonPanel.add(kupButton);
        buttonPanel.add(wsteczButton);
        buttonPanel.add(sumaLabel);

        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    private double obliczSume() {
        return koszyk.getFilmy().stream()
                .mapToDouble(Film::getCena)
                .sum();
    }

    public void updateKoszyk() {
        filmyPanel.removeAll();

        for (Film film : koszyk.getFilmy()) {
            JPanel filmPanel = createFilmPanel(film);
            filmyPanel.add(filmPanel);
        }

        if (koszyk.getFilmy().isEmpty()) {
            JLabel emptyLabel = new JLabel("Koszyk jest pusty", SwingConstants.CENTER);
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            filmyPanel.add(emptyLabel);
        }

        double suma = obliczSume();
        sumaLabel.setText(String.format("Suma: %.2f PLN", suma));

        filmyPanel.revalidate();
        filmyPanel.repaint();
    }

    private JPanel createFilmPanel(Film film) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBackgroundDefault(panel);

        // Film image
        ImageIcon icon = new ImageIcon(film.getSciezkaIkony());
        Image scaledImage = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Film info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3, 1));
        setBackgroundDefault(infoPanel);

        JLabel titleLabel = new JLabel(film.getTytul(), SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        JLabel priceLabel = new JLabel(String.format("Cena: %.2f PLN", film.getCena()), SwingConstants.CENTER);
        priceLabel.setForeground(Color.WHITE);

        // Remove button
        JButton usunButton = new JButton("Usuń z koszyka");
        setPrimaryButtonStyle(usunButton);
        usunButton.addActionListener(e -> {
            koszyk.usunFilm(film);
            updateKoszyk();
        });

        infoPanel.add(titleLabel);
        infoPanel.add(priceLabel);
        infoPanel.add(usunButton);

        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(infoPanel, BorderLayout.SOUTH);

        return panel;
    }

    public JPanel getContentPane() {
        return contentPane;
    }
}
