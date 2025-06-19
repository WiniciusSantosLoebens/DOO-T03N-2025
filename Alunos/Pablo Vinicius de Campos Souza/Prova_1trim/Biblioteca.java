import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    List<Cliente> clientes;

    List<Livro> livros;

    List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.clientes = new ArrayList<>();
        this.livros = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public Biblioteca(List<Cliente> clientes, List<Livro> livros, List<Emprestimo> emprestimos) {
        this.clientes = clientes;
        this.livros = livros;
        this.emprestimos = emprestimos;
    }

    // Ações
    public Livro buscarPorTitulo(String titulo) {
        return livros.stream()
            .filter(livro -> livro.getTitulo().toUpperCase().contains(titulo.toUpperCase()))
            .findFirst()
            .orElse(null);
    }

    public Livro buscarPorNomeAutor(String nome) {
        return livros.stream()
            .filter(livro -> 
                livro.getAutor().getNome().toUpperCase().contains(nome.toUpperCase()))
            .findFirst()
            .orElse(null);
    }
    
    public void imprimirTodosOsLivros() {
        System.out.println("=== LIVROS ===");
        livros.stream()
            .forEach(livro -> System.out.printf("[%d] - %s %s%n", livro.getId(), livro.getTitulo(), livro.getEmprestimoAtual() != null ? "(Emprestado)" : ""));
    }
    
    public Livro encontraLivroPorId(Integer id) {
        return livros.stream()
            .filter(livro -> livro.getId() == id)
            .findFirst()
            .orElse(null);
    }

    // Gerenciamento das listas
    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void removerLivro(Livro livro) {
        livros.remove(livro);
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void removerCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    public void removerEmprestimo(Emprestimo emprestimo) {
        emprestimos.remove(emprestimo);
    }

    // Getters e Setters
    private List<Cliente> getClientes() {
        return clientes;
    }

    private List<Livro> getLivros() {
        return livros;
    }

    private List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
}
