package objetos;

import java.util.*;

public class Biblioteca {
    private List<Cliente> clientes = new ArrayList<>();
    private List<Livro> livros = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public Livro buscarPorTitulo(String titulo) {
        for (Livro l : livros) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) return l;
        }
        return null;
    }

    public Livro buscarPorAutor(String autor) {
        for (Livro l : livros) {
            if (l.getAutor().equalsIgnoreCase(autor)) return l;
        }
        return null;
    }

    public boolean emprestarLivro(String titulo, Cliente cliente) {
        Livro livro = buscarPorTitulo(titulo);
        if (livro != null && livro.podeSerEmprestado()) {
            emprestimos.add(new Emprestimo(cliente, livro));
            System.out.println("livro emprestado com sucesso!");
            return true;
        } else {
            System.out.println("livro nao disponivel para emprestimo.");
            return false;
        }
    }

    public double devolverLivro(String titulo) {
        for (Emprestimo e : emprestimos) {
            if (e.getLivro().getTitulo().equalsIgnoreCase(titulo) && e.getLivro().estaDisponivel() == false) {
                double multa = e.devolverLivro();
                System.out.println("livro devolvido.");
                return multa;
            }
        }
        System.out.println("emprestimo não encontrado.");
        return -1;
    }

    public void verificarDisponibilidade(String titulo) {
        Livro livro = buscarPorTitulo(titulo);
        if (livro != null && livro.estaDisponivel()) {
            System.out.println("Livro disponivel para emprestimo.");
        } else {
            System.out.println("livro nao esta disponivel.");
        }
    }

    public Cliente buscarClientePorNome(String nome) {
        for (Cliente c : clientes) {
            if (c.getNome().equalsIgnoreCase(nome)) {
              return c;
            }
        }
        return null;
    }


}
