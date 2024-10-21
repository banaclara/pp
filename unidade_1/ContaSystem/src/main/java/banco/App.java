package banco;

import banco.contas.ContaEspecial;
import banco.contas.ContaInvestimento;

public class App {
    public static void main(String[] args) {
        ContaEspecial contaE1 = new ContaEspecial(1, 100, 1000);
        ContaInvestimento contaI1 = new ContaInvestimento(1, 100, 10, 10);

        contaI1.sacar(100);
        contaI1.aplicarRendimento(10);
    }
}
