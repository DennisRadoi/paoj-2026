package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class SRLColaborator extends Colaborator implements PersoanaJuridica{
    private double cheltuieli;
    SRLColaborator(){
        super();
    }
    SRLColaborator(String nume, String prenume, double venit, double cheltuieli){
        super(nume, prenume, venit);
        this.cheltuieli = cheltuieli;
    }
    public double calculeazaVenitNetAnual(){
        return (this.venit - this.cheltuieli) * 12 * 0.84;
    }

    public void citeste(Scanner in) {
        this.nume = in.next();
        this.prenume = in.next();
        this.venit = in.nextDouble();
        this.cheltuieli = in.nextDouble();
    }

    public String tipContract() {
        return "SRL";
    }
    public void afiseaza() {
        String venitFormatat = String.format("%.2f", calculeazaVenitNetAnual());
        venitFormatat = venitFormatat.replace(',', '.');
        System.out.println(tipContract() + ": " + nume + " " + prenume + ", venit net anual: " + venitFormatat + " lei");
    }

    public TipColaborator getTip(){
        return TipColaborator.SRL;
    }
}
