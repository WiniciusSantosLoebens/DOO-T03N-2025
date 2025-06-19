package objeto;

public class LivroRaro extends Livro {
    private String justificativa;

    public LivroRaro(String titulo, String autor, String justificativa) {
        super(titulo, autor);
        this.justificativa = justificativa;
    }

    public String getJustificativa() {
        return justificativa;
    }

    @Override
    public void emprestar() {
        System.out.println("Este livro é raro e não pode ser emprestado.");
    }
}