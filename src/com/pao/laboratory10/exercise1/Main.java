package com.pao.laboratory10.exercise1;

import java.util.*;

import static com.pao.laboratory10.exercise1.Tranzactie.citeste;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        LinkedList<Tranzactie> lista = new LinkedList<>();

        while (s.hasNext()) {
            String comanda = s.next();

            if (comanda.equalsIgnoreCase("ENQUEUE")) {
                lista.addLast(citeste(s));
            }
            else if (comanda.equalsIgnoreCase("DEQUEUE")) {
                if (lista.isEmpty()) {
                    System.out.println("Coada goala.");
                }
                else {
                    System.out.println("Procesat: " + lista.removeFirst());
                }
            }
            else if (comanda.equalsIgnoreCase("PUSH")) {
                lista.addFirst(citeste(s));
            }
            else if (comanda.equalsIgnoreCase("POP")) {
                if (lista.isEmpty()) {
                    System.out.println("Coada goala.");
                }
                else {
                    System.out.println("Extras: " + lista.removeFirst());
                }
            }
            else if (comanda.equalsIgnoreCase("REMOVE_DEBIT")) {
                int cnt = 0;
                Iterator<Tranzactie> it = lista.iterator();
                while(it.hasNext()) {
                    if (it.next().getTip() == TipTranzactie.DEBIT) {
                        it.remove();
                        cnt++;
                    }
                }
                System.out.println("Eliminat " + cnt + " tranzactii DEBIT.");
            }
            else if (comanda.equalsIgnoreCase("REMOVE_BELOW")) {
                double threshold = Double.parseDouble(s.next());
                int cnt = 0;
                Iterator<Tranzactie> it = lista.iterator();
                while (it.hasNext()) {
                    if (it.next().getSuma() < threshold) {
                        it.remove();
                        cnt++;
                    }
                }
                System.out.printf(Locale.US, "Eliminat %d tranzactii sub %.2f RON.\n", cnt, threshold);
            }
            else if (comanda.equalsIgnoreCase("PRINT")) {
                Iterator<Tranzactie> it = lista.iterator();
                while (it.hasNext()) {
                    System.out.println(it.next());
                }
            }
            else if (comanda.equalsIgnoreCase("SIZE")) {
                System.out.println("Dimensiune coada: " + lista.size());
            }
        }
    }
}
