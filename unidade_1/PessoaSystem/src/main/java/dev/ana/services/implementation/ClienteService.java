package dev.ana.services.implementation;

import dev.ana.dao.ClienteDAO;
import dev.ana.dto.DadosPessoa;
import dev.ana.models.*;
import dev.ana.models.pessoas.Cliente;
import dev.ana.services.ClienteServiceInterface;

import java.sql.Connection;
import java.util.Scanner;

import static dev.ana.utils.Leitor.*;

public class ClienteService extends PessoaService implements ClienteServiceInterface {
    private final ClienteDAO clienteDAO;

    public ClienteService(Connection connection) {
        super(connection);
        this.clienteDAO = new ClienteDAO(connection);
    }

    @Override
    public void cadastrar(Scanner scanner) {
        System.out.println("Cadastrar novo cliente:");

        DadosPessoa pessoa = super.cadastrarPessoa(scanner);

        String codigo = obterInput(scanner, "Código do cliente:");
        String profissaoAtual = obterInput(scanner, "Profissão:");

        Cliente cliente = new Cliente(pessoa.getNome(), java.sql.Date.valueOf(pessoa.getNascimento()), codigo);

        Profissao profissao = new Profissao(profissaoAtual);

        clienteDAO.salvar(cliente, profissao, pessoa.getEndereco(), pessoa.getTelefone());

        System.out.println("Cliente cadastrado.");
    }
}
