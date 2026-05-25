package com.pao.project.repository;

import com.pao.project.models.*;
import com.pao.project.util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComandaProdusRepository implements Repository<ComandaProdus, Integer> {

    private Connection getConnection() throws SQLException, IOException {
        return DatabaseConnection.getInstance().getConnection();
    }

    private ComandaProdus mapRow(ResultSet rs) throws SQLException {
        ComandaProdus cp = new ComandaProdus();
        cp.setId(rs.getInt("id"));
        cp.setComanda_id(rs.getInt("comanda_id"));
        cp.setProdus_id(rs.getInt("produs_id"));
        return cp;

    }

    @Override
    public void save(ComandaProdus cp) throws SQLException {
        String sql = "INSERT INTO comanda_produs (comanda_id, produs_id) VALUES (?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cp.getComanda_id());
            ps.setInt(2, cp.getProdus_id());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    cp.setId(keys.getInt(1));
                }
            }
        } catch (IOException e) {
            throw new SQLException("Eroare la obtinerea conexiunii: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<ComandaProdus> findById(Integer id) throws SQLException {
        String sql = "SELECT id, comanda_id, produs_id FROM comanda_produs WHERE id = ?";
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
    public List<ComandaProdus> findAll() throws SQLException {
        String sql = "SELECT id, comanda_id, produs_id FROM comanda_produs ORDER BY id";
        List<ComandaProdus> list = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (IOException e) {
            throw new SQLException(e);
        }
        return list;
    }

    public void update(ComandaProdus cp) throws SQLException {
        String sql = "UPDATE comanda_produs SET comanda_id = ?, produs_id = ? WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, cp.getComanda_id());
            ps.setInt(2, cp.getProdus_id());
            ps.setInt(3, cp.getId());
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM comanda_produs WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }
}
