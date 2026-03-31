package com.pao.project.models;

import java.util.ArrayList;

public class Client extends Utilizator {
    private Adresa adresa;
    private ArrayList<CardBancar> listaCarduri;
    public Client(int id, String data_nasterii, String telefon, String email, int varsta, String nume, Adresa adresa) {
        super(id, data_nasterii, telefon, email, varsta, nume);
        this.adresa = adresa;
        this.listaCarduri = new ArrayList<>();
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "Clientul " + id + " pe nume " + nume + " cu adresa de livrare: " + adresa;
    }

    public static void main(){
        Adresa a = new Adresa("sector2", "bucuresti", "pantelimon", 334, "130", 2, 30);
        Client c =  new Client(1, "26/03/2005",
                "0723423860", "da@gmail.com", 20, "dennis", a);
        System.out.println(c);
    }

}

