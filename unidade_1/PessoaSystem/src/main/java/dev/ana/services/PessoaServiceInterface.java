package dev.ana.services;

import dev.ana.dao.FuncionarioDAO;
import dev.ana.dao.PessoaDAO;

import java.util.Scanner;

public interface PessoaServiceInterface {
    void obterIdade(Scanner scanner, PessoaDAO dao);
}
