package objetos;

public class Cliente {
	
	String nome;
	String cpf;
	
	public Cliente(String nome,	String cpf) {
		
		this.cpf = cpf;
		this.nome = nome;
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cpf=" + cpf + "]";
	}
	
	
	
	
	
	

}
