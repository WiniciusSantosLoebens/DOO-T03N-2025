public class Emprestimo {
    private Livro livro;
    private Cliente cliente;

    public Emprestimo(Livro livro, Cliente cliente) {
        this.livro = livro;
        this.cliente = cliente;
    }

    public Livro getLivro() {
        return livro;
    }
    
}
