public class LivroRaro extends Livro {
    private String descricaoRaridade;

    public LivroRaro(String titulo, String autor, String descricaoRaridade) {
        super(titulo, autor);
        this.descricaoRaridade = descricaoRaridade;
    }

    public String getDescricaoRaridade() {
        return descricaoRaridade;
    }

    @Override
    public boolean podeEmprestar() {
        return false; 
    }
}
