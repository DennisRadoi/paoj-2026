package com.pao.project.services;

import com.pao.project.models.*;

import java.util.*;

public class RestaurantService {
    private static RestaurantService instanta = null;
    private ArrayList<Restaurant> restaurante = null;

    private RestaurantService(){
        restaurante= new ArrayList<>();
    }

    public static RestaurantService getInstanta(){
        if(instanta == null){
            instanta = new RestaurantService();
        }
        return instanta;
    }
    public void addRestaurant(Restaurant r){
        restaurante.add(r);
    }
    public ArrayList<Restaurant> getAllRestaurante(){
        return restaurante;
    }
    public void listAllRestaurante(){
        for(Restaurant r : restaurante){
            System.out.println(r);
        }
    }

    public void stergeRestaurant(Restaurant r){
        for(int i = 0; i < restaurante.size(); i++){
            if(restaurante.get(i).getNume().equalsIgnoreCase(r.getNume())){
                restaurante.remove(i);
                break;
            }
        }
    }

    public Restaurant getRestaurantByNume(String nume){
        for(Restaurant r : restaurante){
            if(r.getNume().equalsIgnoreCase(nume)){
                return r;
            }
        }
        return null;
    }

    public void afisProduseSortateDupaCalorii(Restaurant r){
        List<Mancare> sortate = r.getMeniu().stream()
                .filter(p -> p instanceof Mancare)
                .map(p -> (Mancare) p)
                .sorted(Comparator.comparing(Mancare::getCalorii)
                        .thenComparing(Mancare::getNivelPicant).reversed())
                .toList();
        for(Mancare m : sortate){
            System.out.println(m);
        }
    }

    public void afisTopRestauranteDupaRating(){
        List<Restaurant> sortate = restaurante.stream()
                .filter(Restaurant::areMediaCinci)
                .sorted(Comparator.comparing(Restaurant::getRating)
                        .reversed()
                        .thenComparing(Comparator.comparing(Restaurant::getNumarDeserturi).reversed()))
                .toList();
        for(Restaurant r : sortate) {
            System.out.println(r);
        }
    }
}
