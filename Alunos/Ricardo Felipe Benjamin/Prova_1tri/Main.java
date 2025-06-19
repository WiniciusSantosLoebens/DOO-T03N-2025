import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        int option;

        do {
            System.out.println("BibliotecaPub");
            System.out.println("[1] Cadastrar cliente");
            System.out.println("[2] Adicionar livro");
            System.out.println("[3] Buscar livro");
            System.out.println("[4] Realizar empréstimo");
            System.out.println("[5] Realizar devolução");
            System.out.println("[6] Verificar disponibilidade de livro");
            System.out.println("[0] Sair");
            option = scan.nextInt();
            scan.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Nome do cliente: ");
                    String nome = scan.nextLine();
                    biblioteca.cadastrarCliente(nome);
                    break;

                case 2:
                    System.out.print("Título: ");
                    String titulo = scan.nextLine();
                    System.out.print("Autor: ");
                    String autor = scan.nextLine();
                    System.out.print("Livro é raro? (s/n): ");
                    String raro = scan.nextLine();

                    if (raro.equalsIgnoreCase("s")) {
                        System.out.print("Justificativa: ");
                        String justificativa = scan.nextLine();
                        biblioteca.adicionarLivro(new LivroRaro(titulo, autor, justificativa));
                    } else {
                        biblioteca.adicionarLivro(new Livro(titulo, autor));
                    }
                    break;

                case 3:
                    System.out.print("Buscar por [1] Título ou [2] Autor? ");
                    int busca = scan.nextInt();
                    scan.nextLine();
                    if (busca == 1) {
                        System.out.print("Título: ");
                        String t = scan.nextLine();
                        Livro l = biblioteca.buscarPorTitulo(t);
                        if (l != null) {
                            System.out.println("Encontrado: " + l.getTitulo() + " - " + l.getAutor());
                        } else {
                            System.out.println("Livro não encontrado.");
                        }
                    } else {
                        System.out.print("Autor: ");
                        String a = scan.nextLine();
                        Livro l = biblioteca.buscarPorAutor(a);
                        if (l != null) {
                            System.out.println("Encontrado: " + l.getTitulo() + " - " + l.getAutor());
                        } else {
                            System.out.println("Livro não encontrado.");
                        }
                    }
                    break;

                case 4:
                    System.out.print("Nome do cliente: ");
                    String clienteNome = scan.nextLine();
                    System.out.print("Título do livro: ");
                    String tituloEmp = scan.nextLine();
                    boolean sucesso = biblioteca.realizarEmprestimo(clienteNome, tituloEmp);
                    if (sucesso) {
                        System.out.println("Empréstimo realizado com sucesso!");
                    } else {
                        System.out.println("Não foi possível realizar o empréstimo.");
                    }
                    break;

                case 5:
                    System.out.print("Título do livro: ");
                    String tituloDev = scan.nextLine();
                    double multa = biblioteca.realizarDevolucao(tituloDev);
                    if (multa >= 0) {
                        System.out.printf("Devolução concluída. Multa: R$ %.2f%n", multa);
                    } else {
                        System.out.println("Livro não encontrado ou não estava emprestado.");
                    }
                    break;

                case 6:
                    System.out.print("Título do livro: ");
                    String tituloCheck = scan.nextLine();
                    boolean disponivel = biblioteca.verificarDisponibilidade(tituloCheck);
                    if (disponivel) {
                        System.out.println("Disponível para empréstimo.");
                    } else {
                        System.out.println("Indisponível.");
                    }
                    break;

                case 0:
                    System.out.println("Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (option != 0);
    }
}
