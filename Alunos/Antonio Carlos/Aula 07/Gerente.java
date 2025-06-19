package objetosLoja;

import java.util.Arrays;
import java.util.List;

// Representa um Gerente, estendendo Funcionario.
public class Gerente extends Funcionario {
	
	// Construtor que chama o construtor da superclasse (Funcionario)
    // Passa uma lista inicial de salários padrão para gerente
    public Gerente(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
        super(nome, idade, endereco, loja, salarioBase, Arrays.asList(salarioBase * 1.1, salarioBase * 1.2, salarioBase * 1.15)); // Exemplo de salários
    }
    
    
    // Implementa o cálculo de bônus específico para Gerente (35%).
    @Override
    public double calcularBonus() {
        return getSalarioBase() * 0.35;
    }

   
    // Sobrescreve o método apresentarSe para adicionar a identificação [Gerente].
    @Override
    public void apresentarSe() {
        System.out.print("[Gerente] ");
        super.apresentarSe();
    }

}
