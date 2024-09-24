package services;

import dao.CargoDAO;
import dao.FuncionarioDAO;
import dao.PessoaDAO;
import models.*;

import java.util.List;
import java.util.Scanner;

public class FuncionarioService {
    EnderecoService enderecoService = new EnderecoService();

    public void cadastrar(Scanner scanner, FuncionarioDAO dao) {
        System.out.println("Cadastrar novo funcionário:");

        System.out.println("Nome:");
        String nome = scanner.next();
        System.out.println("Data de nascimento (no formato YYYY-MM-DD):");
        String nascimento = scanner.next();

        System.out.println("Telefone:");
        System.out.println("DDD:");
        String ddd = scanner.next();
        System.out.println("Número:");
        String numero = scanner.next();

        System.out.println("Matrícula do funcionário:");
        String matricula = scanner.next();
        System.out.println("Cargo:");
        String cargoAtual = scanner.next();
        System.out.println("Salário:");
        double salario = scanner.nextDouble();
        System.out.println("Data de admissão:");
        String admissao = scanner.next();

        Endereco endereco = enderecoService.cadastrar(scanner);

        Funcionario funcionario = new Funcionario(nome, java.sql.Date.valueOf(nascimento), matricula, salario, java.sql.Date.valueOf(admissao));

        Cargo cargo = new Cargo(cargoAtual);

        Telefone tel = new Telefone(ddd, numero);

        dao.salvar(funcionario, endereco, cargo, tel);

        System.out.println("Funcionário cadastrado.");
    }

    public void reajustarSalario(Scanner scanner, FuncionarioDAO dao) {
        System.out.println("Id do funcionário:");
        int funcionarioId = scanner.nextInt();
        System.out.println("Percentual de reajuste salarial:");
        double percentualReajuste = scanner.nextDouble();

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
        System.out.println("Novo cargo:");
        String novoCargo = scanner.next();

        Cargo cargo = new Cargo(novoCargo);
        dao.atualizarCargo(funcionarioId, cargo);

        System.out.println("Funcionário promovido!");
    }

    public void obterIdade(Scanner scanner, PessoaDAO dao) {
        System.out.println("Id do funcionário:");
        int funcionarioId = scanner.nextInt();
        int idade = dao.obterIdade(funcionarioId);

        System.out.println("O funcionário tem " + idade + " anos.");
    }
}