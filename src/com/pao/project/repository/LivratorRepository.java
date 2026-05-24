package com.pao.project.repository;

import com.pao.project.models.*;
import com.pao.project.util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivratorRepository implements Repository<Livrator, Integer> {

    private Connection getConnection() throws SQLException, IOException {
        return DatabaseConnection.getInstance().getConnection();
    }

    private Livrator mapRow(ResultSet rs) throws SQLException {
        Livrator r = new Livrator();
        r.setId(rs.getInt("id"));
        r.setData_nasterii(rs.getString("data_nasterii"));
        r.setTelefon(rs.getString("telefon"));
        r.setEmail(rs.getString("email"));
        r.setVarsta(rs.getInt("varsta"));
        r.setNume(rs.getString("nume"));
        r.setVehicul(rs.getString("vehicul"));
        r.setEsteDisponibil(rs.getInt("esteDisponibil"));
        return r;
    }

    @Override
    public void save(Livrator r) throws SQLException {
        Connection connection;
        try {
            connection = getConnection();
        } catch (IOException e) {
            throw new SQLException("Eroare la obtinerea conexiunii", e);
        }
        connection.setAutoCommit(false);
        try {
            String insertUtilizatorSql =
                    """
                    INSERT INTO utilizator (data_nasterii, telefon, email, varsta, nume)
                    VALUES (?, ?, ?, ?, ?)
                    """;

            try (PreparedStatement ps = connection.prepareStatement(
                    insertUtilizatorSql,
                    Statement.RETURN_GENERATED_KEYS
            )) {
                ps.setString(1, r.getData_nasterii());
                ps.setString(2, r.getTelefon());
                ps.setString(3, r.getEmail());
                ps.setInt(4, r.getVarsta());
                ps.setString(5, r.getNume());

                ps.executeUpdate();

                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (!keys.next()) {
                        throw new SQLException("Nu s-a putut obtine id-ul utilizatorului.");
                    }

                    r.setId(keys.getInt(1));
                }
            }

            String insertLivratorSql =
                    """
                    INSERT INTO livrator (id, vehicul, este_disponibil)
                    VALUES (?, ?, ?)
                    """;

            try (PreparedStatement ps = connection.prepareStatement(insertLivratorSql)) {
                ps.setInt(1, r.getId());
                ps.setString(2, r.getVehicul());
                ps.setInt(3, r.isEsteDisponibil());
                ps.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public Optional<Livrator> findById(Integer id) throws SQLException {
        String sql = "SELECT id, vehicul, este_disponibil FROM livrator WHERE id = ?";
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
    public List<Livrator> findAll() throws SQLException {
        String sql = "SELECT id, vehicul, este_disponibil FROM livrator ORDER BY id";
        List<Livrator> list = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (IOException e) {
            throw new SQLException(e);
        }
        return list;
    }

    @Override
    public void update(Livrator r) throws SQLException {
        Connection connection;
        try {
            connection = getConnection();
        } catch (IOException e) {
            throw new SQLException("Eroare la obtinerea conexiunii", e);
        }
        connection.setAutoCommit(false);
        try {
            String updateUtilizatorSql =
                    """
                    UPDATE utilizator
                    SET data_nasterii = ?, telefon = ?, email = ?, varsta = ?, nume = ?
                    WHERE id = ?
                    """;
            try (PreparedStatement ps = getConnection().
                    prepareStatement(updateUtilizatorSql))
            {
                ps.setString(1, r.getData_nasterii());
                ps.setString(2, r.getTelefon());
                ps.setString(3, r.getEmail());
                ps.setInt(4, r.getVarsta());
                ps.setString(5, r.getNume());
                ps.setInt(6, r.getId());
                ps.executeUpdate();
            } catch (IOException e) {
                throw new SQLException(e);
            }

            String updateLivratorSql =
                    """
                    UPDATE livrator
                    SET vehicul = ?, este_disponibil = ?
                    WHERE id = ?
                    """;
            try (PreparedStatement ps = getConnection().
                    prepareStatement(updateLivratorSql)) {
                ps.setString(1, r.getVehicul());
                ps.setInt(2, r.isEsteDisponibil());
                ps.setInt(3, r.getId());
                ps.executeUpdate();
            } catch (IOException e) {
                throw new SQLException(e);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
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

