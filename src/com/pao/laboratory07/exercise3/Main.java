package com.pao.laboratory07.exercise3;
import java.util.ArrayList;
import java.util.Scanner;
import com.pao.laboratory07.exercise3.*;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Comanda> comenzi = new ArrayList<>();
        int n = s.nextInt();
        s.nextLine();
        for(int i = 1; i <= n; i++){
            String linie = s.nextLine().split(" ");
            String status = linie[0];
            if(status.equalsIgnoreCase("STANDARD")){
                String nume = linie[1];
                double pret = Double.parseDouble(linie[2]);
                String client= linie[3];
                comenzi.add(new ComandaStandard(nume, pret, client));
            }
            else if(status.equalsIgnoreCase("GIFT")){
                String nume = linie[1];
                String client = linie[3];
                comenzi.add(new ComandaGratuita(nume, client));
            }
            else if(status.equalsIgnoreCase("DISCOUNTED")){
                String nume = linie[1];
                double pret = Double.parseDouble(linie[2]);
                int discount = Integer.parseInt(linie[3]);
                String client = linie[4];
                comenzi.add(new ComandaRedusa(nume, pret, discount, client));
            }
        }
        System.out.println();
        for(Comanda c : comenzi){
            System.out.println(c.descriere());
        }
        System.out.println();
    }
}
