package com.pao.laboratory06.exercise3;

public enum ConstanteFinanciare {
    TVA(0.19), SALARIU_MINIM(2600), COTA_IMPOZIT(300);
    private final double valoare;
    private ConstanteFinanciare(double valoare){
        this.valoare = valoare;
    }
    public double getValoare(){
        return valoare;
    }
}
