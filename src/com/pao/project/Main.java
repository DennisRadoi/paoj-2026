package com.pao.project;

import com.pao.project.models.*;
import com.pao.project.services.*;
import com.pao.project.repository.*;
import com.pao.project.util.DatabaseConnection;

import java.sql.SQLException;
import java.util.*;

public class Main {
    static AuditService audit = AuditService.getInstance();
    static ComandaRepository cr = new ComandaRepository();
    static ProdusRepository pr = new ProdusRepository();
    static UtilizatorRepository u = new UtilizatorRepository();
    static ClientRepository clr = new ClientRepository();
    static LivratorRepository lr = new LivratorRepository();
    static RestaurantRepository rr = new RestaurantRepository();
    static AdresaRepository adr = new AdresaRepository();
    static SQLService ss = SQLService.getInstance();

    public static void main(String[] args) throws SQLException {
        incarcaDate();
        Adresa a1 = new Adresa("Calarasi", "Calarasi", "Zefirului", 26, "I19", 2, 22);
        Adresa a2 = new Adresa("Bucuresti", "Sector 6", "Precizei", 24, "B2", 1, 985);
        adr.save(a1);
        adr.save(a2);
        System.out.println("ETAPA 2 PROIECT\n");

        // Actiunea 1 -> ADAUGA Restaurant
        Restaurant r1 = new Restaurant("Pizzeria galleto", a1.getId());
        rr.save(r1);
        audit.log("add_resaurant");
        System.out.println("1. Restaurant adaugat " + r1);

        // Actiunea 2 -> ADAUGA produs
        Produs p1 = new Produs("Pizza", "Blat cu sos", 30, r1.getId());
        pr.save(p1);
        audit.log("add_produs");
        System.out.println("2. Produs adaugat " + p1);

        // Actiunea 3 -> Adauga client
        Client c1 = new Client("21-04-2001", "0788999000", "miau@gmail.com", 25, "Andrei Alex", a2.getId());
        clr.save(c1);
        audit.log("add_client");
        System.out.println("3. Client adaugat " + c1);

        // Actiunea 4 -> Adauga livrator
        Livrator l1 = new Livrator("26-03-2005", "0734565780", "abdul@gmail.com", 21, "Abdul Abdul" ,"Bicicleta", 1);
        lr.save(l1);
        audit.log("add_livrator");
        System.out.println("4. Livrator adaugat " + l1);

        // Actiunea 5 -> Adauga comanda
        Comanda com1 = new Comanda(c1.getId(), r1.getId(), l1.getId(), 60, "IN_LIVRARE");
        cr.save(com1);
        audit.log("add_comanda");
        System.out.println("5. Comanda adaugat " + com1);

        ComandaProdus cp1 = new ComandaProdus(com1.getId(), p1.getId());
        // Actiunea 5 -> Tranzactia

        // Actiunea 6 -> Q1

        // Actiunea 7 -> Q2

        // Actiunea 8 -> Q3

        // Actiunea 9 -> Sterge un restaurant
        rr.delete(r1.getId());
        audit.log("remove_restaurant");
        System.out.println("A fost sters restaurantul cu id" + r1.getId());

        // Actiunea 10 -> Listeaza toate comenzile
        List<Comanda> comenzi = cr.findAll();
        audit.log("list_all_comenzi");
        for (Comanda c : comenzi) {
            System.out.println(c);
        }

        // Actiunea 11 -> Cauta livrator dupa ID
        lr.findById(l1.getId()).ifPresentOrElse(
                l -> System.out.println("11. Livrator gasit " + l),
                () -> System.out.println("11. Nu a fost gasit livratorul")
        );
        audit.log("find_livrator_by_id");

        // Actiunea 12 -> Actualizeaza restaurant


    }

