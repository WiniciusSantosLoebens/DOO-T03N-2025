import java.util.Date;

public class Emprestimo {
    private Livro livro;
    private Cliente cliente;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public Emprestimo(Livro livro, Cliente cliente, Date dataEmprestimo) {
        this.livro = livro;
        this.cliente = cliente;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = null;
        livro.setDisponivel(false);
    }

    public boolean devolver(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
        livro.setDisponivel(true);
        return true;
    }

    public double calcularMulta() {
        if (dataDevolucao == null) return 0;

        long diasAtraso = (dataDevolucao.getTime() - dataEmprestimo.getTime()) / 86400000;
        return Math.max(0, diasAtraso - 7) * 3.50;
    }

    public Livro getLivro() {
        return livro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }
}
