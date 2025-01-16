package utils;
import utils.Film;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilmLoader {

    /**
     * Wczytuje listę filmów (podfoldery w katalogu głównym).
     * - Szuka plików graficznych (np. okładki: .png, .jpg).
     * - Opcjonalnie sprawdza, czy w podfolderze znajduje się plik `.mp4` (film).
     */
    public static List<utils.Film> wczytajFilmy(String folderGlowny) {
        List<utils.Film> filmy = new ArrayList<>();
        File folder = new File(folderGlowny);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Folder " + folderGlowny + " nie istnieje lub nie jest katalogiem.");
            return filmy;
        }

        // Przeszukaj podfoldery w katalogu głównym
        File[] podfoldery = folder.listFiles(File::isDirectory);
        if (podfoldery == null) return filmy;

        for (File podfolder : podfoldery) {
            String nazwaFilmu = podfolder.getName(); // Nazwa filmu to nazwa podfolderu
            String sciezkaIkony = znajdzPlikZRozszerzeniem(podfolder, Arrays.asList(".png", ".jpg")); // Znalezienie okładki
            String sciezkaVideo = znajdzPlikZRozszerzeniem(podfolder, Arrays.asList(".mp4")); // Znalezienie filmu (jeśli istnieje)
            String opis = wczytajOpis(podfolder); // Wczytanie opisu filmu

            if (sciezkaIkony != null) { // Jeśli okładka istnieje
                filmy.add(new utils.Film(nazwaFilmu, sciezkaVideo, sciezkaIkony, opis));
            }
        }

        return filmy;
    }

    /**
     * Metoda pomocnicza: Znajdź pierwszy plik z podanym rozszerzeniem (np. .png, .jpg, .mp4) w folderze.
     */
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
        return null; // Brak pliku z podanym rozszerzeniem
    }

    /**
     * Metoda pomocnicza: Wczytaj opis filmu z pliku "opis.txt".
     * @param folder Folder, w którym szukamy pliku z opisem.
     * @return Zawartość pliku z opisem lub domyślny tekst, jeśli pliku nie znaleziono.
     */
    private static String wczytajOpis(File folder) {
        File opisFile = new File(folder, "opis.txt");
        if (!opisFile.exists()) {
            return "Brak opisu dla tego filmu.";
        }

        StringBuilder opis = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(opisFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                opis.append(line).append(" ");
            }
        } catch (IOException e) {
            System.err.println("Błąd podczas wczytywania opisu z pliku: " + opisFile.getPath());
            return "Nie udało się wczytać opisu.";
        }

        return opis.toString().trim(); // Zwrot zawartości pliku jako opisu
    }
}