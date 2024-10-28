package dev.ana.dao;

import dev.ana.models.*;
import dev.ana.models.pessoas.Funcionario;

import java.sql.*;
import java.util.UUID;

public class FuncionarioDAO {
    private final Connection connection;
    private final EnderecoDAO enderecoDAO;
    private final PessoaDAO pessoaDAO;
    private final TelefoneDAO telefoneDAO;

    public FuncionarioDAO(Connection connection) {
        this.connection = connection;
        this.enderecoDAO = new EnderecoDAO(connection);
        this.pessoaDAO = new PessoaDAO(connection);
        this.telefoneDAO = new TelefoneDAO(connection);
    }

    public void salvar(Funcionario funcionario, Endereco endereco, Cargo cargo, Telefone telefone) {
        UUID enderecoId = enderecoDAO.buscarOuCriar(endereco);

        UUID idPessoa = pessoaDAO.salvar(funcionario, enderecoId);
        funcionario.setId(idPessoa);

        String inserir = "INSERT INTO Funcionario (id, matricula, cargo, salario, dataAdmissao) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement salvarFuncionario = connection.prepareStatement(inserir)) {
            salvarFuncionario.setObject(1, funcionario.getId());
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

    public void deletar(UUID id) {
        String sql = "DELETE FROM Pessoa WHERE id = ?";
        try (PreparedStatement deletar = connection.prepareStatement(sql)) {
            deletar.setObject(1, id);
            deletar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getSalario(UUID funcionarioId) {
        String sql = "SELECT salario FROM Funcionario WHERE id = ?";
        try (PreparedStatement consulta = connection.prepareStatement(sql)) {
            consulta.setObject(1, funcionarioId);
            ResultSet resultadoConsulta = consulta.executeQuery();
            if (resultadoConsulta.next()) {
                return resultadoConsulta.getDouble("salario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void atualizarSalario(UUID funcionarioId, double novoSalario) {
        String sql = "UPDATE Funcionario SET salario = ? WHERE id = ?";
        try (PreparedStatement atualizarSalario = connection.prepareStatement(sql)) {
            atualizarSalario.setDouble(1, novoSalario);
            atualizarSalario.setObject(2, funcionarioId);
            atualizarSalario.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarCargo(UUID funcionarioId, Cargo cargo) {
        String sql = "UPDATE Funcionario SET cargo = ? WHERE id = ?";

        try (PreparedStatement atualizarCargo = connection.prepareStatement(sql)) {
            atualizarCargo.setObject(1, cargo, java.sql.Types.OTHER);
            atualizarCargo.setObject(2, funcionarioId);
            atualizarCargo.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
