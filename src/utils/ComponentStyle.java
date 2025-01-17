package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//trzeba dodac komponent na kolor textboxow i dobrac inaczej kolory w apce
public interface ComponentStyle {
    // zmienne ktore pomagaja syzbciej edytowac kod
    Color ButtonDefaultColor = new Color(0, 120, 215);
    Color BackgroundDefaultColor = new Color(43, 39, 39);
    Color OnPressColor = new Color(87, 98, 149, 255);

    // Metoda do zaokrąglania przycisków
    default void roundButton(JButton button, Color backgroundColor) {
        button.setOpaque(false);
        button.setBorderPainted(false); // Wyłącza domyślne obramowanie
        button.setFocusPainted(false); // Wyłącza domyślny efekt fokusa
        button.setContentAreaFilled(false); // Umożliwia niestandardowe rysowanie tła

        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Kolor tla przycisku w zależności od stanu noramlny czy klikniety ale mzona dac na hoover
                //do przekmiineia
                Color buttonColor = button.getModel().isPressed() ? OnPressColor : ButtonDefaultColor;

                // Tworzenie zaokrąglonego tła przycisku
                int arcSize = 30; // Promień zaokrąglenia
                g2d.setColor(buttonColor); // Użycie odpowiedniego koloru tła
                g2d.fillRoundRect(0, 0, button.getWidth(), button.getHeight(), arcSize, arcSize);

                super.paint(g2d, c); // Rysowanie tekstu i ikony
                g2d.dispose();
            }

            @Override
            protected void installDefaults(AbstractButton b) {
                super.installDefaults(b);
                b.setForeground(Color.WHITE); // Kolor tekstu
            }
        });

        // Dodanie efektu klikniecia naprzycisk
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.repaint(); // odwiezenie do zaminy koloru
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.repaint();
            }
        });

    }

    // metoda do obramowan tez do ulatwienia zycia ale nie dziala dobrze wiec
    // to sei zrobi pozniej jzezli siebdzie bardzo chiualo komus bo narazie to zmienia button na kwadrat
    default void createBorder(JComponent component) {
        int thickness = 10;
        int radius = 10;
        component.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, thickness),
                BorderFactory.createEmptyBorder(radius, radius, radius, radius)
        ));

    }
    // metoda ktora daje nam kolor przycisku w jakby bardziej przejrzysty sposob do wykrozsytania oraz
    // izoluje nam roundbutton zeby on tylko zaokraglal
    // pozniej w projekcie jak np bedzimy mieli przycisk na kupo subskrypcji fajnei byy byl innego koloru poprsotu
    // i majac takie funkcje (bedziemy tworzyc nowe ) poporstu bedzie to czytalniej jak dla mn
    default void setPrimaryButtonStyle(JButton button) {
        roundButton(button, ButtonDefaultColor); // Niebieski
    }

    // metoda na podstawowe tlo tez do ulatwienia zycia
    default void setBackgroundDefault(JPanel panel) {
        panel.setBackground(BackgroundDefaultColor);
    }
    // ustawianie customowego koloru przycisku
    default void setButtonColor(JButton button, Color color) {
        roundButton(button ,color);
    }
    //customowe tlo
    default void setBackgroundColor(JPanel panel, Color color) {
        panel.setBackground(color);
    }

}