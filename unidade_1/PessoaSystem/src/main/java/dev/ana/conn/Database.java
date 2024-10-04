package dev.ana.conn;

import dev.ana.dao.ClienteDAO;
import dev.ana.dao.FuncionarioDAO;
import dev.ana.dao.PessoaDAO;
import io.github.cdimascio.dotenv.Dotenv;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    Dotenv dotenv = Dotenv.load();
    private final String url = dotenv.get("DB_URL");
    private final String user = dotenv.get("DB_USER");
    private final String password = dotenv.get("DB_PASSWORD");
    private final FuncionarioDAO funcionarioDAO;
    private final ClienteDAO clienteDAO;
    private final PessoaDAO pessoaDAO;
    private final Connection connection;

    public Database() throws SQLException {
        flywayMigrate();

        connection = DriverManager.getConnection(url, user, password);

        funcionarioDAO = new FuncionarioDAO(connection);
        clienteDAO = new ClienteDAO(connection);
        pessoaDAO = new PessoaDAO(connection);
    }

    private void flywayMigrate() {
        Logger logger = Logger.getLogger("org.flywaydb");
        logger.setLevel(Level.WARNING);

        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.WARNING);
        logger.addHandler(handler);

        Flyway flyway = Flyway.configure()
                .dataSource(url, user, password)
                .load();

        try {
            var res = flyway.migrate();
            int migrationsAplicadas = res.migrationsExecuted;
            if (migrationsAplicadas > 0) {
                System.out.println("Migrations aplicadas.");
            } else {
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Erro ao executar migrations: " + e.getMessage());
        }
    }

    public FuncionarioDAO getFuncionarioDAO() {
        return funcionarioDAO;
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public PessoaDAO getPessoaDAO() {
        return pessoaDAO;
    }

    public Connection getConnection() {
        return connection;
    }
}