package Alunos.Braian.Prova_1trim;

public class LivroRaro extends Livro {
    private final String explicacaoRaridade;

    public LivroRaro(String nomeLivro, String autor, String genero, Integer anoPublicacao, String explicacaoRaridade) {
        super(nomeLivro, autor, genero, anoPublicacao);
        this.explicacaoRaridade = explicacaoRaridade;
    }

    public String getExplicacaoRaridade() {
        return explicacaoRaridade;
    }

    @Override
    public boolean podeSerEmprestado() {
        // Livros raros não podem ser emprestados
        System.out.println("O livro " + getNomeLivro() + " é raro e não pode ser emprestado.");
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + " | Explicação da raridade: " + explicacaoRaridade;
    }
}
