package gui;

import utils.ComponentStyle;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AdminAddFilm implements ComponentStyle {
    private JPanel contentPane;
    private JTextField textField1;
    private JTextField textField2;
    private JButton dodajButton;
    private JButton anulujButton;
    private JTextField textField3;
    private JButton dodajOkladkeButton;

    public AdminAddFilm() {
        setBackgroundDefault(contentPane);
        setPrimaryButtonStyle(dodajButton);
        setPrimaryButtonStyle(anulujButton);
        setButtonColor(dodajOkladkeButton,new Color(230, 110, 61));

        final File[] selectedFile = {null};
        dodajOkladkeButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(contentPane);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile[0] = fileChooser.getSelectedFile();
                setButtonColor(dodajOkladkeButton,new Color(103, 230, 61));
                dodajOkladkeButton.setText("Wybrano: " + selectedFile[0].getName());
            }
        });
    }

    public JPanel getContentPane() {
        return contentPane;
    }
}
