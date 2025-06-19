import java.util.ArrayList;

    public class Vendedor {
        String nome;
        int idade;
        Loja loja;
        Endereco endereco;
        double salarioBase;
        ArrayList<Double> salarioRecebido = new ArrayList<>();

        public Vendedor(String nome, int idade, Loja loja, Endereco endereco, double salarioBase, ArrayList<Double> salarioRecebido) {
            this.nome = nome;
            this.idade = idade;
            this.loja = loja;
            this.endereco = endereco;
            this.salarioBase = salarioBase;
            this.salarioRecebido = salarioRecebido;

            salarioRecebido.add(salarioBase);
            salarioRecebido.add(salarioBase + 200);
            salarioRecebido.add(salarioBase - 150);
        }

        public void apresentarse() {
            System.out.println("Nome: " + nome);
            System.out.println("Idade: " + idade);
            System.out.println("Loja: " + loja.nomeFantasia);
            System.out.println("---------------------");
        }

        public double calcularMedia() {
            double soma = 0;
            for (double salario : salarioRecebido) {
                soma += salario;
            }
            return soma / salarioRecebido.size();
        }

        public double calcularBonus() {
            return salarioBase * 0.2;
        }
    }