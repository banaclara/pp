package dev.ana.dao;

import dev.ana.conn.Database;
import dev.ana.models.Telefone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class TelefoneDAO {

    private final Connection connection;

    public TelefoneDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvarTelefone(Telefone telefone, UUID pessoaId) {
        UUID telId = UUID.randomUUID();
        String inserirTelefone = "INSERT INTO Telefone (id, ddd, numero, pessoa_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement salvarTelefone = connection.prepareStatement(inserirTelefone)) {
            salvarTelefone.setObject(1, telId);
            salvarTelefone.setString(2, telefone.getDdd());
            salvarTelefone.setString(3, telefone.getNumero());
            salvarTelefone.setObject(4, pessoaId);
            salvarTelefone.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
