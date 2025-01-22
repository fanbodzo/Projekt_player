package gui;

import utils.ComponentStyle;
import utils.Koszyk;
import utils.Film;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class KoszykPodsumowanie extends JPanel implements ComponentStyle {
    private JFrame parentFrame;
    private Koszyk koszyk;
    private MainPageUser mainPageUserPanel;

    public KoszykPodsumowanie(Koszyk koszyk, JFrame parentFrame, MainPageUser mainPageUserPanel) {
        this.koszyk = koszyk;
        this.parentFrame = parentFrame;
        this.mainPageUserPanel = mainPageUserPanel;

        setLayout(new BorderLayout(10, 10));
        setBackgroundDefault(this);

        initComponents();
    }

    private void initComponents() {
        // Left panel - order summary
        JPanel summaryPanel = new JPanel(new BorderLayout(5, 5));
        setBackgroundDefault(summaryPanel);
        summaryPanel.setPreferredSize(new Dimension(300, 400));

        JTextPane orderDetailsPane = new JTextPane();
        orderDetailsPane.setContentType("text/html");
        orderDetailsPane.setText(generateOrderSummary());
        orderDetailsPane.setEditable(false);
        setEditorPaneStyle(orderDetailsPane);

        summaryPanel.add(new JScrollPane(orderDetailsPane), BorderLayout.CENTER);

        // Right panel - form
        JPanel formPanel = createFormPanel();

        // Bottom panel - purchase button
        JButton purchaseButton = new JButton("Sfinalizuj zakup");
        setPrimaryButtonStyle(purchaseButton);
        purchaseButton.addActionListener(e -> finalizePurchase());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        setBackgroundDefault(buttonPanel);
        buttonPanel.add(purchaseButton);

        add(summaryPanel, BorderLayout.WEST);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private String generateOrderSummary() {
        StringBuilder sb = new StringBuilder("<html>");
        double suma = 0;

        for (Film film : koszyk.getFilmy()) {
            sb.append(film.getTytul()).append("<br>");
            sb.append("Cena: ").append(String.format("%.2f zł", film.getCena())).append("<br><br>");
            suma += film.getCena();
        }

        sb.append("<b>Łączna kwota do zapłaty: ").append(String.format("%.2f zł</b>", suma));
        sb.append("</html>");
        return sb.toString();
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        setBackgroundDefault(formPanel);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField emailField = new JTextField(20);
        JTextField cardNumberField = new JTextField(20);
        JTextField nameField = new JTextField(20);
        JTextField streetField = new JTextField(20);
        JTextField houseNumberField = new JTextField(8);
        JTextField postalCodeField = new JTextField(8);
        JTextField cityField = new JTextField(15);
        JComboBox<String> countryComboBox = new JComboBox<>(new String[]{"Polska", "Niemcy", "Francja", "USA", "Inne"});

        setTextFieldStyle(emailField);
        setTextFieldStyle(cardNumberField);
        setTextFieldStyle(nameField);
        setTextFieldStyle(streetField);
        setTextFieldStyle(houseNumberField);
        setTextFieldStyle(postalCodeField);
        setTextFieldStyle(cityField);
        setComboBoxStyle(countryComboBox);

        addFormRow(formPanel, "Email kupującego:", emailField);
        addFormRow(formPanel, "Numer karty płatniczej:", cardNumberField);
        addFormRow(formPanel, "Imię i nazwisko:", nameField);
        addFormRow(formPanel, "Adres rozliczeniowy:", streetField);

        JPanel addressDetailsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        setBackgroundDefault(addressDetailsPanel);
        addressDetailsPanel.add(createLabeledField("Numer domu:", houseNumberField));
        addressDetailsPanel.add(createLabeledField("Kod pocztowy:", postalCodeField));
        addressDetailsPanel.add(createLabeledField("Miasto:", cityField));
        addressDetailsPanel.add(createLabeledField("Kraj:", countryComboBox));
        formPanel.add(addressDetailsPanel);

        return formPanel;
    }

    private void finalizePurchase() {
        JOptionPane.showMessageDialog(this, "Zakup zrealizowany pomyślnie!", "Sukces", JOptionPane.INFORMATION_MESSAGE);
        koszyk.getFilmy().clear();
        parentFrame.setContentPane(mainPageUserPanel.getContentPane());
        parentFrame.revalidate();
        parentFrame.repaint();
    }

    private void addFormRow(JPanel panel, String labelText, JComponent field) {
        JPanel row = new JPanel(new BorderLayout(5, 2));
        setBackgroundDefault(row);
        JLabel label = new JLabel(labelText);
        setLabelStyle(label);
        row.add(label, BorderLayout.NORTH);
        row.add(field, BorderLayout.CENTER);
        panel.add(row);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    private JPanel createLabeledField(String labelText, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(2, 2));
        setBackgroundDefault(panel);
        JLabel label = new JLabel(labelText);
        setLabelStyle(label);
        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }
}
