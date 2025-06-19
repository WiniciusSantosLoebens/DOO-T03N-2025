public abstract class Livros {
    private String titulo;
    private String autor;
    private boolean emprestado;

    public Livros(String titulo, String autor) {
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
        if (!isEmprestado()) {
            this.emprestado = true;
        }
    }

    public void devolver() {
        this.emprestado = false;
    }

   
    public abstract boolean podeSerEmprestado();

    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor;
    }
}
