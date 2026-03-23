package com.pao.laboratory05.biblioteca;

public class Carte implements Comparable<Carte>{
    private String titlu;
    private String autor;
    private int an;
    private double rating;

    public Carte(String titlu, String autor, int an,  double rating) {
        this.autor = autor;
        this.an = an;
        this.titlu = titlu;
        this.rating = rating;
    }

    public String getTitlu() {
        return titlu;
    }

    public String getAutor() {
        return autor;
    }

    public double getRating() {
        return rating;
    }

    public int getAn() {
        return an;
    }

    public String toString() {
        return "Carte{" +
                "titlu='" + titlu + '\'' +
                ", autor='" + autor + '\'' +
                ", an=" + an +
                ", rating=" + rating +
                '}';
    }

    public int compareTo(Carte other) {
        return Double.compare(other.rating, this.rating);
    }
}
