package com.pao.project.models;

public class Desert extends Produs{
    private int gramaj;
    private boolean contineZahar;
    private boolean contineGluten;
    private boolean contineLactoza;
    private boolean esteRece;

    public Desert(CodProdus cod, String nume, String descriere, double pret, int gramaj, boolean contineZahar, boolean contineGluten, boolean contineLactoza, boolean esteRece) {
        super(cod, nume, descriere, pret);
        this.gramaj = gramaj;
        this.contineZahar = contineZahar;
        this.contineGluten = contineGluten;
        this.contineLactoza = contineLactoza;
        this.esteRece = esteRece;
    }

    public int getGramaj() {
        return gramaj;
    }

    public void setGramaj(int gramaj) {
        this.gramaj = gramaj;
    }

    public boolean isContineZahar() {
        return contineZahar;
    }

    public void setContineZahar(boolean contineZahar) {
        this.contineZahar = contineZahar;
    }

    public boolean isContineGluten() {
        return contineGluten;
    }

    public void setContineGluten(boolean contineGluten) {
        this.contineGluten = contineGluten;
    }

    public boolean isContineLactoza() {
        return contineLactoza;
    }

    public void setContineLactoza(boolean contineLactoza) {
        this.contineLactoza = contineLactoza;
    }

    public boolean isEsteRece() {
        return esteRece;
    }

    public void setEsteRece(boolean esteRece) {
        this.esteRece = esteRece;
    }

    public String getTip(){
        return "DESERT";
    }
}
