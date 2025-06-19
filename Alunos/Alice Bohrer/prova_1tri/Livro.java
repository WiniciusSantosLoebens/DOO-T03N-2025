package objetos;

public abstract class Livro {
	
	String nome;
	String autor;
	boolean disponibilidade;
	
	
	public Livro (String nome,String autor) {
		
		this.autor = autor;
		this.nome = nome;
		this.disponibilidade = true;
		
	}
	
	 public abstract boolean podeSerEmprestado();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public boolean isDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

		
		
	}
	

	


