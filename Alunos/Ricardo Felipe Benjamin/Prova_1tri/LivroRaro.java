public class LivroRaro extends Livro {
    private final String justificativa;

    public LivroRaro(String titulo, String autor, String justificativa) {
        super(titulo, autor);
        this.justificativa = justificativa;
    }

    @Override
    public boolean isRaro() {
        return true;
    }

    @Override
    public void emprestar() {
    }

    @Override
    public double devolver() {
        return -1;
    }

    public String getJustificativa() {
        return justificativa;
    }
}
