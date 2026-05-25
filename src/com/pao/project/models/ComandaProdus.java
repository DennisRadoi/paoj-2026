package com.pao.project.models;

public class ComandaProdus {
    private int id;
    private int comanda_id;
    private int produs_id;
    public ComandaProdus() {}
    public ComandaProdus(int comanda_id, int produs_id) {
        this.comanda_id = comanda_id;
        this.produs_id = produs_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComanda_id() {
        return comanda_id;
    }

    public void setComanda_id(int comanda_id) {
        this.comanda_id = comanda_id;
    }

    public int getProdus_id() {
        return produs_id;
    }

    public void setProdus_id(int produs_cod) {
        this.produs_id = produs_cod;
    }
}
