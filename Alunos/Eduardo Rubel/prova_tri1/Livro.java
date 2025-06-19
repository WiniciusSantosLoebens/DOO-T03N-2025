public class Livro {
    private String titulo;
    private String autor;
    private boolean raro;
    private String descricaoRaridade;
    private boolean emprestado;

    public Livro(String titulo, String autor, boolean raro, String descricaoRaridade) {
        this.titulo = titulo;
        this.autor = autor;
        this.raro = raro;
        this.descricaoRaridade = descricaoRaridade;
        this.emprestado = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isRaro() {
        return raro;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    public boolean podeSerEmprestado() {
        return !raro;
    }

    public String getDescricaoRaridade() {
        return descricaoRaridade;
    }
}
