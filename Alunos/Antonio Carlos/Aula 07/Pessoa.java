package objetosLoja;



// Classe abstrata base para representar pessoas no sistema (Clientes, Funcionários).

public abstract class Pessoa {

	private String nome;
    private int idade;
    private Endereco endereco; // Usa a classe Endereco

    public Pessoa(String nome, int idade, Endereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }

	public String getNome() {
		return nome;
	}

	public int getIdade() {
		return idade;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	
	 public abstract void apresentarSe();
    
}
