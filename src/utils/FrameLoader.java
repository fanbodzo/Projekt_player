package utils;

import gui.*;
import users.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FrameLoader implements LogManager { // Implementacja LogManager
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
    private String currentUsername;
    private AdminLogShow adminLogShow;

    public FrameLoader() {
        frame = new JFrame("Login Form");
        loginForm = new LoginForm();
        koszyk = new Koszyk(); // Inicjalizacja koszyka na początku

        // Dodanie obsługi przycisku rejestracji
        loginForm.getRegisterButton().addActionListener(new SwitchPanelAction(this::switchToRegisterForm, "Kliknięto przycisk Rejestracji"));
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
        registerForm.getBackButton().addActionListener(new SwitchPanelAction(() -> {
            loginForm = new LoginForm();
            loginForm.getRegisterButton().addActionListener(new SwitchPanelAction(this::switchToRegisterForm, "Kliknięto przycisk Rejestracji"));
            frame.setContentPane(loginForm.getContentPane());
            frame.revalidate();
            frame.repaint();
            loginChecker();
        }, "Kliknięto przycisk Powrotu z Rejestracji"));

        frame.setContentPane(registerForm.getContentPane());
        frame.revalidate();
        frame.repaint();

        logEvent("Przełączono na formularz rejestracji.");
    }

    private void loginChecker() {
        Timer loginTimer = new Timer(500, new LoginCheckerAction());
        loginTimer.start();
    }

    private class LoginCheckerAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (loginForm.getLoginConfirmation()) {
                ((Timer) e.getSource()).stop();
                currentUsername = loginForm.getUsername(); // Zakładam, że masz metodę getUsername()
                if (loginForm.getAccountTypeLoggedIn()) {
                    switchToAdminMainPage();
                } else {
                    switchToUserMainPage();
                }
                logEvent("Logowanie zakończone dla użytkownika: " + currentUsername);
            }
        }
    }


    private void switchToBiblioteka() {
        frame.setContentPane(biblioteka.getContentPane());
        frame.revalidate();
        frame.repaint();

        logEvent("Przełączono na bibliotekę filmów.");
    }

    private void switchToPremiumStrefa() {
        frame.setContentPane(premiumStrefa.getPremiumPanel());
        frame.revalidate();
        frame.repaint();

        logEvent("Przełączono na strefę premium.");
    }

    private void switchToKupPremiumStrefa() {
        frame.setContentPane(kupPremiumStrefa.getKupPremiumPanel());
        frame.revalidate();
        frame.repaint();

        logEvent("Przełączono na zakup strefy premium.");
    }

    private void switchToKoszyk() {
        KoszykPanel koszykPanel = new KoszykPanel(koszyk, frame, mainPageUser);
        frame.setContentPane(koszykPanel.getContentPane());
        frame.revalidate();
        frame.repaint();

        logEvent("Przełączono na koszyk.");
    }

    private void switchToUserMainPage() {
        mainPageUser = new MainPageUser();
        mojeKonto = new MojeKonto();
        biblioteka = new Biblioteka("Filmy", koszyk);
        orderCheckout = new OrderCheckout();
        premiumStrefa = new PremiumStrefa();
        kupPremiumStrefa = new KupPremiumStrefa();

        mainPageUser.getMojeKontoButton().addActionListener(new SwitchPanelAction(() -> {
            frame.setContentPane(mojeKonto.getContentPane());
            frame.revalidate();
            frame.repaint();
            logEvent("Przełączono na Moje Konto.");
        }, "Kliknięto przycisk Moje Konto"));

        mainPageUser.getBibliotekaButton().addActionListener(new SwitchPanelAction(this::switchToBiblioteka, "Kliknięto przycisk Biblioteka"));

        biblioteka.getPowrotButton().addActionListener(new SwitchPanelAction(this::backToMainPage, "Kliknięto przycisk Powrotu z Biblioteki"));

        mainPageUser.getKoszykButton().addActionListener(new SwitchPanelAction(this::switchToKoszyk, "Kliknięto przycisk Koszyk"));

        koszyk.getWsteczButton().addActionListener(new SwitchPanelAction(this::backToMainPage, "Kliknięto przycisk Wstecz w Koszyku"));

        koszyk.getKupButton().addActionListener(new SwitchPanelAction(() -> {
            frame.setContentPane(orderCheckout.getContentPane());
            frame.revalidate();
            frame.repaint();
            logEvent("Przełączono na zamówienie.");
        }, "Kliknięto przycisk Kup w Koszyku"));

        mojeKonto.getPremiumButton().addActionListener(new SwitchPanelAction(this::switchToPremiumStrefa, "Kliknięto przycisk Premium"));

        premiumStrefa.getKupPremiumButton().addActionListener(new SwitchPanelAction(this::switchToKupPremiumStrefa, "Kliknięto przycisk Kup Premium"));

        mojeKonto.getWylogujButton().addActionListener(new SwitchPanelAction(this::logout, "Kliknięto przycisk Wyloguj"));

        mojeKonto.getPowrotButton().addActionListener(new SwitchPanelAction(this::backToMainPage, "Kliknięto przycisk Powrotu z Mojego Konta"));

        frame.setContentPane(mainPageUser.getContentPane());
        frame.revalidate();
        frame.repaint();

        logEvent("Przełączono na główną stronę użytkownika.");
    }

    private void switchToAdminMainPage() {
        mainPageAdmin = new MainPageAdmin();
        dodajFilm = new AdminAddFilm();
        editFilm = new AdminEditFilm();
        adminLogShow = new AdminLogShow();

        mainPageAdmin.getWylogujButton().addActionListener(new SwitchPanelAction(this::logout, "Kliknięto przycisk Wyloguj jako Admin"));

        mainPageAdmin.getDodajFilmButton().addActionListener(new SwitchPanelAction(() -> {
            frame.setContentPane(dodajFilm.getContentPane());
            frame.revalidate();
            frame.repaint();
            logEvent("Przełączono na dodawanie filmu.");
        }, "Kliknięto przycisk Dodaj Film"));

        mainPageAdmin.getEdytujFilmButton().addActionListener(new SwitchPanelAction(() -> {
            frame.setContentPane(editFilm.getContentPane());
            frame.revalidate();
            frame.repaint();
            logEvent("Przełączono na edytowanie filmu.");
        }, "Kliknięto przycisk Edytuj Film"));
        mainPageAdmin.getWysweitlLogiButton().addActionListener(new SwitchPanelAction(() -> {
            frame.setContentPane(adminLogShow.getContentPane());
            frame.revalidate();
            frame.repaint();
            logEvent("Przełączono na wyświetlanie logow.");
        }, "Kliknięto przycisk wyświetl logi"));

        dodajFilm.getAnulujButton().addActionListener(new SwitchPanelAction(() -> {
            frame.setContentPane(mainPageAdmin.getContentPane());
            frame.revalidate();
            frame.repaint();
            logEvent("Anulowano dodawanie filmu, powrót na stronę admina.");
        }, "Kliknięto przycisk Anuluj dodawanie filmu"));

        dodajFilm.getDodajButton().addActionListener(new SwitchPanelAction(() -> {
            frame.setContentPane(mainPageAdmin.getContentPane());
            frame.revalidate();
            frame.repaint();
            logEvent("Dodano film, powrót na stronę admina.");
        }, "Kliknięto przycisk dodaj w dodawanie filmu"));

        editFilm.getAnulujButton().addActionListener(new SwitchPanelAction(() -> {
            frame.setContentPane(mainPageAdmin.getContentPane());
            frame.revalidate();
            frame.repaint();
            logEvent("Anulowano edytowanie filmu, powrót na stronę admina.");
        }, "Kliknięto przycisk Anuluj edytowanie filmu"));

        editFilm.getZapiszButton().addActionListener(new SwitchPanelAction(() -> {
            frame.setContentPane(mainPageAdmin.getContentPane());
            frame.revalidate();
            frame.repaint();
            logEvent("Zapisano edytowanie filmu, powrót na stronę admina.");
        }, "Kliknięto przycisk zapisz w edytowanie filmu"));

        adminLogShow.getPowrot().addActionListener(new SwitchPanelAction(() -> {
            frame.setContentPane(mainPageAdmin.getContentPane());
            frame.revalidate();
            frame.repaint();
        }, "Kliknięto przycisk powrot w wyswietl logi"));


        frame.setContentPane(mainPageAdmin.getContentPane());
        frame.revalidate();
        frame.repaint();

        logEvent("Przełączono na główną stronę administratora.");
    }

    private void logout() {
        loginForm = new LoginForm(); // Tworzenie nowej instancji LoginForm
        frame.setContentPane(loginForm.getContentPane());
        frame.revalidate();
        frame.repaint();

        logEvent("Użytkownik wylogował się: " + currentUsername);
        currentUsername = null; // Resetowanie nazwy użytkownika
        loginChecker();
    }

    private void backToMainPage() {
        frame.setContentPane(mainPageUser.getContentPane());
        frame.revalidate();
        frame.repaint();

        logEvent("Przełączono na główną stronę użytkownika.");
    }

    // Klasa wewnętrzna do obsługi przełączania paneli z logowaniem akcji
    private class SwitchPanelAction implements ActionListener {
        private Runnable action;
        private String logMessage;

        public SwitchPanelAction(Runnable action, String logMessage) {
            this.action = action;
            this.logMessage = logMessage;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            action.run();
            logEvent(logMessage);
        }
    }

    @Override
    public void logEvent(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String logMessage = now.format(formatter) + " - [" + (currentUsername != null ? currentUsername : "N/A") + "] - " + message + "\n";

        // Ścieżka do folderu `data`
        String logDirPath = "data";
        String logFilePath = logDirPath + "/application_log.txt";

        // Utwórz folder `data`, jeśli nie istnieje
        File logDir = new File(logDirPath);
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(logFilePath, true)) {
            writer.write(logMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
