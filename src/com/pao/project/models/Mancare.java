package com.pao.project.models;

import java.util.Scanner;

public class Mancare extends Produs {
    private int gramaj;
    private boolean esteVegan;
    private boolean estePicant;
    private int nivelPicant;
    private int calorii;

    public Mancare(CodProdus cod, String nume, String descriere, double pret, int gramaj, boolean esteVegan, boolean estePicant, int nivelPicant, int calorii) {
        super(cod, nume, descriere, pret);
        this.gramaj = gramaj;
        this.esteVegan = esteVegan;
        this.estePicant = estePicant;
        this.nivelPicant = nivelPicant;
        this.calorii = calorii;
    }

    public int getGramaj() {
        return gramaj;
    }

    public void setGramaj(int gramaj) {
        this.gramaj = gramaj;
    }

    public boolean isEsteVegan() {
        return esteVegan;
    }

    public void setEsteVegan(boolean esteVegan) {
        this.esteVegan = esteVegan;
    }

    public boolean isEstePicant() {
        return estePicant;
    }

    public void setEstePicant(boolean estePicant) {
        this.estePicant = estePicant;
    }

    public int getNivelPicant() {
        return nivelPicant;
    }

    public void setNivelPicant(int nivelPicant) {
        this.nivelPicant = nivelPicant;
    }

    public int getCalorii() {
        return calorii;
    }

    public void setCalorii(int calorii) {
        this.calorii = calorii;
    }

    public String getTip(){
        return "MANCARE";
    }

    public String toString() {
        return nume + " are " + gramaj + " grame si " + calorii + " calorii";
    }

    public void citeste(Scanner in) {
        super.citeste(in);
        System.out.print("Gramaj: ");
        this.gramaj = Integer.parseInt(in.nextLine());
        System.out.print("Este vegan (true/false): ");
        this.esteVegan = Boolean.parseBoolean(in.nextLine());
        System.out.print("Este picant (true/false): ");
        this.estePicant = Boolean.parseBoolean(in.nextLine());
        System.out.print("Nivel picant: ");
        this.nivelPicant = Integer.parseInt(in.nextLine());
        System.out.print("Calorii: ");
        this.calorii = Integer.parseInt(in.nextLine());
    }
}
