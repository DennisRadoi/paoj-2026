package com.pao.project.repository;

import com.pao.project.models.Produs;
import com.pao.project.util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdusRepository implements Repository<Produs, Integer> {

    private Connection getConnection() throws SQLException, IOException {
        return DatabaseConnection.getInstance().getConnection();
    }

    private Produs mapRow(ResultSet rs) throws SQLException {
        Produs p = new Produs();
        p.setId(rs.getInt("id"));
        p.setNume(rs.getString("nume"));
        p.setDescriere(rs.getString("descriere"));
        p.setPret(rs.getDouble("pret"));
        p.setRestaurant_id(rs.getInt("restaurant_id"));
        return p;
    }

    @Override
    public void save(Produs p) throws SQLException {
        String sql = "INSERT INTO produs (nume, descriere, pret, restaurant_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNume());
            ps.setString(2, p.getDescriere());
            ps.setDouble(3, p.getPret());
            ps.setInt(4, p.getRestaurant());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    p.setId(keys.getInt(1));
                }
            }
        } catch (IOException e) {
            throw new SQLException("Eroare la obtinerea conexiunii: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Produs> findById(Integer id) throws SQLException {
        String sql = "SELECT id, nume, descriere, pret, restaurant_id FROM produs WHERE id = ?";
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
    public List<Produs> findAll() throws SQLException {
        String sql = "SELECT id, nume, descriere, pret, restaurant_id FROM produs ORDER BY id";
        List<Produs> list = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (IOException e) {
            throw new SQLException(e);
        }
        return list;
    }

    @Override
    public void update(Produs p) throws SQLException {
        String sql = "UPDATE produs SET nume = ?, descriere = ?, pret = ?, restaurant_id = ? WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, p.getNume());
            ps.setString(2, p.getDescriere());
            ps.setDouble(3, p.getPret());
            ps.setInt(4, p.getRestaurant());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM produs WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }
}
