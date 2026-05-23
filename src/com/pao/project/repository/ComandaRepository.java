package com.pao.project.repository;

import com.pao.project.models.Comanda;
import com.pao.project.util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComandaRepository implements Repository<Comanda, Integer> {

    private Connection getConnection() throws SQLException, IOException {
        return DatabaseConnection.getInstance().getConnection();
    }

    private Comanda mapRow(ResultSet rs) throws SQLException {
        Comanda c = new Comanda();
        c.setId(rs.getInt("id"));
        c.setClient(rs.getInt("client_id"));
        c.setRestaurant(rs.getInt("restaurant_id"));
        c.setLivrator(rs.getInt("livrator_id"));
        c.setPretTotal(rs.getDouble("pret_total"));
        c.setStatus(rs.getString("status"));
        return c;
    }

    @Override
    public void save(Comanda c) throws SQLException {
        String sql = "INSERT INTO comanda (client_id, restaurant_id, livrator_id, pret_total, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, c.getClient_id());
            ps.setInt(2, c.getRestaurant());
            ps.setInt(3, c.getLivrator());
            ps.setDouble(4, c.getPretTotal());
            ps.setString(5, c.getStatus());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    c.setId(keys.getInt(1));
                }
            }
        } catch (IOException e) {
            throw new SQLException("Eroare la obtinerea conexiunii: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Comanda> findById(Integer id) throws SQLException {
        String sql = "SELECT id, client_id, restaurant_id, livrator_id, pret_total, status FROM comanda WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
                return Optional.empty();
            }
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<Comanda> findAll() throws SQLException {
        String sql = "SELECT id, client_id, restaurant_id, livrator_id, pret_total, status FROM comanda ORDER BY id";
        List<Comanda> list = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (IOException e) {
            throw new SQLException(e);
        }
        return list;
    }

    @Override
    public void update(Comanda c) throws SQLException {
        String sql = "UPDATE comanda SET client_id = ?, restaurant_id = ?, livrator_id = ?, pret_total = ?, status = ? WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, c.getClient_id());
            ps.setInt(2, c.getRestaurant());
            ps.setInt(3, c.getLivrator());
            ps.setDouble(4, c.getPretTotal());
            ps.setString(5, c.getStatus());
            ps.setInt(6, c.getId());
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM comanda WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }
}
