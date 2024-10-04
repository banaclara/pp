package dev.ana.services.implementation;

import dev.ana.dao.ClienteDAO;
import dev.ana.dao.PessoaDAO;
import dev.ana.models.*;
import dev.ana.services.ClienteServiceInterface;

import java.util.Scanner;

import static dev.ana.utils.Leitor.*;

public class ClienteService implements ClienteServiceInterface {
    EnderecoService enderecoService = new EnderecoService();

    @Override
    public void cadastrar(Scanner scanner, ClienteDAO dao) {
        System.out.println("Cadastrar novo cliente:");

        String nome = obterInput(scanner, "Nome:");
        String nascimento = obterData(scanner, "Data de nascimento:");
        Telefone tel = obterTelefone(scanner);

        String codigo = obterInput(scanner, "Código do cliente:");
        String profissaoAtual = obterInput(scanner, "Profissão:");

        Endereco endereco = enderecoService.cadastrar(scanner);

        Cliente cliente = new Cliente(nome, java.sql.Date.valueOf(nascimento), codigo);

        Profissao profissao = new Profissao(profissaoAtual);

        dao.salvar(cliente, profissao, endereco, tel);

        System.out.println("Cliente cadastrado.");
    }

    @Override
    public void obterIdade(Scanner scanner, PessoaDAO dao) {
        int clienteId = obterInt(scanner, "Id do cliente:");
        scanner.nextLine();
        int idade = dao.obterIdade(clienteId);

        System.out.println("O cliente tem " + idade + " anos.");
    }
}
