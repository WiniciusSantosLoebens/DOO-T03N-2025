public class LivroRaro extends Livro {
    private String justificativa;

    public LivroRaro(String titulo, String autor, String justificativa) {
        super(titulo, autor);
        this.justificativa = justificativa;
    }

    public String detalhes() {
        return "Livro Raro: " + titulo + " de " + autor + " | Justificativa: " + justificativa;
    }

    public void emprestar() {
        System.out.println("Livro raro não pode ser emprestado.");
    }
}
