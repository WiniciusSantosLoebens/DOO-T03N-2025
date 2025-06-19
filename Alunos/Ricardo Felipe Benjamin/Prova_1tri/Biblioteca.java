import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Livro> livros = new ArrayList<>();

    public void cadastrarCliente(String nome) {
        boolean existe = clientes.stream().anyMatch(c -> c.getNome().equalsIgnoreCase(nome));
        if (!existe) {
            clientes.add(new Cliente(nome));
            System.out.println("Cliente cadastrado com sucesso.");
        } else {
            System.out.println("Cliente já existe.");
        }
    }

    public void adicionarLivro(Livro livro) {
        boolean existe = livros.stream().anyMatch(l -> l.getTitulo().equalsIgnoreCase(livro.getTitulo()));
        if (!existe) {
            livros.add(livro);
            System.out.println("Livro adicionado com sucesso.");
        } else {
            System.out.println("Livro já cadastrado.");
        }
    }

    public Livro buscarPorTitulo(String titulo) {
        return livros.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst().orElse(null);
    }

    public Livro buscarPorAutor(String autor) {
        return livros.stream()
                .filter(l -> l.getAutor().equalsIgnoreCase(autor))
                .findFirst().orElse(null);
    }

    public boolean realizarEmprestimo(String nomeCliente, String tituloLivro) {
        Cliente cliente = clientes.stream().filter(c -> c.getNome().equalsIgnoreCase(nomeCliente)).findFirst().orElse(null);
        Livro livro = buscarPorTitulo(tituloLivro);

        if (cliente != null && livro != null && !livro.isEmprestado() && !livro.isRaro()) {
            livro.emprestar();
            return true;
        }
        return false;
    }

    public double realizarDevolucao(String tituloLivro) {
        Livro livro = buscarPorTitulo(tituloLivro);
        if (livro != null && livro.isEmprestado()) {
            return livro.devolver();
        }
        return -1;
    }

    public boolean verificarDisponibilidade(String titulo) {
        Livro livro = buscarPorTitulo(titulo);
        return livro != null && !livro.isEmprestado() && !livro.isRaro();
    }
}
