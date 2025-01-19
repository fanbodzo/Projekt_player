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

        //Pomocznicze wyswietlanie listy uzytkownikow
        /*for (User user : users) {
            System.out.println("Login: " + user.getLogin() + " " + "Haslo: " + user.getPassword());
        }*/

        // uruchamianie login forma czyli tego gui co zrobilem
       FrameLoader frameLoader = new FrameLoader();

        // Wczytaj filmy z katalogu "Filmy"
        String folderFilmy = "Filmy";
        List<Film> filmy = FilmLoader.wczytajFilmy(folderFilmy);

        // Sprawdź, czy wczytano jakiekolwiek filmy
        if (filmy.isEmpty()) {
            System.err.println("Nie znaleziono żadnych filmów w folderze: " + folderFilmy);
            return;
        }
        // Spróbuj załadować ikony
        Map<Film, ImageIcon> mapowanieIkon = FilmLoader.utworzIkonkiFilmow(filmy);
        System.out.println("Załadowane ikonki:");
        for (Map.Entry<Film, ImageIcon> entry : mapowanieIkon.entrySet()) {
            System.out.println("Film: " + entry.getKey().getNazwa() + ", Ikona: " + (entry.getValue() != null));
        }

    }
}

