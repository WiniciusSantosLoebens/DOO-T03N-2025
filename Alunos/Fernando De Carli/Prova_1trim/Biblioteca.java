import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Cliente> clientes;
    private List<Livro> livros;

    public Biblioteca() {
        this.clientes = new ArrayList<>();
        this.livros = new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void adicionarLivro(Livro livro) {
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

    public void emprestarLivro(Livro livro) {
        if (livro != null && livro.isDisponivel() && !livro.isRaro()) {
            livro.emprestar();
            System.out.println("Empréstimo realizado com sucesso!");
        } else {
            System.out.println("Livro não disponível para empréstimo.");
        }
    }

    public void devolverLivro(Livro livro, int diasAtraso) {
        if (livro != null) {
            livro.devolver();
            System.out.println("Devolução realizada com sucesso!");
            if (diasAtraso > 7) {
                double multa = (diasAtraso - 7) * 3.50;
                System.out.println("Multa de atraso: R$ " + multa);
            }
        }
    }
}