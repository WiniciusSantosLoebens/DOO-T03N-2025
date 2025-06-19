package model;

import java.util.ArrayList;

public class Gerente extends Pessoa {
private String loja;
private double salarioBase;
private ArrayList<Double> salarioRecebido;

    public Gerente(String nome, int idade, String loja, double salarioBase) {
    super(nome, idade);
    this.loja = loja;
    this.salarioBase = salarioBase;
    this.salarioRecebido = new ArrayList<>();
    this.salarioRecebido.add(5000.0);
    this.salarioRecebido.add(5100.0);
    this.salarioRecebido.add(5200.0);
    }

    public void apresentarse() {
    System.out.println("Nome: " + getNome());
    System.out.println("Idade: " + getIdade());
    System.out.println("Loja: " + loja);
    }

    public double calcularMedia() {
    double soma = 0;
    for (double salario : salarioRecebido) {
    soma += salario;
    }
    return soma / salarioRecebido.size();
    }

    public double calcularBonus() {
    return salarioBase * 0.35;
    }
}
