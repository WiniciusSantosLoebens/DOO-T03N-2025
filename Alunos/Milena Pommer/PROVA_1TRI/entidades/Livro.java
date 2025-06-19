package entidades;

public abstract class Livro {
	
	private int idLivro;
	private String tituloLivro;
	private String autorLivro;
	private boolean disponibilidade;
	
	public Livro(int idLivro, String tituloLivro, String autorLivro, boolean disponibilidade) {
		super();
		this.idLivro = idLivro;
		this.tituloLivro = tituloLivro;
		this.autorLivro = autorLivro;
		this.disponibilidade = true;
	}

	public int getIdLivro() {
		return idLivro;
	}

	public String getTituloLivro() {
		return tituloLivro;
	}

	public String getAutorLivro() {
		return autorLivro;
	}

	public boolean isDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	@Override
	public String toString() {
		return "Livro [idLivro=" + idLivro + ", tituloLivro=" + tituloLivro + ", autorLivro=" + autorLivro
				+ ", disponibilidade=" + disponibilidade + "]";
	}
}
