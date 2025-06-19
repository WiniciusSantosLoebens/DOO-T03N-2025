package objetos;

public class LivroRaro  extends Livro{
	
	String exRaridade;
	
	public LivroRaro(String nome, String autor, String exRaridade) {
		
		super(nome, autor);
		this.exRaridade = exRaridade;
		
	}

	@Override
	public boolean podeSerEmprestado() {
	
		return false;
	}

	public String getExRaridade() {
		return exRaridade;
	}

	public void setExRaridade(String exRaridade) {
		this.exRaridade = exRaridade;
	}

	@Override
	public String toString() {
		return "LivroRaro [exRaridade=" + exRaridade + "]";
	}

	
	
	
	
	
	
	
	

}
