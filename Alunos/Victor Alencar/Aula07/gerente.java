package kdkak;

import java.util.ArrayList;

public class gerente extends pessoa {
    private String loja;
    private double salarioBase;
    private ArrayList<Double> salarioRecebido;

    public gerente(String nome, int idade, endereco endereco, String loja, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new ArrayList<>();
        salarioRecebido.add(4000.0);
        salarioRecebido.add(4200.0);
        salarioRecebido.add(4500.0);
    }

    @Override
    public void apresentarse() {
        super.apresentarse();
        System.out.println("Loja: " + loja);
    }

    public double calcularMedia() {
        return salarioRecebido.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}
