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

    public AdminEditFilm() {
        setBackgroundDefault(contentPane);
        setPrimaryButtonStyle(wybierzFilmButton);
        setPrimaryButtonStyle(anulujButton);
        setPrimaryButtonStyle(zapiszButton);
        setButtonColor(edytujOkladkeButton,new Color(230, 110, 61));

        edytowanieFilmuHandler();
    }

    public void edytowanieFilmuHandler(){
        final File[] selectedFile = {null}; // Plik okładki
        final File[] selectedFolder = {null}; // Wybrany folder z filmem (stary folder filmu)

        // Obsługa wyboru folderu filmu
        wybierzFilmButton.addActionListener(e -> {
            // Ścieżka do domyślnego folderu "Filmy"
            File defaultFolder = new File("Filmy");
            if (!defaultFolder.exists() || !defaultFolder.isDirectory()) {
                JOptionPane.showMessageDialog(contentPane, "Folder 'Filmy' nie istnieje w projekice", "Błąd", JOptionPane.ERROR_MESSAGE);
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
            }
        });

        // obslugiwaniej nowej okladki
        edytujOkladkeButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Obrazy PNG i JPG", "png", "jpg", "jpeg");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(contentPane);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile[0] = fileChooser.getSelectedFile();
                setButtonColor(edytujOkladkeButton, new Color(103, 230, 61)); // Zmiana koloru przycisku po wybraniu pliku
                edytujOkladkeButton.setText("okladka wybrana do zamiany: " + selectedFile[0].getName());
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

            // Sprawdzanie, czy przynajmniej jedno pole zostało wypełnione lub wybrano nową okładkę
            if (title.isEmpty() && description.isEmpty() && tags.isEmpty() && selectedFile[0] == null) {
                JOptionPane.showMessageDialog(contentPane, "Wprowadź przynajmniej jedną zmianę, aby zapisać", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Nadpisywanie plików: tytuł, opis, tagi
                if (!title.isEmpty()) {
                    saveTextToFile(new File(selectedFolder[0], "tytul.txt"), title);
                }
                if (!description.isEmpty()) {
                    saveTextToFile(new File(selectedFolder[0], "opis.txt"), description);
                }
                if (!tags.isEmpty()) {
                    saveTextToFile(new File(selectedFolder[0], "tagi.txt"), tags);
                }

                // Obsługa okładki
                if (selectedFile[0] != null) {
                    // Usuń istniejącą okładkę w formacie PNG
                    if (selectedFile[0].exists()) {
                        if (selectedFolder[0].exists() && selectedFolder[0].isDirectory()) {
                            File[] potentialPngCovers = selectedFolder[0].listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
                            if (potentialPngCovers != null) {
                                for (File oldCover : potentialPngCovers) {
                                    if (!oldCover.delete()) {
                                        JOptionPane.showMessageDialog(contentPane,
                                                "Nie udało się usunąć starej okładki: " + oldCover.getName(),
                                                "Błąd", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        }
                    }

                    // Zapisz nową okładkę z nazwą opartą na tytule (lub stara nazwa folderu, jeśli brak nowego tytułu)
                    String newCoverName = (!title.isEmpty() ? title : selectedFolder[0].getName()).replaceAll("[\\\\/:*?\"<>|]", "_") + ".png";
                    File newCoverFile = new File(selectedFolder[0], newCoverName);
                    Files.copy(selectedFile[0].toPath(), newCoverFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                }

                // Zmiana nazwy folderu filmu, jeśli użytkownik zmienił tytuł
                if (!title.isEmpty()) {
                    File newFolder = new File(selectedFolder[0].getParent(), title.replaceAll("[\\\\/:*?\"<>|]", "_"));
                    if (!selectedFolder[0].equals(newFolder)) { // Jeśli nowa nazwa folderu jest inna niż poprzednia
                        if (selectedFolder[0].renameTo(newFolder)) {
                            selectedFolder[0] = newFolder; // Uaktualnij referencję do nowego folderu
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

    public JButton getAnulujButton() {
        return anulujButton;
    }

    public JPanel getContentPane() {
        return contentPane;
    }
}
