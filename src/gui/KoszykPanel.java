package gui;

import utils.ComponentStyle;
import utils.Koszyk;
import utils.Film;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class KoszykPanel extends JPanel implements ComponentStyle{
    private JPanel contentPane;
    private Koszyk koszyk;
    private JPanel filmyPanel;
    private JButton kupButton;
    private JButton wsteczButton;
    private JScrollPane scrollPane;
    private JFrame parentFrame; // Dodajemy parentFrame, by przejść do poprzedniego ekranu
    private MainPageUser mainPageUserPanel; // Panel główny użytkownika (MainPageUser)

    public KoszykPanel(Koszyk koszyk, JFrame parentFrame, MainPageUser mainPageUserPanel) {
        this.koszyk = koszyk;
        this.parentFrame = parentFrame;
        this.mainPageUserPanel = mainPageUserPanel;

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(new Color(40, 40, 40)); // Ciemne tło dla całego panelu
        setBackgroundDefault(filmyPanel);
        setLayout(new BorderLayout());
        add(contentPane);

        initComponents();
        layoutComponents();
        updateKoszyk();
    }

    private void initComponents() {
        // Panel na filmy z przewijaniem
        filmyPanel = new JPanel();
        filmyPanel.setLayout(new BoxLayout(filmyPanel, BoxLayout.Y_AXIS));
        filmyPanel.setBackground(new Color(40, 40, 40)); // Tło panelu filmów na ciemno

        // ScrollPane dla filmów
        scrollPane = new JScrollPane(filmyPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Przyciski
        kupButton = new JButton("Kup");
        kupButton.setPreferredSize(new Dimension(150, 30));

        wsteczButton = new JButton("Wstecz");
        wsteczButton.setPreferredSize(new Dimension(150, 30));

        setPrimaryButtonStyle(kupButton);
        setPrimaryButtonStyle(wsteczButton);

        // Akcja dla przycisku „Wstecz”
        wsteczButton.addActionListener(e -> {
            // Przechodzi do panelu głównego (MainPageUser)
            parentFrame.setContentPane(mainPageUserPanel.getContentPane()); // Zmieniamy na MainPageUser
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        // Akcja dla przycisku „Kup”
        kupButton.addActionListener(e -> {
            if (koszyk.getFilmy().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Koszyk jest pusty!", "Błąd", JOptionPane.ERROR_MESSAGE);
            } else {
                // Przejdź do realizacji zakupu (możesz dodać logikę zakupu tutaj)
                JOptionPane.showMessageDialog(this, "Zakup dokonany!", "Sukces", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void layoutComponents() {
        // Panel na przyciski
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(40, 40, 40)); // Zmieniamy tło na ciemniejsze
        buttonPanel.add(kupButton);
        buttonPanel.add(wsteczButton);

        // Dodawanie komponentów do contentPane
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void updateKoszyk() {
        filmyPanel.removeAll();

        for (Film film : koszyk.getFilmy()) {
            JPanel filmPanel = createFilmPanel(film);
            filmyPanel.add(filmPanel);
            filmyPanel.add(Box.createRigidArea(new Dimension(0, 5))); // odstęp między filmami
        }

        // Jeśli koszyk jest pusty, wyświetl informację
        if (koszyk.getFilmy().isEmpty()) {
            JLabel emptyLabel = new JLabel("Koszyk jest pusty", SwingConstants.CENTER);
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            filmyPanel.add(emptyLabel);
        }

        filmyPanel.revalidate();
        filmyPanel.repaint();
    }

    private JPanel createFilmPanel(Film film) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        panel.setBackground(Color.WHITE);

        // Informacje o filmie
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.add(new JLabel(film.getTytul()));
        infoPanel.add(new JLabel("Cena: " + film.getCena() + " zł"));

        // Przycisk usuwania
        JButton usunButton = new JButton("Usuń");
        usunButton.addActionListener(e -> {
            koszyk.usunFilm(film);
            updateKoszyk();
        });

        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(usunButton, BorderLayout.EAST);

        return panel;
    }

    // Gettery dla przycisków
    public JButton getKupButton() {
        return kupButton;
    }

    public JButton getWsteczButton() {
        return wsteczButton;
    }

    // Getter dla contentPane
    public JPanel getContentPane() {
        return contentPane;
    }
}
