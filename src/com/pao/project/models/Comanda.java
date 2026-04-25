package com.pao.project.models;

import com.pao.project.exceptions.StareComandaInvalidaException;
import com.pao.project.services.IOperatiiCitireService;

import java.util.ArrayList;
import java.util.Scanner;

public class Comanda implements IOperatiiCitireService {
    final private int id;
    private Client client;
    private Restaurant restaurant;
    private Livrator livrator;
    private ArrayList<Produs> produse;
    private double pretTotal;
    private StatusComanda status;

    public Comanda(int id, Client client, Restaurant restaurant, Livrator livrator, ArrayList<Produs> produse, StatusComanda status) {
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

    public int getId() {
        return id;
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

    public StatusComanda getStatus() {
        return status;
    }

    public void setStatus(StatusComanda status) {
        if (status == null) {
            throw new StareComandaInvalidaException("Statusul comenzii nu poate fi null.");
        }
        if (this.status != null && this.status.eFinal() && this.status != status) {
            throw new StareComandaInvalidaException(
                    "Comanda este deja intr-o stare finala (" + this.status + ") si nu mai poate fi modificata."
            );
        }
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

    public String toString() {
        String numeLivrator = (livrator != null) ? livrator.getNume() : "Neasignat";
        return "Comanda #" + id + " | Status: " + status +
                " | Client: " + client.getNume() + " | Restaurant: " + restaurant.getNume() +
                " | Livrator: " + numeLivrator + " | Total: " + pretTotal + " RON (" + produse.size() + " produse)";
    }

    public void citeste(Scanner in) {
        System.out.print("Pret total comanda: ");
        this.pretTotal = Double.parseDouble(in.nextLine());
        System.out.print("Status comanda (INITIALIZATA, IN_PREPARARE, IN_LIVRARE, LIVRATA, RETURNATA, ANULATA): ");
        try {
            this.status = StatusComanda.valueOf(in.nextLine().trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new StareComandaInvalidaException("Status comanda invalid.");
        }
    }
}
