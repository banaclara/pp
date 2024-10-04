package dev.ana.utils;

import dev.ana.conn.Database;
import dev.ana.dao.ClienteDAO;
import dev.ana.dao.FuncionarioDAO;
import dev.ana.dao.PessoaDAO;
import dev.ana.services.implementation.ClienteService;
import dev.ana.services.implementation.FuncionarioService;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    public static void principal(Scanner scanner, Database db) throws SQLException {
        while (true) {
            System.out.println("1 - Funcionários");
            System.out.println("2 - Clientes");
            System.out.println("3 - Sair do programa");
            int opt;
            opt = scanner.nextInt();
            switch (opt) {
                case 1:
                    funcionarios(scanner, db.getFuncionarioDAO(), db.getPessoaDAO());
                    break;
                case 2:
                    clientes(scanner, db.getClienteDAO(), db.getPessoaDAO());
                    break;
                case 3:
                    System.out.println("Encerrando...");
                    db.getConnection().close();
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void funcionarios(Scanner scanner, FuncionarioDAO dao, PessoaDAO pDAO) {
        FuncionarioService service = new FuncionarioService();
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
                    service.cadastrar(scanner, dao);
                    break;
                case 2:
                    service.promover(scanner, dao);
                    break;
                case 3:
                    service.reajustarSalario(scanner, dao);
                    break;
                case 4:
                    service.obterIdade(scanner, pDAO);
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!sair);
    }

    private static void clientes(Scanner scanner, ClienteDAO dao, PessoaDAO pDAO) {
        ClienteService service = new ClienteService();
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
                    service.cadastrar(scanner, dao);
                    break;
                case 2:
                    service.obterIdade(scanner, pDAO);
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!sair);
    }
}
