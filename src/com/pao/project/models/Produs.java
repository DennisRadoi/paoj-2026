package com.pao.project.models;

import java.util.Objects;

public abstract class Produs implements Comparable<Produs>{
    protected CodProdus cod;
    protected String nume;
    protected String descriere;
    protected double pret;

    public Produs(CodProdus cod, String nume, String descriere, double pret) {
        this.cod = cod;
        this.nume = nume;
        this.descriere = descriere;
        this.pret = pret;
    }

    public CodProdus getId() {
        return cod;
    }

    public void setId(CodProdus cod) {
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
        return getId() == produs.getId();
    }
    public abstract String getTip();

    public int hashCode() {
        return Objects.hashCode(getId());
    }

    public int compareTo(Produs altProdus) {
        return Double.compare(this.pret, altProdus.getPret());
    }
}
