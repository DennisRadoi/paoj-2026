package com.pao.laboratory10.exercise2;

import com.pao.laboratory10.exercise1.Tranzactie;
import com.pao.laboratory10.exercise1.TipTranzactie;

import java.util.*;

import static com.pao.laboratory10.exercise1.Tranzactie.citeste;

public class Main {
    public static void main(String[] args) {
        ArrayList<Tranzactie> lista = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        for (int i = 0; i < n; i++) {
            lista.add(citeste(s));
        }
        while (s.hasNext()) {
            String comanda = s.next();

            if (comanda.equalsIgnoreCase("UNIQUE_IDS")) {
                LinkedHashSet<Integer> lhs = new LinkedHashSet<>();
                for (Tranzactie t : lista) {
                    lhs.add(t.getId());
                }
                System.out.print("IDs unice (" + lhs.size() + "): " + lhs);
            }
            else if (comanda.equalsIgnoreCase("MONTHLY_REPORT")) {
                TreeMap<String, double[]> tri = new TreeMap<>();
                for (Tranzactie t : lista) {
                    String luna = t.getData().split("-")[0] + "-" + t.getData().split("-")[1];
                    if (!tri.containsKey(luna)) {
                        tri.put(luna, new double[2]);
                    }
                    double sume[] = tri.get(luna);
                    if(t.getTip() == TipTranzactie.CREDIT) {
                        sume[0] += t.getSuma();
                    }
                    else {
                        sume[1] += t.getSuma();
                    }
                }
                for(String luna : tri.keySet()) {
                    double sume[] = tri.get(luna);
                    System.out.printf(Locale.US, "%s: CREDIT %.2f RON, DEBIT %.2f RON\n", luna, sume[0], sume[1]);
                }
            }
            else if (comanda.equalsIgnoreCase("TOP")) {
                int k = s.nextInt();
                ArrayList<Tranzactie> copie = new ArrayList<>(lista);
                Collections.sort(copie, Comparator.comparingDouble(Tranzactie::getSuma).reversed());

                System.out.println("Top " + k + ":");
                for (int i = 0; i < k; i++) {
                    System.out.println(copie.get(i));
                }
            }
            else if (comanda.equalsIgnoreCase("SORT_ASC")) {
                Collections.sort(lista, Comparator.comparingDouble(Tranzactie::getSuma));
                for (Tranzactie t : lista) {
                    System.out.println(t);
                }
            }
            else if (comanda.equalsIgnoreCase("SORT_DESC")) {
                Collections.sort(lista, Comparator.comparingDouble(Tranzactie::getSuma).reversed());
                for (Tranzactie t : lista) {
                    System.out.println(t);
                }
            }
            else if (comanda.equalsIgnoreCase("REVERSE")) {
                Collections.reverse(lista);
                for (Tranzactie t : lista) {
                    System.out.println(t);
                }
            }
            else if (comanda.equalsIgnoreCase("MIN_MAX")) {
                if (!lista.isEmpty()) {
                    Tranzactie mini = Collections.min(lista, Comparator.comparingDouble(Tranzactie::getSuma));
                    Tranzactie maxi = Collections.max(lista, Comparator.comparingDouble(Tranzactie::getSuma));
                    System.out.println("MIN: " + mini);
                    System.out.println("MAX: " + maxi);
                }
            }
            else if (comanda.equalsIgnoreCase("CME_DEMO")) {
                try {
                    for (Tranzactie t : lista) {
                        if (t.getSuma() > 0) {
                            lista.remove(t);
                        }
                    }
                } catch (ConcurrentModificationException e) {
                    System.out.println("ConcurrentModificationException prins: modificare in iteratie detectata.");
                }
            }
        }
   }
}
