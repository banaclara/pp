package dev.ana.dto;

import dev.ana.models.Endereco;
import dev.ana.models.Telefone;

public class DadosPessoa {
    private String nome;
    private String nascimento;
    private Telefone telefone;
    private Endereco endereco;

    public DadosPessoa(String nome, String nascimento, Telefone telefone, Endereco endereco) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    // Getters
    public String getNome() { return nome; }
    public String getNascimento() { return nascimento; }
    public Telefone getTelefone() { return telefone; }
    public Endereco getEndereco() { return endereco; }
}
