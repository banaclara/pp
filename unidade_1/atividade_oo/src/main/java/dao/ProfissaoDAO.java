package dao;

import conn.Database;
import models.Profissao;

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

            try (PreparedStatement salvarCargo = connection.prepareStatement(inserir, Statement.RETURN_GENERATED_KEYS)) {
                salvarCargo.setString(1, profissao.getNome());
                salvarCargo.executeUpdate();

                ResultSet resultadoInserir = salvarCargo.getGeneratedKeys();
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
