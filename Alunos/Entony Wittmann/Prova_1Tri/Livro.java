import java.util.ArrayList;
import java.util.List;

public class Livro {
    public String nome;
    public String autor;
    public String raridade;
    public String descricao;
    public String disponibilidade;
    public String data;
    public String clienteAlugou;
    public static List<Livro> livros = new ArrayList<Livro>();

    public Livro(String nome, String autor, String raridade, String descricao, String disponibilidade, String data, String clienteAlugou) {
        this.nome = nome;
        this.autor = autor;
        this.raridade = raridade;
        this.descricao = descricao;
        this.disponibilidade = disponibilidade;
        this.data = data;
        this.clienteAlugou = clienteAlugou;
    }
}
