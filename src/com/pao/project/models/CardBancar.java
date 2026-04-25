package com.pao.project.models;

import com.pao.project.services.IOperatiiCitireService;

import java.util.Scanner;

public class CardBancar implements IOperatiiCitireService {
    private final int id;
    private String numeTitular;
    private String cvv;
    private String numar;
    private int lunaExp;
    private int anExp;

    public CardBancar(int id, String numeTitular, String cvv, String numar, int lunaExp, int anExp) {
        this.id = id;
        this.numeTitular = numeTitular;
        this.cvv = cvv;
        this.numar = numar;
        this.lunaExp = lunaExp;
        this.anExp = anExp;
    }

    public int getId() {
        return id;
    }

    public String getNumeTitular() {
        return numeTitular;
    }

    public void setNumeTitular(String numeTitular) {
        this.numeTitular = numeTitular;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getNumar() {
        return numar;
    }

    public void setNumar(String numar) {
        this.numar = numar;
    }

    public int getLunaExp() {
        return lunaExp;
    }

    public void setLunaExp(int lunaExp) {
        this.lunaExp = lunaExp;
    }

    public int getAnExp() {
        return anExp;
    }

    public void setAnExp(int anExp) {
        this.anExp = anExp;
    }

    public String toString(){
        String ascuns = "**** **** ****";
        if(this.numar != null){
            ascuns += this.numar.substring(this.numar.length() - 4);
        }
        else{
            ascuns += "????";
        }
        return "CardBancar [Titular: " + numeTitular + " | Numar: " + ascuns +
                " | Expira la: " + lunaExp + "/" + anExp+ "]";
    }

    public void citeste(Scanner in) {
        System.out.print("Nume titular: ");
        this.numeTitular = in.nextLine();
        System.out.print("CVV: ");
        this.cvv = in.nextLine();
        System.out.print("Numar card: ");
        this.numar = in.nextLine();
        System.out.print("Luna expirare: ");
        this.lunaExp = Integer.parseInt(in.nextLine());
        System.out.print("An expirare: ");
        this.anExp = Integer.parseInt(in.nextLine());
    }
}
