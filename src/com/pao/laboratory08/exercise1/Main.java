package com.pao.laboratory08.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_PATH = "paoj-2026/src/com/pao/laboratory08/tests/studenti.txt";

    public static void main(String[] args) throws Exception {
        // TODO: Implementează conform Readme.md
        //
        // 1. Citește studenții din FILE_PATH cu BufferedReader
        // 2. Citește comanda din stdin: PRINT, SHALLOW <nume> sau DEEP <nume>
        // 3. Execută comanda:
        //    - PRINT → afișează toți studenții
        //    - SHALLOW <nume> → shallow clone + modifică orașul clonei la "MODIFICAT" + afișează
        //    - DEEP <nume> → deep clone + modifică orașul clonei la "MODIFICAT" + afișează

        Scanner s = new Scanner(System.in);
        ArrayList<Student> studenti = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))){
            while(br.readLine() != null) {
                String[] parti = br.readLine().split(",");
                if(parti.length == 4) {
                    String nume = parti[0].trim();
                    int varsta = Integer.parseInt(parti[1].trim());
                    String oras = parti[2].trim();
                    String strada = parti[3].trim();

                    studenti.add(new Student(nume, varsta, new Adresa(oras, strada)));
                }
            }
        } catch (FileNotFoundException e){
            System.err.println("Nu am gasit fisierul la calea: " + FILE_PATH);
            return;
        }
        String linie = s.nextLine();
        if(linie.equalsIgnoreCase("PRINT")){
            {
                for(Student stud : studenti){
                    System.out.println(stud);
                }
            }
            return;
        }
        String[] cuvinte = linie.split(" ", 2);
        String comanda = cuvinte[0];
        String nume = cuvinte[1];
        Student student = null;
        for(Student stud : studenti){
            if(stud.getNume().equalsIgnoreCase(nume)){
                student = stud;
            }
        }
        if(comanda.equalsIgnoreCase("SHALLOW")){
            Student clona = student.shallowClone();
            clona.getAdresa().setOras("MODIFICAT");

            System.out.println("Original: " + student);
            System.out.println("Clona: " + clona);
            return;
        }

        if(comanda.equalsIgnoreCase("DEEP")){
            Student clona = student.deepClone();
            clona.getAdresa().setOras("MODIFICAT");

            System.out.println("Original: " + student);
            System.out.println("Clona: " + clona);
            return;
        }
    }
}
