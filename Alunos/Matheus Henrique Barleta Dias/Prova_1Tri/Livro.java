public abstract class Livro {
    protected String titulo;
    protected String autor;
    protected Boolean podeSerEmprestado;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }
    
    public String toString(){
        return "Titulo: " + titulo + ", Autor: "+ autor;
    }

    public String getTitulo() {
        return titulo;
    }
    
    public String getAutor() {
        return autor;
    }

    public abstract boolean podeSerEmprestado();
    
}