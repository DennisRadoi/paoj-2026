package com.pao.project.models;

import com.pao.project.services.IOperatiiCitireService;

import java.util.Scanner;

public class Utilizator implements IOperatiiCitireService {
    protected int id;
    protected int varsta;
    protected String email;
    protected String data_nasterii;
    protected String telefon;
    protected String nume;

    public Utilizator() {}
    public Utilizator(int id, String data_nasterii, String telefon, String email, int varsta, String nume) {
        this.id = id;
        this.data_nasterii = data_nasterii;
        this.telefon = telefon;
        this.email = email;
        this.varsta = varsta;
        this.nume = nume;
    }
    public void setId(int id) {this.id = id;}
    public int getId(){
        return id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public String getEmail() {
        return email;
    }

    public String getData_nasterii() {
        return data_nasterii;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public void setData_nasterii(String data_nasterii) {
        this.data_nasterii = data_nasterii;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

//    public abstract String getTipUtilizator();

    public void citeste(Scanner in) {
        System.out.print("Nume: ");
        this.nume = in.nextLine();
        System.out.print("Data nasterii: ");
        this.data_nasterii = in.nextLine();
        System.out.print("Telefon: ");
        this.telefon = in.nextLine();
        System.out.print("Email: ");
        this.email = in.nextLine();
        System.out.print("Varsta: ");
        this.varsta = Integer.parseInt(in.nextLine());
    }

}
