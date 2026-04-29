package com.pao.laboratory09.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "paoj-2026/output/lab09_ex1.ser";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        int n = scanner.nextInt();
        List<Tranzactie> tranzactii = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int id = scanner.nextInt();
            double suma = scanner.nextDouble();
            String data = scanner.next();
            String contSursa = scanner.next();
            String contDestinatie = scanner.next();
            TipTranzactie tipTranzactie = TipTranzactie.valueOf(scanner.next());

            Tranzactie tranzactie = new Tranzactie(id, suma, data, contSursa, contDestinatie, tipTranzactie);
            tranzactie.setNote("procesat");
            tranzactii.add(tranzactie);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(OUTPUT_FILE))) {
            oos.writeObject(tranzactii);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Tranzactie> deserializate = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(OUTPUT_FILE))) {
            deserializate = (List<Tranzactie>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNext()) {
            String comanda = scanner.next();
            if ("LIST".equals(comanda)) {
                for (Tranzactie t : deserializate) {
                    System.out.println(t);
                }
            } else if ("FILTER".equals(comanda)) {
                String prefixLuna = scanner.next();
                boolean gasit = false;
                for (Tranzactie t : deserializate) {
                    if (t.getData().startsWith(prefixLuna)) {
                        System.out.println(t);
                        gasit = true;
                    }
                }
                if (!gasit) {
                    System.out.println("Niciun rezultat.");
                }
            } else if ("NOTE".equals(comanda)) {
                int id = scanner.nextInt();
                boolean gasit = false;
                for (Tranzactie t : deserializate) {
                    if (t.getId() == id) {
                        gasit = true;
                        System.out.println("NOTE[" + id + "]: " + t.getNote());
                        break;
                    }
                }
                if (!gasit) {
                    System.out.println("NOTE[" + id + "]: not found");
                }
            } else {
                scanner.nextLine();
            }
        }
    }
}
