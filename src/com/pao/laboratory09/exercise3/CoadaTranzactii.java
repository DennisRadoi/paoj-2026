package com.pao.laboratory09.exercise3;

import com.pao.laboratory09.exercise1.Tranzactie;

import java.lang.reflect.Array;
import java.util.ArrayDeque;

public class CoadaTranzactii {
    private int capacitateMaxima;
    private ArrayDeque<Tranzactie> coada;

    public CoadaTranzactii(int c) {
        this.capacitateMaxima = c;
        this.coada = new ArrayDeque<>();
    }

    public synchronized void adauga(Tranzactie t, int id) throws InterruptedException {
        while (coada.size() >= capacitateMaxima) {
            System.out.println("[ATM-" + id + "] astept loc...");
            wait();
        }
        coada.addLast(t);
        notifyAll();
    }

    public synchronized Tranzactie extrage() throws InterruptedException {
        while (coada.isEmpty()) {
            wait();
        }
        Tranzactie t = coada.removeFirst();
        notifyAll();
        return t;
    }

    public synchronized boolean esteGoala() {
        return coada.isEmpty();
    }
}
