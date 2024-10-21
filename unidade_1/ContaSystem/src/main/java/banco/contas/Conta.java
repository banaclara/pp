package banco.contas;

public class Conta {
    protected int numero;
    protected double saldo;

    public Conta() {}

    public Conta(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public boolean sacar(double valor) {
        if (this.saldo < valor) {
            return false;
        } else {
            this.saldo -= valor;
            return true;
        }
    }

    public boolean depositar(double valor) {
        if (valor == 0 || valor < 0) {
            return false;
        } else {
            this.saldo += valor;
            return true;
        }
    }
}
