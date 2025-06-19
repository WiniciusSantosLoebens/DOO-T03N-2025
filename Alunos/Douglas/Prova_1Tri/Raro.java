public class Raro extends Livro {
    String explicacao;
    
    public Raro(int id, String titulo, String autor, int anoPublicacao) {
        super(id, titulo, autor, anoPublicacao);
    }

    public void adicionarExplicacao(String explicacao) {
        this.explicacao = explicacao;
    }

    public boolean podeSerEmprestado() {
        return false;
    }

}
