package com.pao.laboratory07.exercise3;

import com.pao.laboratory07.exercise3.Comanda;

public final class ComandaGratuita extends Comanda {
    public ComandaGratuita(String nume, String c){
        super(nume, 0, c);
    }
    public double pretFinal(){
        return 0;
    }
    public String descriere() {
        return String.format("GIFT: %s, gratuit [%s] - client: %s", nume, o.name(), getClient());
    }
    public String getTip(){
        return "GIFT";
    }
}
