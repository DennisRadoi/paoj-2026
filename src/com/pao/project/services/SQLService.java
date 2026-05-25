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
    Tranzactie:
    marcam o comanda anulata de catre client
     */
    public void tranzactie(int comanda_id) throws SQLException, IOException {
        Connection connection = getConn();
        connection.setAutoCommit(false);
        try{
            int livrator_id;
            String sql1 = """
                    SELECT id, client_id, restaurant_id, livrator_id, pret_total, status 
                    FROM comanda 
                    WHERE id = ?
                    """;
            try (PreparedStatement ps = connection.prepareStatement(sql1)) {
                ps.setInt(1, comanda_id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        throw new SQLException("Comanda cu id " + comanda_id + " nu exista.");
                    }
                    livrator_id = rs.getInt("livrator_id");
                }
            }
            // continuam inseamna ca a fost gasita comanda

            // ne asiguram ca si livratorul exista
            String sql2 = """
                    SELECT id
                    FROM utilizator
                    WHERE id = ?
                    """;
            try (PreparedStatement ps = connection.prepareStatement(sql2)) {
                ps.setInt(1, livrator_id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        throw new SQLException("Livratorul cu id " + livrator_id + " nu exista.");
                    }
                }
            }
            // update la statusul comenzii o marcam anulata
            String sql3 = """
                    UPDATE comanda SET status = "ANULATA" 
                    WHERE id = ?
                    """;
            try (PreparedStatement ps = connection.prepareStatement(sql3)) {
                ps.setInt(1, comanda_id);
                ps.executeUpdate();
            }

            // update la livrator il marcam disponibil sa preia o comanda noua
            String sql4 = """
                    UPDATE livrator SET este_disponibil = 1 WHERE id = ?
                    """;
            try (PreparedStatement ps = connection.prepareStatement(sql4)) {
                ps.setInt(1, livrator_id);
                ps.executeUpdate();
            }
            connection.commit();
            System.out.println("Comanda cu id-ul " + comanda_id + " a fost anulata.");
        } catch (SQLException e){
            connection.rollback();
            throw e;
        }
        finally {
            connection.setAutoCommit(true);
        }
    }
    /*
    Interogarea 1 sa se returneze clientii de peste 17 ani care au comandat cel putin un produs de la Restaurunte din Sector 1;
     */
    public List<String> Clienti_majori() throws SQLException, IOException {
        String sql = """
                SELECT DISTINCT u.nume AS nume, u.email AS email, u.varsta AS varsta, c.adresa_id AS adresa_id
                FROM client c
                JOIN utilizator u ON c.id = u.id
                JOIN comanda com ON com.client_id = c.id
                JOIN restaurant r ON com.restaurant_id = r.id
                JOIN adresa a ON a.id = r.adresa_id
                WHERE u.varsta > 17 AND a.localitate = "Sector 1"
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
    public List<String> Cele_mai_comandate_produse() throws SQLException, IOException {
        String sql = """
                WITH counts AS
                (
                SELECT p.id AS produs_id, p.nume AS nume_produs, r.nume AS nume_restaurant, COUNT(*) AS cnt
                FROM comanda_produs cp
                JOIN produs p ON cp.produs_id = p.id
                JOIN restaurant r ON r.id = p.restaurant_id
                GROUP BY p.id, p.nume, r.nume
                )

                SELECT produs_id, nume_produs, nume_restaurant, cnt
                FROM counts
                WHERE cnt =
                (SELECT MAX(cnt) FROM counts);
                """;
        List<String> results = new ArrayList<>();
        try (PreparedStatement ps = getConn().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                results.add(String.format("Produsul %d, numele lui fiind %s, din meniul restaurantului %s, a fost comandat de %d (de) ori.",
                        rs.getInt("produs_id"),
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

    public List<String> Clienti_recurenti() throws SQLException, IOException {
        String sql = """ 
                SELECT u.nume AS nume, u.email AS email, COUNT(com.id) AS com_cnt, AVG(com.pret_total) AS avg_comanda
                FROM client c 
                JOIN utilizator u ON c.id = u.id 
                JOIN comanda com ON com.client_id = c.id
                JOIN restaurant r ON com.restaurant_id = r.id
                WHERE r.nume LIKE "P%" AND com.status = "LIVRATA"
                GROUP BY u.id, u.nume, u.email 
                HAVING COUNT(com.id) >= 2 
                ORDER BY com_cnt DESC, avg_comanda DESC
                """;
        List<String> results = new ArrayList<>();
        try (PreparedStatement ps = getConn().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                results.add(String.format("Clientul, numele lui fiind %s, email: %s, a comandat de %d (de) ori, pretul mediu al unei comenzi fiind %.2f.",
                        rs.getString("nume"),
                        rs.getString("email"),
                        rs.getInt("com_cnt"),
                        rs.getDouble("avg_comanda")));
            }
        }
        return results;
    }
}
