package services;

import models.Endereco;

import java.util.Scanner;

public class EnderecoService {
    public Endereco cadastrar(Scanner scanner) {
        System.out.println("Endereço:");
        System.out.println("País:");
        String pais = scanner.nextLine();
        System.out.println("Estado:");
        String estado = scanner.nextLine();
        System.out.println("Cidade:");
        String cidade = scanner.nextLine();
        System.out.println("Rua:");
        String rua = scanner.nextLine();
        System.out.println("CEP:");
        String cep = scanner.nextLine();
        System.out.println("Número:");
        String numero = scanner.nextLine();
        System.out.println("Complemento:");
        String complemento = scanner.nextLine();

        Endereco endereco = new Endereco(pais, estado, cidade, rua, cep, numero, complemento);
        return endereco;
    }
}
