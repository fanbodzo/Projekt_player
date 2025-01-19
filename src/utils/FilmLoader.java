package utils;

import javax.swing.*;
import java.io.*;
import java.util.*;

// Klasa odpowiedzialna za wczytywanie danych z katalogu "Filmy"
public class FilmLoader {

    // Wczytuje filmy z podanego katalogu
    public static List<Film> wczytajFilmy(String folderFilmy) {
        List<Film> filmy = new ArrayList<>();
        File folder = new File(folderFilmy); // użycie dynamicznej ścieżki zamiast DOMYSLNY_FOLDER

        // Sprawdź, czy folder istnieje i jest katalogiem
        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Folder '" + folderFilmy + "' nie istnieje lub nie jest katalogiem!");
            return filmy;
        }

        // Znajdź wszystkie podfoldery w katalogu
        File[] podfoldery = folder.listFiles(File::isDirectory);
        if (podfoldery == null || podfoldery.length == 0) {
            System.err.println("Folder '" + folderFilmy + "' jest pusty!");
            return filmy;
        }

        // Iteruj przez podfoldery reprezentujące filmy
        for (File podfolder : podfoldery) {
            String nazwaFilmu = podfolder.getName(); // nazwa filmu to nazwa podfolderu
            String sciezkaIkony = znajdzPlikZRozszerzeniem(podfolder, Arrays.asList(".png", ".jpg"));
            String sciezkaVideo = znajdzPlikZRozszerzeniem(podfolder, Arrays.asList(".mp4"));
            String opis = wczytajOpis(podfolder);

            // Aby dodać film, musi mieć co najmniej ścieżkę ikony
            if (sciezkaIkony != null) {
                // Tworzymy obiekt reprezentujący film i dodajemy go do listy
                filmy.add(new Film(nazwaFilmu, sciezkaVideo, sciezkaIkony, opis));
            } else {
                System.err.println("Podfolder '" + podfolder.getName() + "' nie zawiera pliku .jpg lub .png. Film pominięty.");
            }
        }

        return filmy; // Zwracamy listę filmów
    }

    // Tworzy mapę z ikonami na podstawie listy filmów
    public static Map<Film, ImageIcon> utworzIkonkiFilmow(List<Film> filmy) {
        Map<Film, ImageIcon> mapowanieIkon = new HashMap<>(); // Mapa przechowująca filmy i ich ikonki

        for (Film film : filmy) {
            if (film.getSciezkaIkony() != null) {
                ImageIcon ikona = new ImageIcon(film.getSciezkaIkony());
                mapowanieIkon.put(film, ikona);
            } else {
                System.err.println("Brak pliku ikony dla filmu: " + film.getNazwa());
            }
        }

        return mapowanieIkon;
    }

    // Znajduje plik o podanym rozszerzeniu w folderze
    private static String znajdzPlikZRozszerzeniem(File folder, List<String> rozszerzenia) {
        File[] pliki = folder.listFiles(); // pobieramy wszystkie pliki w folderze
        if (pliki == null) return null;

        for (File plik : pliki) {
            for (String rozszerzenie : rozszerzenia) {
                if (plik.getName().toLowerCase().endsWith(rozszerzenie)) {
                    return plik.getAbsolutePath(); // zwracamy pełną ścieżkę
                }
            }
        }

        return null; // jeśli nie znaleziono pliku o podanym rozszerzeniu
    }

    // Wczytuje opis filmu z pliku opis.txt
    private static String wczytajOpis(File folder) {
        File opisFile = new File(folder, "opis.txt"); // szukamy pliku opis.txt w folderze

        if (!opisFile.exists()) {
            return "Brak opisu dla tego filmu."; // brak pliku -> zwracamy domyślny tekst
        }

        // Wczytujemy dane z pliku opis.txt
        StringBuilder opis = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(opisFile))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                opis.append(linia).append("\n"); // dodajemy każdą linię opisu
            }
        } catch (IOException e) {
            System.err.println("Błąd podczas wczytywania opisu: " + e.getMessage());
            return "Nie udało się wczytać opisu.";
        }

        return opis.toString(); // zwracamy opis jako String
    }
}