package conn;

import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.PessoaDAO;
import services.ClienteService;
import services.FuncionarioService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Database {
    private static final String url = "jdbc:postgresql://localhost:5432/ppunidadeI?user=postgres&password=docker";
    private FuncionarioDAO funcionarioDAO;
    private ClienteDAO clienteDAO;
    private PessoaDAO pessoaDAO;
    private Connection connection;

    public Database() throws SQLException {
         connection = DriverManager.getConnection(url);
         funcionarioDAO = new FuncionarioDAO(connection);
         clienteDAO = new ClienteDAO(connection);
         pessoaDAO = new PessoaDAO(connection);
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
