import objetos.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n BEM VINDO A BIBLIOTECA \n" + "[1] - Adicionar Cliente  \n" + "[2] - Adicionar livro comum \n"  +
                    "[3] - Adicionar livro raro \n" + "[4] - Buscar livro por titulo \n" + "[5] - Buscar livro por autor \n" +
                    "[6] - Emprestimo \n" + "[7] - Devolucao \n" + "[8] - Livros disponiveis \n" + "[0] - Sair \n");
            System.out.print("Escolha uma opcao: ");
            int opcao = scanner.nextInt();


            switch (opcao) {
                case 1:
                    System.out.print("nome: ");
                    String nome = scanner.next();
                    System.out.print("idade: ");
                    String idade = scanner.next();
                    biblioteca.adicionarCliente(new Cliente(nome, idade));
                    System.out.println("Cliente adicionado com sucesso.");
                    break;

                case 2:
                    System.out.print("titulo: ");
                    String tituloC = scanner.next();
                    System.out.print("Autor: ");
                    String autorC = scanner.next();
                    biblioteca.adicionarLivro(new LivroComum(tituloC, autorC));
                    System.out.println("Livro adicionado com sucesso.");
                    break;

                case 3:
                    System.out.print("titulo: ");
                    String tituloRaro = scanner.next();
                    System.out.print("Autor: ");
                    String autorRaro = scanner.next();
                    System.out.print("Motivo de ser raro: ");
                    String motivo = scanner.next();
                    biblioteca.adicionarLivro(new LivroRaro(tituloRaro, autorRaro, motivo));
                    System.out.println("Livro adicionado com sucesso.");
                    break;

                case 4:
                    System.out.print("titulo: ");
                    String tituloBusca = scanner.next();
                    Livro l1 = biblioteca.buscarPorTitulo(tituloBusca);
                    System.out.println(l1 != null ? "Livro encontrado titulo: " + l1.getTitulo() + " autor: " + l1.getAutor() : "Livro não encontrado.");
                    break;

                case 5:
                    System.out.print("autor: ");
                    String autorBusca = scanner.next();
                    Livro l2 = biblioteca.buscarPorAutor(autorBusca);
                    System.out.println(l2 != null ? "livro encontrado titulo: "  + l2.getTitulo() + " autor: "+ l2.getAutor() : "livro nao encontrado.");
                    break;

                case 6:
                    System.out.print("Nome do leitor: ");
                    String nomeEscolhido = scanner.next();
                    Cliente cliente = biblioteca.buscarClientePorNome(nomeEscolhido);
                    if (cliente == null) {
                        System.out.println("Nome nao encontrado.");
                        break;
                    }
                    System.out.print("Título do livro: ");
                    String tituloEscolhido = scanner.next();
                    biblioteca.emprestarLivro(tituloEscolhido, cliente);
                    break;

                case 7:
                    System.out.print("titulo do livro: ");
                    String tituloDevolver = scanner.next();
                    double multa = biblioteca.devolverLivro(tituloDevolver);
                    if (multa > 0) {
                        System.out.printf("multa a pagar: R$ %.2f\n", multa);
                    } else if (multa == 0) {
                        System.out.println("devolucao sem multa.");
                    }
                    break;

                case 8:
                    System.out.print("titulo do livro: ");
                    String tituloDisponivel = scanner.next();
                    biblioteca.verificarDisponibilidade(tituloDisponivel);
                    break;

                case 0:
                    System.out.println("Adeuss...");
                    scanner.close();
                    return;

                default:
                    System.out.println("opcao indisponivel.");
            }
        }
    }
}
