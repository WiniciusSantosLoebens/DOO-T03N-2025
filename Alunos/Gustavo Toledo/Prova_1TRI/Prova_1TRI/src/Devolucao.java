import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Devolucao {
    Livro livro;
    Cliente cliente;
    LocalDate dataEmprestimo;
    LocalDate dataDevolucao;

    public Devolucao(Livro livro, Cliente cliente) {
        this.livro = livro;
        this.cliente = cliente;
        this.dataEmprestimo = LocalDate.now();
    }

    public double realizarDevolucao() {
        dataDevolucao = LocalDate.now();
        livro.emprestado = false;
        long diasAtraso = ChronoUnit.DAYS.between(dataEmprestimo.plusDays(7), dataDevolucao);
        return diasAtraso > 0 ? diasAtraso * 3.50 : 0.0;
    }
}
