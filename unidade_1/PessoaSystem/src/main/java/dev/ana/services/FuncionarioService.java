package dev.ana.services;

import dev.ana.dao.FuncionarioDAO;
import dev.ana.dao.PessoaDAO;
import dev.ana.models.*;

import java.util.Scanner;

public class FuncionarioService {
    EnderecoService enderecoService = new EnderecoService();

    public void cadastrar(Scanner scanner, FuncionarioDAO dao) {
        System.out.println("Cadastrar novo funcionário:");

        System.out.println("Nome:");
        String nome = scanner.nextLine();
        System.out.println("Data de nascimento (no formato YYYY-MM-DD):");
        String nascimento = scanner.nextLine();

        System.out.println("Telefone:");
        System.out.println("DDD:");
        String ddd = scanner.nextLine();
        System.out.println("Número:");
        String numero = scanner.nextLine();

        System.out.println("Matrícula do funcionário:");
        String matricula = scanner.nextLine();
        System.out.println("Cargo (opções: GERENTE, ANALISTA, DESENVOLVEDOR, DEVOPS, DESIGNER, SUPORTE, QA, ESTAGIARIO):");
        Cargo cargo = Cargo.valueOf(scanner.next().toUpperCase());
        System.out.println("Salário:");
        double salario = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Data de admissão:");
        String admissao = scanner.nextLine();

        Endereco endereco = enderecoService.cadastrar(scanner);

        Funcionario funcionario = new Funcionario(nome, java.sql.Date.valueOf(nascimento), matricula, salario, java.sql.Date.valueOf(admissao));

        Telefone tel = new Telefone(ddd, numero);

        dao.salvar(funcionario, endereco, cargo, tel);

        System.out.println("Funcionário cadastrado.");
    }

    public void reajustarSalario(Scanner scanner, FuncionarioDAO dao) {
        System.out.println("Id do funcionário:");
        int funcionarioId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Percentual de reajuste salarial:");
        double percentualReajuste = scanner.nextDouble();
        scanner.nextLine();

        double salarioAtual = dao.getSalario(funcionarioId);

        if (salarioAtual > 0) {
            double novoSalario = salarioAtual + (salarioAtual * percentualReajuste / 100);
            dao.atualizarSalario(funcionarioId, novoSalario);
        } else {
            System.out.println("Funcionário não encontrado ou salário inválido.");
        }
    }

    public void promover(Scanner scanner, FuncionarioDAO dao) {
        System.out.println("Id do funcionário:");
        int funcionarioId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Novo cargo:");
        Cargo novoCargo = Cargo.valueOf(scanner.next().toUpperCase());

        dao.atualizarCargo(funcionarioId, novoCargo);

        System.out.println("Novo cargo atribuído ao funcionário!");
    }

    public void obterIdade(Scanner scanner, PessoaDAO dao) {
        System.out.println("Id do funcionário:");
        int funcionarioId = scanner.nextInt();
        scanner.nextLine();
        int idade = dao.obterIdade(funcionarioId);

        System.out.println("O funcionário tem " + idade + " anos.");
    }
}
