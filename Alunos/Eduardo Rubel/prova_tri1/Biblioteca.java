import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Cliente> clientes = new ArrayList<>();
    private List<Livro> livros = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public Biblioteca() {
        livros.add(new Livro("O Senhor", "J.R.R. Tolkien", true, "É um livro raro devido à sua primeira edicao e relevância histórica."));
        livros.add(new Livro("Harry Potter", "J.K. Rowling", false, null));
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public Livro buscarPorTitulo(String titulo) {
        Livro livro = livros.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
        if (livro != null && livro.isRaro()) {
            System.out.println("Descricao da raridade: " + livro.getDescricaoRaridade());
        }
        return livro;
    }

    public Livro buscarPorAutor(String autor) {
        Livro livro = livros.stream()
                .filter(l -> l.getAutor().equalsIgnoreCase(autor))
                .findFirst()
                .orElse(null);
        if (livro != null && livro.isRaro()) {
            System.out.println("Descricao da raridade: " + livro.getDescricaoRaridade());
        }
        return livro;
    }

    public boolean realizarEmprestimo(String titulo, Cliente cliente, int diaEmprestimo) {
        Livro livro = buscarPorTitulo(titulo);
        if (livro != null && livro.podeSerEmprestado() && !livro.isEmprestado()) {
            emprestimos.add(new Emprestimo(cliente, livro, diaEmprestimo));
            return true;
        }
        return false;
    }

    public double realizarDevolucao(String titulo, int diaDevolucao) {
        for (Emprestimo emp : emprestimos) {
            if (emp.getLivro().getTitulo().equalsIgnoreCase(titulo) && emp.getLivro().isEmprestado()) {
                double multa = emp.devolver(diaDevolucao);
                emprestimos.remove(emp);
                return multa;
            }
        }
        return 0.0;
    }

    public boolean estaDisponivel(String titulo) {
        Livro livro = buscarPorTitulo(titulo);
        return livro != null && !livro.isEmprestado() && livro.podeSerEmprestado();
    }

    public Cliente buscarClientePorNome(String nome) {
        return clientes.stream()
                .filter(c -> c.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado na biblioteca.");
        } else {
            System.out.println("Livros disponíveis na biblioteca:");
            for (Livro livro : livros) {
                String tipoLivro = livro.isRaro() ? "(Raro)" : "(Comum)";
                String emprestado = livro.isEmprestado() ? "(Emprestado)" : "(Disponível)";
                System.out.print(livro.getTitulo() + " - " + livro.getAutor() + " " + tipoLivro + " " + emprestado);
                if (livro.isRaro()) {
                    System.out.println(" | Descricao: " + livro.getDescricaoRaridade());
                } else {
                    System.out.println();
                }
            }
        }
    }
}
