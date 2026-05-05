package com.pao.laboratory10.exercise1;

import java.util.Locale;
import java.util.Scanner;

public class Tranzactie {
    private int id;
    private double suma;
    private String data;
    private TipTranzactie tip;
    private String contSursa;

    public Tranzactie(int id, double suma, String data, TipTranzactie tip) {
        this.id = id;
        this.suma = suma;
        this.data = data;
        this.tip = tip;
    }

    public Tranzactie(int id, double suma, String data, TipTranzactie tip, String contSursa) {
        this.id = id;
        this.suma = suma;
        this.data = data;
        this.tip = tip;
        this.contSursa = contSursa;
    }

    public String getContSursa() {
        return contSursa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public TipTranzactie getTip() {
        return tip;
    }

    public void setTip(TipTranzactie tip) {
        this.tip = tip;
    }

    public String toString() {
        return String.format(Locale.US, "[%d] %s %s: %.2f RON", id, data, tip, suma);
    }

    public static Tranzactie citeste(Scanner s) {
        int id = s.nextInt();
        double suma = Double.parseDouble(s.next());
        String data = s.next();
        String tranzactie = s.next();
        return new Tranzactie(id, suma, data, TipTranzactie.valueOf(tranzactie));
    }
}
