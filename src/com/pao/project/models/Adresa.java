package com.pao.project.models;

import com.pao.project.services.IOperatiiCitireService;

import java.util.Objects;
import java.util.Scanner;

public class Adresa implements IOperatiiCitireService {
    private String judet;
    private String localitate;
    private String strada;
    private int numar;
    private String bloc;
    private int scara;
    private int apartament;

    public Adresa() {
    }

    public Adresa(String judet, String localitate, String strada, int numar, String bloc, int scara, int apartament) {
        if (judet == null || judet.isBlank()) {
            throw new IllegalArgumentException("judet nu poate fi null sau gol");
        }
        if (localitate == null || localitate.isBlank()) {
            throw new IllegalArgumentException("localitate nu poate fi null sau gol");
        }
        if (strada == null || strada.isBlank()) {
            throw new IllegalArgumentException("strada nu poate fi null sau gol");
        }

        this.judet = judet.trim();
        this.localitate = localitate.trim();
        this.strada = strada.trim();

        if (numar <= 0) {
            throw new IllegalArgumentException("numar trebuie sa fie > 0");
        }
        this.numar = numar;

        if (bloc == null || bloc.isBlank()) {
            this.bloc = null;
            this.scara = 0;
            this.apartament = 0;
        } else {
            this.bloc = bloc.trim();

            if (scara <= 0) {
                throw new IllegalArgumentException("scara trebuie sa fie > 0 cand bloc este prezent");
            }
            if (apartament <= 0) {
                throw new IllegalArgumentException("apartament trebuie sa fie > 0 cand bloc este prezent");
            }

            this.scara = scara;
            this.apartament = apartament;
        }
    }

    public String getJudet() {
        return judet;
    }

    public String getLocalitate() {
        return localitate;
    }

    public String getStrada() {
        return strada;
    }

    public int getNumar() {
        return numar;
    }

    public String getBloc() {
        return bloc;
    }

    public int getScara() {
        return scara;
    }

    public int getApartament() {
        return apartament;
    }

    @Override
    public String toString() {
        if (bloc == null) {
            return "jud. " + judet + ", loc. " + localitate + ", str. " + strada + ", nr. " + numar;
        }

        return "jud. " + judet + ", loc. " + localitate + ", str. " + strada + ", nr. " + numar
                + ", bl. " + bloc + ", sc. " + scara + ", ap. " + apartament;
    }

    @Override
    public int hashCode() {
        return Objects.hash(judet, localitate, strada, numar, bloc, scara, apartament);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adresa)) return false;
        Adresa adresa = (Adresa) o;
        return numar == adresa.numar
                && scara == adresa.scara
                && apartament == adresa.apartament
                && Objects.equals(judet, adresa.judet)
                && Objects.equals(localitate, adresa.localitate)
                && Objects.equals(strada, adresa.strada)
                && Objects.equals(bloc, adresa.bloc);
    }

    public void citeste(Scanner in) {
        System.out.print("Judet: ");
        this.judet = in.nextLine();

        System.out.print("Localitate: ");
        this.localitate = in.nextLine();

        System.out.print("Strada: ");
        this.strada = in.nextLine();

        System.out.print("Numar: ");
        this.numar = Integer.parseInt(in.nextLine());

        System.out.print("Bloc (gol daca nu exista): ");
        this.bloc = in.nextLine();

        if (this.bloc.isEmpty()) {
            this.bloc = null;
            this.scara = 0;
            this.apartament = 0;
        } else {
            System.out.print("Scara: ");
            this.scara = Integer.parseInt(in.nextLine());

            System.out.print("Apartament: ");
            this.apartament = Integer.parseInt(in.nextLine());
        }
    }
}