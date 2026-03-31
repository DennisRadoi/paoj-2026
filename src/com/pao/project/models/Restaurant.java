package com.pao.project.models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String nume;
    private Adresa adresa;
    private double rating;
    final private int id;
    private List<Produs> meniu;
    public Restaurant(String nume, Adresa adresa, double rating, int id){
        this.nume = nume;
        this.adresa = adresa;
        this.rating = rating;
        this.id = id;
        this.meniu = new ArrayList<>();
    }

    public List<Produs> getMeniu() {
        return meniu;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void adaugaProdusMeniu(Produs p){
        this.meniu.add(p);
    }

    @Override
    public String toString() {
        return "Restaurantul: " + nume + " din " + adresa + " (are " + meniu.size() + " produse in meniu)";
    }
}

