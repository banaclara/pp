package dev.ana.dao;

import dev.ana.models.Profissao;

import java.sql.*;
import java.util.UUID;

public class ProfissaoDAO {

    private final Connection connection;

    public ProfissaoDAO(Connection connection) {
        this.connection = connection;
    }

    public UUID buscarOuCriar(Profissao profissao) {
        String consulta = "SELECT id FROM Profissao WHERE nome = ?";
        String inserir = "INSERT INTO Profissao (id, nome) VALUES (?, ?)";

        try {
            try (PreparedStatement buscarNoBD = connection.prepareStatement(consulta)) {
                buscarNoBD.setString(1, profissao.getNome());
                ResultSet resultadoConsulta = buscarNoBD.executeQuery();

                if (resultadoConsulta.next()) {
                    return (UUID) resultadoConsulta.getObject("id");
                }
            }

            UUID idProfissao = UUID.randomUUID();
            try (PreparedStatement salvarProfissao = connection.prepareStatement(inserir)) {
                salvarProfissao.setObject(1, idProfissao);
                salvarProfissao.setString(2, profissao.getNome());
                salvarProfissao.executeUpdate();
                return idProfissao;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
