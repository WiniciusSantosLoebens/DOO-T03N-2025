import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        while (true) {
            System.out.println("\n--- BibliotecaPub ---");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Cadastrar livro");
            System.out.println("3. Buscar livro por título");
            System.out.println("4. Buscar livro por autor");
            System.out.println("5. Realizar empréstimo");
            System.out.println("6. Realizar devolução");
            System.out.println("7. Simular dias emprestado");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            int opcao;
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
                continue;
            }

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do cliente: ");
                    String nome = scanner.nextLine();
                    biblioteca.adicionarCliente(new Clientes(nome));
                }

                case 2 -> {
                    System.out.print("Tipo do livro (1 - Comum | 2 - Raro): ");
                    int tipo;
                    try {
                        tipo = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Tipo inválido.");
                        continue;
                    }

                    System.out.print("Nome do livro: ");
                    String nomeLivro = scanner.nextLine();
                    System.out.print("Nome do autor: ");
                    String nomeAutor = scanner.nextLine();

                    if (tipo == 1) {
                        biblioteca.adicionarLivro(new Comum(nomeLivro, nomeAutor));
                    } else if (tipo == 2) {
                        System.out.print("Motivo de ser raro: ");
                        String motivo = scanner.nextLine();
                        biblioteca.adicionarLivro(new Raro(nomeLivro, nomeAutor, motivo));
                    } else {
                        System.out.println("Tipo inválido.");
                    }
                }

                case 3 -> {
                    System.out.print("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    Livros livro = biblioteca.buscarPorTitulo(titulo);
                    if (livro != null) {
                        System.out.println("Livro encontrado: " + livro.nomeLivro + " - " + livro.nomeAutor);
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                }

                case 4 -> {
                    System.out.print("Digite o nome do autor: ");
                    String autor = scanner.nextLine();
                    Livros livro = biblioteca.buscarPorAutor(autor);
                    if (livro != null) {
                        System.out.println("Livro encontrado: " + livro.nomeLivro + " - " + livro.nomeAutor);
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                }

                case 5 -> {
                    System.out.print("Digite o nome do livro: ");
                    String nome = scanner.nextLine();
                    Livros livro = biblioteca.buscarPorTitulo(nome);
                    if (livro != null) {
                        livro.registrarEmprestimo();
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                }

                case 6 -> {
                    System.out.print("Digite o nome do livro: ");
                    String nome = scanner.nextLine();
                    Livros livro = biblioteca.buscarPorTitulo(nome);
                    if (livro != null) {
                        livro.registrarDevolucao();
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                }

                case 7 -> {
                    System.out.print("Digite o nome do livro: ");
                    String nome = scanner.nextLine();
                    Livros livro = biblioteca.buscarPorTitulo(nome);
                    if (livro != null && livro.emEmprestimo) {
                        System.out.print("Quantos dias deseja simular? ");
                        try {
                            int dias = Integer.parseInt(scanner.nextLine());
                            livro.diasEmprestado += dias;
                            System.out.println("Dias simulados com sucesso.");
                        } catch (NumberFormatException e) {
                            System.out.println("Valor inválido.");
                        }
                    } else {
                        System.out.println("Livro não está emprestado ou não encontrado.");
                    }
                }

                case 0 -> {
                    System.out.println("Encerrando o sistema...");
                    return;
                }

                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
