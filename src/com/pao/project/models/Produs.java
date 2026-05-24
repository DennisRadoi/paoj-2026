package com.pao.project.models;


import java.util.Objects;
import java.util.Scanner;

public class Produs implements Comparable<Produs> {
    protected String cod;
    protected String nume;
    protected String descriere;
    protected double pret;
    protected int restaurant_id;

    public Produs() {}
    public Produs(String cod, String nume, String descriere, double pret) {
        this.cod = cod;
        this.nume = nume;
        this.descriere = descriere;
        this.pret = pret;
    }

    public Produs(String nume, String descriere, double pret, int restaurant_id) {
        this.nume = nume;
        this.descriere = descriere;
        this.pret = pret;
        this.restaurant_id = restaurant_id;
    }

    public int getRestaurant() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getId() {
        return cod;
    }

    public void setId(String cod) {
        this.cod = cod;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produs produs = (Produs) o;
        return cod.equals(produs.cod);
    }
//    public abstract String getTip();

    public int hashCode() {
        return Objects.hashCode(getId());
    }

    public int compareTo(Produs altProdus) {
        return Double.compare(this.pret, altProdus.getPret());
    }

    public void citeste(Scanner in) {
        System.out.print("Nume produs: ");
        this.nume = in.nextLine();
        System.out.print("Descriere produs: ");
        this.descriere = in.nextLine();
        System.out.print("Pret produs: ");
        this.pret = Double.parseDouble(in.nextLine());
    }
}
