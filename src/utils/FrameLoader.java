package utils;

import gui.LoginForm;
import gui.MainPageUser;

import javax.swing.JFrame;

public class FrameLoader {
    private JFrame frame;
    private LoginForm loginForm;

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

    private void loginChecker (){
        //uzywamy nowego watku zeby przyspieszyc dzialanie kodu
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500); // Sprawdzanie co 500ms
                    if (loginForm.getLoginConfirmation()) {
                        switchToUserMainPage(); // Otwórz główną stronę po zalogowaniu
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void switchToUserMainPage() {

        MainPageUser mainPageUser = new MainPageUser();

        // podmiana jframe
        frame.setContentPane(mainPageUser.getContentPane());
        frame.revalidate();
        frame.repaint();
    }


//    public JFrame getFrame() {
//        return frame;
//    }
//
//    public LoginForm getLoginForm() {
//        return loginForm;
//    }
}
