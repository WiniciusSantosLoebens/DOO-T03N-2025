import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void cadastrarCliente(Cliente cliente) {
    clientes.add(cliente);
    }

    public void cadastrarLivro(Livro livro) {
    livros.add(livro);
    }

    public Livro buscarLivroPorTitulo(String titulo) {
    return livros.stream()
    .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
    .findFirst().orElse(null);
    }

    public Livro buscarLivroPorAutor(String autor) {
    return livros.stream()
    .filter(l -> l.getAutor().equalsIgnoreCase(autor))
    .findFirst().orElse(null);
    }

    public Cliente buscarClientePorNome(String nome) {
        return clientes.stream()
        .filter(c -> c.getNome().equalsIgnoreCase(nome))
        .findFirst()
        .orElse(null);
       }

    public boolean estaDisponivel(String titulo) {
        Livro livro = buscarLivroPorTitulo(titulo);
        if (livro == null || !livro.podeEmprestar()) return false;
        return emprestimos.stream()
        .noneMatch(e -> e.getLivro().equals(livro) && !e.isDevolvido());
                   }

    public boolean realizarEmprestimo(String titulo, Cliente cliente) {
        Livro livro = buscarLivroPorTitulo(titulo);
        if (livro != null && livro.podeEmprestar() && estaDisponivel(titulo)) {
        emprestimos.add(new Emprestimo(livro, cliente));
        return true;
        }
        return false;
                       }

    public double realizarDevolucao(String titulo) {
        for (Emprestimo e : emprestimos) {
        if (e.getLivro().getTitulo().equalsIgnoreCase(titulo) && !e.isDevolvido()) {
        e.devolver();
        return e.calcularMulta();
            }
        }
        return -1; 
           }
                                          }
