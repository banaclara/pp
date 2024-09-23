package dao;

import conn.Database;
import models.Cargo;

import java.sql.*;

public class CargoDAO {
    private final Connection connection;

    public CargoDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public int buscarOuCriar(Cargo cargo) {
        String consulta = "SELECT id FROM Cargo WHERE funcao = ?";
        String inserir = "INSERT INTO Cargo (funcao) VALUES (?)";

        try {
            try (PreparedStatement buscarNoBD = connection.prepareStatement(consulta)) {
                buscarNoBD.setString(1, cargo.getFuncao());
                ResultSet rs = buscarNoBD.executeQuery();

                if (rs.next()) {
                    return rs.getInt("id");
                }
            }

            try (PreparedStatement salvarCargo = connection.prepareStatement(inserir, Statement.RETURN_GENERATED_KEYS)) {
                salvarCargo.setString(1, cargo.getFuncao());
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
