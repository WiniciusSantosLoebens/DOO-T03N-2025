import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Biblioteca {
    List<Cliente> clientes = new ArrayList<>();
    List<Livro> livros = new ArrayList<>();
    List<Emprestimo> emprestimos = new ArrayList<>();

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public Livro buscarLivroPorAutor(String autor) {
        for (Livro livro : livros) {
            if (livro.getAutor().equalsIgnoreCase(autor)) {
                return livro;
            }
        }
        return null;
    }

    public Cliente buscarClientePorNome(String nome) {
        for (Cliente c : clientes) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        return null;
    }

    public boolean realizarEmprestimo(String titulo, Cliente cliente) {
        Livro livro = buscarLivroPorTitulo(titulo);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return false;
        }

        if (!livro.podeSerEmprestado()) {
            System.out.println("Este livro não pode ser emprestado.");
            return false;
        }

        Emprestimo emprestimo = new Emprestimo(livro, cliente, new Date());
        emprestimos.add(emprestimo);
        System.out.println("Empréstimo realizado com sucesso.");
        return true;
    }

    public boolean realizarDevolucao(String titulo) {
        for (Emprestimo emp : emprestimos) {
            if (emp.getLivro().getTitulo().equalsIgnoreCase(titulo) && emp.getDataDevolucao() == null) {
                emp.devolver(new Date());
                double multa = emp.calcularMulta();
                System.out.println("Devolução realizada.");
                if (multa > 0) {
                    System.out.printf("Multa por atraso: R$ %.2f%n", multa);
                } else {
                    System.out.println("Sem multa.");
                }
                return true;
            }
        }
        System.out.println("Empréstimo não encontrado ou já devolvido.");
        return false;
    }

    public boolean verificarDisponibilidade(String titulo) {
        Livro livro = buscarLivroPorTitulo(titulo);
        return livro != null && livro.isDisponivel();
    }
}
