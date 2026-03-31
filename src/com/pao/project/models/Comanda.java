package com.pao.project.models;

import java.util.ArrayList;

public class Comanda {
    final private int id;
    private Client client;
    private Restaurant restaurant;
    private Livrator livrator;
    private ArrayList<Produs> produse;
    private double pretTotal;
    private String status;

    public Comanda(int id, Client client, Restaurant restaurant, Livrator livrator, ArrayList<Produs> produse, String status) {
        this.id = id;
        this.client = client;
        this.restaurant = restaurant;
        this.livrator = livrator;
        this.produse = produse;
        this.status = status;
        this.pretTotal = 0;
        for (int i = 0; i < produse.size(); i++){
            this.pretTotal += produse.get(i).getPret();
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Livrator getLivrator() {
        return livrator;
    }

    public void setLivrator(Livrator livrator) {
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
        this.status = status;
    }

    public void adaugaProdus(Produs p){
        this.produse.add(p);
        this.pretTotal += p.getPret();
    }

    public String toString() {
        String numeLivrator = (livrator != null) ? livrator.getNume() : "Neasignat";
        return "Comanda #" + id + " | Status: " + status +
                " | Client: " + client.getNume() + " | Restaurant: " + restaurant.getNume() +
                " | Livrator: " + numeLivrator + " | Total: " + pretTotal + " RON (" + produse.size() + " produse)";
    }
}
