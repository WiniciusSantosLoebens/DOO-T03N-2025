package Alunos.Gustavo_Baú.Prova_1Tri;

public class Livros {
    
    private String titulo;
    private String autor;
    private boolean emprestado;
    private String raridade;
    private String motivoRaridade;

    Livros(String titulo, String autor, String raridade) {
        this.titulo = titulo;
        this.autor = autor;
        this.raridade = raridade;
        this.emprestado = false;
        this.motivoRaridade = "Raridade: " + raridade;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    public String getRaridade() {
        return raridade;
    }

    public void setRaridade(String raridade) {
        this.raridade = raridade;
    }

    public String getMotivoRaridade() {
        return motivoRaridade;
    }

    public void setMotivoRaridade(String motivoraridade) {
        this.motivoRaridade = motivoraridade;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", \nAutor: " + autor;
    }
}
