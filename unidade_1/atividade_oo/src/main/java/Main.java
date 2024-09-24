import conn.Database;
import dao.*;
import services.ClienteService;
import services.FuncionarioService;
import utils.Menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = Database.getConnection();
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO(connection);
            ClienteDAO clienteDAO = new ClienteDAO(connection);
            PessoaDAO pessoaDAO = new PessoaDAO(connection);
            FuncionarioService funcionarioService = new FuncionarioService();
            ClienteService clienteService = new ClienteService();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                Menu.principal();
                int opt;
                opt = scanner.nextInt();
                switch (opt) {
                    case 1:
                        Menu.funcionarios(scanner, funcionarioService, funcionarioDAO, pessoaDAO);
                        break;
                    case 2:
                        Menu.clientes(scanner, clienteService, clienteDAO, pessoaDAO);
                        break;
                    case 3:
                        System.out.println("Encerrando o programa...");
                        connection.close();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexão ao banco de dados: " + e.getMessage());
        }
    }
}
