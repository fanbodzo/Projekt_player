package gui;

import utils.ComponentStyle;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class AdminEditFilm implements ComponentStyle {
    private JPanel contentPane;
    private JButton wybierzFilmButton;
    private JTextField titleField;
    private JTextField descriptionField;
    private JTextField tagsField;
    private JButton zapiszButton;
    private JButton anulujButton;
    private JButton edytujOkladkeButton;
    private JLabel tytulLabel;
    private JLabel opisLabel;
    private JLabel tagiLabel;
    private JTextField edytujCeneField;
    private JLabel edytujCene;

    public AdminEditFilm() {
        setBackgroundDefault(contentPane);
        setPrimaryButtonStyle(wybierzFilmButton);
        setPrimaryButtonStyle(anulujButton);
        setPrimaryButtonStyle(zapiszButton);
        setButtonColor(edytujOkladkeButton, new Color(230, 110, 61));
        setTextFieldStyle(titleField);
        setTextFieldStyle(descriptionField);
        setTextFieldStyle(tagsField);
        setTextFieldStyle(edytujCeneField);
        setLabelStyle(tagiLabel);
        setLabelStyle(opisLabel);
        setLabelStyle(tytulLabel);
        setLabelStyle(edytujCene);

        edytowanieFilmuHandler();
    }

    public void edytowanieFilmuHandler() {
        final File[] selectedFile = {null}; // Plik okładki
        final File[] selectedFolder = {null}; // Wybrany folder z filmem (stary folder filmu)

        // Obsługa wyboru folderu filmu
        wybierzFilmButton.addActionListener(e -> {
            // Ścieżka do domyślnego folderu "Filmy"
            File defaultFolder = new File("Filmy");
            if (!defaultFolder.exists() || !defaultFolder.isDirectory()) {
                JOptionPane.showMessageDialog(contentPane, "Folder 'Filmy' nie istnieje w projekcie", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFileChooser folderChooser = new JFileChooser(defaultFolder); // Ustawiamy folder domyślny
            folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Tylko katalogi
            folderChooser.setDialogTitle("Wybierz folder filmu z katalogu 'Filmy'");

            int folderResult = folderChooser.showOpenDialog(contentPane);
            if (folderResult == JFileChooser.APPROVE_OPTION) {
                File chosenFolder = folderChooser.getSelectedFile();
                selectedFolder[0] = chosenFolder; // Ustawiamy wybrany folder
                wybierzFilmButton.setText("Wybrano film do zmiany: " + selectedFolder[0].getName());

                // Opcjonalnie, wczytaj obecne dane filmu do pól tekstowych
                try {
                    String currentTitle = new String(Files.readAllBytes(new File(chosenFolder, "tytul.txt").toPath())).trim();
                    String currentDescription = new String(Files.readAllBytes(new File(chosenFolder, "opis.txt").toPath())).trim();
                    String currentTags = new String(Files.readAllBytes(new File(chosenFolder, "tagi.txt").toPath())).trim();
                    String currentPrice = new String(Files.readAllBytes(new File(chosenFolder, "cena.txt").toPath())).trim();

                    titleField.setText(currentTitle);
                    descriptionField.setText(currentDescription);
                    tagsField.setText(currentTags);
                    edytujCeneField.setText(currentPrice);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(contentPane, "Wystąpił błąd podczas odczytu danych filmu.", "Błąd", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        // Obsługa nowej okładki
        edytujOkladkeButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Obrazy PNG i JPG", "png", "jpg", "jpeg");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(contentPane);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile[0] = fileChooser.getSelectedFile();
                setButtonColor(edytujOkladkeButton, new Color(103, 230, 61));
                edytujOkladkeButton.setText("Okładka wybrana do zamiany: " + selectedFile[0].getName());
            }
        });

        // Obsługa zapisywania zmian
        zapiszButton.addActionListener(e -> {
            // Sprawdzanie, czy wybrano folder filmu do edycji
            if (selectedFolder[0] == null) {
                JOptionPane.showMessageDialog(contentPane, "Najpierw wybierz film do edycji", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String title = titleField.getText().trim();
            String description = descriptionField.getText().trim();
            String tags = tagsField.getText().trim();
            String priceText = edytujCeneField.getText().trim();

            // Sprawdzanie, czy przynajmniej jedno pole zostało wypełnione lub wybrano nową okładkę
            if (title.isEmpty() && description.isEmpty() && tags.isEmpty() && priceText.isEmpty() && selectedFile[0] == null) {
                JOptionPane.showMessageDialog(contentPane, "Wprowadź przynajmniej jedną zmianę, aby zapisać", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Walidacja ceny, jeśli została wprowadzona
            double cena = 0.0;
            if (!priceText.isEmpty()) {
                try {
                    cena = Double.parseDouble(priceText);
                    if (cena < 0) {
                        throw new NumberFormatException("Cena nie może być ujemna");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(contentPane, "Wprowadź poprawną wartość ceny (liczbę dodatnią).", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            try {
                // Nadpisywanie plików: tytuł, opis, tagi, cena
                if (!title.isEmpty()) {
                    saveTextToFile(new File(selectedFolder[0], "tytul.txt"), title);
                }
                if (!description.isEmpty()) {
                    saveTextToFile(new File(selectedFolder[0], "opis.txt"), description);
                }
                if (!tags.isEmpty()) {
                    saveTextToFile(new File(selectedFolder[0], "tagi.txt"), tags);
                }
                if (!priceText.isEmpty()) {
                    saveTextToFile(new File(selectedFolder[0], "cena.txt"), String.valueOf(cena));
                }

                // Obsługa okładki
                if (selectedFile[0] != null) {

                    if (selectedFolder[0].exists() && selectedFolder[0].isDirectory()) {
                        File[] potentialCovers = selectedFolder[0].listFiles((dir, name) -> {
                            String lowerName = name.toLowerCase();
                            return lowerName.endsWith(".png") || lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg");
                        });
                        if (potentialCovers != null) {
                            for (File oldCover : potentialCovers) {
                                if (!oldCover.delete()) {
                                    JOptionPane.showMessageDialog(contentPane,
                                            "Nie udało się usunąć starej okładki: " + oldCover.getName(),
                                            "Błąd", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }


                    String baseName = (!title.isEmpty() ? title : selectedFolder[0].getName()).replaceAll("[\\\\/:*?\"<>|]", "_");
                    String newCoverExtension = getFileExtension(selectedFile[0].getName());
                    String newCoverName = baseName + "." + newCoverExtension;
                    File newCoverFile = new File(selectedFolder[0], newCoverName);
                    Files.copy(selectedFile[0].toPath(), newCoverFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                }


                if (!title.isEmpty()) {
                    File newFolder = new File(selectedFolder[0].getParent(), title.replaceAll("[\\\\/:*?\"<>|]", "_"));
                    if (!selectedFolder[0].equals(newFolder)) { // Jeśli nowa nazwa folderu jest inna niż poprzednia
                        if (selectedFolder[0].renameTo(newFolder)) {
                            selectedFolder[0] = newFolder; // Uaktualnij referencję do nowego folderu
                            wybierzFilmButton.setText("Wybrano film do zmiany: " + selectedFolder[0].getName());
                        } else {
                            JOptionPane.showMessageDialog(contentPane, "Nie udało się zmienić nazwy folderu", "Błąd", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

                JOptionPane.showMessageDialog(contentPane, "Zmiany zostały zapisane pomyślnie", "Sukces", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(contentPane, "Wystąpił błąd podczas zapisywania zmian", "Błąd", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }

    private void saveTextToFile(File file, String content) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
    }

    private String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < filename.length() - 1) {
            return filename.substring(lastDotIndex + 1).toLowerCase();
        }
        return "png";
    }

    public JButton getAnulujButton() {
        return anulujButton;
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public JButton getZapiszButton() {
        return zapiszButton;
    }
}
