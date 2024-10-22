package dev.ana.dao;

import dev.ana.models.Endereco;

import java.sql.*;

public class EnderecoDAO {
    private final Connection connection;

    public EnderecoDAO(Connection connection) {
        this.connection = connection;
    }

    public int buscarOuCriar(Endereco endereco) {
        String consulta = "SELECT id FROM Endereco WHERE pais = ? AND estado = ? AND cidade = ? AND rua = ? AND cep = ? AND numero = ? AND complemento = ?";
        String inserir = "INSERT INTO Endereco (pais, estado, cidade, rua, cep, numero, complemento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            try (PreparedStatement buscarNoDB = connection.prepareStatement(consulta)) {
                buscarNoDB.setString(1, endereco.getPais());
                buscarNoDB.setString(2, endereco.getEstado());
                buscarNoDB.setString(3, endereco.getCidade());
                buscarNoDB.setString(4, endereco.getRua());
                buscarNoDB.setString(5, endereco.getCEP());
                buscarNoDB.setString(6, endereco.getNumero());
                buscarNoDB.setString(7, endereco.getComplemento());
                ResultSet resultadoConsulta = buscarNoDB.executeQuery();

                if (resultadoConsulta.next()) {
                    return resultadoConsulta.getInt("id");
                }
            }

            try (PreparedStatement salvarEndereco = connection.prepareStatement(inserir, Statement.RETURN_GENERATED_KEYS)) {
                salvarEndereco.setString(1, endereco.getPais());
                salvarEndereco.setString(2, endereco.getEstado());
                salvarEndereco.setString(3, endereco.getCidade());
                salvarEndereco.setString(4, endereco.getRua());
                salvarEndereco.setString(5, endereco.getCEP());
                salvarEndereco.setString(6, endereco.getNumero());
                salvarEndereco.setString(7, endereco.getComplemento());
                salvarEndereco.executeUpdate();

                ResultSet resultadoInsert = salvarEndereco.getGeneratedKeys();
                if (resultadoInsert.next()) {
                    return resultadoInsert.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
