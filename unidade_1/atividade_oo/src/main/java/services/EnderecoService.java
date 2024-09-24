package services;

import models.Endereco;

import java.util.Scanner;

public class EnderecoService {
    public Endereco cadastrar(Scanner scanner) {
        System.out.println("Endereço:");
        System.out.println("Estado:");
        String estado = scanner.next();
        System.out.println("Cidade:");
        String cidade = scanner.next();
        System.out.println("Rua:");
        String rua = scanner.next();
        System.out.println("Complemento:");
        String complemento = scanner.next();

        Endereco endereco = new Endereco(estado, cidade, rua, complemento);
        return endereco;
    }
}
