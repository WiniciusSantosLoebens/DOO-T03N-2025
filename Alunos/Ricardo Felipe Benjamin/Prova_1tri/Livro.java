import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Livro {
    protected String titulo;
    protected String autor;
    protected boolean emprestado = false;
    protected LocalDate dataEmprestimo;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public boolean isRaro() {
        return false;
    }

    public void emprestar() {
        emprestado = true;
        dataEmprestimo = LocalDate.now();
    }

    public double devolver() {
        emprestado = false;
        long dias = ChronoUnit.DAYS.between(dataEmprestimo, LocalDate.now());
        return dias > 7 ? (dias - 7) * 3.5 : 0;
    }
}
