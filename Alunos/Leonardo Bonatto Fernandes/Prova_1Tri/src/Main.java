import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("BibliotecaPub");
            System.out.println("Digite 1 para Cadastrar Cliente");
            System.out.println("Digite 2 para Cadastrar Livro");
            System.out.println("Digite 3 para Buscar Livro por Título");
            System.out.println("Digite 4 para Buscar Livro por Autor");
            System.out.println("Digite 5 para Realizar Empréstimo");
            System.out.println("Digite 6 para Realizar Devolução");
            System.out.println("Digite 7 para Verificar Disponibilidade");
            System.out.println("Digite 8 para  Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nomeCliente = scanner.nextLine();
                    biblioteca.cadastrarCliente(nomeCliente);
                    System.out.println("O cliente foi cadastrado com sucesso");
                    break;

                case 2:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("O livro é raro? Responda com (s ou n) ");
                    String raro = scanner.nextLine();
                    if (raro.equalsIgnoreCase("s")) {
                        System.out.print("Justificativa: ");
                        String justificativa = scanner.nextLine();
                        biblioteca.cadastrarLivro(new LivroRaro(titulo, autor, justificativa));
                    } else {
                        biblioteca.cadastrarLivro(new Livro(titulo, autor));
                    }
                    System.out.println("Livro cadastrado!");
                    break;

                case 3:
                    System.out.print("Título do livro: ");
                    titulo = scanner.nextLine();
                    Livro livroEncontrado = biblioteca.buscarPorTitulo(titulo);
                    System.out.println(livroEncontrado != null ? "Livro: " + livroEncontrado.getTitulo() : "Livro não encontrado.");
                    break;

                case 4:
                    System.out.print("Autor: ");
                    autor = scanner.nextLine();
                    livroEncontrado = biblioteca.buscarPorAutor(autor);
                    System.out.println(livroEncontrado != null ? "Livro: " + livroEncontrado.getTitulo() : "Livro não encontrado.");
                    break;

                case 5:
                    System.out.print("Título do livro para empréstimo: ");
                    titulo = scanner.nextLine();
                    System.out.print("Nome do cliente: ");
                    nomeCliente = scanner.nextLine();
                    biblioteca.realizarEmprestimo(titulo, nomeCliente);
                    break;

                case 6:
                    System.out.print("Título do livro para devolução: ");
                    titulo = scanner.nextLine();
                    System.out.print("Dias de atraso na devolução: ");
                    int diasAtraso = scanner.nextInt();
                    biblioteca.realizarDevolucao(titulo, diasAtraso);
                    break;

                case 7:
                    System.out.print("Título: ");
                    titulo = scanner.nextLine();
                    boolean disponivel = biblioteca.verificarDisponibilidade(titulo);
                    System.out.println(disponivel ? "Livro disponível para empréstimo." : "Livro indisponível.");
                    break;

                case 8:
                System.out.println("Saindo");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
