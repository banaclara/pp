package dev.ana.services;

import dev.ana.dao.ClienteDAO;

import java.util.Scanner;

public interface ClienteServiceInterface extends PessoaServiceInterface {
    void cadastrar(Scanner scanner, ClienteDAO dao);
}
