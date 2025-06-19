import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Livro {
    private String titulo;
    private String autor;
    private String tipo;
    private boolean emprestado;
    private LocalDate dataEmprestimo;

    public Livro(String titulo, String autor, String tipo, boolean emprestado) {
        this.titulo = titulo;
        this.autor = autor;
        this.tipo = tipo;
        this.emprestado = emprestado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void emprestar() {
        if (!emprestado) {
            this.emprestado = true;
            this.dataEmprestimo = LocalDate.now();
        }
    }

    public double devolver() {
        if (emprestado) {
            this.emprestado = false;
            long dias = ChronoUnit.DAYS.between(dataEmprestimo, LocalDate.now());
            this.dataEmprestimo = null;

            if (dias > 7) {
                return (dias - 7) * 3.50;
            }
        }
        return 0.0;
    }

    public String mostrarLivros(){
        String status = emprestado ? "Emprestado" : "Disponível";
        return String.format("Título: %s, Autor: %s, Tipo: %s, Status: %s", titulo, autor, tipo, status);
    }
}
