package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;

public interface ComponentStyle {
    // Metoda do zaokrąglania przycisków
    default void roundButton(JButton button) {
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);

        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Tworzenie zaokrąglonego tła przycisku
                int arcSize = 30; // promień zaokrąglenia
                g2d.setColor(button.getBackground());
                g2d.fillRoundRect(0, 0, button.getWidth(), button.getHeight(), arcSize, arcSize);

                super.paint(g2d, c); // Rysowanie tekstu i ikony
                g2d.dispose();
            }

            @Override
            protected void installDefaults(AbstractButton b) {
                super.installDefaults(b);
                b.setBackground(new Color(0, 120, 215)); // Kolor domyślny tła przycisku
                b.setForeground(Color.WHITE); // Kolor tekstu
            }
        });
    }


    // Metoda do tworzenia obramowań
    default void createBorder(JComponent component, Color color, int thickness, int radius) {
        component.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, thickness),
                BorderFactory.createEmptyBorder(radius, radius, radius, radius)
        ));
    }

    // Metoda do zmiany koloru tła
    default void changeBackground(JPanel panel, Color color) {
        panel.setBackground(color);
    }

    // Metoda do logowania zamknięcia aplikacji
    default void logApplicationClose() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String logMessage = "Aplikacja została zamknięta: " + now.format(formatter) + "\n";

        try (FileWriter writer = new FileWriter("application_log.txt", true)) {
            writer.write(logMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}