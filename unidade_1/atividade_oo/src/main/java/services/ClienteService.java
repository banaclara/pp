package services;

import dao.ClienteDAO;
import dao.PessoaDAO;
import models.*;

import java.util.Scanner;

public class ClienteService {
    public void cadastrar(Scanner scanner, ClienteDAO dao) {
        System.out.println("Cadastrar novo cliente:");

        System.out.println("Nome:");
        String nome = scanner.next();
        System.out.println("Data de nascimento (no formato YYYY-MM-DD):");
        String nascimento = scanner.next();
        System.out.println("Telefone:");
        String numero = scanner.next();

        System.out.println("Código do cliente:");
        String codigo = scanner.next();
        System.out.println("Profissão:");
        String profissaoAtual = scanner.next();

        System.out.println("Endereço:");
        System.out.println("Estado:");
        String estado = scanner.next();
        System.out.println("Cidade:");
        String cidade = scanner.next();
        System.out.println("Rua:");
        String rua = scanner.next();
        System.out.println("Complemento:");
        String complemento = scanner.next();

        Cliente cliente = new Cliente(nome, java.sql.Date.valueOf(nascimento), codigo);

        Endereco endereco = new Endereco(estado, cidade, rua, complemento);

        Profissao profissao = new Profissao(profissaoAtual);

        Telefone tel = new Telefone(numero);

        dao.salvar(cliente, profissao, endereco, tel);

        System.out.println("Cliente cadastrado.");
    }

    public void obterIdade(Scanner scanner, PessoaDAO dao) {
        System.out.println("Id do cliente:");
        int clienteId = scanner.nextInt();
        int idade = dao.obterIdade(clienteId);

        System.out.println("O cliente tem " + idade + " anos.");
    }
}
