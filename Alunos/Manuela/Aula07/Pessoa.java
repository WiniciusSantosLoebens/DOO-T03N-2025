package objetos;

public abstract class Pessoa {
	 private String nome;
	    private int idade;
	    private Endereco endereco; // Usando a classe Endereco

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

	    // Método abstrato para forçar implementação nas subclasses
	    public abstract void apresentarse();
    
    
}