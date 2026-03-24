package com.pao.laboratory05.audit;

/**
 * Exercise 4 (Bonus) — Audit Log
 *
 * Cerințele complete se află în:
 *   src/com/pao/laboratory05/Readme.md  →  secțiunea "Exercise 4 (Bonus) — Audit"
 *
 * Extinde soluția de la Exercise 3 cu un sistem de audit bazat pe record.
 * Creează fișierele de la zero în acest pachet, apoi rulează Main.java
 * pentru a verifica output-ul așteptat din Readme.
 */
import com.pao.laboratory05.audit.Angajat;
import com.pao.laboratory05.audit.AngajatService;
import com.pao.laboratory05.audit.Departament;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Cerințele se află în Readme.md — secțiunea Exercise 4 (Bonus).");
        Scanner s = new Scanner(System.in);
        AngajatService srv = AngajatService.getInstance();
        while (true) {
            System.out.println("\n===== Gestionare Angajați =====");
            System.out.println("1. Adaugă angajat");
            System.out.println("2. Listare după salariu");
            System.out.println("3. Caută după departament");
            System.out.println("4. Afiseaza audit log");
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
            else if(alegere == 4){
                srv.printAuditLog();
            }
            else if(alegere == 0){
                System.out.println("LA REVEDERE");
                s.close();
                return;
            }

        }
    }
}
