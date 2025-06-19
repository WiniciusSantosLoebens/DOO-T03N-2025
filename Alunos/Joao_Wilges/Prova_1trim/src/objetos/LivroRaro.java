package objetos;

public class LivroRaro extends Livro {
    public Livro toString;
    private String motivoRaridade;

    public LivroRaro(String titulo, String autor, String editora, int anoPublicacao, int numeroDePaginas, String isbn, String motivoRaridade) {
        super(titulo, autor, editora, anoPublicacao, numeroDePaginas, isbn);
        this.motivoRaridade = motivoRaridade;
    }
    public void explicacao(){
        System.out.println(motivoRaridade);
    }


    @Override
    public String toString() {
        return "Livro [" +
                "título='" + super.getTitulo() + '\'' +
                ", autor='" + super.getAutor() + '\'' +
                ", editora='" + super.getEditora() + '\'' +
                ", ano de publicação=" + super.getAnoPublicacao() +
                ", numero de páginas=" + super.getNumeroDePaginas() +
                ", emprestado=" + super.getStatusEmprestado() +
                ", isbn='" + super.getIsbn() + '\'' +
                ", motivo raridade='" + motivoRaridade + '\'' +
                ']';
    }
}