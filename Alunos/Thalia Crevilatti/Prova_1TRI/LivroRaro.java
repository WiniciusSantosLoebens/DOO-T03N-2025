//LivroRaro.java
public class LivroRaro extends Livro {
    private String justificativa;

    public LivroRaro(String titulo, String autor, String justificativa) {
        super(titulo, autor);
        this.justificativa = justificativa;
    }

    @Override
    public boolean podeSerEmprestado() {
        return false;
    }

    public String getJustificativa() {
        return justificativa;
    }

    @Override
    public String toString() {
        return super.toString() + ", Tipo: Raro";
    }
}    