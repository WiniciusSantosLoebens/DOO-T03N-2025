import java.util.ArrayList;
import java.util.Scanner;

public class Livros {

  private String titulo;
  private String autor;
  private String tipo;
  private Boolean emprestado = false;

    public Livros (){

    }
    public Livros(String titulo, String autor, String tipo) {
        this.autor = autor;
        this.tipo = tipo;
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getTipo() {
        return tipo;
    }

    public Boolean isEmprestado() {
      return emprestado;
  }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEmprestado(Boolean emprestado) {
      this.emprestado = emprestado;
  }

    public static void cadastrarLivro(ArrayList<Livros> livros, Scanner scanner){
        System.out.println("Digite o titulo do livro:");
        System.out.println("Digite o nome do autor:");
        System.out.println("Digite o tipo do livro (Comum ou Raro):");
        Livros livro = new Livros(scanner.next(),scanner.next(),scanner.next());
        livros.add(livro);
    }
    public static void mostrarlivros(ArrayList<Livros> livros){
      for (Livros livros2 : livros) {
        System.out.println("Titulo: " + livros2.getTitulo());
        System.out.println("Autor: " + livros2.getAutor());
        System.out.println("Tipo: " + livros2.getTipo() + "  " + "(Livros Raros não podem ser emprestados)");
        System.out.println("-------------------------");

      }
  }
    public static void buscarLivroTitulo(ArrayList<Livros> livros, Scanner scanner){
        System.out.println("Digite o titulo do livro: ");
        String tituloBusca = scanner.next();


        boolean encontrado = false;
        for (Livros livro : livros) {

          if (livro.getTitulo().equalsIgnoreCase(tituloBusca)) {
            System.out.println("Livro encontrado:");
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Tipo: " + livro.getTipo() + "  " + "(Livros Raros não podem ser emprestados)");
            System.out.println("-------------------------");
            encontrado = true;

        }
        if (!encontrado){
          System.out.println("Livro nao encontrado!");
          break;
        }
      }
    }
    public static void buscarLivroAutor(ArrayList<Livros> livros, Scanner scanner){
        System.out.println("Digite o nome do autor: ");
        String autorBusca = scanner.next();


        boolean encontrado = false;
        for (Livros livro : livros) {

          if (livro.getAutor().equalsIgnoreCase(autorBusca)) {
            System.out.println("Livro encontrado:");
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Tipo: " + livro.getTipo() + "  " + "(Livros Raros não podem ser emprestados)");
            System.out.println("-------------------------");
            encontrado = true;

        }
        if (!encontrado){
          System.out.println("Livro nao encontrado!");
        }
      }
    }













}
