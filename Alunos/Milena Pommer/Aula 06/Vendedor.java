package atividadeAula06Objetos;
import java.util.ArrayList; 
import java.util.List;
import java.util.Arrays;

public class Vendedor {

	private String nome;
	private int idade;
	private int loja;
	private String cidade;
	private String bairro;
	private String rua;
	private double salarioBase;
	private List<Double> salarioRecebido = new ArrayList<>(Arrays.asList(2000.00, 2300.30, 2400.50));
	
	public Vendedor(String nome, int idade, int loja, String cidade, String bairro, String rua, double salarioBase,
			List<Double> salarioRecebido) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.loja = loja;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.salarioBase = salarioBase;
		this.salarioRecebido = salarioRecebido;
	}
	
	public void apresentarse() {
		
		System.out.println("Nome vendedor: "+ nome +", idade: "+ idade +", loja: "+ loja);
	}
	
	public double calcularMedia() {
		
		 return salarioRecebido.stream().mapToDouble(Double::doubleValue).average().orElse(0);
	}
	
	public double calcularBonus() {
		
		return salarioBase*0.2;
	}

	public String getNome() {
		return nome;
	}

	public int getLoja() {
		return loja;
	}

	public List<Double> getSalarioRecebido() {
		return salarioRecebido;
	}

	public void setSalarioRecebido(List<Double> salarioRecebido) {
		this.salarioRecebido = salarioRecebido;
	}
	
}
