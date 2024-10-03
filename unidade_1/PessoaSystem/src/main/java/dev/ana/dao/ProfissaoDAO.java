package dev.ana.dao;

import dev.ana.conn.Database;
import dev.ana.models.Profissao;

import java.sql.*;

public class ProfissaoDAO {

    private final Connection connection;

    public ProfissaoDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public int buscarOuCriar(Profissao profissao) {
        String consulta = "SELECT id FROM Profissao WHERE nome = ?";
        String inserir = "INSERT INTO Profissao (nome) VALUES (?)";

        try {
            try (PreparedStatement buscarNoBD = connection.prepareStatement(consulta)) {
                buscarNoBD.setString(1, profissao.getNome());
                ResultSet resultadoConsulta = buscarNoBD.executeQuery();

                if (resultadoConsulta.next()) {
                    return resultadoConsulta.getInt("id");
                }
            }

            try (PreparedStatement salvarProfissao = connection.prepareStatement(inserir, Statement.RETURN_GENERATED_KEYS)) {
                salvarProfissao.setString(1, profissao.getNome());
                salvarProfissao.executeUpdate();

                ResultSet resultadoInserir = salvarProfissao.getGeneratedKeys();
                if (resultadoInserir.next()) {
                    return resultadoInserir.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
