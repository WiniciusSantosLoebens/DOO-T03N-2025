package objetos;

public class LivroComum extends Livro {

	public LivroComum(String nome, String autor) {
		super(nome, autor);
	
	}

	@Override
	public boolean podeSerEmprestado() {
		
		return true;
	}
	
	
}
