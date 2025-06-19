public class Emprestimo {
    private Cliente cliente;
    private Livro livro;
    private int dataEmprestimo;

    public Emprestimo(Cliente cliente, Livro livro, int dataEmprestimo) {
        this.cliente = cliente;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.livro.setEmprestado(true);
    }

    public double devolver(int dataDevolucao) {
        livro.setEmprestado(false);
        int dias = dataDevolucao - dataEmprestimo;
        if (dias > 7) {
            return (dias - 7) * 3.5;
        }
        return 0.0;
    }

    public Livro getLivro() {
        return livro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getDataEmprestimo() {
        return dataEmprestimo;
    }
}
