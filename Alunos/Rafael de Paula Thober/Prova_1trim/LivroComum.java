public class LivroComum extends Livro {

    public LivroComum(String titulo, String autor) {
        super(titulo, autor);
    }

    @Override
    public boolean podeSerEmprestado() {
        return disponivel;
    }

    @Override
    public void apresentarse() {
        System.out.println("Livro Comum");
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Disponível: " + (disponivel ? "Sim" : "Não"));
    }
}
