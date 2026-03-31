//package com.pao.laboratory06.exercise3;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class PersoanaJuridica extends Persoana implements PlataOnlineSMS{
//    private List<String> smsTrimise;
//    public PersoanaJuridica(String nume, String prenume, String telefon, double salariu){
//        super(nume, prenume, telefon, salariu);
//        smsTrimise = new ArrayList<>();
//    }
//    public boolean trimiteSMS(String mesaj){
//        if(this.telefon == null || this.telefon.isEmpty()) return false;
//        this.smsTrimise.add(mesaj);
//        return true;
//    }
//}
