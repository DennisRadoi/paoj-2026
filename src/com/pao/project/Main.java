package com.pao.project;

import com.pao.project.models.*;
import com.pao.project.services.ComandaService;
import com.pao.project.services.RestaurantService;
import com.pao.project.services.UtilizatorService;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final UtilizatorService UTILIZATOR_SERVICE = UtilizatorService.getInstanta();
    private static final RestaurantService RESTAURANT_SERVICE = RestaurantService.getInstanta();
    private static final ComandaService COMANDA_SERVICE = ComandaService.getInstanta();

    public static void main(String[] args){
        boolean ruleaza = true;

        while (ruleaza){
            afiseazaMeniu();
            String optiune = SCANNER.nextLine().trim();

            try{
                switch (optiune){
                    case "1" -> adaugaProdusLaComanda();
                    case "2" -> inregistreazaUtilizatorNou();
                    case "3" -> afiseazaClientCuCeleMaiMulteComenzi();
                    case "4" -> returneazaComanda();
                    case "5" -> afiseazaMancareSortataRestaurant();
                    case "6" -> afiseazaComenziInLivrare();
                    case "7" -> afiseazaCelMaiComandatProdus();
                    case "8" -> afiseazaTopRestaurante();
                    case "9" -> afiseazaClientiCuMinimDouaCarduri();
                    case "10" -> afiseazaComenziLivrateDeLivrator();
                    case "11" -> inregistreazaRestaurantNou();
                    case "12" -> inregistreazaProdusNou();
                    case "13" -> inregistreazaComandaNoua();
                    case "14" -> stergeUtilizator();
                    case "15" -> stergeRestaurant();
                    case "16" -> stergeComanda();
                    case "17" -> afisAllUtilizatori();
                    case "18" -> afisAllRestaurante();
                    case "19" -> afisAllProduse();
                    case "20" -> afisAllComenzi();
                    case "0" -> {
                        ruleaza = false;
                        System.out.println("Aplicatia a fost inchisa.");
                    }
                    default -> System.out.println("Optiune invalida.");
                }
            }
             catch (Exception e){
                System.out.println("A aparut o eroare: " + e.getMessage());
            }
        }
    }

    private static void afiseazaMeniu(){
        System.out.println("\n--- MENIU FOOD DELIVERY ---");
        System.out.println("1. Adauga un produs la comanda");
        System.out.println("2. Inregistreaza un utilizator nou");
        System.out.println("3. Afiseaza clientul cu cele mai multe comenzi");
        System.out.println("4. Returneaza o comanda");
        System.out.println("5. Afiseaza si sorteaza produsele de tip mancare ale unui restaurant");
        System.out.println("6. Afiseaza comenzile aflate in livrare");
        System.out.println("7. Afiseaza cel mai comandat produs din toate restaurantele");
        System.out.println("8. Afiseaza topul restaurantelor (media notelor >= 5)");
        System.out.println("9. Afiseaza clientii cu cel putin 2 carduri bancare");
        System.out.println("10. Afiseaza comenzile livrate de un livrator (sortate descrescator dupa pret)");
        System.out.println("11. Inregistreaza un restaurant nou");
        System.out.println("12. Inregistreaza un produs nou");
        System.out.println("13. Inregistreaza o comanda noua");
        System.out.println("14. Sterge un utilizator");
        System.out.println("15. Sterge un restaurant");
        System.out.println("16. Sterge o comanda");
        System.out.println("17. Afiseaza toti utilizatorii");
        System.out.println("18. Afiseaza toate restaurantele");
        System.out.println("19. Afiseaza toate produsele");
        System.out.println("20. Afiseaza toate comenzile");
        System.out.println("0. Iesire");
        System.out.print("Alege o optiune: ");
    }

    private static void afisAllUtilizatori(){
        UTILIZATOR_SERVICE.listAllUtilizatori();
    }

    private static void afisAllRestaurante(){
        for(Restaurant r : RESTAURANT_SERVICE.getAllRestaurante()){
            System.out.println(r);
        }
    }

    private static void afisAllProduse(){
        for(Restaurant r : RESTAURANT_SERVICE.getAllRestaurante()){
            for(Produs p : r.getMeniu()){
                System.out.println(p);
            }
        }
    }

    private static void afisAllComenzi(){
        COMANDA_SERVICE.listAllComenzi();
    }

    private static void adaugaProdusLaComanda(){
        System.out.print("ID comanda: ");
        int idComanda = Integer.parseInt(SCANNER.nextLine().trim());
        Comanda comanda = COMANDA_SERVICE.getComandaById(idComanda);
        if(comanda == null){
            System.out.println("Comanda nu exista.");
            return;
        }

        Produs produsNou = citesteProdus();
        COMANDA_SERVICE.adaugaProdusLaComanda(idComanda, produsNou);
        System.out.println("Produs adaugat cu succes. Total actualizat: " + comanda.getPretTotal() + " RON.");
    }

    private static void inregistreazaUtilizatorNou(){
        System.out.print("Tip utilizator (1 - Client, 2 - Livrator): ");
        String tip = SCANNER.nextLine().trim();
        System.out.print("ID: ");
        int id = Integer.parseInt(SCANNER.nextLine().trim());

        if("1".equals(tip)){
            Client client = new Client(
                    id,
                    "",
                    "",
                    "",
                    0,
                    "",
                    new Adresa()
            );
            client.citeste(SCANNER);
            System.out.print("Numar carduri de adaugat initial: ");
            int nrCarduri = Integer.parseInt(SCANNER.nextLine().trim());
            for (int i = 0; i < nrCarduri; i++){
                client.adaugaCard(citesteCard());
            }
            UTILIZATOR_SERVICE.addUtilizator(client);
            System.out.println("Client inregistrat cu succes.");
        } else if("2".equals(tip)){
            Livrator livrator = new Livrator(id, "", "", "", 0, "", "", false, 0);
            livrator.citeste(SCANNER);
            UTILIZATOR_SERVICE.addUtilizator(livrator);
            System.out.println("Livrator inregistrat cu succes.");
        } else{
            System.out.println("Tip utilizator invalid.");
        }
    }

    private static void afiseazaClientCuCeleMaiMulteComenzi(){
        Map<Client, Integer> rezultat = UTILIZATOR_SERVICE.getClientCuMaxComenzi(COMANDA_SERVICE.getAllComenzi());
        if(rezultat.isEmpty()){
            System.out.println("Nu exista clienti/comenzi inregistrate.");
            return;
        }
        for(Map.Entry<Client, Integer> entry : rezultat.entrySet()){
            System.out.println("Clientul cu cele mai multe comenzi: " + entry.getKey() + " | nr. comenzi: " + entry.getValue());
        }
    }

    private static void returneazaComanda(){
        System.out.print("ID comanda de returnat: ");
        int idComanda = Integer.parseInt(SCANNER.nextLine().trim());
        Comanda comanda = COMANDA_SERVICE.getComandaById(idComanda);
        if (comanda == null){
            System.out.println("Comanda nu exista.");
            return;
        }
        COMANDA_SERVICE.returneazaComanda(idComanda);
        System.out.println("Comanda #" + idComanda + " a fost marcata ca RETURNATA.");
    }

    private static void afiseazaMancareSortataRestaurant(){
        System.out.print("Nume restaurant: ");
        String numeRestaurant = SCANNER.nextLine().trim();
        Restaurant restaurant = RESTAURANT_SERVICE.getRestaurantByNume(numeRestaurant);
        if (restaurant == null){
            System.out.println("Restaurantul nu exista.");
            return;
        }
        RESTAURANT_SERVICE.afisProduseSortateDupaCalorii(restaurant);
    }

    private static void afiseazaComenziInLivrare(){
        ArrayList<Comanda> inLivrare = COMANDA_SERVICE.getComenziInLivrare();
        if (inLivrare.isEmpty()) {
            System.out.println("Nu exista comenzi in livrare.");
            return;
        }
        for (Comanda comanda : inLivrare) {
            System.out.println(comanda);
        }
    }

    private static void afiseazaCelMaiComandatProdus(){
        Produs produs = COMANDA_SERVICE.getCelMaiComandatProdus();
        if (produs == null) {
            System.out.println("Nu exista comenzi cu produse.");
            return;
        }
        System.out.println("Cel mai comandat produs este: " + produs.getNume() + " [" + produs.getTip() + "]");
    }

    private static void afiseazaTopRestaurante(){
        RESTAURANT_SERVICE.afisTopRestauranteDupaRating();
    }

    private static void afiseazaClientiCuMinimDouaCarduri(){
        ArrayList<Client> clienti = UTILIZATOR_SERVICE.getClientiCuMinimDouaCarduri();
        if (clienti.isEmpty()) {
            System.out.println("Nu exista clienti cu minim 2 carduri.");
            return;
        }
        for (Client client : clienti) {
            System.out.println(client + " | carduri: " + client.getListaCarduri().size());
        }
    }

    private static void afiseazaComenziLivrateDeLivrator(){
        System.out.print("ID livrator: ");
        int idLivrator = Integer.parseInt(SCANNER.nextLine().trim());
        Livrator livrator = UTILIZATOR_SERVICE.getLivratorById(idLivrator);
        if (livrator == null) {
            System.out.println("Livratorul nu exista.");
            return;
        }
        COMANDA_SERVICE.afisComenziLivratorFinalizate(livrator);
    }

    private static void inregistreazaRestaurantNou(){
        System.out.print("ID restaurant: ");
        int idRestaurant = Integer.parseInt(SCANNER.nextLine().trim());

        Restaurant restaurant = new Restaurant("", new Adresa(), idRestaurant, new java.util.HashMap<>());
        restaurant.citeste(SCANNER);
        System.out.println("Adresa restaurant:");
        restaurant.getAdresa().citeste(SCANNER);

        RESTAURANT_SERVICE.addRestaurant(restaurant);
        System.out.println("Restaurant inregistrat cu succes.");
    }

    private static void inregistreazaProdusNou(){
        System.out.print("Nume restaurant: ");
        String numeRestaurant = SCANNER.nextLine().trim();
        Restaurant restaurant = RESTAURANT_SERVICE.getRestaurantByNume(numeRestaurant);

        if(restaurant == null){
            System.out.println("Restaurantul nu exista.");
            return;
        }

        Produs produs = citesteProdus();
        restaurant.adaugaProdusMeniu(produs);
        System.out.println("Produs inregistrat cu succes in meniul restaurantului.");
    }

    private static void inregistreazaComandaNoua(){
        System.out.print("ID comanda: ");
        int idComanda = Integer.parseInt(SCANNER.nextLine().trim());
        if (COMANDA_SERVICE.getComandaById(idComanda) != null) {
            System.out.println("Exista deja o comanda cu acest ID.");
            return;
        }

        System.out.print("ID client: ");
        int idClient = Integer.parseInt(SCANNER.nextLine().trim());
        Client client = UTILIZATOR_SERVICE.getClientById(idClient);
        if (client == null){
            System.out.println("Clientul nu exista.");
            return;
        }

        System.out.print("Nume restaurant: ");
        String numeRestaurant = SCANNER.nextLine().trim();
        Restaurant restaurant = RESTAURANT_SERVICE.getRestaurantByNume(numeRestaurant);
        if (restaurant == null) {
            System.out.println("Restaurantul nu exista.");
            return;
        }

        System.out.print("ID livrator (gol daca nu e asignat): ");
        String idLivratorText = SCANNER.nextLine().trim();
        Livrator livrator = null;
        if(!idLivratorText.isEmpty()){
            livrator = UTILIZATOR_SERVICE.getLivratorById(Integer.parseInt(idLivratorText));
            if (livrator == null) {
                System.out.println("Livratorul nu exista.");
                return;
            }
        }

        System.out.print("Numar produse in comanda: ");
        int nrProduse = Integer.parseInt(SCANNER.nextLine().trim());
        ArrayList<Produs> produse = new ArrayList<>();
        if(restaurant.getMeniu().isEmpty()){
            System.out.println("Restaurantul nu are produse in meniu.");
            return;
        }

        System.out.println("Produse disponibile in meniul restaurantului:");
        for(int i = 0; i < restaurant.getMeniu().size(); i++){
            Produs produsMeniu = restaurant.getMeniu().get(i);
            System.out.println((i + 1) + ". " + produsMeniu.getNume() + " [" + produsMeniu.getTip() + "] - " + produsMeniu.getPret() + " RON");
        }

        for(int i = 0; i < nrProduse; i++){
            System.out.print("Alege index produs pentru pozitia " + (i + 1) + ": ");
            int indexProdus = Integer.parseInt(SCANNER.nextLine().trim()) - 1;
            if(indexProdus < 0 || indexProdus >= restaurant.getMeniu().size()){
                System.out.println("Index produs invalid.");
                return;
            }
            produse.add(restaurant.getMeniu().get(indexProdus));
        }

        Comanda comanda = new Comanda(
                idComanda,
                client,
                restaurant,
                livrator,
                produse,
                StatusComanda.INITIALIZATA
        );
        COMANDA_SERVICE.addComanda(comanda);
        System.out.println("Comanda inregistrata cu succes. Total: " + comanda.getPretTotal() + " RON.");
    }

    private static void stergeUtilizator( {
        System.out.print("ID utilizator: ");
        int idUtilizator = Integer.parseInt(SCANNER.nextLine().trim());

        Utilizator utilizatorGasit = null;
        for(Utilizator utilizator : UTILIZATOR_SERVICE.getAllUtilizatori()){
            if(utilizator.getId() == idUtilizator){
                utilizatorGasit = utilizator;
                break;
            }
        }

        if(utilizatorGasit == null){
            System.out.println("Utilizatorul nu exista.");
            return;
        }

        UTILIZATOR_SERVICE.stergeUtilizator(utilizatorGasit);
        System.out.println("Utilizator sters cu succes.");
    }

    private static void stergeRestaurant(){
        System.out.print("Nume restaurant: ");
        String numeRestaurant = SCANNER.nextLine().trim();
        Restaurant restaurant = RESTAURANT_SERVICE.getRestaurantByNume(numeRestaurant);

        if(restaurant == null){
            System.out.println("Restaurantul nu exista.");
            return;
        }

        RESTAURANT_SERVICE.stergeRestaurant(restaurant);
        System.out.println("Restaurant sters cu succes.");
    }

    private static void stergeComanda(){
        System.out.print("ID comanda: ");
        int idComanda = Integer.parseInt(SCANNER.nextLine().trim());
        Comanda comanda = COMANDA_SERVICE.getComandaById(idComanda);

        if(comanda == null){
            System.out.println("Comanda nu exista.");
            return;
        }

        COMANDA_SERVICE.stergeComanda(comanda);
        System.out.println("Comanda stearsa cu succes.");
    }

    private static CardBancar citesteCard(){
        System.out.print("ID card: ");
        int idCard = Integer.parseInt(SCANNER.nextLine().trim());
        CardBancar card = new CardBancar(idCard, "", "", "", 1, 2000);
        card.citeste(SCANNER);
        return card;
    }

    private static Produs citesteProdus(){
        System.out.print("Tip produs (1 - Mancare, 2 - Desert, 3 - Bautura): ");
        String tip = SCANNER.nextLine().trim();
        System.out.print("Cod produs: ");
        CodProdus cod = new CodProdus(SCANNER.nextLine().trim());
        if("1".equals(tip)){
            Mancare mancare = new Mancare(cod, "", "", 0.0, 0, false, false, 0, 0);
            mancare.citeste(SCANNER);
            return mancare;
        }

        if("2".equals(tip)){
            Desert desert = new Desert(cod, "", "", 0.0, 0, false, false, false, false);
            desert.citeste(SCANNER);
            return desert;
        }

        if("3".equals(tip)){
            Bautura bautura = new Bautura(cod, "", "", 0.0, 0, false, false, 0.0, false);
            bautura.citeste(SCANNER);
            return bautura;
        }

        throw new IllegalArgumentException("Tip de produs invalid.");
    }
}
