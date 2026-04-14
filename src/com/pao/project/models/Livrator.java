package com.pao.project.models;

public class Livrator extends Utilizator {
    private String vehicul;
    private int nrComenzi;
    private boolean esteDisponibil;

    public Livrator(int id, String data_nasterii, String telefon, String email, int varsta, String nume, String vehicul, boolean esteDisponibil, int nrComenzi) {
        super(id, data_nasterii, telefon, email, varsta, nume);
        this.vehicul = vehicul;
        this.esteDisponibil = esteDisponibil;
        this.nrComenzi = nrComenzi;
    }

    public String getVehicul() {
        return vehicul;
    }

    public void setVehicul(String vehicul) {
        this.vehicul = vehicul;
    }

    public boolean isEsteDisponibil() {
        return esteDisponibil;
    }

    public void setEsteDisponibil(boolean esteDisponibil) {
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
}
