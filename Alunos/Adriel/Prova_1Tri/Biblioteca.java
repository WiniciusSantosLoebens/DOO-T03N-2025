import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Cliente> clientes = new ArrayList<>();
    private List<Livro> livros = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public boolean realizarEmprestimo(Cliente cliente, Livro livro, LocalDate dataEmprestimo) {
        if (livro.isDisponivel() && livro.podeSerEmprestado()) {
            livro.setDisponivel(false);
            Emprestimo emprestimo = new Emprestimo(livro, dataEmprestimo);
            emprestimos.add(emprestimo);
            return true;
        }
        return false;
    }

    public double realizarDevolucao(Livro livro, LocalDate dataDevolucaoReal) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().equals(livro)) {
                emprestimo.devolverLivro(dataDevolucaoReal);
                livro.setDisponivel(true);
                return emprestimo.getMulta();
            }
        }
        return 0.0;
    }
}
