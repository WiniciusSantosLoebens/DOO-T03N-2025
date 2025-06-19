
import java.util.ArrayList;
import java.util.Scanner;


public class Biblioteca {

    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       ArrayList<Clientes> clientes = new ArrayList<Clientes>();
       ArrayList<Livros> livros = new ArrayList<Livros>();
       ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();

       int opcao = 0;

        while(opcao != 10){

        System.out.println("----- Menu-------");
        System.out.println("1 - Cadastrar clientes");
        System.out.println("2 - clientes adicionados");
        System.out.println("3 - cadastrar livros");
        System.out.println("4 - livros cadastrados");
        System.out.println("5 - buscar livro por titulo");
        System.out.println("6 - buscar livro por autor");
        System.out.println("7 - Ver livros disponiveis por titulo");
        System.out.println("8 - realizar emprestimos");
        System.out.println("9 - realizar devolucao");
        System.out.println("10 - SAIR");
        opcao = scanner.nextInt();

        switch (opcao){

          case 1 -> {
              Clientes.cadastrarCliente(clientes, scanner);
          }
          case 2 -> {
              Clientes.mostrarCliente(clientes);
          }
          case 3 -> {
              Livros.cadastrarLivro(livros, scanner);
          }
          case 4 -> {
              Livros.mostrarlivros(livros);
          }
          case 5 -> {
              Livros.buscarLivroTitulo(livros, scanner);
          }
          case 6 -> {
              Livros.buscarLivroAutor(livros, scanner);
          }
          case 7 -> {
              Emprestimo.verificarLivro(livros, scanner);
          }
          case 8 -> {
            Emprestimo.realizarEmprestimo(clientes, livros, emprestimos, scanner);
          }
          case 9 -> {
            Emprestimo.realizarDevolucao(emprestimos, scanner);
          }
          case 10 -> {
            System.out.println("saindo!");
            scanner.close();
          }
          default -> {
              System.out.println("Algo deu errado!!");
          }
        }


    }
  }

}
