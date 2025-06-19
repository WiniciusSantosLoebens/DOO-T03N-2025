package objetosLoja;

import java.util.Arrays;
import java.util.List;

// Representa um Vendedor, estendendo Funcionario.
public class Vendedor extends Funcionario {
	
	// Construtor que chama o construtor da superclasse (Funcionario)
    // Passa uma lista inicial de salários padrão para vendedor
    public Vendedor(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
        super(nome, idade, endereco, loja, salarioBase, Arrays.asList(salarioBase * 0.95, salarioBase, salarioBase * 1.05)); // Exemplo de salários
    }
    
    // Implementa o cálculo de bônus específico para Vendedor (20%). 
    @Override
    public double calcularBonus() {
        return getSalarioBase() * 0.20;
    }

}
