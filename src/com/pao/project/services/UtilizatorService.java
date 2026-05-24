//package com.pao.project.services;
//
//import com.pao.project.models.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class UtilizatorService {
//    private static UtilizatorService instanta = null;
//    private ArrayList<Utilizator> utilizatori;
//
//    private UtilizatorService(){
//        utilizatori = new ArrayList<>();
//    }
//
//    public static UtilizatorService getInstanta(){
//        if(instanta == null){
//            instanta = new UtilizatorService();
//        }
//        return instanta;
//    }
//
//    public void addUtilizator(Utilizator u){
//        utilizatori.add(u);
//    }
//
//    public ArrayList<Utilizator> getAllUtilizatori(){
//        return utilizatori;
//    }
//
//    public void listAllUtilizatori(){
//        for(Utilizator u : utilizatori){
//            System.out.println(u);
//        }
//    }
//    public void stergeUtilizator(Utilizator u){
//        for(int i = 0; i < utilizatori.size(); i++){
//            if(utilizatori.get(i).getId() == u.getId()){
//                utilizatori.remove(i);
//                break;
//            }
//        }
//    }
//
//    public Client getClientById(int id) {
//        for(Utilizator utilizator : utilizatori) {
//            if(utilizator instanceof Client && utilizator.getId() == id) {
//                return (Client) utilizator;
//            }
//        }
//        return null;
//    }
//    public Livrator getLivratorById(int id) {
//        for (Utilizator utilizator : utilizatori) {
//            if(utilizator instanceof Livrator && utilizator.getId() == id) {
//                return (Livrator) utilizator;
//            }
//        }
//        return null;
//    }
//
//    public ArrayList<Client> getClientiCuMinimDouaCarduri() {
//        ArrayList<Client> rezultat = new ArrayList<>();
//
//        for (Utilizator utilizator : utilizatori) {
//            if(utilizator instanceof Client) {
//                Client client = (Client) utilizator;
//                if(client.getListaCarduri().size() >= 2) {
//                    rezultat.add(client);
//                }
//            }
//        }
//        return rezultat;
//    }
//
//    public ArrayList<Client> getAllClienti() {
//        ArrayList<Client> clienti = new ArrayList<>();
//
//        for(Utilizator utilizator : utilizatori) {
//            if(utilizator instanceof Client) {
//                clienti.add((Client) utilizator);
//            }
//        }
//        return clienti;
//    }
//
//    public ArrayList<Livrator> getAllLivratori() {
//        ArrayList<Livrator> livratori = new ArrayList<>();
//
//        for(Utilizator utilizator : utilizatori) {
//            if(utilizator instanceof Livrator) {
//                livratori.add((Livrator) utilizator);
//            }
//        }
//        return livratori;
//    }
//
//    public HashMap<Client, Integer> getClientCuMaxComenzi(ArrayList<Comanda> comenzi){
//        Client cmax = null;
//        int maxComenzi = 0;
//        for(Client client : this.getAllClienti()){
//            int nrComenzi = 0;
//            for(Comanda comanda : comenzi){
//                if(comanda.getClient().getId() == client.getId()){
//                    nrComenzi++;
//                }
//            }
//            if(nrComenzi > maxComenzi){
//                maxComenzi = nrComenzi;
//                cmax = client;
//            }
//        }
//        HashMap<Client, Integer> rezultat = new HashMap<>();
//        if (cmax != null) {
//            rezultat.put(cmax, maxComenzi);
//        }
//        return rezultat;
//    }
//
//}
