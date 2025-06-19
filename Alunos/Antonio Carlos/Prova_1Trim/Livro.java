package biblioteca.objetos;

public abstract class Livro {
	
	private String titulo;
	private String autor;
	
	public Livro(String titulo, String autor) {
		super();
		this.titulo = titulo;
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}
	
	public abstract boolean podeSerEmprestado();

   
    @Override
    public String toString() {
        return "Livro [Titulo=" + titulo + ", Autor=" + autor;
    }
	

}