    private static void incarcaDate() throws SQLException {

    }

//    private static void afiseazaMeniu(){
//        System.out.println("\n--- MENIU FOOD DELIVERY ---");
//        System.out.println("1. Adauga un produs la comanda");
//        System.out.println("2. Inregistreaza un utilizator nou");
//        System.out.println("3. Afiseaza clientul cu cele mai multe comenzi");
//        System.out.println("4. Returneaza o comanda");
//        System.out.println("5. Afiseaza si sorteaza produsele de tip mancare ale unui restaurant dupa numarul de calorii");
//        System.out.println("6. Afiseaza comenzile aflate in livrare");
//        System.out.println("7. Afiseaza cel mai comandat produs din toate restaurantele");
//        System.out.println("8. Afiseaza topul restaurantelor (media notelor >= 5)");
//        System.out.println("9. Afiseaza clientii cu cel putin 2 carduri bancare");
//        System.out.println("10. Afiseaza comenzile livrate de un livrator (sortate descrescator dupa pret)");
//        System.out.println("11. Inregistreaza un restaurant nou");
//        System.out.println("12. Inregistreaza un produs nou");
//        System.out.println("13. Inregistreaza o comanda noua");
//        System.out.println("14. Sterge un utilizator");
//        System.out.println("15. Sterge un restaurant");
//        System.out.println("16. Sterge o comanda");
//        System.out.println("17. Afiseaza toti utilizatorii");
//        System.out.println("18. Afiseaza toate restaurantele");
//        System.out.println("19. Afiseaza toate produsele");
//        System.out.println("20. Afiseaza toate comenzile");
//        System.out.println("0. Iesire");
//        System.out.print("Alege o optiune: ");
//    }
//
//    private static void afisAllUtilizatori(){
//        UTILIZATOR_SERVICE.listAllUtilizatori();
//    }
//
//    private static void afisAllRestaurante(){
//        for(Restaurant r : RESTAURANT_SERVICE.getAllRestaurante()){
//            System.out.println(r);
//        }
//    }
//
//    private static void afisAllProduse(){
//        for(Restaurant r : RESTAURANT_SERVICE.getAllRestaurante()){
//            for(Produs p : r.getMeniu()){
//                System.out.println(p);
//            }
//        }
//    }
//
//    private static void afisAllComenzi(){
//        COMANDA_SERVICE.listAllComenzi();
//    }
//
//    private static void adaugaProdusLaComanda(){
//        System.out.print("ID comanda: ");
//        int idComanda = Integer.parseInt(SCANNER.nextLine().trim());
//        Comanda comanda = COMANDA_SERVICE.getComandaById(idComanda);
//        if(comanda == null){
//            System.out.println("Comanda nu exista.");
//            return;
//        }
//
//        Restaurant restaurant = comanda.getRestaurant();
//        if(restaurant == null || restaurant.getMeniu().isEmpty()){
//            System.out.println("Comanda nu are restaurant asociat sau meniul restaurantului este gol.");
//            return;
//        }
//
//        System.out.println("Produse disponibile pentru comanda #" + idComanda + ":");
//        for(int i = 0; i < restaurant.getMeniu().size(); i++){
//            Produs produsMeniu = restaurant.getMeniu().get(i);
//            System.out.println((i + 1) + ". " + produsMeniu.getNume() + " [" + produsMeniu.getTip() + "] - " + produsMeniu.getPret() + " RON");
//        }
//
//        System.out.print("Alege index produs: ");
//        int indexProdus = Integer.parseInt(SCANNER.nextLine().trim()) - 1;
//        if(indexProdus < 0 || indexProdus >= restaurant.getMeniu().size()){
//            System.out.println("Index produs invalid.");
//            return;
//        }
//
//        Produs produsSelectat = restaurant.getMeniu().get(indexProdus);
//        COMANDA_SERVICE.adaugaProdusLaComanda(idComanda, produsSelectat);
//        System.out.println("Produs adaugat cu succes. Total actualizat: " + comanda.getPretTotal() + " RON.");
//    }
//
//    private static void inregistreazaUtilizatorNou(){
//        System.out.print("Tip utilizator (1 - Client, 2 - Livrator): ");
//        String tip = SCANNER.nextLine().trim();
//        System.out.print("ID: ");
//        int id = Integer.parseInt(SCANNER.nextLine().trim());
//
//        if("1".equals(tip)){
//            Client client = new Client(
//                    id,
//                    "",
//                    "",
//                    "",
//                    0,
//                    "",
//                    new Adresa()
//            );
//            client.citeste(SCANNER);
//            System.out.print("Numar carduri de adaugat initial: ");
//            int nrCarduri = Integer.parseInt(SCANNER.nextLine().trim());
//            for (int i = 0; i < nrCarduri; i++){
//                client.adaugaCard(citesteCard());
//            }
//            UTILIZATOR_SERVICE.addUtilizator(client);
//            System.out.println("Client inregistrat cu succes.");
//        } else if("2".equals(tip)){
//            Livrator livrator = new Livrator(id, "", "", "", 0, "", "", false, 0);
//            livrator.citeste(SCANNER);
//            UTILIZATOR_SERVICE.addUtilizator(livrator);
//            System.out.println("Livrator inregistrat cu succes.");
//        } else{
//            System.out.println("Tip utilizator invalid.");
//        }
//    }
//
//    private static void afiseazaClientCuCeleMaiMulteComenzi(){
//        Map<Client, Integer> rezultat = UTILIZATOR_SERVICE.getClientCuMaxComenzi(COMANDA_SERVICE.getAllComenzi());
//        if(rezultat.isEmpty()){
//            System.out.println("Nu exista clienti/comenzi inregistrate.");
//            return;
//        }
//        for(Map.Entry<Client, Integer> entry : rezultat.entrySet()){
//            System.out.println("Clientul cu cele mai multe comenzi: " + entry.getKey() + " | nr. comenzi: " + entry.getValue());
//        }
//    }
//
//    private static void returneazaComanda(){
//        System.out.print("ID comanda de returnat: ");
//        int idComanda = Integer.parseInt(SCANNER.nextLine().trim());
//        Comanda comanda = COMANDA_SERVICE.getComandaById(idComanda);
//        if(comanda == null){
//            System.out.println("Comanda nu exista.");
//            return;
//        }
//        COMANDA_SERVICE.returneazaComanda(idComanda);
//        System.out.println("Comanda #" + idComanda + " a fost marcata ca RETURNATA.");
//    }
//
//    private static void afiseazaMancareSortataRestaurant(){
//        System.out.print("Nume restaurant: ");
//        String numeRestaurant = SCANNER.nextLine().trim();
//        Restaurant restaurant = RESTAURANT_SERVICE.getRestaurantByNume(numeRestaurant);
//        if(restaurant == null){
//            System.out.println("Restaurantul nu exista.");
//            return;
//        }
//        RESTAURANT_SERVICE.afisProduseSortateDupaCalorii(restaurant);
//    }
//
//    private static void afiseazaComenziInLivrare(){
//        ArrayList<Comanda> inLivrare = COMANDA_SERVICE.getComenziInLivrare();
//        if(inLivrare.isEmpty()){
//            System.out.println("Nu exista comenzi in livrare.");
//            return;
//        }
//        for(Comanda comanda : inLivrare){
//            System.out.println(comanda);
//        }
//    }
//
//    private static void afiseazaCelMaiComandatProdus(){
//        Produs produs = COMANDA_SERVICE.getCelMaiComandatProdus();
//        if(produs == null){
//            System.out.println("Nu exista comenzi cu produse.");
//            return;
//        }
//        System.out.println("Cel mai comandat produs este: " + produs.getNume() + " [" + produs.getTip() + "]");
//    }
//
//    private static void afiseazaTopRestaurante(){
//        RESTAURANT_SERVICE.afisTopRestauranteDupaRating();
//    }
//
//    private static void afiseazaClientiCuMinimDouaCarduri(){
//        ArrayList<Client> clienti = UTILIZATOR_SERVICE.getClientiCuMinimDouaCarduri();
//        if(clienti.isEmpty()) {
//            System.out.println("Nu exista clienti cu minim 2 carduri.");
//            return;
//        }
//        for(Client client : clienti) {
//            System.out.println(client + " | carduri: " + client.getListaCarduri().size());
//        }
//    }
//
//    private static void afiseazaComenziLivrateDeLivrator(){
//        System.out.print("ID livrator: ");
//        int idLivrator = Integer.parseInt(SCANNER.nextLine().trim());
//        Livrator livrator = UTILIZATOR_SERVICE.getLivratorById(idLivrator);
//        if(livrator == null){
//            System.out.println("Livratorul nu exista.");
//            return;
//        }
//        COMANDA_SERVICE.afisComenziLivratorFinalizate(livrator);
//    }
//
//    private static void inregistreazaRestaurantNou(){
//        System.out.print("ID restaurant: ");
//        int idRestaurant = Integer.parseInt(SCANNER.nextLine().trim());
//
//        Restaurant restaurant = new Restaurant("", new Adresa(), idRestaurant, new java.util.HashMap<>());
//        restaurant.citeste(SCANNER);
//        System.out.println("Adresa restaurant:");
//        restaurant.getAdresa().citeste(SCANNER);
//
//        RESTAURANT_SERVICE.addRestaurant(restaurant);
//        System.out.println("Restaurant inregistrat cu succes.");
//    }
//
//    private static void inregistreazaProdusNou(){
//        System.out.print("Nume restaurant: ");
//        String numeRestaurant = SCANNER.nextLine().trim();
//        Restaurant restaurant = RESTAURANT_SERVICE.getRestaurantByNume(numeRestaurant);
//
//        if(restaurant == null){
//            System.out.println("Restaurantul nu exista.");
//            return;
//        }
//
//        Produs produs = citesteProdus();
//        restaurant.adaugaProdusMeniu(produs);
//        System.out.println("Produs inregistrat cu succes in meniul restaurantului.");
//    }
//
//    private static void inregistreazaComandaNoua(){
//        System.out.print("ID comanda: ");
//        int idComanda = Integer.parseInt(SCANNER.nextLine().trim());
//        if(COMANDA_SERVICE.getComandaById(idComanda) != null){
//            System.out.println("Exista deja o comanda cu acest ID.");
//            return;
//        }
//
//        System.out.print("ID client: ");
//        int idClient = Integer.parseInt(SCANNER.nextLine().trim());
//        Client client = UTILIZATOR_SERVICE.getClientById(idClient);
//        if (client == null){
//            System.out.println("Clientul nu exista.");
//            return;
//        }
//
//        System.out.print("Nume restaurant: ");
//        String numeRestaurant = SCANNER.nextLine().trim();
//        Restaurant restaurant = RESTAURANT_SERVICE.getRestaurantByNume(numeRestaurant);
//        if(restaurant == null){
//            System.out.println("Restaurantul nu exista.");
//            return;
//        }
//
//        System.out.print("ID livrator (gol daca nu e asignat): ");
//        String idLivratorText = SCANNER.nextLine().trim();
//        Livrator livrator = null;
//        if(!idLivratorText.isEmpty()){
//            livrator = UTILIZATOR_SERVICE.getLivratorById(Integer.parseInt(idLivratorText));
//            if (livrator == null) {
//                System.out.println("Livratorul nu exista.");
//                return;
//            }
//        }
//
//        System.out.print("Numar produse in comanda: ");
//        int nrProduse = Integer.parseInt(SCANNER.nextLine().trim());
//        ArrayList<Produs> produse = new ArrayList<>();
//        if(restaurant.getMeniu().isEmpty()){
//            System.out.println("Restaurantul nu are produse in meniu.");
//            return;
//        }
//
//        System.out.println("Produse disponibile in meniul restaurantului:");
//        for(int i = 0; i < restaurant.getMeniu().size(); i++){
//            Produs produsMeniu = restaurant.getMeniu().get(i);
//            System.out.println((i + 1) + ". " + produsMeniu.getNume() + " [" + produsMeniu.getTip() + "] - " + produsMeniu.getPret() + " RON");
//        }
//
//        for(int i = 0; i < nrProduse; i++){
//            System.out.print("Alege index produs pentru pozitia " + (i + 1) + ": ");
//            int indexProdus = Integer.parseInt(SCANNER.nextLine().trim()) - 1;
//            if(indexProdus < 0 || indexProdus >= restaurant.getMeniu().size()){
//                System.out.println("Index produs invalid.");
//                return;
//            }
//            produse.add(restaurant.getMeniu().get(indexProdus));
//        }
//
//        Comanda comanda = new Comanda(
//                idComanda,
//                client,
//                restaurant,
//                livrator,
//                produse,
//                StatusComanda.INITIALIZATA
//        );
//        COMANDA_SERVICE.addComanda(comanda);
//        System.out.println("Comanda inregistrata cu succes. Total: " + comanda.getPretTotal() + " RON.");
//    }
//
//    private static void stergeUtilizator(){
//        System.out.print("ID utilizator: ");
//        int idUtilizator = Integer.parseInt(SCANNER.nextLine().trim());
//
//        Utilizator utilizatorGasit = null;
//        for(Utilizator utilizator : UTILIZATOR_SERVICE.getAllUtilizatori()){
//            if(utilizator.getId() == idUtilizator){
//                utilizatorGasit = utilizator;
//                break;
//            }
//        }
//
//        if(utilizatorGasit == null){
//            System.out.println("Utilizatorul nu exista.");
//            return;
//        }
//
//        UTILIZATOR_SERVICE.stergeUtilizator(utilizatorGasit);
//        System.out.println("Utilizator sters cu succes.");
//    }
//
//    private static void stergeRestaurant(){
//        System.out.print("Nume restaurant: ");
//        String numeRestaurant = SCANNER.nextLine().trim();
//        Restaurant restaurant = RESTAURANT_SERVICE.getRestaurantByNume(numeRestaurant);
//
//        if(restaurant == null){
//            System.out.println("Restaurantul nu exista.");
//            return;
//        }
//
//        RESTAURANT_SERVICE.stergeRestaurant(restaurant);
//        System.out.println("Restaurant sters cu succes.");
//    }
//
//    private static void stergeComanda(){
//        System.out.print("ID comanda: ");
//        int idComanda = Integer.parseInt(SCANNER.nextLine().trim());
//        Comanda comanda = COMANDA_SERVICE.getComandaById(idComanda);
//
//        if(comanda == null){
//            System.out.println("Comanda nu exista.");
//            return;
//        }
//
//        COMANDA_SERVICE.stergeComanda(comanda);
//        System.out.println("Comanda stearsa cu succes.");
//    }
//
//    private static CardBancar citesteCard(){
//        System.out.print("ID card: ");
//        int idCard = Integer.parseInt(SCANNER.nextLine().trim());
//        CardBancar card = new CardBancar(idCard, "", "", "", 1, 2000);
//        card.citeste(SCANNER);
//        return card;
//    }
//
//    private static Produs citesteProdus(){
//        System.out.print("Tip produs (1 - Mancare, 2 - Desert, 3 - Bautura): ");
//        String tip = SCANNER.nextLine().trim();
//        System.out.print("Cod produs: ");
//        CodProdus cod = new CodProdus(SCANNER.nextLine().trim());
//        if("1".equals(tip)){
//            Mancare mancare = new Mancare(cod, "", "", 0.0, 0, false, false, 0, 0);
//            mancare.citeste(SCANNER);
//            return mancare;
//        }
//
//        if("2".equals(tip)){
//            Desert desert = new Desert(cod, "", "", 0.0, 0, false, false, false, false);
//            desert.citeste(SCANNER);
//            return desert;
//        }
//
//        if("3".equals(tip)){
//            Bautura bautura = new Bautura(cod, "", "", 0.0, 0, false, false, 0.0, false);
//            bautura.citeste(SCANNER);
//            return bautura;
//        }
//
//        throw new IllegalArgumentException("Tip de produs invalid.");
//    }

    private static void incarcaDatePredefinite(){
//
    }
}
