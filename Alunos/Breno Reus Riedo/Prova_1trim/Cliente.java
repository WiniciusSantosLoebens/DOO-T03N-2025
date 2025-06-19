import java.util.List;

public class Cliente {


    private int id;
    private String nome;
    private int idade;
    private List<Livro> inventarioDeLivro;

    public Cliente(int id, String nome, int idade, List<Livro> inventarioDeLivro){
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.inventarioDeLivro = inventarioDeLivro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    public List<Livro> getInventarioDeLivro() {
        return inventarioDeLivro;
    }

    public void setInventarioDeLivro(List<Livro> inventarioDeLivro) {
        this.inventarioDeLivro = inventarioDeLivro;
    }

    public void addLivro(Livro livro){
        inventarioDeLivro.add(livro);
    }
    public void removeLivro(Livro livro){
        inventarioDeLivro.remove(livro);
    }
}
