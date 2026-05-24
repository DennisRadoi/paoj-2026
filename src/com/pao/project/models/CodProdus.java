package com.pao.project.models;

import com.pao.project.exceptions.CodProdusInvalidException;


import java.util.Objects;
import java.util.Scanner;

public final class CodProdus {
    private final String cod;
    public CodProdus(String _cod){
        if (_cod == null || _cod.trim().isEmpty()) {
            throw new CodProdusInvalidException("Codul produsului nu poate fi gol!");
        }
        if(_cod.length() < 5){
            throw new CodProdusInvalidException("Lungimea codului trebuie sa aiba minim 5 caractere!");
        }
        this.cod = _cod.toUpperCase();
    }

    public String getCodAsignat() {
        return this.cod;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodProdus codProdus = (CodProdus) o;
        return cod.equals(codProdus.cod);
    }
    public int hashCode() {
        return Objects.hash(cod);
    }
    public String toString() {
        return this.cod;
    }

    public void citeste(Scanner in) {
        System.out.println("CodProdus este imutabil. Creeaza un nou obiect CodProdus cu datele citite.");
    }
}
