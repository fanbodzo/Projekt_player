package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public interface ComponentStyle {
    // Kolory podstawowe
    Color ButtonDefaultColor = new Color(0, 120, 215);
    Color BackgroundDefaultColor = new Color(43, 39, 39);
    Color OnPressColor = new Color(87, 98, 149, 255);
    Color TextFieldBackgroundColor = new Color(60, 63, 65);
    Color TextFieldFontColor = Color.WHITE;
    Color LabelFontColor = Color.WHITE;
    Color ScrollPaneBackgroundColor = new Color(60, 63, 65);
    Color EditorPaneBackgroundColor = new Color(165, 155, 155, 244);
    Color EditorPaneFontColor = Color.WHITE;
    Color ComboBoxBackgroundColor = new Color(60, 63, 65);
    Color ComboBoxFontColor = Color.WHITE;
    Color ComboBoxBorderColor = Color.GRAY;

    // Czcionki
    Font DefaultFont = new Font("Arial", Font.PLAIN, 14);
    Font BoldFont = new Font("Arial", Font.BOLD, 14);

    // Metoda do zaokrąglania przycisków
    default void roundButton(JButton button, Color backgroundColor) {
        if (button == null) return; // Sprawdzenie, czy przycisk nie jest null

        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);

        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color buttonColor = button.getModel().isPressed() ? OnPressColor : backgroundColor;

                int arcSize = 30;
                g2d.setColor(buttonColor);
                g2d.fillRoundRect(0, 0, button.getWidth(), button.getHeight(), arcSize, arcSize);

                super.paint(g2d, c);
                g2d.dispose();
            }

            @Override
            protected void installDefaults(AbstractButton b) {
                super.installDefaults(b);
                b.setForeground(Color.WHITE);
            }
        });

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.repaint();
            }
        });
    }

    // Metoda do stylizacji JTextField
    default void setTextFieldStyle(JTextField textField) {
        textField.setBackground(TextFieldBackgroundColor);
        textField.setForeground(TextFieldFontColor);
        textField.setCaretColor(TextFieldFontColor);
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        textField.setFont(DefaultFont);
    }

    // Metoda do stylizacji JLabel
    default void setLabelStyle(JLabel label) {
        label.setForeground(LabelFontColor);
        label.setFont(BoldFont);
    }

    // Metoda do stylizacji JScrollPane
    default void setScrollPaneStyle(JScrollPane scrollPane) {
        scrollPane.getViewport().setBackground(ScrollPaneBackgroundColor);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    // Metoda do stylizacji JEditorPane
    default void setEditorPaneStyle(JEditorPane editorPane) {
        editorPane.setForeground(Color.GRAY);
        editorPane.setBackground(EditorPaneBackgroundColor);
        editorPane.setCaretColor(EditorPaneFontColor);
        editorPane.setFont(DefaultFont);
    }

    // Metoda do stylizacji JComboBox
    default void setComboBoxStyle(JComboBox<?> comboBox) {
        comboBox.setBackground(ComboBoxBackgroundColor);
        comboBox.setForeground(ComboBoxFontColor);
        comboBox.setFont(DefaultFont);
        comboBox.setBorder(BorderFactory.createLineBorder(ComboBoxBorderColor));

        // Ustawienie rendererów, aby elementy listy miały odpowiedni styl
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected) {
                    label.setBackground(OnPressColor);
                } else {
                    label.setBackground(ComboBoxBackgroundColor);
                }
                label.setForeground(ComboBoxFontColor);
                label.setFont(DefaultFont);
                return label;
            }
        });
    }

    // Metoda do tworzenia obramowania
    default void createBorder(JComponent component) {
        int thickness = 10;
        int radius = 10;
        component.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, thickness),
                BorderFactory.createEmptyBorder(radius, radius, radius, radius)
        ));
    }

    // Metoda do ustawiania stylu przycisku
    default void setPrimaryButtonStyle(JButton button) {
        roundButton(button, ButtonDefaultColor);
    }

    // Metoda do ustawiania podstawowego tła panelu
    default void setBackgroundDefault(JPanel panel) {
        panel.setBackground(BackgroundDefaultColor);
    }

    // Metoda do ustawiania niestandardowego koloru przycisku
    default void setButtonColor(JButton button, Color color) {
        roundButton(button, color);
    }

    // Metoda do ustawiania niestandardowego tła panelu
    default void setBackgroundColor(JPanel panel, Color color) {
        panel.setBackground(color);
    }
}
