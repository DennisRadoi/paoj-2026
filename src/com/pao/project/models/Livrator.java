package com.pao.project.models;

import java.util.Scanner;

public class Livrator extends Utilizator {
    private String vehicul;
    private int nrComenzi;
    private int esteDisponibil;

    public Livrator(String data_nasterii, String telefon, String email, int varsta, String nume, String vehicul, int esteDisponibil) {
        super(data_nasterii, telefon, email, varsta, nume);
        this.vehicul = vehicul;
        this.esteDisponibil = esteDisponibil;
    }
    public Livrator() {}
    public Livrator(String vehicul, int esteDisponibil) {
        this.vehicul = vehicul;
        this.esteDisponibil = esteDisponibil;
    }
    public String getVehicul() {
        return vehicul;
    }

    public void setVehicul(String vehicul) {
        this.vehicul = vehicul;
    }

    public int isEsteDisponibil() {
        return esteDisponibil;
    }

    public void setEsteDisponibil(int esteDisponibil) {
        this.esteDisponibil = esteDisponibil;
    }

    public int getNrComenzi() {
        return nrComenzi;
    }

    public void setNrComenzi(int nrComenzi) {
        this.nrComenzi = nrComenzi;
    }

    public String getTipUtilizator() {
        return "LIVRATOR";
    }

    @Override
    public String toString() {
        return "Livratorul " + id + " pe nume " + nume + " conduce un vehicul de tip " + vehicul + ", a efecutat " + nrComenzi +
                " comenzi ";
    }

    public void citeste(Scanner in) {
        super.citeste(in);
        System.out.print("Vehicul: ");
        this.vehicul = in.nextLine();
        System.out.print("Disponibil (true/false): ");
        System.out.print("Numar comenzi: ");
        this.nrComenzi = Integer.parseInt(in.nextLine());
    }
}
