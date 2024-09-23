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
                ResultSet rs = buscarNoBD.executeQuery();

                if (rs.next()) {
                    return rs.getInt("id");
                }
            }

            try (PreparedStatement salvarCargo = connection.prepareStatement(inserir, Statement.RETURN_GENERATED_KEYS)) {
                salvarCargo.setString(1, profissao.getNome());
                salvarCargo.executeUpdate();

                ResultSet rsInserir = salvarCargo.getGeneratedKeys();
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
