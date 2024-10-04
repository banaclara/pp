package dev.ana.services.implementation;

import dev.ana.dao.FuncionarioDAO;
import dev.ana.dao.PessoaDAO;
import dev.ana.models.*;
import dev.ana.services.FuncionarioServiceInterface;

import java.util.Scanner;

import static dev.ana.utils.Leitor.*;

public class FuncionarioService implements FuncionarioServiceInterface {
    EnderecoService enderecoService = new EnderecoService();

    @Override
    public void cadastrar(Scanner scanner, FuncionarioDAO dao) {
        System.out.println("Cadastrar novo funcionário:");

        String nome = obterInput(scanner, "Nome:");
        String nascimento = obterData(scanner, "Data de nascimento:");
        Telefone tel = obterTelefone(scanner);

        String matricula = obterInput(scanner, "Matrícula do funcionário:");
        Cargo cargo = obterCargo(scanner);
        double salario = obterDouble(scanner, "Salário:");
        String admissao = obterData(scanner, "Data de admissão:");

        Endereco endereco = enderecoService.cadastrar(scanner);

        Funcionario funcionario = new Funcionario(nome, java.sql.Date.valueOf(nascimento), matricula, salario, java.sql.Date.valueOf(admissao));

        dao.salvar(funcionario, endereco, cargo, tel);

        System.out.println("Funcionário cadastrado.");
    }

    @Override
    public void reajustarSalario(Scanner scanner, FuncionarioDAO dao) {
        int funcionarioId = obterInt(scanner, "Id do funcionário:");
        scanner.nextLine();
        double percentualReajuste = obterDouble(scanner, "Percentual de reajuste salarial:");
        scanner.nextLine();

        double salarioAtual = dao.getSalario(funcionarioId);

        if (salarioAtual > 0) {
            double novoSalario = salarioAtual + (salarioAtual * percentualReajuste / 100);
            dao.atualizarSalario(funcionarioId, novoSalario);
        } else {
            System.out.println("Funcionário não encontrado ou salário inválido.");
        }
    }

    @Override
    public void promover(Scanner scanner, FuncionarioDAO dao) {
        int funcionarioId = obterInt(scanner, "Id do funcionário:");
        scanner.nextLine();
        Cargo novoCargo = obterCargo(scanner);

        dao.atualizarCargo(funcionarioId, novoCargo);

        System.out.println("Novo cargo atribuído ao funcionário.");
    }

    @Override
    public void obterIdade(Scanner scanner, PessoaDAO dao) {
        int funcionarioId = obterInt(scanner, "Id do funcionário:");
        scanner.nextLine();
        int idade = dao.obterIdade(funcionarioId);

        System.out.println("O funcionário tem " + idade + " anos.");
    }
}
