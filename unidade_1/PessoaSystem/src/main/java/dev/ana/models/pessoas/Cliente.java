package dev.ana.models.pessoas;

import dev.ana.models.Profissao;

import java.util.Date;

public class Cliente extends Pessoa {
    private String codigo;
    private Profissao profissao;

    public Cliente(String nome, Date nascimento, String codigo) {
        super(nome, nascimento);
        this.codigo = codigo;
        this.profissao = profissao;
    }

    public String getCodigo() {
        return codigo;
    }

    public Profissao getProfissao() {
        return profissao;
    }
}
