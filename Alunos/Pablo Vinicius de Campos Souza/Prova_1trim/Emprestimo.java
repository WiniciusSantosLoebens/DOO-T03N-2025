
import java.time.LocalDate;

public class Emprestimo {
    private static int CONTADOR = 0;

    private final int id;

    private boolean ativo;

    private LocalDate dataEmprestimo;

    private Cliente emprestador;

    public Emprestimo(Cliente emprestador) {
        id = CONTADOR++;
        this.ativo = true;
        this.dataEmprestimo = LocalDate.now();
        this.emprestador = emprestador;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate data) {
        this.dataEmprestimo = data;
    }

    public Cliente getEmprestador() {
        return emprestador;
    }

    public void setEmprestador(Cliente emprestador) {
        this.emprestador = emprestador;
    }

    public int getId() {
        return id;
    }

}
