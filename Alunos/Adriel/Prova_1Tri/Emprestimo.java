import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private double multa;

    public Emprestimo(Livro livro, LocalDate dataEmprestimo) {
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataEmprestimo.plusDays(7);
    }

    public void devolverLivro(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
        if (dataDevolucaoReal.isAfter(dataDevolucaoPrevista)) {
            long diasAtraso = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucaoReal);
            this.multa = diasAtraso * 3.50;
        } else {
            this.multa = 0.0;
        }
    }

    public Livro getLivro() {
        return livro;
    }

    public double getMulta() {
        return multa;
    }
}
