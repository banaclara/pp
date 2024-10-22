package dev.ana.services;

import java.util.Scanner;

public interface FuncionarioServiceInterface extends PessoaServiceInterface {
    void cadastrar(Scanner scanner);
    void promover(Scanner scanner);
    void reajustarSalario(Scanner scanner);
}
