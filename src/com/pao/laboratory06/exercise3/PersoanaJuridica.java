package com.pao.laboratory06.exercise3;

import java.util.ArrayList;
import java.util.List;

public class PersoanaJuridica extends Persoana implements PlataOnlineSMS{
    private List<String> smsTrimise;
    private double sold;
    private String user;
    private String parola;
    public PersoanaJuridica(String nume, String prenume, String telefon, double sold,
                            String user, String parola){
        super(nume, prenume, telefon);
        this.sold = sold;
        this.user = user;
        this.parola = parola;
        smsTrimise = new ArrayList<>();
    }
    public boolean trimiteSMS(String mesaj){
        if(this.telefon == null || this.telefon.isEmpty()) return false;
        if (mesaj == null || mesaj.isEmpty()) return false;
        this.smsTrimise.add(mesaj);
        return true;
    }

    public List<String> getSmsTrimise() {
        return smsTrimise;
    }

    public double consultareSold() {
        return sold;
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

    public boolean efectuarePlata(double suma) {
        if(this.sold < suma) return true;
        this.sold -= suma;
        return true;
    }
}
