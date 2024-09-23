package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String sql_script = "schema.sql";
    private static final String url = "jdbc:postgresql://localhost:5432/ppunidadeI?user=postgres&password=docker";
    ;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
