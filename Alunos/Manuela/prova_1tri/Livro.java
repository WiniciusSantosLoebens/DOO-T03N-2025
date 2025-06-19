package objeto;
import java.time.LocalDate;

public abstract class Livro {
    private String titulo;
    private String autor;
    private boolean emprestado;
    private LocalDate dataEmprestimo; 

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.emprestado = false;
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

    public void emprestar() {
        this.emprestado = true;
        this.dataEmprestimo = LocalDate.now(); 
    }

    public void devolver() {
        this.emprestado = false;
        this.dataEmprestimo = null; 
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public double calcularMulta(int diasDeAtraso) {
        if (diasDeAtraso > 7) {
            return (diasDeAtraso - 7) * 3.5; 
        }
        return 0.0; 
    }

   
    public abstract void emprestarLivro();
}