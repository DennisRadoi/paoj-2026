package com.pao.project.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Restaurant {
    private String nume;
    private Adresa adresa;
    private double rating;
    final private int id;
    private ArrayList<Produs> meniu;
    private HashMap<Utilizator, Integer> note;
    public Restaurant(String nume, Adresa adresa, double rating, int id){
        this.nume = nume;
        this.adresa = adresa;
        this.rating = rating;
        this.id = id;
        this.meniu = new ArrayList<>();
        this.note = new HashMap<>();
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

    public void adaugaNota(Utilizator u, int nota){
        note.put(u, nota);
    }

    public boolean areCinci(){
        int suma = 0;
        for(int nota : note.values()){
            suma += nota;
        }
        return suma / note.size() >= 5;
    }

    public long getNumarDeserturi() {
        return meniu.stream()
                .filter(p -> p instanceof Desert)
                .count();
    }


    @Override
    public String toString() {
        return "Restaurantul: " + nume + " din " + adresa + " (are " + meniu.size() + " produse in meniu)";
    }

    public boolean equals(Object o){
        if(o == null || o.getClass() != getClass()) return false;
        Restaurant r = (Restaurant) o;
        return getNume() == r.getNume();
    }

    public int hashCode() {
        return java.util.Objects.hash(nume);
    }
}

