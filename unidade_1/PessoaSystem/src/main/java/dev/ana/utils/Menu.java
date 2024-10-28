package dev.ana.utils;

import dev.ana.conn.Database;
import dev.ana.controllers.ClienteController;
import dev.ana.controllers.FuncionarioController;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    public static void principal(Scanner scanner, Database db) throws SQLException {
        while (true) {
            System.out.println("1 - Funcionários");
            System.out.println("2 - Clientes");
            System.out.println("3 - Dependentes");
            System.out.println("4 - Sair do programa");
            int opt;
            opt = scanner.nextInt();
            switch (opt) {
                case 1:
                    funcionarios(scanner, db.getConnection());
                    break;
                case 2:
                    clientes(scanner, db.getConnection());
                    break;
                case 3:
                    dependentes(scanner, db.getConnection());
                    break;
                case 4:
                    System.out.println("Encerrando...");
                    db.getConnection().close();
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void funcionarios(Scanner scanner, Connection conn) {
        FuncionarioController funcionarioController = new FuncionarioController(conn);
        boolean sair = false;

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar novo funcionário");
            System.out.println("2. Promover funcionário");
            System.out.println("3. Reajustar salário de um funcionário");
            System.out.println("4. Consultar idade do funcionário");
            System.out.println("0. Voltar ao menu principal");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    funcionarioController.cadastrarFuncionario(scanner);
                    break;
                case 2:
                    funcionarioController.promoverFuncionario(scanner);
                    break;
                case 3:
                    funcionarioController.reajustarSalario(scanner);
                    break;
                case 4:
                    funcionarioController.obterIdade(scanner);
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!sair);
    }

    private static void clientes(Scanner scanner, Connection conn) {
        ClienteController clienteController = new ClienteController(conn);
        boolean sair = false;

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Inserir cliente");
            System.out.println("2. Consultar idade do cliente");
            System.out.println("0. Voltar ao menu principal");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    clienteController.cadastrarCliente(scanner);
                    break;
                case 2:
                    clienteController.obterIdade(scanner);
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!sair);
    }

    private static void dependentes(Scanner scanner, Connection conn) {
//        DependenteController dependenteController = new DependenteController(conn);
        boolean sair = false;

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Inserir dependente");
            System.out.println("2. Consultar idade do dependente");
            System.out.println("0. Voltar ao menu principal");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
//                    dependenteController.cadastrarDependente(scanner);
                    break;
                case 2:
//                    dependenteController.obterIdade(scanner);
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!sair);
    }
}
