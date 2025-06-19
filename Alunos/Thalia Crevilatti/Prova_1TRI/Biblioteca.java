import java.util.ArrayList;
import java.util.List;
// Biblioteca.java
public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Livro buscarPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public boolean realizarEmprestimo(String cpf, String titulo) {
        Cliente cliente = clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        Livro livro = buscarPorTitulo(titulo);

        if (cliente != null && livro != null && livro.podeSerEmprestado()) {
            livro.emprestar();
            emprestimos.add(new Emprestimo(cliente, livro, "2025-04-01", "2025-04-15"));
            return true;
        }
        return false;
    }

    public double realizarDevolucao(String titulo) {
        for (Emprestimo e : emprestimos) {
            if (e.getLivro().getTitulo().equalsIgnoreCase(titulo)) {
                double multa = e.calcularMulta();
                e.getLivro().devolver();
                emprestimos.remove(e);
                return multa;
            }
        }
        return 0.0;
    }

    public boolean verificarDisponibilidade(String titulo) {
        Livro livro = buscarPorTitulo(titulo);
        return livro != null && !livro.isEmprestado();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Livro> getLivros() {
        return livros;
    }
}