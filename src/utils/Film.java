package utils;

public class Film {
    private String tytul;
    private String sciezkaVideo;
    private String sciezkaIkony;
    private String opis;
    private String tagi;

    // Konstruktor
    public Film(String nazwa, String sciezkaVideo, String sciezkaIkony, String opis, String tagi) {
        this.tytul = nazwa;
        this.sciezkaVideo = sciezkaVideo;
        this.sciezkaIkony = sciezkaIkony;
        this.opis = opis;
        this.tagi = tagi;
    }

    // Gettery
    public String getTytul() {
        return tytul;
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
    public String getTagi() {
        return tagi;
    }

    // Prosta reprezentacja tekstowa
    @Override
    public String toString() {
        return "Film{" +
                "nazwa='" + tytul + '\'' +
                ", sciezkaVideo='" + sciezkaVideo + '\'' +
                ", sciezkaIkony='" + sciezkaIkony + '\'' +
                ", opis='" + opis + '\'' +
                '}';
    }
}