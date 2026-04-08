package com.pao.laboratory07.exercise3;

import com.pao.laboratory07.exercise1.OrderState;
import com.pao.laboratory07.exercise3.ComandaGratuita;
import com.pao.laboratory07.exercise3.ComandaRedusa;
import com.pao.laboratory07.exercise3.ComandaStandard;

public abstract sealed class Comanda permits ComandaStandard, ComandaRedusa, ComandaGratuita {
    protected String nume;
    protected double pret;
    protected String client;
    protected OrderState o;
    public Comanda(String nume, double pret, String client){
        this.nume = nume;
        this.pret = pret;
        this.client = client;
        this.o = OrderState.PLACED;
    }
    public abstract double pretFinal();
    public abstract String descriere();
    public abstract String getTip();
    public String getClient(){
        return this.client;
    }
    public String getNume(){
        return this.nume;
    }
}
