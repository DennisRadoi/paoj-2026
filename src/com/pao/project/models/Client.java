package com.pao.project.models;

import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Utilizator {
    private Adresa adresa;
    private ArrayList<CardBancar> listaCarduri;
    private int adresa_id;
    public Client(int id, String data_nasterii, String telefon, String email, int varsta, String nume, Adresa adresa) {
        super(id, data_nasterii, telefon, email, varsta, nume);
        this.adresa = adresa;
        this.listaCarduri = new ArrayList<>();
    }
    public Client() {}
    public int getAdresa() {
        return adresa_id;
    }

    public void setAdresa(int adresa) {
        this.adresa_id = adresa_id;
    }

    @Override
    public String toString() {
        return "Clientul " + id + " pe nume " + nume + " cu adresa de livrare: " + adresa;
    }

    public ArrayList<CardBancar> getListaCarduri() {
        return listaCarduri;
    }

    public void adaugaCard(CardBancar card) {
        listaCarduri.add(card);
    }

    public String getTipUtilizator() {
        return "CLIENT";
    }

    public void citeste(Scanner in) {
        super.citeste(in);
        if (this.adresa == null) {
            this.adresa = new Adresa();
        }
        this.adresa.citeste(in);
    }
}

