package dev.ana.services;

import dev.ana.dao.FuncionarioDAO;

import java.util.Scanner;

public interface FuncionarioServiceInterface extends PessoaServiceInterface {
    void cadastrar(Scanner scanner, FuncionarioDAO dao);
    void promover(Scanner scanner, FuncionarioDAO dao);
    void reajustarSalario(Scanner scanner, FuncionarioDAO dao);
}
