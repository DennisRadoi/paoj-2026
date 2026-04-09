package com.pao.laboratory07.exercise3;
import java.util.*;
import java.util.stream.Collectors;

import com.pao.laboratory07.exercise3.*;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Comanda> comenzi = new ArrayList<>();
        int n = s.nextInt();
        s.nextLine();
        for(int i = 1; i <= n; i++){
            String[] linie = s.nextLine().split(" ");
            String status = linie[0];
            if(status.equalsIgnoreCase("STANDARD")){
                String nume = linie[1];
                double pret = Double.parseDouble(linie[2]);
                String client= linie[3];
                comenzi.add(new ComandaStandard(nume, pret, client));
            }
            else if(status.equalsIgnoreCase("GIFT")){
                String nume = linie[1];
                String client = linie[2];
                comenzi.add(new ComandaGratuita(nume, client));
            }
            else if(status.equalsIgnoreCase("DISCOUNTED")){
                String nume = linie[1];
                double pret = Double.parseDouble(linie[2]);
                int discount = Integer.parseInt(linie[3]);
                String client = linie[4];
                comenzi.add(new ComandaRedusa(nume, pret, client, discount));
            }
        }
        System.out.println();
        for(Comanda c : comenzi){
            System.out.println(c.descriere());
        }
        System.out.println();
        Map<String, Double> medii = comenzi.stream().collect(Collectors.groupingBy(c -> c.getTip(), Collectors.averagingDouble(Comanda::pretFinal)));
        while(s.hasNextLine()){
            String linie = s.nextLine().trim();

            if(linie.equalsIgnoreCase("QUIT")){
                break;
            }
            else if(linie.equalsIgnoreCase("STATS")){
                System.out.println("-----STATS----");
                System.out.printf("STANDARD: medie = %.2f lei \n", medii.getOrDefault("STANDARD", 0.0));
                System.out.printf("DISCOUNTED: medie = %.2f lei \n", medii.getOrDefault("DISCOUNTED", 0.0));
                System.out.println("GIFT: medie = 0.00 lei\n");
                System.out.println();
            }
            else if(linie.equalsIgnoreCase("FILTER")){
                double numar = Double.parseDouble(linie.split(" ")[1]);
                System.out.println("----FILTER (>=" + numar + ")-----");
                List<Comanda> filtrate = comenzi.stream()
                        .filter(c -> c.pretFinal() >= numar)
                        .toList();
                for(Comanda c : filtrate) {
                    System.out.println(c.descriere());
                }
                System.out.println();
            }
            else if(linie.equalsIgnoreCase("SORT")){
                System.out.println("-- SORT by client, then by pret ----");
                List<Comanda> sortate = comenzi.stream()
                        .sorted(Comparator.comparing(Comanda::getClient)
                                .thenComparing(Comanda::pretFinal)).toList();
                for(Comanda c : sortate){
                    System.out.println(c.descriere());
                }
                System.out.println();
            }
            else if(linie.equalsIgnoreCase("SPECIAL")){
                List<Comanda> speciale = comenzi.stream()
                        .filter(c -> c instanceof ComandaRedusa cr && cr.getDiscountProcent() > 15)
                        .toList();
                for(Comanda c : speciale) {
                    System.out.println(c.descriere());
                }
                System.out.println();
            }
        }
    }
}
