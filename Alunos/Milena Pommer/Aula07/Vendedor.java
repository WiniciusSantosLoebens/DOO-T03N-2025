package objetosMyPlant;

import java.util.Arrays;
import java.util.List;

public class Vendedor extends Funcionario {

	public Vendedor(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
		super(nome, idade, endereco, loja, salarioBase,
				Arrays.asList(salarioBase * 0.95, salarioBase, salarioBase * 1.05));
	}

	@Override
	public double calcularBonus() {
		return getSalarioBase() * 0.20;
	}
}
