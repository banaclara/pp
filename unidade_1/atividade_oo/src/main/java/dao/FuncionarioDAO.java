package dao;

import conn.Database;
import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    private final Connection connection;
    private final EnderecoDAO enderecoDAO;
    private final CargoDAO cargoDAO;
    private final PessoaDAO pessoaDAO;
    private final TelefoneDAO telefoneDAO;

    public FuncionarioDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.enderecoDAO = new EnderecoDAO(connection);
        this.cargoDAO = new CargoDAO(connection);
        this.pessoaDAO = new PessoaDAO(connection);
        this.telefoneDAO = new TelefoneDAO(connection);
    }

    public void salvar(Funcionario funcionario, Endereco endereco, Cargo cargo, Telefone telefone) {
        int enderecoId = enderecoDAO.buscarOuCriar(endereco);
        int cargoId = cargoDAO.buscarOuCriar(cargo);

        int idPessoa = pessoaDAO.salvar(funcionario, enderecoId);
        funcionario.setId(idPessoa);

        String inserir = "INSERT INTO Funcionario (id, matricula, cargo_id, salario, dataAdmissao) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement salvarFuncionario = connection.prepareStatement(inserir)) {
            salvarFuncionario.setInt(1, funcionario.getId());
            salvarFuncionario.setString(2, funcionario.getMatricula());
            salvarFuncionario.setInt(3, cargoId);
            salvarFuncionario.setDouble(4, funcionario.getSalario());
            salvarFuncionario.setDate(5, funcionario.getDataAdmissao());
            salvarFuncionario.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (telefone != null) {
           telefoneDAO.salvarTelefone(telefone, idPessoa);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Pessoa WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getSalario(int funcionarioId) {
        String sql = "SELECT salario FROM Funcionario WHERE id = ?";
        try (PreparedStatement consulta = connection.prepareStatement(sql)) {
            consulta.setInt(1, funcionarioId);
            ResultSet rs = consulta.executeQuery();
            if (rs.next()) {
                return rs.getDouble("salario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void atualizarSalario(int funcionarioId, double novoSalario) {
        String sql = "UPDATE Funcionario SET salario = ? WHERE id = ?";
        try (PreparedStatement atualizarSalario = connection.prepareStatement(sql)) {
            atualizarSalario.setDouble(1, novoSalario);
            atualizarSalario.setInt(2, funcionarioId);
            atualizarSalario.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarCargo(int funcionarioId, Cargo cargo) {
        int cargoId = cargoDAO.buscarOuCriar(cargo);
        String sql = "UPDATE Funcionario SET cargo_id = ? WHERE id = ?";

        try (PreparedStatement atualizarCargo = connection.prepareStatement(sql)) {
            atualizarCargo.setInt(1, cargoId);
            atualizarCargo.setInt(2, funcionarioId);
            atualizarCargo.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
