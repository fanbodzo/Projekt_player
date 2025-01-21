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
    private AdminEditFilm editFilm;
    private Koszyk koszyk;
    private OrderCheckout orderCheckout;
    private PremiumStrefa premiumStrefa;
    private KupPremiumStrefa kupPremiumStrefa;

    public FrameLoader() {
        frame = new JFrame("Login Form");
        loginForm = new LoginForm();
        koszyk = new Koszyk(); // Inicjalizacja koszyka na początku

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

    private void switchToBiblioteka() {
        frame.setContentPane(biblioteka.getContentPane());
        frame.revalidate();
        frame.repaint();
    }

    private void switchToPremiumStrefa() {
        frame.setContentPane(premiumStrefa.getPremiumPanel());
        frame.revalidate();
        frame.repaint();
    }

    private void switchToKupPremiumStrefa() {
        frame.setContentPane(kupPremiumStrefa.getKupPremiumPanel());
        frame.revalidate();
        frame.repaint();
    }

    private void switchToKoszyk() {
        KoszykPanel koszykPanel = new KoszykPanel(koszyk, frame, mainPageUser);
        frame.setContentPane(koszykPanel.getContentPane());
        frame.revalidate();
        frame.repaint();
    }

    private void switchToUserMainPage() {
        mainPageUser = new MainPageUser();
        mojeKonto = new MojeKonto();
        biblioteka = new Biblioteka("Filmy", koszyk);
        orderCheckout = new OrderCheckout();
        premiumStrefa = new PremiumStrefa();
        kupPremiumStrefa = new KupPremiumStrefa();

        mainPageUser.getMojeKontoButton().addActionListener(e -> {
            frame.setContentPane(mojeKonto.getContentPane());
            frame.revalidate();
            frame.repaint();
        });

        mainPageUser.getBibliotekaButton().addActionListener(e -> switchToBiblioteka());

        biblioteka.getPowrotButton().addActionListener(e -> backToMainPage());

        mainPageUser.getKoszykButton().addActionListener(e -> switchToKoszyk());

        koszyk.getWsteczButton().addActionListener(e -> {
            frame.setContentPane(mainPageUser.getContentPane());
            frame.revalidate();
            frame.repaint();
        });

        koszyk.getKupButton().addActionListener(e -> {
            frame.setContentPane(orderCheckout.getContentPane());
            frame.revalidate();
            frame.repaint();
        });

        mojeKonto.getPremiumButton().addActionListener(e -> switchToPremiumStrefa());

        premiumStrefa.getKupPremiumButton().addActionListener(e -> switchToKupPremiumStrefa());

        mojeKonto.getWylogujButton().addActionListener(e -> logout());

        mojeKonto.getPowrotButton().addActionListener(e -> backToMainPage());

        frame.setContentPane(mainPageUser.getContentPane());
        frame.revalidate();
        frame.repaint();
    }

    private void switchToAdminMainPage() {
        mainPageAdmin = new MainPageAdmin();
        dodajFilm = new AdminAddFilm();
        editFilm = new AdminEditFilm();

        mainPageAdmin.getWylogujButton().addActionListener(e -> logout());

        mainPageAdmin.getDodajFilmButton().addActionListener(e -> {
            frame.setContentPane(dodajFilm.getContentPane());
            frame.revalidate();
            frame.repaint();
        });

        mainPageAdmin.getEdytujFilmButton().addActionListener(e -> {
            frame.setContentPane(editFilm.getContentPane());
            frame.revalidate();
            frame.repaint();
        });

        dodajFilm.getAnulujButton().addActionListener(e -> {
            frame.setContentPane(mainPageAdmin.getContentPane());
            frame.revalidate();
            frame.repaint();
        });

        editFilm.getAnulujButton().addActionListener(e -> {
            frame.setContentPane(mainPageAdmin.getContentPane());
            frame.revalidate();
            frame.repaint();
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

        loginChecker();
    }

    private void backToMainPage() {
        frame.setContentPane(mainPageUser.getContentPane());
        frame.revalidate();
        frame.repaint();
    }
}