package banco.contas;

public class ContaEspecial extends Conta {
    private double limite;

    public ContaEspecial() {}

    public ContaEspecial(int numero, double saldo, double limite) {
        super(numero, saldo);
        this.limite = limite;
    }

    public boolean sacar(double valor) {
        if (this.saldo < valor) {
            return false;
        } else {
            this.saldo -= (valor * .99);
            return true;
        }
    }
}
