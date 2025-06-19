import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private Livro livro;
    private Cliente cliente;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(Livro livro, Cliente cliente) {
        this.livro = livro;
        this.cliente = cliente;
        this.dataEmprestimo = LocalDate.now();
    }

    public void devolver() {
        this.dataDevolucao = LocalDate.now();
    }

    public double calcularMulta() {
        if (dataDevolucao == null) return 0;
        long diasAtraso = ChronoUnit.DAYS.between(dataEmprestimo.plusDays(7), dataDevolucao);
        return diasAtraso > 0 ? diasAtraso * 3.5 : 0;
    }

    public Livro getLivro() {
        return livro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public boolean isDevolvido() {
        return dataDevolucao != null;
    }
}
