import java.util.ArrayList;
import java.util.Scanner;

public class Emprestimo {

  private Clientes cliente;
  private Livros livro;

    public Emprestimo(Clientes cliente, Livros livro) {
        this.cliente = cliente;
        this.livro = livro;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public Livros getLivro() {
        return livro;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public void setLivro(Livros livro) {
        this.livro = livro;
    }

 public static void  realizarEmprestimo(ArrayList<Clientes> clientes, ArrayList<Livros> livros, ArrayList<Emprestimo> emprestimos, Scanner scanner){

  System.out.println("Digite o nome do cliente:");
  String nomeCliente = scanner.next();

  Clientes clienteSelecionado = null;
  for (Clientes c : clientes) {
      if (c.getNome().equalsIgnoreCase(nomeCliente)) {
          clienteSelecionado = c;
          break;
      }
  }

  if (clienteSelecionado == null) {
      System.out.println("Cliente não encontrado.");
      return;
  }

  System.out.println("Digite o título do livro:");
  String tituloLivro = scanner.next();

  Livros livroSelecionado = null;
  for (Livros l : livros) {
      if (l.getTitulo().equalsIgnoreCase(tituloLivro)) {
          livroSelecionado = l;
          break;
      }
  }

  if (livroSelecionado == null) {
      System.out.println("Livro não encontrado.");
      return;
  }

  if (livroSelecionado.getTipo().equalsIgnoreCase("raro")) {
    System.out.println("Este livro é raro e não pode ser emprestado.");
    return;
}


  if (livroSelecionado.isEmprestado()) {
      System.out.println("Esse livro já está emprestado.");
      return;
  }

  livroSelecionado.setEmprestado(true);
  emprestimos.add(new Emprestimo(clienteSelecionado, livroSelecionado));
  System.out.println("Empréstimo realizado com sucesso!");
       }

      public static void realizarDevolucao(ArrayList<Emprestimo> emprestimos, Scanner scanner){
        System.out.println("Digite o nome do cliente:");
        String nomeCliente = scanner.next();

        System.out.println("Digite o título do livro:");
        String tituloLivro = scanner.next();

        Emprestimo emprestimoEncontrado = null;

        for (Emprestimo e : emprestimos) {
            if (e.getCliente().getNome().equalsIgnoreCase(nomeCliente) &&
                e.getLivro().getTitulo().equalsIgnoreCase(tituloLivro)) {
                emprestimoEncontrado = e;
                break;
            }
        }

        if (emprestimoEncontrado == null) {
            System.out.println("Nenhum empréstimo encontrado para esse cliente com esse livro.");
            return;
        }


        emprestimoEncontrado.getLivro().setEmprestado(false);


        emprestimos.remove(emprestimoEncontrado);

        System.out.println("Livro devolvido com sucesso!");
      }

      public static void verificarLivro(ArrayList<Livros> livros, Scanner scanner){

        System.out.println("Digite o título do livro:");
        String titulo = scanner.next();

    Livros livroEncontrado = null;

    for (Livros livro : livros) {
        if (livro.getTitulo().equalsIgnoreCase(titulo)) {
            livroEncontrado = livro;
            break;
        }
    }

    if (livroEncontrado == null) {
        System.out.println("Livro não encontrado.");
    } else {
        if (livroEncontrado.isEmprestado()) {
            System.out.println("O livro está emprestado no momento.");
        } else {
            System.out.println("O livro está disponível para empréstimo.");
        }
    }

      }



    }


