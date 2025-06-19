public class LivroRaro extends Livro {
    private String justificativa;

    public LivroRaro(String titulo, String autor, String justificativa) {
        super(titulo, autor);
        this.justificativa = justificativa;
        this.disponivel = false;
    }

    public String getJustificativa() {
        return justificativa;
    }
}