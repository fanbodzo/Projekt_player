package utils;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class FilmLoader {

    public static List<Film> wczytajFilmy(String folderFilmy) {
        List<Film> filmy = new ArrayList<>();
        File folder = new File(folderFilmy);

        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Folder '" + folderFilmy + "' nie istnieje lub nie jest katalogiem!");
            return filmy;
        }

        File[] podfoldery = folder.listFiles(File::isDirectory);
        if (podfoldery == null || podfoldery.length == 0) {
            System.err.println("Folder '" + folderFilmy + "' jest pusty!");
            return filmy;
        }

        for (File podfolder : podfoldery) {
            String nazwaFilmu = podfolder.getName();
            String sciezkaIkony = znajdzPlikZRozszerzeniem(podfolder, Arrays.asList(".png", ".jpg"));
            String sciezkaVideo = znajdzPlikZRozszerzeniem(podfolder, Arrays.asList(".mp4"));
            String tagi = znajdzPlikZRozszerzeniem(podfolder, Arrays.asList(".txt"));
            String opis = wczytajOpis(podfolder);

            // Domyślna cena dla filmów, możesz dostosować
            double cena = 19.99;

            if (sciezkaIkony != null) {
                filmy.add(new Film(nazwaFilmu, sciezkaVideo, sciezkaIkony, opis ,tagi, cena));
            } else {
                System.err.println("Brak pliku ikony dla filmu: " + nazwaFilmu);
            }
        }

        return filmy;
    }

    // Tworzy mapę z ikonami na podstawie listy filmów
    public static Map<Film, ImageIcon> utworzIkonkiFilmow(List<Film> filmy) {
        Map<Film, ImageIcon> mapowanieIkon = new HashMap<>(); // Mapa przechowująca filmy i ich ikonki

        for (Film film : filmy) {
            if (film.getSciezkaIkony() != null) {
                ImageIcon ikona = new ImageIcon(film.getSciezkaIkony());
                mapowanieIkon.put(film, ikona);
            } else {
                System.err.println("Brak pliku ikony dla filmu: " + film.getTytul());
            }
        }

        return mapowanieIkon;
    }

    private static String znajdzPlikZRozszerzeniem(File folder, List<String> rozszerzenia) {
        File[] pliki = folder.listFiles();
        if (pliki == null) return null;

        for (File plik : pliki) {
            for (String rozszerzenie : rozszerzenia) {
                if (plik.getName().toLowerCase().endsWith(rozszerzenie)) {
                    return plik.getAbsolutePath();
                }
            }
        }

        return null;
    }

    private static String wczytajOpis(File folder) {
        File opisFile = new File(folder, "opis.txt");

        if (!opisFile.exists()) {
            return "Brak opisu dla tego filmu.";
        }

        StringBuilder opis = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(opisFile))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                opis.append(linia).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Błąd podczas wczytywania opisu: " + e.getMessage());
            return "Nie udało się wczytać opisu.";
        }

        return opis.toString();
    }
}
