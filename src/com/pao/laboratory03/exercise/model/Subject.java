package com.pao.laboratory03.exercise.model;

public enum Subject {
    PAOJ("Programare avansata pe obiecte", 6),
    BD("Baze de date", 5),
    SO("Sisteme de operare", 4),
    RC("Retele de calculatoare", 4);

    private String fullname;
    private int nrCredite;

    private Subject(String fullname, int c){
        this.fullname = fullname;
        this.nrCredite = c;
    }

    public String getFullname() {
        return fullname;
    }

    public int getNrCredite() {
        return nrCredite;
    }

}
