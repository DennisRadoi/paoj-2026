package com.pao.laboratory10.exercise3;
import com.pao.laboratory10.exercise1.Tranzactie;
import com.pao.laboratory10.exercise1.TipTranzactie;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<Tranzactie> lista = new ArrayList<>();

        lista.add(new Tranzactie(1, 1500.00, "2024-01-10", TipTranzactie.CREDIT, "RO01BANK"));
        lista.add(new Tranzactie(2, 200.50, "2024-01-12", TipTranzactie.DEBIT, "RO02BANK"));
        lista.add(new Tranzactie(3, 50.00, "2024-01-15", TipTranzactie.DEBIT, "RO01BANK"));

        lista.add(new Tranzactie(4, 3000.00, "2024-02-05", TipTranzactie.CREDIT, "RO03BANK"));
        lista.add(new Tranzactie(5, 120.00, "2024-02-10", TipTranzactie.DEBIT, "RO01BANK"));
        lista.add(new Tranzactie(6, 450.75, "2024-02-20", TipTranzactie.DEBIT, "RO02BANK"));
        lista.add(new Tranzactie(7, 1000.00, "2024-02-25", TipTranzactie.CREDIT, "RO03BANK"));

        lista.add(new Tranzactie(8, 2500.00, "2024-03-01", TipTranzactie.CREDIT, "RO04BANK"));
        lista.add(new Tranzactie(9, 800.00, "2024-03-15", TipTranzactie.DEBIT, "RO01BANK"));
        lista.add(new Tranzactie(10, 15.25, "2024-03-20", TipTranzactie.DEBIT, "RO05BANK"));

        System.out.println("!!!!! OPERATIA 1 TOATE TRANZACTIILE CREDIT");
        lista.stream().filter(t -> t.getTip() == TipTranzactie.CREDIT).forEach(t -> System.out.println(t));
        System.out.println("\n");



        System.out.println("\n!!!!! OPERATIA 2 ");
        System.out.println("Total procesat: " + lista.stream().mapToDouble(t -> t.getSuma()).sum() + " RON");

        System.out.println("\n!!!!!! OPERATIA 3 (Suma pe luni)");
        Map<String, Double> dict = lista.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getData().substring(0, 7),
                        Collectors.summingDouble(Tranzactie::getSuma)
                ));

        dict.forEach((luna, suma) ->
                System.out.printf("Luna: %s - Total: %.2f RON\n", luna, suma)
        );

        System.out.println("\n!!!! OPERATIA 4 \n" +
                "TOP 3 tranzactii:");
        lista.stream().sorted(Comparator.comparingDouble(Tranzactie::getSuma).reversed())
                .limit(3)
                .forEach(t -> System.out.println(t));

        System.out.println("\n!!! OPERATIA 5" + "\n" +
                "Conturi sursa unice: ");
        lista.stream().map(t -> t.getContSursa()).distinct().collect(Collectors.toList()).forEach(t -> System.out.println(t));

        System.out.println("\n!!! OPERATIA 6");
        System.out.printf("Suma medie: %.2f ", lista.stream().mapToDouble(t -> t.getSuma()).average().orElse(0.0));

        System.out.println("\n!!! OPERATIA 7");
        Map<String, List<Tranzactie>> grupate = lista.stream()
                .collect(Collectors.groupingBy(t -> t.getData().substring(0, 7)));

        grupate.forEach((luna, tranzactii) -> {
            int n = tranzactii.size();
            double total = tranzactii.stream().mapToDouble(Tranzactie::getSuma).sum();

            System.out.printf("EXTRAS DE CONT - %s: %d tranzactii, total: %.2f RON\n",
                    luna, n, total);
        });
    }
}
