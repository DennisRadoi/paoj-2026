package com.pao.project.repository;

import com.pao.project.models.Utilizator;
import com.pao.project.util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilizatorRepository implements Repository<Utilizator, Integer> {

    private Connection getConnection() throws SQLException, IOException {
        return DatabaseConnection.getInstance().getConnection();
    }

    private Utilizator mapRow(ResultSet rs) throws SQLException {
        Utilizator r = new Utilizator();
        r.setId(rs.getInt("id"));
        r.setData_nasterii(rs.getString("data_nasterii"));
        r.setTelefon(rs.getString("telefon"));
        r.setEmail(rs.getString("email"));
        r.setVarsta(rs.getInt("varsta"));
        r.setNume(rs.getString("nume"));
        return r;
    }

    @Override
    public void save(Utilizator r) throws SQLException {
        String sql = "INSERT INTO utilizator (data_nasterii, telefon, email, varsta, nume) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, r.getData_nasterii());
            ps.setString(2, r.getTelefon());
            ps.setString(3, r.getEmail());
            ps.setInt(4, r.getVarsta());
            ps.setString(5, r.getNume());
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
    public Optional<Utilizator> findById(Integer id) throws SQLException {
        String sql = "SELECT id, data_nasterii, telefon, email, nume FROM utilizator WHERE id = ?";
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
    public List<Utilizator> findAll() throws SQLException {
        String sql = "SELECT id, data_nasterii, telefon, email, nume FROM utilizator ORDER BY id";
        List<Utilizator> list = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (IOException e) {
            throw new SQLException(e);
        }
        return list;
    }

    @Override
    public void update(Utilizator r) throws SQLException {
        String sql = "UPDATE utilizator SET telefon = ?, email = ?, varsta = ? WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, r.getTelefon());
            ps.setString(2, r.getEmail());
            ps.setInt(3, r.getVarsta());
            ps.setInt(4, r.getId());
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM utilizator WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }
}
