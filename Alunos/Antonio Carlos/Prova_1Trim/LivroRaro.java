package biblioteca.objetos;


// subClasse LivroRaro que herda da SuperClasse Livro
public class LivroRaro extends Livro{
	
	private String motivoRaridade;
	
	public LivroRaro(String titulo, String autor, String motivoRaridade) {
        super(titulo, autor); // Chama o construtor de Livro
        this.motivoRaridade = motivoRaridade;
    }

    //Polimorfismo, representa o metodo abstrato firmado na classe Pai Livro
    // Neste caso indica que livros raros não podem ser emprestados
	@Override
    public boolean podeSerEmprestado() {
        return false; 
    }

	
    public String getMotivoRaridade() {
        return motivoRaridade;
    }

    // Método toString que complementa o toString de Livro mostra que é raro portanto não pode ser emprestado
   
    @Override
    public String toString() {
        return super.toString() + ", Tipo=Raro, Motivo=" + motivoRaridade + ", Status=Consulta Local" + "]";
    }

}
