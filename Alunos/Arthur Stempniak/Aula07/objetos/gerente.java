package objetos;

import java.util.ArrayList;

public class gerente {
    private String nome;
    private int idade;
    private String loja;
    private endereco endereco;
    private double salarioBase;
    private ArrayList<Double> salarioRecebido;

    // Construtor
    public gerente(String nome, int idade, String loja, endereco endereco, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.endereco = endereco;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new ArrayList<>();

        // Inserindo três salários como exemplo
        this.salarioRecebido.add(salarioBase);
        this.salarioRecebido.add(salarioBase + 200);
        this.salarioRecebido.add(salarioBase - 150);
    }

    // Método para apresentar as informações do gerente
    public void apresentarSe() {
        System.out.println("Gerente: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
        endereco.apresentarLogradouro();
    }

    // Método para calcular a média dos salários
    public double calcularMedia() {
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.size();
    }

    // Método para calcular bônus
    public double calcularBonus() {
        return salarioBase * 0.35;
    }

    // Método para exibir todos os salários recebidos
    public void exibirSalarios() {
        System.out.println("Salários Recebidos:");
        for (double salario : salarioRecebido) {
            System.out.printf("R$ %.2f\n", salario);
        }
    }
}
