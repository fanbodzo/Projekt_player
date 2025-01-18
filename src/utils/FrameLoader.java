package utils;

import gui.*;
import users.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameLoader {
    private JFrame frame;
    private LoginForm loginForm;
    private MainPageUser mainPageUser;
    private MainPageAdmin mainPageAdmin;
    private Biblioteka biblioteka;
    private MojeKonto mojeKonto;
    private AdminAddFilm dodajFilm;
    public FrameLoader() {
        frame = new JFrame("Login Form");
        loginForm = new LoginForm();

        // Dodanie obsługi przycisku rejestracji
        loginForm.getRegisterButton().addActionListener(e -> switchToRegisterForm());
        frame.setContentPane(loginForm.getContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null); // Wyśrodkowanie okna
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
        // bez watku bo byl useless
        Timer loginTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginForm.getLoginConfirmation()) {
                    ((Timer) e.getSource()).stop();  // Zatrzymaj timer, gdy użytkownik się zaloguje
                    if(loginForm.getAccountTypeLoggedIn()){
                        switchToAdminMainPage(); // admin main page
                    }else{
                        switchToUserMainPage(); // otwiera glowna strone dl auzytkownika po zalogowaniu
                    }

                }
            }
        });
        loginTimer.start();
    }

    private void switchToBiblioteka() {
        frame.setContentPane(biblioteka.getContentPane());
        frame.revalidate();
        frame.repaint();
    }

    private void switchToUserMainPage() {
        mainPageUser = new MainPageUser();
        mojeKonto = new MojeKonto();
        biblioteka = new Biblioteka();
        // obsluga przycisku moje konto
        mainPageUser.getMojeKontoButton().addActionListener(e -> {
            frame.setContentPane(mojeKonto.getContentPane());
            frame.revalidate();
            frame.repaint();
        });
        mainPageUser.getBibliotekaButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToBiblioteka();
            }
        });



        // Obsluga przyciskow , wylogowania i powrotu do glownejstrony
        mojeKonto.getWyologujButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });

        mojeKonto.getPowrotButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToMainPage();
            }
        });



        // pwodrot na strone glowna
        frame.setContentPane(mainPageUser.getContentPane());
        frame.revalidate();
        frame.repaint();

    }

    private void switchToAdminMainPage() {
        mainPageAdmin = new MainPageAdmin();
        dodajFilm = new AdminAddFilm();

        mainPageAdmin.getWylogujButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        mainPageAdmin.getDodajFilmButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(dodajFilm.getContentPane());
                frame.revalidate();
                frame.repaint();
            }
        });

        frame.setContentPane(mainPageAdmin.getContentPane());
        frame.revalidate();
        frame.repaint();
    }


    private void logout() {

        loginForm = new LoginForm();
        frame.setContentPane(loginForm.getContentPane());
        frame.revalidate();
        frame.repaint();

        //znowu wlaczamy logowanie jak sie wylogowalismy
        loginChecker();
    }

    private void backToMainPage(){
        frame.setContentPane(mainPageUser.getContentPane());
        frame.revalidate();
        frame.repaint();
    }

}