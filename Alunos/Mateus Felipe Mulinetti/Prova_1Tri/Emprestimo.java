package objetos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private Cliente cliente;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(Cliente cliente, Livro livro) {
        this.cliente = cliente;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        this.livro.setDisponivel(false);
    }

    public double devolverLivro() {
        this.dataDevolucao = LocalDate.now();
        livro.setDisponivel(true);
        long dias = ChronoUnit.DAYS.between(dataEmprestimo, dataDevolucao);
        if (dias > 7) {
            return (dias - 7) * 3.50;
        }
        return 0.0;
    }

    public Livro getLivro() {
        return livro;
    }
}
