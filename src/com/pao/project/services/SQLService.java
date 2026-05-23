package com.pao.project.services;

import com.pao.project.models.*;
import com.pao.project.util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SQLService {

    private static SQLService instance;

    private SQLService() {}

    public static SQLService getInstance() {
        if (instance == null) instance = new SQLService();
        return instance;
    }

    private Connection getConn() throws SQLException, IOException {
        return DatabaseConnection.getInstance().getConnection();
    }

    /*
    Interogarea 1 sa se returneze clientii de peste 17 ani care au comandat cel putin un produs de la Restauruntul
    cu adresa_id = 1;
     */
    public List<String> Q1() throws SQLException, IOException {
        String sql = """
                SELECT DISTINCT u.nume AS nume, u.email AS email, u.varsta AS varsta, c.adresa_id AS adresa_id
                FROM client c
                JOIN utilizator u ON c.id = u.id
                JOIN comanda com ON com.client_id = c.id
                JOIN comanda_produs cp ON cp.comanda_id = com.id
                JOIN produs p ON p.cod = cp.produs_cod
                JOIN restaurant r ON p.restaurant_id = r.id
                WHERE u.varsta > 17 AND r.adresa_id = 1
                ORDER BY u.nume
                """;
        List<String> results = new ArrayList<>();
        try (PreparedStatement ps = getConn().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                results.add(String.format("Clientul %s, cu mail-ul: %s, are %d (de) ani si adresa lui are id-ul %d.",
                        rs.getString("nume"),
                        rs.getString("email"),
                        rs.getInt("varsta"),
                        rs.getInt("adresa_id")));
            }
        }
        return results;
    }

    /*
    Interogarea 2
    Sa se afiseze cele mai comandate produse, restaurantul din meniul din care provin si de cate ori au fost acestea comandate
     */
    public List<String> Q2() throws SQLException, IOException {
        String sql = """
                WITH counts AS 
                ( 
                SELECT p.cod AS produs_id, p.nume AS nume_produs, r.nume AS nume_restaurant, COUNT(*) AS cnt 
                FROM comanda_produs cp 
                JOIN produs p ON cp.produs_cod = p.cod 
                JOIN restaurant ON r.id = p.restaurant_id
                GROUP BY p.cod, p.nume, p.restaurant_id 
                )
                 
                SELECT cod, nume, restaurant_id, cnt 
                FROM counts 
                WHERE cnt = 
                (SELECT MAX(cnt) FROM counts);
                """;
        List<String> results = new ArrayList<>();
        try (PreparedStatement ps = getConn().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                results.add(String.format("Produsul #s, numele lui fiind %s, din meniul restaurantului %s, a fost comandat de %d (de) ori.",
                        rs.getString("produs_id"),
                        rs.getString("nume_produs"),
                        rs.getString("nume_restaurant"),
                        rs.getInt("cnt")));
            }
        }
        return results;
    }

    /*
    Interogarea 3
    Clienti recurenti cu mai mult de 2 sau chiar 2 comenzi finalizate la
    restaurante al caror nume incepe cu litera p
     */

    public List<String> Q3() throws SQLException, IOException {
        String sql = """ 
                SELECT u.nume AS nume, u.email AS email, COUNT(com.id) AS com_cnt, AVG(com.pret_total) AS avg_comanda
                FROM client c 
                JOIN utilizator u ON c.id = u.id 
                JOIN comanda com ON com.client_id = c.id
                JOIN comanda_prouds cp ON com.id = cp.comanda_id
                JOIN restaurant r ON com.restaurant_id = r.id
                WHERE r.nume LIKE "P%"
                GROUP BY u.id, u.nume, u.email 
                HAVING COUNT(com.id) >= 2 
                ORDER BY com_cnt DESC, avg_comanda DESC
                """;
        List<String> results = new ArrayList<>();
        try (PreparedStatement ps = getConn().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                results.add(String.format("Clientul, numele lui fiind %s, email: %s, a comandat de %d (de) ori, pretul mediu al unei comenzi fiind %f.",
                        rs.getString("nume"),
                        rs.getString("email"),
                        rs.getInt("com_cnt")));
                        rs.getDouble("avg_comanda");
            }
        }
        return results;
    }
}