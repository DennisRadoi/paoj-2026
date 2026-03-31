package com.pao.laboratory06.exercise2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Colaborator> colaboratori = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if (in.hasNext() == false) break;
            String tip = in.next();
            Colaborator c = null;
            if (tip.equalsIgnoreCase("CIM")) {
                c = new CIMColaborator();
            } else if (tip.equalsIgnoreCase("SRL")) {
                c = new SRLColaborator();
            } else if (tip.equalsIgnoreCase("PFA")) {
                c = new PFAColaborator();
            }
            if (c != null) {
                c.citeste(in);
                colaboratori.add(c);
                }
            }
            for(Colaborator cb : colaboratori){
                cb.afiseaza();
            }
            colaboratori.sort(Comparator.comparingDouble(Colaborator::calculeazaVenitNetAnual).reversed());

            System.out.println();

            if (!colaboratori.isEmpty()) {
                System.out.print("Colaborator cu venit net maxim: ");
                colaboratori.get(0).afiseaza();
                System.out.println();
            }

            System.out.println("Colaboratori persoane juridice:");
            for (Colaborator cb : colaboratori) {
                if (cb instanceof PersoanaJuridica) {
                    cb.afiseaza();
                }
            }
            System.out.println("\nSume și număr colaboratori pe tip:");
            for(TipColaborator t : TipColaborator.values()){
                double suma = 0;
                int cnt = 0;
                for(Colaborator cb : colaboratori){
                    if(cb.getTip() == t){
                        cnt += 1;
                        suma += cb.calculeazaVenitNetAnual();
                    }
                }
                if(cnt > 0){
                    String sumaFormatata = String.format("%.2f", suma);
                    sumaFormatata = sumaFormatata.replace(',', '.');
                    System.out.printf("%s: suma = %s lei, număr = %d\n", t, sumaFormatata, cnt);
                } else {
                    System.out.println(t + ": suma = nu lei, număr = null");
                }
            }
        }

        }
//        for (int i = 0; i < n; i++) {
//            String tip = in.next();
//            Colaborator c = switch (tip) {
//                case "CIM" -> {
//                    CIMColaborator obj = new CIMColaborator();
//                    obj.citeste(in);
//                    yield obj;
//                }
//                case "PFA" -> {
//                    PFAColaborator obj = new PFAColaborator();
//                    obj.citeste(in);
//                    yield obj;
//                }
//                case "SRL" -> {
//                    SRLColaborator obj = new SRLColaborator();
//                    obj.citeste(in);
//                    yield obj;
//                }
//                default -> throw new IllegalArgumentException("Tip necunoscut: " + tip);
//            };
//            colaboratori.add(c);
//        }
//        // Sortează și afișează pe tip, fiecare descrescător după venit net anual
//        for (TipColaborator tipColab : TipColaborator.values()) {
//            colaboratori.stream()
//                    .filter(c -> c.getTip() == tipColab)
//                    .sorted((a, b) -> Double.compare(b.calculeazaVenitNetAnual(), a.calculeazaVenitNetAnual()))
//                    .forEach(Colaborator::afiseaza);
//        }
//        // Colaborator cu venit net maxim
//        Colaborator max = colaboratori.stream().max(Comparator.comparingDouble(Colaborator::calculeazaVenitNetAnual)).orElse(null);
//        System.out.printf("\nColaborator cu venit net maxim: ");
//        if (max != null) max.afiseaza();
//        // Colaboratori persoane juridice (SRL)
//        System.out.println("\nColaboratori persoane juridice:");
//        colaboratori.stream()
//                .filter(c -> c instanceof PersoanaJuridica)
//                .sorted((a, b) -> Double.compare(b.calculeazaVenitNetAnual(), a.calculeazaVenitNetAnual()))
//                .forEach(Colaborator::afiseaza);
//        // Sume și număr colaboratori pe tip
//        System.out.println("\nSume și număr colaboratori pe tip:");
//        Map<TipColaborator, Double> suma = new EnumMap<>(TipColaborator.class);
//        Map<TipColaborator, Integer> numar = new EnumMap<>(TipColaborator.class);
//        var typesOfCollaborators = new HashSet<TipColaborator>();
//        for (Colaborator c : colaboratori) {
//            typesOfCollaborators.add(c.getTip());
//        }
//        for (TipColaborator t : typesOfCollaborators) {
//            suma.put(t, 0.0);
//            numar.put(t, 0);
//        }
//        for (Colaborator c : colaboratori) {
//            TipColaborator t = c.getTip();
//            suma.put(t, suma.get(t) + c.calculeazaVenitNetAnual());
//            numar.put(t, numar.get(t) + 1);
//        }
//        for (TipColaborator t : TipColaborator.values()) {
//            if (numar.containsKey(t) && numar.get(t) > 0) {
//                double valoareSuma = suma.get(t);
//                int valoareNumar = numar.get(t);
//
//                String sumaPunct = String.format("%.2f", valoareSuma).replace(',', '.');
//
//                System.out.println(t + ": suma = " + sumaPunct + " lei, număr = " + valoareNumar);
//            }
//            else {
//                System.out.println(t + ": suma = nu lei, număr = null");
//            }
//        }
//    }
