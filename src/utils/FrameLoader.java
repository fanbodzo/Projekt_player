package utils;

import gui.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameLoader {
    private JFrame frame;
    private LoginForm loginForm;
    private MainPageUser mainPageUser;
    private MainPageAdmin mainPageAdmin;
    private MojeKonto mojeKonto;

    public FrameLoader() {
        frame = new JFrame("Login Form");
        loginForm = new LoginForm();

        // Dodanie obsługi przycisku rejestracji
        loginForm.getRegisterButton().addActionListener(e -> switchToRegisterForm());

        frame.setContentPane(loginForm.getContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        loginChecker();
    }

    private void switchToRegisterForm() {
        RegisterForm registerForm = new RegisterForm();

        // Obsługa przycisku powrotu
        registerForm.getBackButton().addActionListener(e -> {
            loginForm = new LoginForm();
            loginForm.getRegisterButton().addActionListener(ev -> switchToRegisterForm());
            frame.setContentPane(loginForm.getContentPane());
            frame.revalidate();
            frame.repaint();
            loginChecker();
        });

        frame.setContentPane(registerForm.getContentPane());
        frame.revalidate();
        frame.repaint();
    }

    private void loginChecker() {
        Timer loginTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginForm.getLoginConfirmation()) {
                    ((Timer) e.getSource()).stop();
                    if(loginForm.getAccountTypeLoggedIn()){
                        switchToAdminMainPage();
                    } else {
                        switchToUserMainPage();
                    }
                }
            }
        });
        loginTimer.start();
    }

    private void switchToUserMainPage() {
        mainPageUser = new MainPageUser();
        mojeKonto = new MojeKonto();

        mainPageUser.getMojeKontoButton().addActionListener(e -> {
            frame.setContentPane(mojeKonto.getContentPane());
            frame.revalidate();
            frame.repaint();
        });

        mojeKonto.getWyologujButton().addActionListener(e -> logout());
        mojeKonto.getPowrotButton().addActionListener(e -> backToMainPage());

        frame.setContentPane(mainPageUser.getContentPane());
        frame.revalidate();
        frame.repaint();
    }

    private void switchToAdminMainPage() {
        mainPageAdmin = new MainPageAdmin();
        mainPageAdmin.getWylogujButton().addActionListener(e -> logout());
        frame.setContentPane(mainPageAdmin.getContentPane());
        frame.revalidate();
        frame.repaint();
    }

    private void logout() {
        loginForm = new LoginForm();
        loginForm.getRegisterButton().addActionListener(e -> switchToRegisterForm());
        frame.setContentPane(loginForm.getContentPane());
        frame.revalidate();
        frame.repaint();
        loginChecker();
    }

    private void backToMainPage() {
        frame.setContentPane(mainPageUser.getContentPane());
        frame.revalidate();
        frame.repaint();
    }
}