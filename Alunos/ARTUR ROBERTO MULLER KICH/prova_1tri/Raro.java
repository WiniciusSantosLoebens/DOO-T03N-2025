public class Raro extends Livros {
    String motivo;

    public Raro(String nomeLivro, String nomeAutor, String motivo) {
        super(nomeLivro, nomeAutor);
        this.motivo = motivo;
    }

    @Override
    public void registrarEmprestimo() {
        System.out.println("Livro raro não pode ser emprestado. Motivo: " + motivo);
    }

    @Override
    public void registrarDevolucao() {
        System.out.println("Livro raro não foi emprestado.");
    }
}
