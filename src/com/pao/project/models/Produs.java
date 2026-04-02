package com.pao.project.models;

import java.util.Objects;

public class Produs implements Comparable<Produs>{
    private CodProdus cod;
    private String nume;
    private String tip;
    private String descriere;
    private double pret;

    public Produs(CodProdus cod, String nume, String tip, String descriere, double pret) {
        this.cod = cod;
        this.nume = nume;
        this.tip = tip;
        this.descriere = descriere;
        this.pret = pret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
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
        return getId() == produs.getId();
    }

    public int hashCode() {
        return Objects.hashCode(getId());
    }

    public int compareTo(Produs altProdus) {
        return Double.compare(this.pret, altProdus.getPret());
    }

    public String toString() {
        return "Produs #" + id + " | " + nume + " (" + tip + ") - " + pret + " RON";
    }
}
