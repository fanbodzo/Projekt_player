import users.User;
import utils.*;
import gui.*;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserLoader loader = new UserLoader();
        List<User> users = loader.loadUsersFromFile("data/users.txt");

        //Pomocznicze wyswietlanie listy uzytkownikow
        /*for (User user : users) {
            System.out.println("Login: " + user.getLogin() + " " + "Haslo: " + user.getPassword());
        }*/

        // uruchamianie login forma czyli tego gui co zrobilem
       FrameLoader frameLoader = new FrameLoader();

    }
}

