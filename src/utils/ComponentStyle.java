package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public interface ComponentStyle {
    // kolory podstawowe
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

    // czcionki
    Font DefaultFont = new Font("Arial", Font.PLAIN, 14);
    Font BoldFont = new Font("Arial", Font.BOLD, 14);

    // metoda do zaokraglania przycisków
    default void roundButton(JButton button, Color backgroundColor) {
        if (button == null) return; //czy przycisk nie jest null

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

    //stylizacja JTextField
    default void setTextFieldStyle(JTextField textField) {
        textField.setBackground(TextFieldBackgroundColor);
        textField.setForeground(TextFieldFontColor);
        textField.setCaretColor(TextFieldFontColor);
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        textField.setFont(DefaultFont);
    }

    //stylizacja JLabel
    default void setLabelStyle(JLabel label) {
        label.setForeground(LabelFontColor);
        label.setFont(BoldFont);
    }

    //stylizacja JScrollPane
    default void setScrollPaneStyle(JScrollPane scrollPane) {
        scrollPane.getViewport().setBackground(ScrollPaneBackgroundColor);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    //stylizacja JEditorPane
    default void setEditorPaneStyle(JEditorPane editorPane) {
        editorPane.setForeground(Color.GRAY);
        editorPane.setBackground(EditorPaneBackgroundColor);
        editorPane.setCaretColor(EditorPaneFontColor);
        editorPane.setFont(DefaultFont);
    }

    //stylizacja JComboBox
    default void setComboBoxStyle(JComboBox<?> comboBox) {
        comboBox.setBackground(ComboBoxBackgroundColor);
        comboBox.setForeground(ComboBoxFontColor);
        comboBox.setFont(DefaultFont);
        comboBox.setBorder(BorderFactory.createLineBorder(ComboBoxBorderColor));

        // ustawienie rendererów, zeby elementy listy mialy odpowiedni styl
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

    //obramowania
    default void createBorder(JComponent component) {
        int thickness = 10;
        int radius = 10;
        component.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, thickness),
                BorderFactory.createEmptyBorder(radius, radius, radius, radius)
        ));
    }

    //ustawianie stylu przycisku
    default void setPrimaryButtonStyle(JButton button) {
        roundButton(button, ButtonDefaultColor);
    }

    //podstawowe tlo panelu
    default void setBackgroundDefault(JPanel panel) {
        panel.setBackground(BackgroundDefaultColor);
    }

    //niestandardowy kolor przycisku
    default void setButtonColor(JButton button, Color color) {
        roundButton(button, color);
    }

    //niestandardowe tlo panelu
    default void setBackgroundColor(JPanel panel, Color color) {
        panel.setBackground(color);
    }
}
