package com.pao.project.repository;

import com.pao.project.models.Restaurant;
import com.pao.project.util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantRepository implements Repository<Restaurant, Integer> {

    private Connection getConnection() throws SQLException, IOException {
        return DatabaseConnection.getInstance().getConnection();
    }

    private Restaurant mapRow(ResultSet rs) throws SQLException {
        Restaurant r = new Restaurant();
        r.setId(rs.getInt("id"));
        r.setNume(rs.getString("nume"));
        r.setAdresa(rs.getInt("adresa_id"));
        return r;
    }

    @Override
    public void save(Restaurant r) throws SQLException {
        String sql = "INSERT INTO restaurant (nume, adresa_id) VALUES (?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, r.getNume());
            ps.setInt(2, r.getAdresa());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    r.setId(keys.getInt(1));
                }
            }
        } catch (IOException e) {
            throw new SQLException("Eroare la obtinerea conexiunii: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Restaurant> findById(Integer id) throws SQLException {
        String sql = "SELECT id, nume, adresa_id FROM restaurant WHERE id = ?";
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
    public List<Restaurant> findAll() throws SQLException {
        String sql = "SELECT id, nume, adresa_id FROM restaurant ORDER BY cod";
        List<Restaurant> list = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (IOException e) {
            throw new SQLException(e);
        }
        return list;
    }

    @Override
    public void update(Restaurant r) throws SQLException {
        String sql = "UPDATE restaurant SET nume = ?, adresa_id = ? WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, r.getNume());
            ps.setInt(2, r.getAdresa());
            ps.setInt(3, r.getId());
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM restaurant WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }
}
