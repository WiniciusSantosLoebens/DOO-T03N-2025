public abstract class Livro {
    protected String titulo;
    protected String autor;
    protected boolean emprestado;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.emprestado = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void emprestar() {
        this.emprestado = true;
        System.out.println("Empréstimo realizado: " + titulo);
    }

    public void devolver() {
        this.emprestado = false;
        System.out.println("Devolução realizada: " + titulo);
    }

    public abstract String detalhes();
}
