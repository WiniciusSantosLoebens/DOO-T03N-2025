import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    List<Cliente> clientes;
    List<Livro> livros;
    List<Emprestimo> emprestimos;

    public Biblioteca() {
        clientes = new ArrayList<>();
        livros = new ArrayList<>();
        emprestimos = new ArrayList<>();
    }

    public void adicionarCliente(Cliente cliente){
        clientes.add(cliente);
    }

    public void adicionarLivro(Livro livro){
        livros.add(livro);
    }

    public Livro buscaTitulo(String titulo){
        for(Livro l : livros){
            if(l.getTitulo().equalsIgnoreCase(titulo)){
                return l;
            }
        }
        return null;
    }

    public Livro buscarAutor(String autor){
        for(Livro l : livros){
            if(l.getAutor().equalsIgnoreCase(autor)){
                return l;
            }
        }
        return null;
    }

    public String emprestarLivro(String titulo, Cliente cliente){
        Livro livro = buscaTitulo(titulo);

        emprestimos.add(new Emprestimo(livro, cliente));
        return "Emprestimo realizado com sucesso!";
    }

    public String devolverLivro(String titulo){
        for(Emprestimo e : emprestimos){
            if (e.getLivro().getTitulo().equalsIgnoreCase(titulo)){
                emprestimos.remove(e);
                return "Livro devolvido com sucesso!";
            }
        }
        return null;
    }

    public String verificarDisponibilidade(String titulo){
        for(Emprestimo e : emprestimos){
            if(e.getLivro().getTitulo().equalsIgnoreCase(titulo)){
                return "Está emprestado!";
            }
        }
        return "Não está emprestado!";
    }
}
    
