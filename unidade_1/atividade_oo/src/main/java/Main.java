import conn.Database;
import utils.Menu;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Database db = new Database();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                Menu.principal();
                int opt;
                opt = scanner.nextInt();
                switch (opt) {
                    case 1:
                        Menu.funcionarios(scanner, db.getFuncionarioDAO(), db.getPessoaDAO());
                        break;
                    case 2:
                        Menu.clientes(scanner, db.getClienteDAO(), db.getPessoaDAO());
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
        } catch (SQLException e) {
            System.out.println("Erro na conexão; " + e.getMessage());
        }
    }
}
