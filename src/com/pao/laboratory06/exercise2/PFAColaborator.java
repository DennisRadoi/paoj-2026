package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class PFAColaborator extends Colaborator implements PersoanaFizica {
    private static final double salariuMIN = 4050 * 12;
    private double cheltuieli;
    PFAColaborator(){}
    PFAColaborator(String nume, String prenume, double venit, double cheltuieli){
        super(nume, prenume, venit);
        this.cheltuieli = cheltuieli;
    }
    public void citeste(Scanner in) {
        this.nume = in.next();
        this.prenume = in.next();
        this.venit = in.nextDouble();
        this.cheltuieli = in.nextDouble();
    }

    public String tipContract() {
        return "PFA";
    }

    public double calculeazaVenitNetAnual(){
        double venitNet = (this.venit - this.cheltuieli) * 12;
        double impozit = venitNet * 0.1;
        double CASS = 0;
        if(venit < 6 * salariuMIN){
            CASS = 0.1 * 6 * salariuMIN;
        }
        else if(venit >= 6 * salariuMIN && venit < 72 * salariuMIN){
            CASS = impozit;
        }
        else{
            CASS = 10 * 72 * salariuMIN;
        }
        double CAS = 0;
        if(venit < 12 * salariuMIN){
            CAS = 0;
        }
        else if(venit >= 12 * salariuMIN && venit <= 24 * salariuMIN){
            CAS = 0.25 * 12 * salariuMIN;
        }
        else{
            CAS = 0.25 * 24 * salariuMIN;
        }
        return venitNet - impozit - CASS - CAS;
    }
    public void afiseaza() {
        String venitFormatat = String.format("%.2f", calculeazaVenitNetAnual());
        venitFormatat = venitFormatat.replace(',', '.');
        System.out.println(tipContract() + ": " + nume + " " + prenume + ", venit net anual: " + venitFormatat + " lei");
    }
    public TipColaborator getTip(){
        return TipColaborator.PFA;
    }
}
