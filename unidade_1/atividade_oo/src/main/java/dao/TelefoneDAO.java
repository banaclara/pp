package dao;

import conn.Database;
import models.Telefone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelefoneDAO {

    private final Connection connection;

    public TelefoneDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public void salvarTelefone(Telefone telefone, int pessoaId) {
        String inserirTelefone = "INSERT INTO Telefone (ddd, numero, pessoa_id) VALUES (?, ?, ?)";
        try (PreparedStatement salvarTelefone = connection.prepareStatement(inserirTelefone)) {
            salvarTelefone.setString(1, telefone.getDdd());
            salvarTelefone.setString(2, telefone.getNumero());
            salvarTelefone.setInt(3, pessoaId);
            salvarTelefone.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
