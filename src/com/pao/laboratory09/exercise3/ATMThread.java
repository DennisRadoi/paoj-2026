package com.pao.laboratory09.exercise3;

import com.pao.laboratory09.exercise1.TipTranzactie;
import com.pao.laboratory09.exercise1.Tranzactie;

public class ATMThread extends Thread {
    private int id;
    private CoadaTranzactii coada;

    public ATMThread(int id, CoadaTranzactii coada) {
        this.id = id;
        this.coada = coada;
    }

    public void run() {
        try {
            for (int i = 1; i <= 4; i++) {
                int idTranzactie = id * 100 + i;
                double suma = 100 + Math.random() * 900;
                Tranzactie t = new Tranzactie(idTranzactie, suma, "2026-04-30", "ATM-" + id, "Banca", TipTranzactie.DEBIT);

                String atmTag = "ATM-" + id;
                System.out.format("[%s] trimite: Tranzactie #%d %.2f RON\n", atmTag, idTranzactie, suma);

                coada.adauga(t, id);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
