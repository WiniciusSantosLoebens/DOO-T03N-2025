package objetosLoja;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Classe abstrata para funcionários (Vendedores, Gerentes), herda de Pessoa.
public abstract class Funcionario extends Pessoa {
	
	private Loja loja;
    private double salarioBase;
    private List<Double> salarioRecebido;
    
    public Funcionario(String nome, int idade, Endereco endereco, Loja loja, double salarioBase, List<Double> salariosIniciais) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
        // Garante que a lista exista e tenha pelo menos 3 salários
        this.salarioRecebido = salariosIniciais != null ? new ArrayList<>(salariosIniciais) : new ArrayList<>();
        while (this.salarioRecebido.size() < 3) {
            // Adiciona o salário base se a lista inicial tiver menos de 3
            this.salarioRecebido.add(salarioBase);
        }
    }
    
    public Loja getLoja() {
    	return loja; 
    }
    
    public double getSalarioBase() { 
    	return salarioBase; 
    
    }
    // Retorna uma cópia para proteger a lista interna
    public List<Double> getSalarioRecebido() {
    	return new ArrayList<>(salarioRecebido);
    }
    
    //  Calcula a média dos salários na lista salarioRecebido.
    public double calcularMediaSalarial() {
        if (salarioRecebido == null || salarioRecebido.isEmpty()) {
            return 0.0;
        }
        return salarioRecebido.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }
    
   // Método abstrato para cálculo de bônus, varia conforme o cargo.
   public abstract double calcularBonus();

   @Override
   public void apresentarSe() {
       System.out.println("Nome: " + getNome() + ", Idade: " + getIdade() + ", Loja: " + loja.getNomeFantasia());
       getEndereco().apresentarLogradouro(); // Mostra o endereço
   }

   // Método para adicionar salário
   public void adicionarSalario(double salario) {
       this.salarioRecebido.add(salario);
   }
    

}
