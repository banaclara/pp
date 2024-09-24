package models;

public class Endereco {
    private String estado;
    private String cidade;
    private String rua;
    private String CEP;
    private String numero;
    private String complemento;

    public Endereco(String estado, String cidade, String rua, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.complemento = complemento;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getRua() {
        return rua;
    }

    public String getComplemento() {
        return complemento;
    }
}
