package com.pao.project.models;

public class Adresa {
    private String judet;
    private String localitate;
    private String strada;
    private int numar;
    private String bloc;
    private int scara;
    private int apartament;

    public Adresa(String judet, String localitate, String strada, int numar, String bloc, int scara, int apartament) {
        this.judet = judet;
        this.localitate = localitate;
        this.strada = strada;
        this.numar = numar;
        this.bloc = bloc;
        this.scara = scara;
        this.apartament = apartament;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public String getLocalitate() {
        return localitate;
    }

    public void setLocalitate(String localitate) {
        this.localitate = localitate;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    public String getBloc() {
        return bloc;
    }

    public void setBloc(String bloc) {
        this.bloc = bloc;
    }

    public int getScara() {
        return scara;
    }

    public void setScara(int scara) {
        this.scara = scara;
    }

    public int getApartament() {
        return apartament;
    }

    public void setApartament(int apartament) {
        this.apartament = apartament;
    }

    @Override
    public String toString() {
        if (bloc == null) {
            return "jud. " + judet + ", loc. " + localitate + ", str. " + strada + ", nr. " + numar;
        }
        return "jud. " + judet + ", loc. " + localitate + ", str. " + strada + ", nr. " + numar
                + ", bl. " + bloc + ", sc. " + scara + ", ap. " + apartament;
    }
}

