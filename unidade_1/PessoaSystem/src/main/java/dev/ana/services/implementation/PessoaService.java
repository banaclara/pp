package dev.ana.services.implementation;

import dev.ana.dao.PessoaDAO;
import dev.ana.dto.DadosPessoa;
import dev.ana.models.Endereco;
import dev.ana.models.Telefone;
import dev.ana.services.PessoaServiceInterface;

import java.sql.Connection;
import java.util.Scanner;
import java.util.UUID;

import static dev.ana.utils.Leitor.*;

public class PessoaService implements PessoaServiceInterface {
    private final PessoaDAO pessoaDAO;
    private final EnderecoService enderecoService;

    public PessoaService(Connection connection) {
        this.pessoaDAO = new PessoaDAO(connection);
        this.enderecoService = new EnderecoService();
    }

    @Override
    public void obterIdade(Scanner scanner) {
        UUID pessoaId = obterUUID(scanner, "Id da pessoa:");
        int idade = pessoaDAO.obterIdade(pessoaId);
        System.out.println("Idade: " + idade + " anos.");
    }

    public DadosPessoa cadastrarPessoa(Scanner scanner) {
        String nome = obterInput(scanner, "Nome:");
        String nascimento = obterData(scanner, "Data de nascimento:");
        Telefone tel = obterTelefone(scanner);
        Endereco endereco = enderecoService.cadastrar(scanner);

        return new DadosPessoa(nome, nascimento, tel, endereco);
    }
}
