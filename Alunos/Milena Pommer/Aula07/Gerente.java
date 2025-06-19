package objetosMyPlant;

import java.util.Arrays;
import java.util.List;

public class Gerente extends Funcionario {

	public Gerente(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
		super(nome, idade, endereco, loja, salarioBase,
				Arrays.asList(salarioBase * 1.1, salarioBase * 1.2, salarioBase * 1.15)); // Exemplo de salários
	}

	@Override
	public double calcularBonus() {
		return getSalarioBase() * 0.35;
	}

	@Override
	public void apresentarSe() {
		System.out.print("Gerente ");
		super.apresentarSe();
	}
}
