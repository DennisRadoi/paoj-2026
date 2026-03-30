package com.pao.laboratory06.exercise3;

public class Inginer extends Angajat implements PlataOnline, Comparable<Inginer>{
    public Inginer(String nume, String prenume, String telefon, double salariu){
        super(nume, prenume, telefon, salariu);
    }
    public void autentificare(String user, String parola) {
        if (user == null || parola == null || user.isEmpty() || parola.isEmpty()) {
            throw new IllegalArgumentException("Argumente invalide");
        }
        System.out.println("Inginer autentificat: " + user);
    }

    public double consultareSold() {
        return this.salariu;
    }

    @Override
    public boolean efectuarePlata(double suma) {
        return this.salariu >= suma;
    }

    public int compareTo(Inginer o) {
        return this.nume.compareTo(o.nume);
    }
}
