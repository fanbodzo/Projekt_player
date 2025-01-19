package gui;

import utils.ComponentStyle;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class AdminAddFilm implements ComponentStyle {
    private JPanel contentPane;
    private JTextField titleField;
    private JTextField descriptionField;
    private JButton dodajButton;
    private JButton anulujButton;
    private JTextField tagsField;
    private JButton dodajOkladkeButton;

    public AdminAddFilm() {
        setBackgroundDefault(contentPane);
        setPrimaryButtonStyle(dodajButton);
        setPrimaryButtonStyle(anulujButton);
        setButtonColor(dodajOkladkeButton,new Color(230, 110, 61));

        dodawanieFilmuHandler();

    }

    public void dodawanieFilmuHandler(){
        final File[] selectedFile = {null};
        dodajOkladkeButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            //nie potrzeba nawet dzieki temu wyrzucania exception na zly typ pliku bo poprostu go nie wyswietla
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Obrazy PNG i JPG", "png", "jpg", "jpeg");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(contentPane);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile[0] = fileChooser.getSelectedFile();
                setButtonColor(dodajOkladkeButton,new Color(103, 230, 61));
                dodajOkladkeButton.setText("Wybrano: " + selectedFile[0].getName());
            }
        });

        // przucisk dodaj i jego akcje
        dodajButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String description = descriptionField.getText().trim();
            String tags = tagsField.getText().trim();

            if (title.isEmpty() || description.isEmpty() || tags.isEmpty() || selectedFile[0] == null) {
                JOptionPane.showMessageDialog(contentPane, "Wszystkie pola oraz plik muszą być wypełnione!", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // tworzenie fodleru na bazie tytulu
            File movieFolder = new File("Filmy/" + title.replaceAll("[\\\\/:*?\"<>|]", "_"));
            if (!movieFolder.exists()) {
                if (!movieFolder.mkdirs()) {
                    JOptionPane.showMessageDialog(contentPane, "Nie udało się utworzyć folderu!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            try {
                // zapis plikow
                saveTextToFile(new File(movieFolder, "tytul.txt"), title);
                saveTextToFile(new File(movieFolder, "opis.txt"), description);
                saveTextToFile(new File(movieFolder, "tagi.txt"), tags);

                // kopiowanie pliku okladki
                File coverFile = new File(movieFolder, selectedFile[0].getName());
                Files.copy(selectedFile[0].toPath(), coverFile.toPath());

                JOptionPane.showMessageDialog(contentPane, "Film został dodany pomyślnie!", "Sukces", JOptionPane.INFORMATION_MESSAGE);

                // czysczenie pol zeby mozna bylo dodac np 3 filmy po sobie bez wchodzenia i wychodzenia
                titleField.setText("");
                descriptionField.setText("");
                tagsField.setText("");
                dodajOkladkeButton.setBackground(new Color(230, 110, 61));
                dodajOkladkeButton.setText("Dodaj Okładkę");
                selectedFile[0] = null;

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(contentPane, "Wystąpił błąd podczas zapisywania plików!", "Błąd", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }

    // Metoda pomocnicza do zapisywania tekstu do pliku
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
