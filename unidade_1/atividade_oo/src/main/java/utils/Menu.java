package utils;

import dao.CargoDAO;
import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.PessoaDAO;
import models.Pessoa;
import services.ClienteService;
import services.FuncionarioService;

import java.util.Scanner;

public class Menu {
    public static void principal() {
        System.out.println("1 - Funcionários");
        System.out.println("2 - Clientes");
        System.out.println("3 - Sair do programa");
    }

    public static void funcionarios(Scanner scanner, FuncionarioService service, FuncionarioDAO dao, PessoaDAO pDAO) {
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

    public static void clientes(Scanner scanner, ClienteService service, ClienteDAO dao, PessoaDAO pDAO) {
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
