package com.pao.laboratory09.exercise3;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CoadaTranzactii coada = new CoadaTranzactii(5);

        ATMThread at1 = new ATMThread(1, coada);
        ATMThread at2 = new ATMThread(2, coada);
        ATMThread at3 = new ATMThread(3, coada);

        ProcessorThread processor = new ProcessorThread(coada);
        Thread processorThread = new Thread(processor);

        at1.start();
        at2.start();
        at3.start();
        processorThread.start();

        try {
            at1.join();
            at2.join();
            at3.join();

            processor.activ = false;

            synchronized (coada) {
                coada.notifyAll();
            }

            processorThread.join();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Toate tranzactiile procesate. Total: 12");
    }
}
