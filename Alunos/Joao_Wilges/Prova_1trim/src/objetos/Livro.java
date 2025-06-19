package objetos;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Livro{
    private String titulo;
    private String autor;
    private String editora;
    private int anoPublicacao;
    private int numeroDePaginas;
    private String isbn;
    private Boolean emprestado;
    protected Date dataEmprestimo;
    protected Date dataDevolucao;

    public Livro(String titulo,String autor,String editora,int anoPublicacao,int numeroDePaginas, String isbn){
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.dataEmprestimo = null;
        this.dataEmprestimo = null;
    }

    public String getTitulo() {
        return titulo;
    }

    public Boolean getStatusEmprestado(){return emprestado;}
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getAutor() {
        return autor;
    }


    public void setAutor(String autor) {
        this.autor = autor;
    }


    public String getEditora() {
        return editora;
    }


    public void setEditora(String editora) {
        this.editora = editora;
    }


    public int getAnoPublicacao() {
        return anoPublicacao;
    }


    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }


    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }


    public void setNumeroDePaginas(int numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }


    public String getIsbn() {
        return isbn;
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    @Override
    public String toString() {
        return "Livro [" +
                "título='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editora='" + editora + '\'' +
                ", ano de publicação=" + anoPublicacao +
                ", numero de páginas=" + numeroDePaginas +
                ", emprestado=" + emprestado +
                ", isbn='" + isbn + '\'' +
                ']';
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }
    public void emprestarLivro(){
        this.emprestado = true;
        dataEmprestimo = new Date();
    }
    public void devolverLivro(){
        this.emprestado = false;
    }
}