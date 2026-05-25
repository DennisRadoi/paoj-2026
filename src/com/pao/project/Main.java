package com.pao.project;

import com.pao.project.models.*;
import com.pao.project.services.*;
import com.pao.project.repository.*;
import com.pao.project.util.DatabaseConnection;

import java.io.IOException;
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
    static ComandaProdusRepository cpr = new ComandaProdusRepository();
    static SQLService ss = SQLService.getInstance();

    public static void main(String[] args) throws SQLException, IOException {
        Adresa adr1 = new Adresa("Calarasi", "Calarasi", "Zefirului", 26, "I19", 2, 22);
        Adresa adr2 = new Adresa("Bucuresti", "Sector 6", "Precizei", 24, "B2", 1, 985);
        Adresa adr3 = new Adresa("Bucuresti", "Sector 1", "Calea Victoriei", 10, "A", 1, 2);
        Adresa adr4 = new Adresa("Bucuresti", "Sector 3", "Bulevardul Unirii", 25, "B", 2, 14);
        Adresa adr5 = new Adresa("Bucuresti", "Sector 6", "Preciziei", 24, "B2", 1, 985);
        Adresa adr6 = new Adresa("Ilfov", "Voluntari", "Eroilor", 5, "", 0, 0);
        adr.save(adr1);
        adr.save(adr2);
        adr.save(adr3);
        adr.save(adr4);
        adr.save(adr5);
        adr.save(adr6);

        Restaurant r1 = new Restaurant("Pizzeria Galleto", adr3.getId());
        Restaurant r2 = new Restaurant("Sushi Zen", adr4.getId());
//        Restaurant r3 = new Restaurant("Pasta Trapezului", adr6.getId());

        rr.save(r1);
        rr.save(r2);
//        rr.save(r3);

        Produs p1 = new Produs("Pizza Margherita", "Blat, sos de rosii, mozzarella", 32.50, r1.getId());
        Produs p2 = new Produs("Paste Carbonara", "Paste, ou, pancetta, parmezan", 38.00, r1.getId());
        Produs p3 = new Produs("Cola 500ml", "Bautura carbogazoasa", 8.50, r1.getId());
        Produs p4 = new Produs("Sushi Set", "12 piese sushi", 55.00, r2.getId());

        pr.save(p1);
        pr.save(p2);
        pr.save(p3);
        pr.save(p4);

        Client c1 = new Client("2001-04-21", "0788999000", "andrei@example.com", 25, "Andrei Alex", adr5.getId());
        Client c2 = new Client("1999-11-02", "0722000000", "maria@example.com", 27, "Maria Popescu", adr6.getId());

        clr.save(c1);
        clr.save(c2);

        Livrator l1 = new Livrator("2005-03-26", "0734565780", "abdul@example.com", 21, "Abdul Abdul", "Bicicleta", 0);
        Livrator l2 = new Livrator("1998-07-10", "0711000000", "ion@example.com", 28, "Ion Ionescu", "Scuter", 1);

        lr.save(l1);
        lr.save(l2);

        Comanda com1 = new Comanda(c1.getId(), r1.getId(), l1.getId(), 41.00, "LIVRATA");
        Comanda com2 = new Comanda(c1.getId(), r1.getId(), l2.getId(), 70.50, "LIVRATA");
        Comanda com3 = new Comanda(c2.getId(), r2.getId(), l2.getId(), 55.00, "LIVRATA");
        Comanda com4 = new Comanda(c2.getId(), r1.getId(), l1.getId(), 32.50, "IN_LIVRARE");

        cr.save(com1);
        cr.save(com2);
        cr.save(com3);
        cr.save(com4);

        cpr.save(new ComandaProdus(com1.getId(), p1.getId()));
        cpr.save(new ComandaProdus(com2.getId(), p1.getId()));
        cpr.save(new ComandaProdus(com2.getId(), p2.getId()));
        cpr.save(new ComandaProdus(com3.getId(), p4.getId()));
        cpr.save(new ComandaProdus(com4.getId(), p1.getId()));


        System.out.println("ETAPA 2 PROIECT\n");

        // Actiunea 1 -> ADAUGA Restaurant
        Restaurant r3 = new Restaurant("Pizzeria ROYAL FOODS", adr1.getId());
        rr.save(r3);
        audit.log("add_resaurant");
        System.out.println("1. Restaurant adaugat " + r3);

        // Actiunea 2 -> ADAUGA produs
        Produs p5 = new Produs("Pizza", "Blat cu sos", 30, r1.getId());
        pr.save(p5);
        audit.log("add_produs");
        System.out.println("2. Produs adaugat " + p5);

        // Actiunea 3 -> Adauga client
        Client c3 = new Client("21-04-2001", "0788999000", "miau@gmail.com", 25, "Andrei Alex", adr2.getId());
        clr.save(c3);
        audit.log("add_client");
        System.out.println("3. Client adaugat " + c3);

        // Actiunea 4 -> Adauga livrator
        Livrator l3 = new Livrator("26-03-2005", "0734565780", "abdul@gmail.com", 21, "Abdul Abdul" ,"Bicicleta", 0);
        lr.save(l3);
        audit.log("add_livrator");
        System.out.println("4. Livrator adaugat " + l3);

        // Actiunea 5 -> Adauga comanda
        Comanda com5 = new Comanda(c1.getId(), r1.getId(), l1.getId(), 60, "IN_LIVRARE");
        cr.save(com5);
        audit.log("add_comanda");
        System.out.println("5. Comanda adaugat " + com5);

        cpr.save(new ComandaProdus(com5.getId(), p3.getId()));

        // Actiunea 6 -> Tranzactia
        System.out.println("6. ");
        ss.tranzactie(com4.getId());
        cr.findById(com4.getId()).ifPresent(c -> System.out.println(c));
        audit.log("anuleaza_comanda");

        // Actiunea 7 -> Q1
        System.out.println("7. Q1");
        for (String s : ss.Clienti_majori()) {
            System.out.println(s);
        }
        audit.log("clienti_majori");

        // Actiunea 8 -> Q2
        System.out.println("8. Q2");
        for (String s : ss.Cele_mai_comandate_produse()) {
            System.out.println(s);
        }
        audit.log("cele_mai_comandate_produse");

        // Actiunea 9 -> Q3
        System.out.println("9. Q3");
        for (String s : ss.Clienti_recurenti()) {
            System.out.println(s);
        }
        audit.log("clienti_recurenti");

        // Actiunea 10 -> Listeaza toate comenzile
        System.out.println("10.");
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
        rr.update2(r1, "Pasta Trapezului", adr6.getId());
        System.out.println("12. Restaurantul cu id " + r1.getId() + " a fost actualizat");
        rr.findById(r1.getId()).ifPresentOrElse(
                r -> System.out.println("Dupa update " + r),
                () -> System.out.println("NU A FOST GASIT")
        );
        audit.log("actualizeaza_restaurant");

        // Actiunea 13 -> Sterge un restaurant
        System.out.println("13.");
        rr.delete(r1.getId());
        rr.findById(r1.getId()).ifPresentOrElse(
                r -> System.out.println(r),
                () -> System.out.println("NU A FOST GASIT")
        );

        audit.log("remove_restaurant");
        System.out.println("A fost sters restaurantul cu id " + r1.getId());

        DatabaseConnection.getInstance().close();
    }

}
