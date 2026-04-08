package com.pao.laboratory07.exercise3;

import com.pao.laboratory07.exercise3.Comanda;

import java.util.Locale;

public final class ComandaRedusa extends Comanda {
    private final int discountProcent;
    public ComandaRedusa(String nume, double pret, String c, int d){
        super(nume, pret, c);
        this.discountProcent = d;
    }
    public double pretFinal(){
        return this.pret * (1 - discountProcent / 100.0);
    }
    public String descriere() {
        return String.format(Locale.US, "DISCOUNTED: %s, pret: %.2f lei (-%s%%) [%s] - client: %s", nume, pretFinal(), discountProcent, o.name(), getClient());
    }

    public int getDiscountProcent() {
        return discountProcent;
    }

    public String getTip(){
        return "DISCOUNTED";
    }
}
