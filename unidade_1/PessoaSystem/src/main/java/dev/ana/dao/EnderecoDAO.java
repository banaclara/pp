package dev.ana.dao;

import dev.ana.models.Endereco;

import java.sql.*;
import java.util.UUID;

public class EnderecoDAO {
    private final Connection connection;

    public EnderecoDAO(Connection connection) {
        this.connection = connection;
    }

    public UUID buscarOuCriar(Endereco endereco) {
        String consulta = "SELECT id FROM Endereco WHERE pais = ? AND estado = ? AND cidade = ? AND rua = ? AND cep = ? AND numero = ? AND complemento = ?";
        String inserir = "INSERT INTO Endereco (id, pais, estado, cidade, rua, cep, numero, complemento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
                    return (UUID) resultadoConsulta.getObject("id");
                }
            }

            UUID idEndereco = UUID.randomUUID();
            try (PreparedStatement salvarEndereco = connection.prepareStatement(inserir)) {
                salvarEndereco.setObject(1, idEndereco);
                salvarEndereco.setString(2, endereco.getPais());
                salvarEndereco.setString(3, endereco.getEstado());
                salvarEndereco.setString(4, endereco.getCidade());
                salvarEndereco.setString(5, endereco.getRua());
                salvarEndereco.setString(6, endereco.getCEP());
                salvarEndereco.setString(7, endereco.getNumero());
                salvarEndereco.setString(8, endereco.getComplemento());
                salvarEndereco.executeUpdate();
                return idEndereco;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
