package objetos;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Pessoa {
	
	private String nomeLoja;
    private double salarioBase;
    private List<Double> salariosRecebidos;

    
    public Vendedor(String nome, int idade, String nomeLoja, Endereco endereco, double salarioBase) {
        super(nome, idade, endereco);
        this.nomeLoja = nomeLoja;
        this.salarioBase = salarioBase;
        this.salariosRecebidos = new ArrayList<>();
    }

    public void adicionarSalario(double salario) {
        this.salariosRecebidos.add(salario);
    }

    public double calcularMedia() {
        if (salariosRecebidos.isEmpty()) {
            return 0.0;
        }
        double soma = 0;
        for (double salario : salariosRecebidos) {
            soma += salario;
        }
        return soma / salariosRecebidos.size();
    }

    public double calcularBonus() {
        return this.salarioBase * 0.20;
    }

    @Override
    public void apresentarse() {
        System.out.println("--------------------");
        System.out.println("Vendedor: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Loja: " + nomeLoja);
        if (getEndereco() != null) {
            getEndereco().apresentarLogradouro();
        } else {
            System.out.println("Endereço não informado.");
        }
        System.out.printf("Salário Base: R$ %.2f\n", salarioBase);
        System.out.println("--------------------");
    }

   
    public double getSalarioBase() {
        return salarioBase;
    }

    public List<Double> getSalariosRecebidos() {
        return salariosRecebidos;
    }

     public String getNomeLoja() {
        return nomeLoja;
     }
 
    }