package dev.ana.dao;

import dev.ana.models.*;

import java.sql.*;

public class FuncionarioDAO {
    private final Connection connection;
    private final EnderecoDAO enderecoDAO;
    private final PessoaDAO pessoaDAO;
    private final TelefoneDAO telefoneDAO;

    public FuncionarioDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.enderecoDAO = new EnderecoDAO(connection);
        this.pessoaDAO = new PessoaDAO(connection);
        this.telefoneDAO = new TelefoneDAO(connection);
    }

    public void salvar(Funcionario funcionario, Endereco endereco, Cargo cargo, Telefone telefone) {
        int enderecoId = enderecoDAO.buscarOuCriar(endereco);

        int idPessoa = pessoaDAO.salvar(funcionario, enderecoId);
        funcionario.setId(idPessoa);

        String inserir = "INSERT INTO Funcionario (id, matricula, cargo, salario, dataAdmissao) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement salvarFuncionario = connection.prepareStatement(inserir)) {
            salvarFuncionario.setInt(1, funcionario.getId());
            salvarFuncionario.setString(2, funcionario.getMatricula());
            salvarFuncionario.setObject(3, cargo, java.sql.Types.OTHER);
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
        try (PreparedStatement deletar = connection.prepareStatement(sql)) {
            deletar.setInt(1, id);
            deletar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getSalario(int funcionarioId) {
        String sql = "SELECT salario FROM Funcionario WHERE id = ?";
        try (PreparedStatement consulta = connection.prepareStatement(sql)) {
            consulta.setInt(1, funcionarioId);
            ResultSet resultadoConsulta = consulta.executeQuery();
            if (resultadoConsulta.next()) {
                return resultadoConsulta.getDouble("salario");
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
        String sql = "UPDATE Funcionario SET cargo = ? WHERE id = ?";

        try (PreparedStatement atualizarCargo = connection.prepareStatement(sql)) {
            atualizarCargo.setObject(1, cargo, java.sql.Types.OTHER);
            atualizarCargo.setInt(2, funcionarioId);
            atualizarCargo.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
