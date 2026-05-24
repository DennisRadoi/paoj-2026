package com.pao.project.models;

public class ComandaProdus {
    private int id;
    private int comanda_id;
    private String produs_cod;
    public ComandaProdus() {}
    public ComandaProdus(int comanda_id, String produs_cod) {
        this.comanda_id = comanda_id;
        this.produs_cod = produs_cod;
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

    public String getProdus_cod() {
        return produs_cod;
    }

    public void setProdus_cod(String produs_cod) {
        this.produs_cod = produs_cod;
    }
}
