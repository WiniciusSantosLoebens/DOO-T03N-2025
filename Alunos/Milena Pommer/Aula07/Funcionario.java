package objetosMyPlant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Funcionario extends Pessoa {

	private Loja loja;
	private double salarioBase;
	private List<Double> salarioRecebido;

	public Funcionario(String nome, int idade, Endereco endereco, Loja loja, double salarioBase,
			List<Double> salariosIniciais) {
		super(nome, idade, endereco);
		this.loja = loja;
		this.salarioBase = salarioBase;
		this.salarioRecebido = salariosIniciais != null ? new ArrayList<>(salariosIniciais) : new ArrayList<>();
		while (this.salarioRecebido.size() < 3) {
			this.salarioRecebido.add(salarioBase);
		}
	}

	public Loja getLoja() {
		return loja;
	}

	public double getSalarioBase() {
		return salarioBase;

	}

	public List<Double> getSalarioRecebido() {
		return new ArrayList<>(salarioRecebido);
	}

	public double calcularMediaSalarial() {
		if (salarioRecebido == null || salarioRecebido.isEmpty()) {
			return 0.0;
		}
		return salarioRecebido.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
	}

	public abstract double calcularBonus();

	@Override
	public void apresentarSe() {
		System.out.println("Nome: " + getNome() + ", idade: " + getIdade() + ", loja: " + loja.getNomeFantasia());
		getEndereco().apresentarLogradouro();
	}

	public void adicionarSalario(double salario) {
		this.salarioRecebido.add(salario);
	}
}
