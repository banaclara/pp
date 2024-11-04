package dev.ana.conn;

import io.github.cdimascio.dotenv.Dotenv;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance;
    private final String url;
    private final String user;
    private final String password;
    private final Connection connection;

    private Database() throws SQLException {
        Dotenv dotenv = Dotenv.load();
        this.url = dotenv.get("DB_URL");
        this.user = dotenv.get("DB_USER");
        this.password = dotenv.get("DB_PASSWORD");

        this.connection = DriverManager.getConnection(url, user, password);

        flywayMigrate();
    }

    public static synchronized Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private void flywayMigrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(url, user, password)
                .load();

        if (flyway.info().pending().length > 0) {
            try {
                flyway.migrate();
                System.out.println("Migrations aplicadas ao banco de dados.");
            } catch (Exception e) {
                System.out.println("Erro ao executar migrations: " + e.getMessage());
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
