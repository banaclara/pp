package models;

public class Endereco {
    private String pais;
    private String estado;
    private String cidade;
    private String rua;
    private String CEP;
    private String numero;
    private String complemento;

    public Endereco(String pais, String estado, String cidade, String rua, String CEP, String numero, String complemento) {
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.CEP = CEP;
        this.numero = numero;
        this.complemento = complemento;

    }

    public String getPais() {
        return pais;
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

    public String getCEP() {
        return CEP;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }
}
