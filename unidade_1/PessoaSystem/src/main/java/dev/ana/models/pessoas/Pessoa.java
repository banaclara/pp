package dev.ana.models.pessoas;

import dev.ana.models.Endereco;
import dev.ana.models.Telefone;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Pessoa {
    protected UUID id;
    protected String nome;
    protected Date dataNascimento;
    protected List<Telefone> tel;
    protected Endereco endereco;

    public Pessoa() {}

    public Pessoa(String nome, Date nascimento) {
        this.nome = nome;
        this.dataNascimento = nascimento;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public UUID getId() {
        return id;
    }

    public java.sql.Date getDataNascimento() {
        return (java.sql.Date) dataNascimento;
    }

    public List<Telefone> getTel() {
        return tel;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}
