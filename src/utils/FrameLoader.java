package utils;

import gui.LoginForm;
import gui.MainPageUser;
import gui.MojeKonto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameLoader {
    private JFrame frame;
    private LoginForm loginForm;
    private MainPageUser mainPageUser;

    public FrameLoader() {
        frame = new JFrame("Login Form");
        loginForm = new LoginForm();

        frame.setContentPane(loginForm.getContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null); // Wyśrodkowanie okna
        frame.setVisible(true);

        loginChecker();
    }

    private void loginChecker() {
        // bez watku bo byl useless
        Timer loginTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginForm.getLoginConfirmation()) {
                    ((Timer) e.getSource()).stop();  // Zatrzymaj timer, gdy użytkownik się zaloguje
                    switchToUserMainPage(); // Otwórz główną stronę po zalogowaniu
                }
            }
        });
        loginTimer.start();
    }

    private void switchToUserMainPage() {

        mainPageUser = new MainPageUser();
        MojeKonto mojeKonto = new MojeKonto();
        // zrobilem to jako przekazanie przycisku przez metode i poczekanie na akcje zwaizana z nim
        // uwzam ze jest to lepiej niz metoda ktora zwraaca czy przycisk zostal nacisniety

        mainPageUser.getMojeKontoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // utworzenie obiektu moje konto i podmiana
                MojeKonto mojeKontoUser = new MojeKonto();
                frame.setContentPane(mojeKontoUser.getContentPane());
                frame.revalidate();
                frame.repaint();
            }
        });

        // trzeba to naprawic zeby mozna bylo sie wyologwac
        mojeKonto.getWyologujButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // utworzenie obiektu moje konto i podmiana
                frame.setContentPane(loginForm.getContentPane());
                frame.revalidate();
                frame.repaint();
            }
        });

        // podmiana jframe
        frame.setContentPane(mainPageUser.getContentPane());
        frame.revalidate();
        frame.repaint();

    }

}
