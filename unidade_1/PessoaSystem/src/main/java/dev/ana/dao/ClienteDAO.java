package dev.ana.dao;

import dev.ana.conn.Database;
import dev.ana.models.Cliente;
import dev.ana.models.Endereco;
import dev.ana.models.Profissao;
import dev.ana.models.Telefone;

import java.sql.*;

public class ClienteDAO {
    private final Connection connection;
    private final EnderecoDAO enderecoDAO;
    private final ProfissaoDAO profissaoDAO;
    private final PessoaDAO pessoaDAO;
    private final TelefoneDAO telefoneDAO;

    public ClienteDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.enderecoDAO = new EnderecoDAO(connection);
        this.profissaoDAO = new ProfissaoDAO(connection);
        this.pessoaDAO = new PessoaDAO(connection);
        this.telefoneDAO = new TelefoneDAO(connection);
    }

    public int salvar(Cliente cliente, Profissao profissao, Endereco endereco, Telefone telefone) {
        int enderecoId = enderecoDAO.buscarOuCriar(endereco);
        int profissaoId = profissaoDAO.buscarOuCriar(profissao);

        int idPessoa = pessoaDAO.salvar(cliente, enderecoId);
        cliente.setId(idPessoa);

        String inserir = "INSERT INTO Cliente (id, codigo, profissao_id) VALUES (?, ?, ?)";

        try (PreparedStatement salvarCliente = connection.prepareStatement(inserir, Statement.RETURN_GENERATED_KEYS)) {
            salvarCliente.setInt(1, cliente.getId());
            salvarCliente.setString(2, cliente.getCodigo());
            salvarCliente.setInt(3, profissaoId);
            salvarCliente.executeUpdate();

            ResultSet resultadoInsert = salvarCliente.getGeneratedKeys();
            if (resultadoInsert.next()) {
                return resultadoInsert.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (telefone != null) {
            telefoneDAO.salvarTelefone(telefone, idPessoa);
        }

        return -1;
    }
}
