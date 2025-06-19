package objetos;

import java.util.ArrayList;
import java.util.List;

 
    public class Gerente extends Pessoa {
    	private String nomeLoja; 
        private double salarioBase;
        private List<Double> salarioRecebido; 

        public Gerente(String nome, int idade, String nomeLoja, Endereco endereco, double salarioBase) {
            super(nome, idade, endereco);
            this.nomeLoja = nomeLoja;
            this.salarioBase = salarioBase;
            this.salarioRecebido = new ArrayList<>();
        }

        public void adicionarSalario(double salario) {
            this.salarioRecebido.add(salario);
        }

        @Override
        public void apresentarse() {
            System.out.println("--------------------");
            System.out.println("Gerente: " + getNome());
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

        public double calcularMedia() {
            if (salarioRecebido.isEmpty()) {
                return 0.0;
            }
            double soma = 0;
            for (double salario : salarioRecebido) {
                soma += salario;
            }
            return soma / salarioRecebido.size();
        }

        public double calcularBonus() {
            return this.salarioBase * 0.35;
        }

      
        public double getSalarioBase() {
            return salarioBase;
        }

        public List<Double> getSalarioRecebido() {
            return salarioRecebido;
        }

        public String getNomeLoja() {
            return nomeLoja;
        }
}