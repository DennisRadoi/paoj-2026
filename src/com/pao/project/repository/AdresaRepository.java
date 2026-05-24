package com.pao.project.repository;

import com.pao.project.models.Adresa;
import com.pao.project.util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdresaRepository implements Repository<Adresa, Integer> {

    private Connection getConnection() throws SQLException, IOException {
        return DatabaseConnection.getInstance().getConnection();
    }

    private Adresa mapRow(ResultSet rs) throws SQLException {
        Adresa p = new Adresa();
        p.setId(rs.getInt("id"));
        p.setJudet(rs.getString("judet"));
        p.setLocalitate(rs.getString("localitate"));
        p.setStrada(rs.getString("strada"));
        p.setNumar(rs.getInt("numar"));
        p.setBloc((rs.getString("bloc")));
        p.setScara(rs.getInt("scara"));
        p.setApartament(rs.getInt("apartament"));
        return p;
    }

    @Override
    public void save(Adresa p) throws SQLException {
        String sql = "INSERT INTO adresa (judet, localitate, strada, numar, bloc, scara, apartament) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getJudet());
            ps.setString(2, p.getLocalitate());
            ps.setString(3, p.getStrada());
            ps.setInt(4, p.getNumar());
            ps.setString(5, p.getBloc());
            ps.setInt(6, p.getScara());
            ps.setInt(7, p.getApartament());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    p.setId(keys.getInt(1));
                }
            }
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Optional<Adresa> findById(Integer id) throws SQLException {
        String sql = "SELECT id, judet, localitate, strada, numar, bloc, scara, apartament FROM adresa WHERE id = ?";
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
    public List<Adresa> findAll() throws SQLException {
        String sql = "SELECT id, judet, localitate, strada, numar, bloc, scara, apartament FROM adresa ORDER BY id";
        List<Adresa> list = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (IOException e) {
            throw new SQLException(e);
        }
        return list;
    }

    @Override
    public void update(Adresa p) throws SQLException {
        String sql = "UPDATE adresa SET judet = ?, localitate = ?, strada = ?, numar = ?, bloc = ?, scara = ?, apartament = ? WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, p.getJudet());
            ps.setString(2, p.getLocalitate());
            ps.setString(3, p.getStrada());
            ps.setInt(4, p.getNumar());
            ps.setString(5, p.getBloc());
            ps.setInt(6, p.getScara());
            ps.setInt(7, p.getApartament());
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM adresa WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }
}
