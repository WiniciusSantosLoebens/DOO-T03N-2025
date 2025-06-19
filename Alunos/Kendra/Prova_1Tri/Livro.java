package objeto;

public class Livro {
    private String titulo;
    private String autor;
    protected boolean emprestado = false;
    private Cliente clienteEmprestimo;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
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
        emprestado = true;
    }

    public void devolver() {
        emprestado = false;
    }
    
    public void setClienteEmprestimo(Cliente cliente) {
        this.clienteEmprestimo = cliente;
    }

    public Cliente getClienteEmprestimo() {
        return clienteEmprestimo;
    }
    
}
