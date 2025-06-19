public class Raro extends Livro {
    private String descricao;

    public Raro(String titulo, String autor, String descricao) {
        super(titulo, autor);
        this.descricao = descricao;
    }

    @Override
    public boolean podeSerEmprestado(){
        return false;
    }

    public String descricao(){
        return descricao;
    }
}
