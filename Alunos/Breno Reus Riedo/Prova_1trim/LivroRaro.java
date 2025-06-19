public class LivroRaro extends Livro{
    private String justificativa;


    public LivroRaro(String tituloLivro, String nomeAutor, String descricaoLivro, boolean disponibilidade, String justificativa){
        super(tituloLivro, nomeAutor, descricaoLivro, disponibilidade);
        this.justificativa = justificativa;
    }

    public String getJustificativa() {
        return justificativa;
    }
    public void setJustificativa(String justificativa){
        this.justificativa = justificativa;
    }

    @Override
    public String toString() {
        return "Titulo: " + super.getTituloLivro() + "\n" + "Autor: " + super.getNomeAutor() + "\n" + "Descrição: " + super.getDescricaoLivro() + "\n" +  "Disponibilidade: " + super.isDisponivel() + "\n" + "Esse livro é raro porque: " + this.justificativa;
    }
}
