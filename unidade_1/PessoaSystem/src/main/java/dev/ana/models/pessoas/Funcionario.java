package dev.ana.models.pessoas;

import dev.ana.models.Cargo;

import java.util.Date;

public class Funcionario extends Pessoa {
    private String matricula;
    private Cargo cargo;
    private double salario;
    private Date dataAdmissao;

    public Funcionario(String nome, Date nascimento, String matricula, double salario, Date admissao) {
        super(nome, nascimento);
        this.matricula = matricula;
        this.salario = salario;
        this.dataAdmissao = admissao;
    }

    public String getMatricula() {
        return matricula;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public double getSalario() {
        return salario;
    }

    public java.sql.Date getDataAdmissao() {
        return (java.sql.Date) dataAdmissao;
    }
}
