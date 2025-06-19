package entidades;

public class LivroRaro extends Livro {

	private String raridade;

	public LivroRaro(int idLivro, String tituloLivro, String autorLivro, boolean disponibilidade) {
		super(idLivro, tituloLivro, autorLivro, disponibilidade);
		// TODO Auto-generated constructor stub
		this.raridade = "Este livro foi encontrado em meio a destroços da segunda guerra mundial,"
				+ " portanto está disponível apenas para visualização dentro da biblioteca,"
				+ " para assim evitar acidentes ou estravios. Obrigada pela compreensão! :)";
	}

	public final String raridade() {

		return this.raridade;
	}

	public void emprestar() {
		System.out.println("Este livro raro não pode ser emprestado.");
	}

	public void devolver() {
		setDisponibilidade(true);
	}
}
