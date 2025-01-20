package utils;

public class Film {
    private String tytul;
    private String sciezkaVideo;
    private String sciezkaIkony;
    private String opis;
    private String tagi;
    private double cena;

    // Konstruktor
    public Film(String nazwa, String sciezkaVideo, String sciezkaIkony, String opis, String tagi, double cena) {
        this.tytul = nazwa;
        this.sciezkaVideo = sciezkaVideo;
        this.sciezkaIkony = sciezkaIkony;
        this.opis = opis;
        this.tagi = tagi;
        this.cena = cena;
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
    public double getCena() { return cena; }

    // Prosta reprezentacja tekstowa
    @Override
    public String toString() {
        return "Film{" +
                "nazwa='" + tytul + '\'' +
                ", sciezkaVideo='" + sciezkaVideo + '\'' +
                ", sciezkaIkony='" + sciezkaIkony + '\'' +
                ", opis='" + opis + '\'' +
                ", tagi='" + tagi + '\'' +
                ", cena=" + cena +
                '}';
    }
}