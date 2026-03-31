package com.pao.laboratory06.exercise3;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Inginer[] ingineri = new Inginer[3];
        ingineri[0] = new Inginer("Mihai", "Andrei", "0722999888", 20000, 800, "iMA", "1234");
        ingineri[1] = new Inginer("Davidescu", "Marinescu", "07111888999", 15000, 7900, "iKK", "ABCD");
        ingineri[2] = new Inginer("Ion", "Ionut", "0722555666", 10000, 8621, "iOP", "1243");
        System.out.println("Inainte de sortare:");
        for(Inginer i : ingineri){
            System.out.println(i);
        }
        System.out.println("Dupa: ");
        Arrays.sort(ingineri, new ComparatorInginerSalariu());
        for(Inginer i : ingineri){
            System.out.println(i);
        }

        PlataOnline plata = ingineri[0];
//        System.out.println(plata.nume); NU MERGE
//        plata.compareTo(ingineri[1]); NU MERGE
          plata.autentificare("iMA", "1234"); // merge
        PersoanaJuridica pj = new PersoanaJuridica("Andronie", "Marius", "07234999000", 10000, "pj2", "1234");
        PlataOnlineSMS plataSMS = pj;
        plataSMS.trimiteSMS("Suna"); // se trimite si se stocheaza
        System.out.println(pj.getSmsTrimise());
//        System.out.println(plataSMS.getMesajeTrimise()); nu merge
        String msj = null;
        System.out.println(plataSMS.trimiteSMS(null));
        PersoanaJuridica faratel = new PersoanaJuridica("X", "Y", null, 90000, "ft", "124");
        PlataOnlineSMS faratelefon = faratel;
        System.out.println(faratelefon.trimiteSMS("DA"));


        ConstanteFinanciare c = ConstanteFinanciare.COTA_IMPOZIT;
        System.out.println("COTA IMPOZIT ARE O VALOARE DE " + c.getValoare());

        //5.
        try {
            ingineri[0].autentificare(null, "1234");
        }
        catch (IllegalArgumentException e){
            System.out.println("EROARE!! : " + e.getMessage());
        }


        Inginer ip = new Inginer("A", "B", "07123456789", 90, 0, "i9", "1");
//        ip.trimiteSMS("mesaj"); NU MERGE

    }
}
