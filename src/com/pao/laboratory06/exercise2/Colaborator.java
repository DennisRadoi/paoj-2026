package com.pao.laboratory06.exercise2;

public abstract class Colaborator implements IOperatiiCitireService{
    protected String nume;
    protected String prenume;
    protected double venit;

    public Colaborator(){

    }

    public Colaborator(String nume, String prenume, double venit) {
        this.nume = nume;
        this.prenume = prenume;
        this.venit = venit;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public double getVenit() {
        return venit;
    }

    public void setVenit(double venit) {
        this.venit = venit;
    }

    public abstract double calculeazaVenitNetAnual();
    public abstract TipColaborator getTip();
}
