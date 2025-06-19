public class LivroNormal extends Livro {
    public LivroNormal(String titulo, String autor) {
        super(titulo, autor);
    }

    @Override
    public boolean podeEmprestar() {
        return true; 
    }
}
