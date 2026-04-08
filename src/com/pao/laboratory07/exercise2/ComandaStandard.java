package com.pao.laboratory07.exercise2;

import java.util.Locale;

public final class ComandaStandard extends Comanda{
    public ComandaStandard(String nume, double p){
        super(nume, p);
    }
    public double pretFinal(){
        return this.pret;
    }
    public String descriere() {
        return String.format(Locale.US, "STANDARD: %s, pret: %.2f lei [%s]", nume, pretFinal(), o.name());
    }
}
