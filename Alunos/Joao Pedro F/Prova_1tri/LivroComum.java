public class LivroComum extends Livro {
    public LivroComum(String titulo, String autor) {
        super(titulo, autor);
    }

    public String detalhes() {
        return "Livro Comum: " + titulo + " de " + autor;
    }
}
