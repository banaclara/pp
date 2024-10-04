package dev.ana.services.implementation;

import dev.ana.models.Endereco;
import dev.ana.services.EnderecoServiceInterface;

import java.util.Scanner;

import static dev.ana.utils.Leitor.obterInput;

public class EnderecoService implements EnderecoServiceInterface {
    @Override
    public Endereco cadastrar(Scanner scanner) {
        System.out.println("Endereço:");

        String pais = obterInput(scanner, "País: ");
        String estado = obterInput(scanner, "Estado: ");
        String cidade = obterInput(scanner, "Cidade: ");
        String rua = obterInput(scanner, "Rua: ");
        String cep = obterInput(scanner, "CEP: ");
        String numero = obterInput(scanner, "Número: ");
        String complemento = obterInput(scanner, "Complemento: ");

        Endereco endereco = new Endereco(pais, estado, cidade, rua, cep, numero, complemento);
        return endereco;
    }
}
