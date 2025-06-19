import java.util.ArrayList;

public class Biblioteca {
    ArrayList<Clientes> clientes = new ArrayList<>();
    ArrayList<Livros> livros = new ArrayList<>();

    public void adicionarCliente(Clientes cliente) {
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso.");
    }

    public void adicionarLivro(Livros livro) {
        for (Livros l : livros) {
            if (l.nomeLivro.equalsIgnoreCase(livro.nomeLivro) &&
                    l.nomeAutor.equalsIgnoreCase(livro.nomeAutor)) {
                System.out.println("Este livro já está cadastrado.");
                return;
            }
        }
        livros.add(livro);
        System.out.println("Livro cadastrado com sucesso.");
    }

    public Livros buscarPorTitulo(String titulo) {
        for (Livros l : livros) {
            if (l.nomeLivro.equalsIgnoreCase(titulo)) {
                return l;
            }
        }
        return null;
    }

    public Livros buscarPorAutor(String autor) {
        for (Livros l : livros) {
            if (l.nomeAutor.equalsIgnoreCase(autor)) {
                return l;
            }
        }
        return null;
    }
}
