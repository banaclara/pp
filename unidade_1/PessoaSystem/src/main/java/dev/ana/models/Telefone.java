package dev.ana.models;

public class Telefone {
    private String ddd;
    private String numero;

    public Telefone(String ddd, String tel) {
        this.ddd= ddd;
        this.numero = tel;
    }

    public String getDdd() {
        return ddd;
    }

    public String getNumero() {
        return numero;
    }
}
