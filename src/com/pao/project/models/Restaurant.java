package com.pao.project.models;

import com.pao.project.services.IOperatiiCitireService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Restaurant implements IOperatiiCitireService {
    private String nume;
    private Adresa adresa;
    private double rating = 0;
    final private int id;
    private ArrayList<Produs> meniu;
    private HashMap<Utilizator, Integer> note;
    public Restaurant(String nume, Adresa adresa, int id, HashMap<Utilizator, Integer> note){
        this.nume = nume;
        this.adresa = adresa;
        this.id = id;
        this.meniu = new ArrayList<>();
        this.note = note;
        if(!note.isEmpty()){
            int suma = 0;
            for(int nota : note.values()) {
                suma += nota;
            }
            this.rating = (double) suma / note.size();
        }
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
        int suma = 0;
        for(int n : note.values()){
            suma += n;
        }
        this.rating = (double) suma / note.size();
    }

    public boolean areMediaCinci(){
        if(note.isEmpty()) return false;
        int suma = 0;
        for(int nota : note.values()){
            suma += nota;
        }
        return (double) suma / note.size() >= 5;
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
        return getNume().equalsIgnoreCase(r.getNume());
    }

    public int hashCode() {
        return java.util.Objects.hash(nume);
    }

    public void citeste(Scanner in) {
        System.out.print("Nume restaurant: ");
        this.nume = in.nextLine();
        System.out.print("Rating restaurant: ");
        this.rating = Double.parseDouble(in.nextLine());
    }
}

