package dev.ana.controllers;

import dev.ana.services.implementation.ClienteService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(Connection connection) throws SQLException {
        this.clienteService = new ClienteService(connection);
    }

    public void cadastrarCliente(Scanner scanner) {
        clienteService.cadastrar(scanner);
    }

    public void obterIdade(Scanner scanner) {
        clienteService.obterIdade(scanner);
    }
}
