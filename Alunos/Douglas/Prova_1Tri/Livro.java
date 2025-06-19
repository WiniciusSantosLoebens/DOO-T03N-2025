public class Livro {
    int id;
    String titulo;
    String autor;
    int anoPublicacao;
    boolean emprestado;

    public Livro(int id, String titulo, String autor, int anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.emprestado = false;
    }

    public boolean podeSerEmprestado() {
        return true;
    }

    @Override
    public String toString() {
        return 
            "ID: " + id +
            "\nTítulo: " + titulo +
            "\nAutor: " + autor +
            "\nAno de Publicação: " + anoPublicacao +
            "\nEmprestado: " + (emprestado ? "Sim" : "Não");
    }

}
