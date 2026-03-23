package com.pao.laboratory05.angajati;

/**
 * Exercise 3 — Angajați
 *
 * Cerințele complete se află în:
 *   src/com/pao/laboratory05/Readme.md  →  secțiunea "Exercise 3 — Angajați"
 *
 * Creează fișierele de la zero în acest pachet, apoi rulează Main.java
 * pentru a verifica output-ul așteptat din Readme.
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Cerințele se află în Readme.md — secțiunea Exercise 3.");
        Scanner s = new Scanner(System.in);
        AngajatService srv = AngajatService.getInstance();
        while (true) {
            System.out.println("\n===== Gestionare Angajați =====");
            System.out.println("1. Adaugă angajat");
            System.out.println("2. Listare după salariu");
            System.out.println("3. Caută după departament");
            System.out.println("0. Ieșire");
            System.out.print("Opțiune: ");

            int alegere = s.nextInt();
            s.nextLine();
            if(alegere == 1){
                System.out.println("Nume: ");
                String nume = s.nextLine();

                System.out.println("Departament (nume): ");
                String numeDept = s.nextLine();

                System.out.println("Departament (locatie): ");
                String locDept = s.nextLine();

                System.out.println("Salariu: ");
                double salariu = s.nextDouble();
                s.nextLine();

                Departament d = new Departament(numeDept, locDept);
                Angajat a = new Angajat(d, salariu, nume);
                srv.addAngajat(a);
            }
            else if(alegere == 2){
                srv.listBySalary();
            }
            else if(alegere == 3){
                System.out.println("Ce departament cauti: ");
                String nume = s.nextLine();
                srv.findByDepartament(nume);
            }
            else if(alegere == 0){
                System.out.println("LA REVEDERE");
                s.close();
                return;
            }

        }
    }
}
