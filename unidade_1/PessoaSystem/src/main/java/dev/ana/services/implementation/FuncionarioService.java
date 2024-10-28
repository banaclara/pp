package dev.ana.services.implementation;

import dev.ana.dao.FuncionarioDAO;
import dev.ana.dto.DadosPessoa;
import dev.ana.models.*;
import dev.ana.models.pessoas.Funcionario;
import dev.ana.services.FuncionarioServiceInterface;

import java.sql.Connection;
import java.util.Scanner;
import java.util.UUID;

import static dev.ana.utils.Leitor.*;

public class FuncionarioService extends PessoaService implements FuncionarioServiceInterface {

    private final FuncionarioDAO funcionarioDAO;

    public FuncionarioService(Connection connection) {
        super(connection);
        this.funcionarioDAO = new FuncionarioDAO(connection);
    }

    @Override
    public void cadastrar(Scanner scanner) {
        System.out.println("Cadastrar novo funcionário:");

        DadosPessoa pessoa = super.cadastrarPessoa(scanner);

        String matricula = obterInput(scanner, "Matrícula do funcionário:");
        Cargo cargo = obterCargo(scanner);
        double salario = obterDouble(scanner, "Salário:");
        String admissao = obterData(scanner, "Data de admissão:");

        Funcionario funcionario = new Funcionario(pessoa.getNome(), java.sql.Date.valueOf(pessoa.getNascimento()), matricula, salario, java.sql.Date.valueOf(admissao));

        funcionarioDAO.salvar(funcionario, pessoa.getEndereco(), cargo, pessoa.getTelefone());

        System.out.println("Funcionário cadastrado.");
    }

    @Override
    public void reajustarSalario(Scanner scanner) {
        UUID funcionarioId = obterUUID(scanner, "Id do funcionário:");
        double percentualReajuste = obterDouble(scanner, "Percentual de reajuste salarial:");
        scanner.nextLine();

        double salarioAtual = funcionarioDAO.getSalario(funcionarioId);

        if (salarioAtual > 0) {
            double novoSalario = salarioAtual + (salarioAtual * percentualReajuste / 100);
            funcionarioDAO.atualizarSalario(funcionarioId, novoSalario);
            System.out.println("Aumento de " + percentualReajuste + "% aplicado. O salário do funcionário é " + novoSalario);
        } else {
            System.out.println("Funcionário não encontrado ou salário inválido.");
        }
    }

    @Override
    public void promover(Scanner scanner) {
        UUID funcionarioId = obterUUID(scanner, "Id do funcionário:");
        Cargo novoCargo = obterCargo(scanner);

        funcionarioDAO.atualizarCargo(funcionarioId, novoCargo);

        System.out.println("Novo cargo atribuído ao funcionário.");
    }
}
