package banco.contas;

public class ContaInvestimento extends Conta {
    private double taxa;
    private int prazo;

    public ContaInvestimento() {}

    public ContaInvestimento(int numero, double saldo, double taxa, int prazo) {
        super(numero, saldo);
        this.taxa = taxa;
        this.prazo = prazo;
    }

    public boolean sacar(double valor) {
        if (this.saldo < valor) {
            return false;
        } else {
            this.saldo -= (valor * 1.01);
            return true;
        }
    }

    public boolean depositar(double valor) {
        if (valor == 0 || valor < 0) {
            return false;
        } else {
            this.saldo += (valor * 1.01);
            return true;
        }
    }

    public void aplicarRendimento(double taxa) {
        this.saldo += this.saldo * taxa / 100;
    }
}