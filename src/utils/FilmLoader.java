package utils;
import utils.Film;
import java.io.File;
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

            if (sciezkaIkony != null) { // Jeśli okładka istnieje
                filmy.add(new utils.Film(nazwaFilmu, sciezkaVideo, sciezkaIkony));
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
}