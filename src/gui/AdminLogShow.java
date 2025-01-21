package gui;

import utils.ComponentStyle;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdminLogShow implements ComponentStyle {
    private JPanel contentPane;
    private JEditorPane editorPane1;
    private JButton powrot;
    private JScrollPane scrollPane;

    public AdminLogShow() {
        setBackgroundDefault(contentPane);
        setPrimaryButtonStyle(powrot);
        loadLogs();
    }

    private void loadLogs() {
        StringBuilder logContent = new StringBuilder();
        String filePath = "data/application_log.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                logContent.append(line).append("\n");
            }
            editorPane1.setText(logContent.toString());
        } catch (IOException e) {
            editorPane1.setText("Błąd podczas ładowania pliku logów.");
            e.printStackTrace();
        }
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public JEditorPane getEditorPane1() {
        return editorPane1;
    }

    public JButton getPowrot() {
        return powrot;
    }
}
