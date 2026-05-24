package com.pao.project.models;

import com.pao.project.exceptions.StareComandaInvalidaException;


import java.util.ArrayList;
import java.util.Scanner;

public class Comanda{
    private int id;
    private int client_id;
    private int restaurant_id;
    private int livrator;
    private ArrayList<Produs> produse;
    private double pretTotal;
    private String status;

    public Comanda() {}

    public Comanda(int client_id, int restaurant_id, int livrator, double pretTotal, String status) {
        this.client_id = client_id;
        this.restaurant_id = restaurant_id;
        this.livrator = livrator;
        this.status = status;
        this.pretTotal = pretTotal;

    }
    public void setId(int id) {this.id = id;}
    public int getId() {
        return id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient(int client_id) {
        this.client_id = client_id;
    }

    public int getRestaurant() {
        return restaurant_id;
    }

    public void setRestaurant(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public int getLivrator() {
        return livrator;
    }

    public void setLivrator(int livrator) {
        this.livrator = livrator;
    }

    public ArrayList<Produs> getProduse() {
        return produse;
    }

    public void setProduse(ArrayList<Produs> produse) {
        this.produse = produse;
    }

    public double getPretTotal() {
        return pretTotal;
    }

    public void setPretTotal(double pretTotal) {
        this.pretTotal = pretTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
//        if (status == null) {
//            throw new StareComandaInvalidaException("Statusul comenzii nu poate fi null.");
//        }
//        if (this.status != null && this.status.eFinal() && this.status != status) {
//            throw new StareComandaInvalidaException(
//                    "Comanda este deja intr-o stare finala (" + this.status + ") si nu mai poate fi modificata."
//            );
//        }
        this.status = status;
    }

    public void adaugaProdus(Produs p){
        this.produse.add(p);
        this.pretTotal += p.getPret();
    }

    public double calcTotalComanda(){
        double total = 0;
        for(Produs p : produse){
            total += p.getPret();
        }
        this.pretTotal = total;
        return total;
    }

//    public String toString() {
//        String numeLivrator = (livrator != null) ? livrator.getNume() : "Neasignat";
//        return "Comanda #" + id + " | Status: " + status +
//                " | Client: " + client.getNume() + " | Restaurant: " + restaurant_id.getNume() +
//                " | Livrator: " + numeLivrator + " | Total: " + pretTotal + " RON (" + produse.size() + " produse)";
//    }

    public void citeste(Scanner in) {
//        System.out.print("Pret total comanda: ");
//        this.pretTotal = Double.parseDouble(in.nextLine());
//        System.out.print("Status comanda (INITIALIZATA, IN_PREPARARE, IN_LIVRARE, LIVRATA, RETURNATA, ANULATA): ");
//        try {
//            this.status = String.valueOf(in.nextLine().trim().toUpperCase());
//        } catch (IllegalArgumentException e) {
//            throw new StareComandaInvalidaException("Status comanda invalid.");
//        }
    }
}
