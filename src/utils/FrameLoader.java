package utils;

import gui.LoginForm;
import gui.MainPageAdmin;
import gui.MainPageUser;
import gui.MojeKonto;

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

    private void switchToUserMainPage() {
        mainPageUser = new MainPageUser();
        mojeKonto = new MojeKonto();

        // obsluga przycisku moje konto
        mainPageUser.getMojeKontoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(mojeKonto.getContentPane());
                frame.revalidate();
                frame.repaint();
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

        mainPageAdmin.getWylogujButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
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
