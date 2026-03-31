package com.pao.laboratory06.exercise3;

public class Inginer extends Angajat implements PlataOnline, Comparable<Inginer>{
    private double sold;
    private String user;
    private String parola;
    public Inginer(String nume, String prenume, String telefon, double salariu, double sold,
                   String user, String parola){
        super(nume, prenume, telefon, salariu);
        this.sold = sold;
        this.user = user;
        this.parola = parola;
    }
    public void autentificare(String user, String parola) {
        if (user == null || parola == null || user.isEmpty() || parola.isEmpty()) {
            throw new IllegalArgumentException("Argumente invalide");
        }
        if(this.user.equalsIgnoreCase(user) && this.parola.equalsIgnoreCase(parola))
            System.out.println("Inginer autentificat: " + user);
        else
            System.out.println("credentiale invalide");
    }

    public double consultareSold() {
        return this.sold;
    }

    @Override
    public boolean efectuarePlata(double suma) {
        if(this.sold < suma) return true;
        this.sold -= suma;
        return true;
    }

    public int compareTo(Inginer o) {
        return this.nume.compareTo(o.nume);
    }

    public String toString(){
        return "Nume complet: " + nume + " " + prenume + " Salariu: " + salariu;
    }
}
