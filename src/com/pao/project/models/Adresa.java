package com.pao.project.models;

public final class Adresa {
    private final String judet;
    private final String localitate;
    private final String strada;
    private final int numar;
    private final String bloc;
    private final int scara;
    private final int apartament;

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


    public String getLocalitate() {
        return localitate;
    }


    public String getStrada() {
        return strada;
    }


    public int getNumar() {
        return numar;
    }


    public String getBloc() {
        return bloc;
    }


    public int getScara() {
        return scara;
    }


    public int getApartament() {
        return apartament;
    }


    @Override
    public String toString() {
        if (bloc == null) {
            return "jud. " + judet + ", loc. " + localitate + ", str. " + strada + ", nr. " + numar;
        }
        return "jud. " + judet + ", loc. " + localitate + ", str. " + strada + ", nr. " + numar
                + ", bl. " + bloc + ", sc. " + scara + ", ap. " + apartament;
    }

    public int hashCode() {
        return java.util.Objects.hash(judet, localitate, strada, numar);
    }
}

