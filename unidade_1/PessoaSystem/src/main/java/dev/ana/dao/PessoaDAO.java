package dev.ana.dao;

import dev.ana.models.pessoas.Pessoa;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class PessoaDAO {
    private final Connection connection;

    public PessoaDAO(Connection connection) {
        this.connection = connection;
    }

    public UUID salvar(Pessoa pessoa, UUID enderecoId) {
        UUID idPessoa = UUID.randomUUID();
        String inserir = "INSERT INTO Pessoa (id, nome, dataNascimento, endereco_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement salvarPessoa = connection.prepareStatement(inserir)) {
            salvarPessoa.setObject(1, idPessoa);
            salvarPessoa.setString(2, pessoa.getNome());
            salvarPessoa.setDate(3, pessoa.getDataNascimento());
            salvarPessoa.setObject(4, enderecoId);
            salvarPessoa.executeUpdate();
            return idPessoa;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int obterIdade(UUID pessoaId) {
        String sql = "SELECT dataNascimento FROM Pessoa WHERE id = ?";
        try (PreparedStatement consulta = connection.prepareStatement(sql)) {
            consulta.setObject(1, pessoaId);
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