package com.pao.laboratory05.biblioteca;

import java.util.Arrays;
import java.util.Comparator;
public class BibliotecaService {
    private static BibliotecaService instanta = null;
    private Carte[] carti;
    private BibliotecaService(){
        this.carti = new Carte[0];
    }
    private static class Holder {
        private static final BibliotecaService instanta = new BibliotecaService();
    }

    public static BibliotecaService getInstance() {
        return Holder.instanta;
    }

    public void addCarte(Carte c){
        Carte[] copy = new Carte[this.carti.length + 1];
        System.arraycopy(this.carti, 0, copy, 0, this.carti.length);
        copy[this.carti.length] = c;
        this.carti = copy;
        System.out.println("Carte " + c.getTitlu() + " adaugata cu succes !");
    }

    public void listSortedByRating(){
        Carte[] copy = this.carti.clone();
        Arrays.sort(copy);
        for(Carte c : copy) {
            System.out.println(c);
        }
    }

    void listSortedBy(Comparator<Carte> comparator){
        Carte[] copy = this.carti.clone();
        Arrays.sort(copy, comparator);
        for(Carte c : copy) {
            System.out.println(c);
        }
    }




}
