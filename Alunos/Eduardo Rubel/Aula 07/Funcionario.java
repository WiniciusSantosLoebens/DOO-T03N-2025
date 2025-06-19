
import java.util.ArrayList;
import java.util.List;

abstract class Funcionario extends Pessoa {
    protected Loja loja;
    protected double salarioBase;
    protected List<Double> salariosRecebidos = new ArrayList<>();

    public Funcionario(String nome, int idade, Loja loja, Endereco endereco, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
    }

    public void adicionarSalario(double valor) {
        salariosRecebidos.add(valor);
    }

    public double calcularMedia() {
        return salariosRecebidos.stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }

    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}
