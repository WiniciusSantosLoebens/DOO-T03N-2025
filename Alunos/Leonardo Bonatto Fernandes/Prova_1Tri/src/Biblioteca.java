import java.util.*;

public class Biblioteca {
    private List<Cliente> clientes = new ArrayList<>();
    private List<Livro> livros = new ArrayList<>();
    private Map<Livro, String> emprestimos = new HashMap<>();

    public void cadastrarCliente(String nome) {
        clientes.add(new Cliente(nome));
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
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

    public boolean verificarDisponibilidade(String titulo) {
        Livro livro = buscarPorTitulo(titulo);
        return livro != null && livro.isDisponivel();
    }

    public void realizarEmprestimo(String titulo, String nomeCliente) {
        Livro livro = buscarPorTitulo(titulo);
        if (livro != null && livro.isDisponivel()) {
            livro.setDisponivel(false);
            emprestimos.put(livro, nomeCliente);
            System.out.println("Empréstimo realizado para " + nomeCliente );
        } else {
            System.out.println("Livro indisponível para empréstimo.");
        }
    }

    public void realizarDevolucao(String titulo, int diasAtraso) {
        Livro livro = buscarPorTitulo(titulo);
        if (livro != null && emprestimos.containsKey(livro)) {
            livro.setDisponivel(true);
            emprestimos.remove(livro);
            if (diasAtraso > 7) {
                double multa = (diasAtraso - 7) * 3.50;
                System.out.println("Multa por atraso: R$" + multa);
            } else {
                System.out.println("Devolução realizada sem multa.");
            }
        } else {
            System.out.println("Livro não encontrado nos registros de empréstimos.");
        }
    }
}