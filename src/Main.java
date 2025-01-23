import users.User;
import utils.*;
import gui.*;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        UserLoader loader = new UserLoader();
        List<User> users = loader.loadUsersFromFile("data/users.txt");

        //pomocznicze wyswietlanie listy uzytkownikow
        /*for (User user : users) {
            System.out.println("Login: " + user.getLogin() + " " + "Haslo: " + user.getPassword());
        }*/

        // uruchamianie login forma
       FrameLoader frameLoader = new FrameLoader();

        // wczytanie filmow z folderu "Filmy"
        String folderFilmy = "Filmy";
        List<Film> filmy = FilmLoader.wczytajFilmy(folderFilmy);

        // wczytano jakiekolwiek filmy
        if (filmy.isEmpty()) {
            System.err.println("Nie znaleziono żadnych filmów w folderze: " + folderFilmy);
            return;
        }
        //ikony
        Map<Film, ImageIcon> mapowanieIkon = FilmLoader.utworzIkonkiFilmow(filmy);
        System.out.println("Załadowane ikonki:");
        for (Map.Entry<Film, ImageIcon> entry : mapowanieIkon.entrySet()) {
            System.out.println("Film: " + entry.getKey().getTytul() + ", Ikona: " + (entry.getValue() != null));
        }

    }
}


