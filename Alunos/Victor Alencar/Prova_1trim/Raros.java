public class Raros extends Livros {
    private String descricao;

    public Raros(String titulo, String autor, String descricao) {
        super(titulo, autor);
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

   
    public boolean podeSerEmprestado() {
        return false;  
    }

    public String toString() {
        return super.toString() + ", Descrição: " + descricao;
    }
}
