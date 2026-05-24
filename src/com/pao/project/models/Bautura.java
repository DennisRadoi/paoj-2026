package com.pao.project.models;

import java.util.Scanner;

public class Bautura extends Produs{
    private int volumMl;
    private boolean esteCarbogazoasa;
    private boolean contineAlcool;
    private double procentAlcool;
    private boolean rece;

    public Bautura(CodProdus cod, String nume, String descriere, double pret, int volumMl, boolean esteCarbogazoasa, boolean contineAlcool, double procentAlcool, boolean rece) {
//        super(cod, nume, descriere, pret);
        this.volumMl = volumMl;
        this.esteCarbogazoasa = esteCarbogazoasa;
        this.contineAlcool = contineAlcool;
        this.procentAlcool = procentAlcool;
        this.rece = rece;
    }

    public int getVolumMl() {
        return volumMl;
    }

    public void setVolumMl(int volumMl) {
        this.volumMl = volumMl;
    }

    public boolean isEsteCarbogazoasa() {
        return esteCarbogazoasa;
    }

    public void setEsteCarbogazoasa(boolean esteCarbogazoasa) {
        this.esteCarbogazoasa = esteCarbogazoasa;
    }

    public boolean isContineAlcool() {
        return contineAlcool;
    }

    public void setContineAlcool(boolean contineAlcool) {
        this.contineAlcool = contineAlcool;
    }

    public double getProcentAlcool() {
        return procentAlcool;
    }

    public void setProcentAlcool(double procentAlcool) {
        this.procentAlcool = procentAlcool;
    }

    public boolean isRece() {
        return rece;
    }

    public void setRece(boolean rece) {
        this.rece = rece;
    }

    public String getTip() {
        return "BAUTURA";
    }

    public String toString(){
        return "Bautura " + nume + " are " + volumMl + " ml ";
    }

    public void citeste(Scanner in) {
        super.citeste(in);
        System.out.print("Volum ml: ");
        this.volumMl = Integer.parseInt(in.nextLine());
        System.out.print("Este carbogazoasa (true/false): ");
        this.esteCarbogazoasa = Boolean.parseBoolean(in.nextLine());
        System.out.print("Contine alcool (true/false): ");
        this.contineAlcool = Boolean.parseBoolean(in.nextLine());
        System.out.print("Procent alcool: ");
        this.procentAlcool = Double.parseDouble(in.nextLine());
        System.out.print("Este rece (true/false): ");
        this.rece = Boolean.parseBoolean(in.nextLine());
    }

}
