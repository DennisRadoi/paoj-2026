package com.pao.project.models;

public final class CodProdus {
    private final String cod;
    public CodProdus(String _cod){
        if (_cod == null || _cod.trim().isEmpty()) {
            throw new IllegalArgumentException("Codul produsului nu poate fi gol!");
        }
        if(_cod.length() < 5){
            throw new IllegalArgumentException("Lungimea codului trebuie sa aiba minim 5 caractere!");
        }
        this.cod = _cod.toUpperCase();
    }

    public String getCodAsignat() {
        return this.cod;
    }

    public String toString() {
        return this.cod;
    }
}
