public class Livro {
    private String titulo;
    private String autor;
    private boolean emprestado;
    private boolean raro;
    private String explicacao;

    public Livro(String titulo, String autor, boolean raro, String explicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.raro = raro;
        this.explicacao = explicacao;
        this.emprestado = false;
    }

    // Getters e setters
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public boolean isEmprestado() { return emprestado; }
    public boolean isRaro() { return raro; }
    public String getExplicacao() { return explicacao; }
    public void setEmprestado(boolean emprestado) { this.emprestado = emprestado; }
}
