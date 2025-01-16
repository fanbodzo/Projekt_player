package utils;

/**
 * Klasa modelująca dane filmów.
 */
public class Film {
    private String nazwa; // Tytuł filmu (nazwa katalogu)
    private String sciezkaVideo; // Ścieżka do pliku .mp4 (jeśli istnieje)
    private String sciezkaIkony; // Ścieżka do pliku graficznego (okładki)
    private String opis;

    public Film(String nazwa, String sciezkaVideo, String sciezkaIkony, String opis) {
        this.nazwa = nazwa;
        this.sciezkaVideo = sciezkaVideo;
        this.sciezkaIkony = sciezkaIkony;
        this.opis = opis;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getSciezkaVideo() {
        return sciezkaVideo;
    }

    public String getSciezkaIkony() {
        return sciezkaIkony;
    }

    public String getOpis() {
        return opis;
    }
    public boolean maFilm() {
        return sciezkaVideo != null && !sciezkaVideo.isBlank();
    }
}