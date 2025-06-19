package objeto;

public class LivroComum extends Livro {

    public LivroComum(String titulo, String autor) {
        super(titulo, autor);
    }

    @Override
    public void emprestarLivro() {
        super.emprestar(); 
    }
}