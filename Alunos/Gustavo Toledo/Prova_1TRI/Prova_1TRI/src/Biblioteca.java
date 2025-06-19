import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    List<Livro> livros;
    List<Cliente> clientes;
    List<Emprestimo> emprestimos;

    public Biblioteca() {
        livros = new ArrayList<>();
        clientes = new ArrayList<>();
        emprestimos = new ArrayList<>();

        // Adiciona livros comuns e raros já cadastrados
        livros.add(new LivroComum("Nuvem", "Carlos Alberto"));
        livros.add(new LivroComum("Mariposa", "Martha Lucena"));
        livros.add(new LivroRaro("Nebula", "John Doe", "Edição limitada e autografada"));
        livros.add(new LivroRaro("Oráculo", "Jane Smith", "Exemplar único, raro e inédito"));
    }

    public void cadastrarCliente(String nome, String id) {
        clientes.add(new Cliente(nome, id));
    }

    public void cadastrarLivroComum(String titulo, String autor) {
        livros.add(new LivroComum(titulo, autor));
        mostrarLivros();
    }

    public void cadastrarLivroRaro(String titulo, String autor, String motivoRaridade) {
        livros.add(new LivroRaro(titulo, autor, motivoRaridade));
        mostrarLivros();
    }

    public void mostrarLivros() {
        System.out.println("\n=== Lista de Livros ===");
        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            String tipo = l instanceof LivroRaro ? "Raro" : "Comum";
            String status = (l instanceof LivroRaro) ? "Indisponível" : (l.emprestado ? "Indisponível" : "Disponível");
            System.out.printf("[%d] %s - %s (%s) - %s\n", i + 1, l.titulo, l.autor, tipo, status);
        }
        System.out.println("[0] Voltar ao menu principal");
        System.out.println("========================\n");
    }

    public boolean realizarEmprestimo(int indiceLivro, String idCliente, String dataEmprestimo) {
        Livro livro = getLivroPorIndice(indiceLivro);
        Cliente cliente = clientes.stream().filter(c -> c.id.equals(idCliente)).findFirst().orElse(null);
    
        if (livro != null && cliente != null && !livro.emprestado) {
            if (livro instanceof LivroRaro) {
                System.out.println("ERRO: Livros raros não podem ser emprestados devido ao seu valor e raridade.");
                return false;
            }
            livro.emprestado = true;
            emprestimos.add(new Emprestimo(livro, cliente, dataEmprestimo));
            return true;
        }
        return false;
    }

    public double realizarDevolucao(int indiceLivro, String dataDevolucao) {
        Livro livro = getLivroPorIndice(indiceLivro);
        if (livro != null && livro.emprestado) {
            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.livro.equals(livro)) {
                    return emprestimo.devolver(dataDevolucao);
                }
            }
        }
        return -1;
    }

    public boolean verificarDisponibilidade(int indiceLivro) {
        Livro livro = getLivroPorIndice(indiceLivro);
        return livro != null && !(livro instanceof LivroRaro) && !livro.emprestado;
    }

    private Livro getLivroPorIndice(int indice) {
        if (indice >= 0 && indice < livros.size()) {
            return livros.get(indice);
        }
        return null;
    }
}
