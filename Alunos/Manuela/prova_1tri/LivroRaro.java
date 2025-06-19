package objeto;

public class LivroRaro extends Livro {
    private String motivoRaridade;

    public LivroRaro(String titulo, String autor, String motivoRaridade) {
        super(titulo, autor);
        this.motivoRaridade = motivoRaridade;
    }

    public String getMotivoRaridade() {
        return motivoRaridade;
    }

    @Override
    public void emprestarLivro() {
        System.out.println("Este livro é raro e não pode ser emprestado.");
    }
}