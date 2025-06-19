package Alunos.Braian.Prova_1trim;
public class LivroComum extends Livro {

    public LivroComum(String nomeLivro, String autor, String genero, Integer anoPublicacao) {
        super(nomeLivro, autor, genero, anoPublicacao);
    }

    @Override
    public boolean podeSerEmprestado() {
        if (isDisponivel()) {
            System.out.println("O livro " + getNomeLivro() + " pode ser emprestado.");
            return true;
        } else {
            System.out.println("O livro " + getNomeLivro() + " não pode ser emprestado.");
            return false;
        }
    }
}
