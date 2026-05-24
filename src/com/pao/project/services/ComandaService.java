//package com.pao.project.services;
//
//import com.pao.project.models.*;
//
//import java.util.*;
//
//public class ComandaService {
//    private static ComandaService instanta = null;
//    private ArrayList<Comanda> comenzi = null;
//
//    private ComandaService(){
//        comenzi = new ArrayList<>();
//    }
//
//    public static ComandaService getInstanta(){
//        if(instanta == null){
//            instanta = new ComandaService();
//        }
//        return instanta;
//    }
//    public void addComanda(Comanda c){
//        comenzi.add(c);
//    }
//
//    public ArrayList<Comanda> getAllComenzi(){
//        return comenzi;
//    }
//
//    public void listAllComenzi(){
//        for(Comanda c : comenzi){
//            System.out.println(c);
//        }
//    }
//
//    public void stergeComanda(Comanda c){
//        for(int i = 0; i < comenzi.size(); i++){
//            if(comenzi.get(i).getId() == c.getId()){
//                comenzi.remove(i);
//                break;
//            }
//        }
//    }
//
//    public Comanda getComandaById(int id) {
//        for(Comanda comanda : comenzi) {
//            if(comanda.getId() == id) {
//                return comanda;
//            }
//        }
//        return null;
//    }
//    public void adaugaProdusLaComanda(int idComanda, Produs p){
//        Comanda comanda = getComandaById(idComanda);
//        if (comanda != null) {
//            comanda.adaugaProdus(p);
//        }
//    }
//    public void returneazaComanda(int idComanda) {
//        Comanda comanda = getComandaById(idComanda);
//        if(comanda != null) {
//            comanda.setStatus(StatusComanda.RETURNATA);
//        }
//    }
//    public ArrayList<Comanda> getComenziInLivrare(){
//        ArrayList<Comanda> rezultat = new ArrayList<>();
//        for(Comanda c : comenzi){
//            if(c.getStatus() ==  StatusComanda.IN_LIVRARE){
//                rezultat.add(c);
//            }
//        }
//        return rezultat;
//    }
//
//    public Produs getCelMaiComandatProdus(){
//        Produs pmax = null;
//        int aparitiimax = 0;
//        HashMap<Produs, Integer> dict = new HashMap<>();
//        for(Comanda c : comenzi){
//            for(Produs pCandidat : c.getProduse()){
//                if(!dict.containsKey(pCandidat)){
//                    dict.put(pCandidat, 1);
//                }
//                else{
//                    dict.put(pCandidat, dict.get(pCandidat) + 1);
//                }
//            }
//        }
//        for (Map.Entry<Produs, Integer> intrare :dict.entrySet()) {
//            int aparitii = intrare.getValue();
//            if(aparitii > aparitiimax){
//                aparitiimax = aparitii;
//                pmax = intrare.getKey();
//            }
//        }
//        return pmax;
//    }
//
//    public void afisComenziLivratorFinalizate(Livrator l){
//        TreeSet<Comanda> rez = new TreeSet<>(new ComparatorComanda());
//        for(Comanda c : comenzi){
//            if(c.getLivrator() != null && c.getLivrator().getId() == l.getId() && c.getStatus() == StatusComanda.LIVRATA){
//                rez.add(c);
//            }
//        }
//        for(Comanda c : rez){
//            System.out.println(c);
//        }
//    }
//
//}
