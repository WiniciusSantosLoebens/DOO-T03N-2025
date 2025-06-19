package entidades;

public class LivroComum extends Livro {

	public LivroComum(int idLivro, String tituloLivro, String autorLivro, boolean disponibilidade) {
		super(idLivro, tituloLivro, autorLivro, disponibilidade);
		// TODO Auto-generated constructor stub
	}

	public void emprestar() {
		if (isDisponibilidade()) {
			setDisponibilidade(false);
		} else {
			System.out.println("Livro não está disponível para empréstimo.");
		}
	}

	public void devolver() {
		setDisponibilidade(true);
	}

}
