import java.util.ArrayList;

public class Gerente extends Vendedor {
    public Gerente(String nome, int idade, Loja loja, Endereco endereco, double salarioBase, ArrayList<Double> salarioRecebido) {
        super(nome, idade, loja, endereco, salarioBase, salarioRecebido);
    }

    @Override
    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}