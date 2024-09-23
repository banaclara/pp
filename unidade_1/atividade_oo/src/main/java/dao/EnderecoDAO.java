package dao;

import conn.Database;
import models.Endereco;

import java.sql.*;

public class EnderecoDAO {
    private final Connection connection;

    public EnderecoDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public int buscarOuCriar(Endereco endereco) {
        String consulta = "SELECT id FROM Endereco WHERE estado = ? AND cidade = ? AND rua = ? AND complemento = ?";
        String inserir = "INSERT INTO Endereco (estado, cidade, rua, complemento) VALUES (?, ?, ?, ?)";

        try {
            try (PreparedStatement buscarNoDB = connection.prepareStatement(consulta)) {
                buscarNoDB.setString(1, endereco.getEstado());
                buscarNoDB.setString(2, endereco.getCidade());
                buscarNoDB.setString(3, endereco.getRua());
                buscarNoDB.setString(4, endereco.getComplemento());
                ResultSet rs = buscarNoDB.executeQuery();

                if (rs.next()) {
                    return rs.getInt("id");
                }
            }

            try (PreparedStatement salvarEndereco = connection.prepareStatement(inserir, Statement.RETURN_GENERATED_KEYS)) {
                salvarEndereco.setString(1, endereco.getEstado());
                salvarEndereco.setString(2, endereco.getCidade());
                salvarEndereco.setString(3, endereco.getRua());
                salvarEndereco.setString(4, endereco.getComplemento());
                salvarEndereco.executeUpdate();

                ResultSet rsInserir = salvarEndereco.getGeneratedKeys();
                if (rsInserir.next()) {
                    return rsInserir.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
