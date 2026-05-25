package com.pao.project.repository;

import com.pao.project.models.*;
import com.pao.project.util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository implements Repository<Client, Integer> {

    private Connection getConnection() throws SQLException, IOException {
        return DatabaseConnection.getInstance().getConnection();
    }

    private Client mapRow(ResultSet rs) throws SQLException {
        Client r = new Client();
        r.setId(rs.getInt("id"));
        r.setData_nasterii(rs.getString("data_nasterii"));
        r.setTelefon(rs.getString("telefon"));
        r.setEmail(rs.getString("email"));
        r.setVarsta(rs.getInt("varsta"));
        r.setNume(rs.getString("nume"));
        r.setAdresa(rs.getInt("adresa_id"));
        return r;
    }

    @Override
    public void save(Client r) throws SQLException {
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

            String insertClientSql =
                    """
                    INSERT INTO client (id, adresa_id)
                    VALUES (?, ?)
                    """;

            try (PreparedStatement ps = connection.prepareStatement(insertClientSql)) {
                ps.setInt(1, r.getId());
                ps.setInt(2, r.getAdresa());
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
    public Optional<Client> findById(Integer id) throws SQLException {
        String sql = """
            SELECT u.id, u.data_nasterii, u.telefon, u.email, u.varsta, u.nume,
                   c.adresa_id,
            FROM client c
            JOIN utilizator u ON c.id = u.id
            WHERE u.id = ?
            """;
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
    public List<Client> findAll() throws SQLException {
        String sql = """
            SELECT u.id, u.data_nasterii, u.telefon, u.email, u.varsta, u.nume,
                   c.adresa_id
            FROM livrator l
            JOIN utilizator u ON l.id = u.id
            ORDER BY u.id
            """;
        List<Client> list = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (IOException e) {
            throw new SQLException(e);
        }
        return list;
    }

    @Override
    public void update(Client r) throws SQLException {
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

            String updateClientSql =
                        """
                        UPDATE client
                        SET adresa
                        WHERE id = ?
                        """;
            try (PreparedStatement ps = getConnection().
                prepareStatement(updateClientSql)) {
                ps.setInt(1, r.getAdresa());
                ps.setInt(2, r.getId());

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
