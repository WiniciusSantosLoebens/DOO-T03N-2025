// Emprestimo.java

public class Emprestimo {
    private Cliente cliente;
    private Livro livro;
    private String dataEmprestimo;
    private String dataDevolucao;

    public Emprestimo(Cliente cliente, Livro livro, String dataEmprestimo, String dataDevolucao) {
        this.cliente = cliente;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Livro getLivro() {
        return livro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public double calcularMulta() {
        long diasAtraso = 10;
        double multaPorDia = 3.50;

        if (diasAtraso > 7) {
            return (diasAtraso - 7) * multaPorDia;
        }
        return 0.0;
    }
}