import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        while (true) {
            System.out.println("\n--- Bem-vindo à BibliotecaPub! ---");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Cadastrar livro comum");
            System.out.println("3. Cadastrar livro raro");
            System.out.println("4. Buscar livro por título");
            System.out.println("5. Buscar livro por autor");
            System.out.println("6. Realizar empréstimo");
            System.out.println("7. Realizar devolução");
            System.out.println("8. Verificar disponibilidade");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do cliente: ");
                    String nome = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine();
                    Cliente cliente = new Cliente(nome, idade);
                    biblioteca.cadastrarCliente(cliente);
                    System.out.println("Cliente cadastrado com sucesso!");
                }

                case 2 -> {
                    System.out.print("Título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    LivroComum livro = new LivroComum(titulo, autor);
                    biblioteca.cadastrarLivro(livro);
                    System.out.println("Livro comum cadastrado!");
                }

                case 3 -> {
                    System.out.print("Título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("Justificativa de raridade: ");
                    String justificativa = scanner.nextLine();
                    LivroRaro livro = new LivroRaro(titulo, autor, justificativa);
                    biblioteca.cadastrarLivro(livro);
                    System.out.println("Livro raro cadastrado!");
                }

                case 4 -> {
                    System.out.print("Título a buscar: ");
                    String titulo = scanner.nextLine();
                    Livro livro = biblioteca.buscarLivroPorTitulo(titulo);
                    if (livro != null) livro.apresentarse();
                    else System.out.println("Livro não encontrado.");
                }

                case 5 -> {
                    System.out.print("Autor a buscar: ");
                    String autor = scanner.nextLine();
                    Livro livro = biblioteca.buscarLivroPorAutor(autor);
                    if (livro != null) livro.apresentarse();
                    else System.out.println("Livro não encontrado.");
                }

                case 6 -> {
                    System.out.print("Título do livro a emprestar: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Nome do cliente: ");
                    String nome = scanner.nextLine();
                    Cliente cliente = biblioteca.buscarClientePorNome(nome);
                    if (cliente == null) {
                        System.out.println("Cliente não encontrado.");
                    } else {
                        biblioteca.realizarEmprestimo(titulo, cliente);
                    }
                }

                case 7 -> {
                    System.out.print("Título do livro a devolver: ");
                    String titulo = scanner.nextLine();
                    biblioteca.realizarDevolucao(titulo);
                }

                case 8 -> {
                    System.out.print("Título do livro: ");
                    String titulo = scanner.nextLine();
                    boolean disponivel = biblioteca.verificarDisponibilidade(titulo);
                    System.out.println("Disponível? " + (disponivel ? "Sim" : "Não"));
                }

                case 0 -> {
                    System.out.println("Obrigado por usar nossa Biblioteca, volte sempre!!");
                    return;
                }

                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
