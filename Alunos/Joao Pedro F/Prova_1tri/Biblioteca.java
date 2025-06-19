import java.util.*;

public class Biblioteca {
    private final List<Cliente> clientes;
    private final List<Livro> livros;

    public Biblioteca() {
        this.clientes = new ArrayList<>();
        this.livros = new ArrayList<>();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void cadastrarCliente(String nome) {
        Cliente cliente = new Cliente(nome);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso: " + cliente);
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
        System.out.println("Livro cadastrado: " + livro.detalhes());
    }

    public Livro buscarPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public Livro buscarPorAutor(String autor) {
        for (Livro livro : livros) {
            if (livro.getAutor().equalsIgnoreCase(autor)) {
                return livro;
            }
        }
        return null;
    }

    public boolean verificarDisponibilidade(Livro livro) {
        return !livro.isEmprestado();
    }

    public void realizarEmprestimo(Cliente cliente, Livro livro) {
        if (!verificarDisponibilidade(livro)) {
            System.out.println("O livro '" + livro.getTitulo() + "' já está emprestado.");
        } else {
            livro.emprestar();
            System.out.println(cliente.getNome() + " realizou o empréstimo do livro '" + livro.getTitulo() + "'.");
        }
    }

    public void realizarDevolucao(Cliente cliente, Livro livro, int diasDeAtraso) {
        if (livro.isEmprestado()) {
            livro.devolver();
            if (diasDeAtraso > 7) {
                double multa = (diasDeAtraso - 7) * 3.50;
                System.out.println("Multa gerada: R$ " + multa);
            } else {
                System.out.println(cliente.getNome() + " devolveu no prazo.");
            }
        } else {
            System.out.println("Este livro não está emprestado.");
        }
    }

    public void exibirLivros() {
        for (Livro livro : livros) {
            System.out.println(livro.detalhes());
        }
    }

    public void exibirClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }
}
