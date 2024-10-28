package dev.ana.controllers;

import dev.ana.services.implementation.FuncionarioService;

import java.sql.Connection;
import java.util.Scanner;

public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(Connection connection) {
        this.funcionarioService = new FuncionarioService(connection);
    }

    public void cadastrarFuncionario(Scanner scanner) {
        funcionarioService.cadastrar(scanner);
    }

    public void promoverFuncionario(Scanner scanner) {
        funcionarioService.promover(scanner);
    }

    public void reajustarSalario(Scanner scanner) {
        funcionarioService.reajustarSalario(scanner);
    }

    public void obterIdade(Scanner scanner) {
        funcionarioService.obterIdade(scanner);
    }
}
