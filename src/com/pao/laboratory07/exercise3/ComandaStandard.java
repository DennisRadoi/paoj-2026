package com.pao.laboratory07.exercise3;

import com.pao.laboratory07.exercise3.Comanda;

import java.util.Locale;

public final class ComandaStandard extends Comanda {
    public ComandaStandard(String nume, double p, String c){
        super(nume, p, c);
    }
    public double pretFinal(){
        return this.pret;
    }
    public String descriere() {
        return String.format(Locale.US, "STANDARD: %s, pret: %.2f lei [%s] - client: %s", nume, pretFinal(), o.name(), getClient());
    }
    public String getTip(){
        return "STANDARD";
    }
}
