package utils;

public class Film {
    private String nazwa;
    private String sciezkaVideo;
    private String sciezkaIkony;
    private String opis;

    // Konstruktor
    public Film(String nazwa, String sciezkaVideo, String sciezkaIkony, String opis) {
        this.nazwa = nazwa;
        this.sciezkaVideo = sciezkaVideo;
        this.sciezkaIkony = sciezkaIkony;
        this.opis = opis;
    }

    // Gettery
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

    // Prosta reprezentacja tekstowa
    @Override
    public String toString() {
        return "Film{" +
                "nazwa='" + nazwa + '\'' +
                ", sciezkaVideo='" + sciezkaVideo + '\'' +
                ", sciezkaIkony='" + sciezkaIkony + '\'' +
                ", opis='" + opis + '\'' +
                '}';
    }
}