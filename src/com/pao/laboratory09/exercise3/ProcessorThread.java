package com.pao.laboratory09.exercise3;

import com.pao.laboratory09.exercise1.Tranzactie;

import java.util.Locale;

public class ProcessorThread implements Runnable {
    public volatile boolean activ = true;
    private CoadaTranzactii coada;

    public ProcessorThread(CoadaTranzactii coada) {
        this.coada = coada;
    }
    public void run() {
        try {
            while (activ || !coada.esteGoala()) {
                Tranzactie t = null;
                synchronized (coada) {
                    if (!coada.esteGoala()) {
                        t = coada.extrage();
                    } else {
                        coada.wait(100);
                        continue;
                    }
                }

                if (t != null) {
                    System.out.format(Locale.US, "[Processor] Factura #%d - %.2f RON | %s\n",
                            t.getId(), t.getSuma(), t.getData());
                    Thread.sleep(80);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
