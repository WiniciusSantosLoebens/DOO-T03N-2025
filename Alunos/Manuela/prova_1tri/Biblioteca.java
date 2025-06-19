package objeto;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    public List<Cliente> clientes = new ArrayList<>();

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

    public boolean realizarEmprestimo(Cliente cliente, Livro livro) {
        if (livro == null || livro.isEmprestado() || livro instanceof LivroRaro) {
            return false; 
        }
        livro.emprestar(); 
        return true;
    }

    public double realizarDevolucao(Livro livro, int diasDeAtraso) {
        if (livro == null || !livro.isEmprestado()) {
            return 0.0; 
        }
        double multa = livro.calcularMulta(diasDeAtraso);
        livro.devolver(); 
        return multa;
    }

    public boolean verificarDisponibilidade(String titulo) {
        Livro livro = buscarLivroPorTitulo(titulo);
        return livro != null && !livro.isEmprestado();
    }
}