package com.pao.laboratory08.exercise2;

import java.io.*;
import java.util.*;
import com.pao.laboratory08.exercise1.Student;
import com.pao.laboratory08.exercise1.Adresa;

public class Main {
    private static final String FILE_PATH = "paoj-2026/src/com/pao/laboratory08/tests/studenti.txt";

    public static void main(String[] args) throws Exception {
        // TODO: Implementează conform Readme.md
        //
        // 1. Citește studenții din FILE_PATH cu BufferedReader
        // 2. Citește pragul de vârstă din stdin cu Scanner
        // 3. Filtrează studenții cu varsta >= prag
        // 4. Scrie filtrații în "rezultate.txt" cu BufferedWriter
        // 5. Afișează sumarul la consolă
        Scanner s = new Scanner(System.in);
        ArrayList<Student> studenti = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))){
            String linie;
            while((linie = br.readLine()) != null) {
                String[] parti = linie.split(",");
                if (parti.length == 4){
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
        int prag = Integer.parseInt(s.nextLine().trim());

        List<Student> filtrati = studenti.stream().filter(stud -> stud.getVarsta() >= prag).toList();
        System.out.println("Filtru: varsta >= " + prag);
        System.out.println("Rezultat " + filtrati.size() + " studenti");
        System.out.println("");
        for(Student stud : filtrati){
            System.out.println(stud);
        }
        System.out.println("");
        System.out.println("Scris in: rezultate.txt");
        BufferedWriter fout = new BufferedWriter(new FileWriter("rezultate.txt"));
        for(Student stud : filtrati){
            fout.write(stud.toString());
            fout.newLine();
        }
        fout.close();
    }
}

