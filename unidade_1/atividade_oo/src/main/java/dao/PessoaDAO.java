package dao;

import conn.Database;
import models.Pessoa;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class PessoaDAO {
    private final Connection connection;

    public PessoaDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public int salvar(Pessoa pessoa, int enderecoId) {
        String inserir = "INSERT INTO Pessoa (nome, dataNascimento, endereco_id) VALUES (?, ?, ?)";

        try (PreparedStatement salvarPessoa = connection.prepareStatement(inserir, Statement.RETURN_GENERATED_KEYS)) {
            salvarPessoa.setString(1, pessoa.getNome());
            salvarPessoa.setDate(2, pessoa.getDataNascimento());
            salvarPessoa.setInt(3, enderecoId);
            salvarPessoa.executeUpdate();

            ResultSet rs = salvarPessoa.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public int obterIdade(int pessoaId) {
        String sql = "SELECT dataNascimento FROM Pessoa WHERE id = ?";
        try (PreparedStatement consulta = connection.prepareStatement(sql)) {
            consulta.setInt(1, pessoaId);
            ResultSet rs = consulta.executeQuery();
            if (rs.next()) {
                Date dataNascimento = rs.getDate("dataNascimento");
                return Period.between(dataNascimento.toLocalDate(), LocalDate.now()).getYears();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}