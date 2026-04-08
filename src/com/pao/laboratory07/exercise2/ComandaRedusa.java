package com.pao.laboratory07.exercise2;

import java.util.Locale;

public final class ComandaRedusa extends Comanda {
    private final int discountProcent;
    public ComandaRedusa(String nume, double pret, int d){
        super(nume, pret);
        this.discountProcent = d;
    }
    public double pretFinal(){
        return this.pret * (1 - discountProcent / 100.0);
    }
    public String descriere() {
        return String.format(Locale.US, "DISCOUNTED: %s, pret: %.2f lei (-%s%%) [%s]", nume, pretFinal(), discountProcent, o.name());
    }
}
