package dev.ana.dao;

import dev.ana.models.pessoas.Cliente;
import dev.ana.models.Endereco;
import dev.ana.models.Profissao;
import dev.ana.models.Telefone;

import java.sql.*;
import java.util.UUID;

public class ClienteDAO {
    private final Connection connection;
    private final EnderecoDAO enderecoDAO;
    private final ProfissaoDAO profissaoDAO;
    private final PessoaDAO pessoaDAO;
    private final TelefoneDAO telefoneDAO;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
        this.enderecoDAO = new EnderecoDAO(connection);
        this.profissaoDAO = new ProfissaoDAO(connection);
        this.pessoaDAO = new PessoaDAO(connection);
        this.telefoneDAO = new TelefoneDAO(connection);
    }

    public UUID salvar(Cliente cliente, Profissao profissao, Endereco endereco, Telefone telefone) {
        UUID enderecoId = enderecoDAO.buscarOuCriar(endereco);
        UUID profissaoId = profissaoDAO.buscarOuCriar(profissao);

        UUID idPessoa = pessoaDAO.salvar(cliente, enderecoId);
        cliente.setId(idPessoa);

        String inserir = "INSERT INTO Cliente (id, codigo, profissao_id) VALUES (?, ?, ?)";

        try (PreparedStatement salvarCliente = connection.prepareStatement(inserir)) {
            salvarCliente.setObject(1, cliente.getId());
            salvarCliente.setString(2, cliente.getCodigo());
            salvarCliente.setObject(3, profissaoId);
            salvarCliente.executeUpdate();

            return cliente.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (telefone != null) {
            telefoneDAO.salvarTelefone(telefone, idPessoa);
        }

        return null;
    }
}
