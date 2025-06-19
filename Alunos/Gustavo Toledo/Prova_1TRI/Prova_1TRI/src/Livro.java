public class Livro {
    String titulo;
    String autor;
    boolean emprestado;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.emprestado = false;
    }

    public boolean podeSerEmprestado() {
        return !this.emprestado;
    }
}
