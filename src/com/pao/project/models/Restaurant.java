package com.pao.project.models;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Restaurant {
    private String nume;
    private Adresa adresa;
    private int adresa_id;
    private double rating = 0;
    private int id;

    public Restaurant(String nume, int adresa_id) {
        this.nume = nume;
        this.adresa_id = adresa_id;
    }
    public Restaurant() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getAdresa() {
        return adresa_id;
    }

    public void setAdresa(int adresa) {
        this.adresa_id = adresa;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }







    public String toString() {
        return "Restaurantul: " + nume + " din " + "adresa cu id " + adresa_id;
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

