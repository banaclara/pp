package dev.ana.dao;

import dev.ana.conn.Database;
import dev.ana.models.Pessoa;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class PessoaDAO {
    private final Connection connection;

    public PessoaDAO(Connection connection) {
        this.connection = connection;
    }

    public int salvar(Pessoa pessoa, int enderecoId) {
        String inserir = "INSERT INTO Pessoa (nome, dataNascimento, endereco_id) VALUES (?, ?, ?)";

        try (PreparedStatement salvarPessoa = connection.prepareStatement(inserir, Statement.RETURN_GENERATED_KEYS)) {
            salvarPessoa.setString(1, pessoa.getNome());
            salvarPessoa.setDate(2, pessoa.getDataNascimento());
            salvarPessoa.setInt(3, enderecoId);
            salvarPessoa.executeUpdate();

            ResultSet resultadoInsert = salvarPessoa.getGeneratedKeys();
            if (resultadoInsert.next()) {
                return resultadoInsert.getInt(1);
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
            ResultSet resultadoConsulta = consulta.executeQuery();
            if (resultadoConsulta.next()) {
                Date dataNascimento = resultadoConsulta.getDate("dataNascimento");
                return Period.between(dataNascimento.toLocalDate(), LocalDate.now()).getYears();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}