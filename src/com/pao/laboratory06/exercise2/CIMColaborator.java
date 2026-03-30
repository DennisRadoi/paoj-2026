package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class CIMColaborator extends Colaborator implements PersoanaFizica{
    private boolean bonus;
    CIMColaborator(){
        super();
    }
    CIMColaborator(String nume, String prenume, double venit){
        super(nume, prenume, venit);
    }
    public double calculeazaVenitNetAnual(){
        double anual = this.venit * 12 * 0.55;
        if(this.areBonus()){
            return anual + 0.10 * anual;
        }
        return anual;
    }
    public void citeste(Scanner in){
        this.nume = in.next();
        this.prenume = in.next();
        this.venit = in.nextDouble();
        String restulLiniei = in.nextLine().trim();
        if (restulLiniei.toUpperCase().contains("DA")) {
            this.bonus = true;
        } else {
            this.bonus = false;
        }
    }

    public String tipContract(){
        return "CIM";
    }

    public TipColaborator getTip(){
        return TipColaborator.CIM;
    }

    public void afiseaza() {
        String venitFormatat = String.format("%.2f", calculeazaVenitNetAnual());
        venitFormatat = venitFormatat.replace(',', '.');
        System.out.println(tipContract() + ": " + nume + " " + prenume + ", venit net anual: " + venitFormatat + " lei");
    }

    public boolean areBonus() {
        return this.bonus;
    }

}
