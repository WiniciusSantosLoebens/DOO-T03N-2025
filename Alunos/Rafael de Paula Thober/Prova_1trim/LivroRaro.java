public class LivroRaro extends Livro {
    private String justificativa;

    public LivroRaro(String titulo, String autor, String justificativa) {
        super(titulo, autor);
        this.justificativa = justificativa;
    }

    public String getJustificativa() {
        return justificativa;
    }

    @Override
    public boolean podeSerEmprestado() {
        return false;
    }

    @Override
    public void apresentarse() {
        System.out.println("Livro Raro");
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Justificativa: " + justificativa);
        System.out.println("Disponível: Não. Este é um livro raro");
    }
}
