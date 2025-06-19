public class LivroRaro extends Livro {
    private String motivoRaridade;

    public LivroRaro(String titulo, String autor, String motivoRaridade) {
        super(titulo, autor);
        this.motivoRaridade = motivoRaridade;
    }

    @Override
    public boolean podeSerEmprestado() {
        return false;
    }

    public String getMotivoRaridade() {
        return motivoRaridade;
    }
}
