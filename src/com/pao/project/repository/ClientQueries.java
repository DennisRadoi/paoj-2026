package com.pao.project.repository;

import com.pao.project.util.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper queries related to clients (read-only analytical queries).
 */
public class ClientQueries {

    /**
     * Q5 - Recurrent clients: clients with at least minOrders orders and their average order value.
     * Returns list of strings: "nume | email | orders_count | avg_order"
     */
    public List<String> findRecurrentClients(int minOrders) throws SQLException, IOException {
        String sql = "SELECT u.id, u.nume, u.email, COUNT(com.id) AS orders_count, AVG(com.pret_total) AS avg_order " +
                "FROM client c " +
                "JOIN utilizator u ON c.id = u.id " +
                "JOIN comanda com ON com.client_id = c.id " +
                "GROUP BY u.id, u.nume, u.email " +
                "HAVING COUNT(com.id) >= ? " +
                "ORDER BY orders_count DESC, avg_order DESC";

        List<String> result = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, minOrders);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String row = rs.getString("nume") + " | "
                            + rs.getString("email") + " | "
                            + rs.getInt("orders_count") + " | "
                            + rs.getDouble("avg_order");
                    result.add(row);
                }
            }
        }
        return result;
    }
}
