package models;

import java.util.Date;
import java.util.List;

public class Pessoa {
    protected Integer id;
    protected String nome;
    protected Date dataNascimento;
    protected List<Telefone> tel;
    protected Endereco endereco;

    public Pessoa() {}

    public Pessoa(String nome, Date nascimento) {
        this.nome = nome;
        this.dataNascimento = nascimento;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getId() {
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
