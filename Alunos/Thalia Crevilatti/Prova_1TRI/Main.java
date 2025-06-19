// Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        int opcao;
        do {
            System.out.println("\n");
            System.out.println(" =============    BEM-VINDO    ============= ");
            System.out.println(" =============  BIBLIOTECA PUB ============= \n");
            System.out.println("[1] Cadastrar cliente");
            System.out.println("[2] Cadastrar livro");
            System.out.println("[3] Realizar empréstimo");
            System.out.println("[4] Realizar devolução");
            System.out.println("[5] Verificar disponibilidade de livro");
            System.out.println("[6] Listar clientes e livros");
            System.out.println("[7] Sair");
            System.out.println("===============================================");
            System.out.print("\n Escolha uma opção: \n");

            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();

                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();

                    Cliente cliente = new Cliente(nome, endereco, telefone, email, idade, cpf);
                    biblioteca.cadastrarCliente(cliente);

                    System.out.println("Cliente cadastrado com sucesso!");
                }

                case 2 -> {
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();

                    System.out.print("Tipo (1 - Comum, 2 - Raro): ");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();

                    Livro livro;
                    if (tipo == 1) {
                        livro = new LivroComum(titulo, autor);
                    } else {
                        System.out.print("Justificativa do livro raro: ");
                        String justificativa = scanner.nextLine();
                        livro = new LivroRaro(titulo, autor, justificativa);
                    }
                    biblioteca.cadastrarLivro(livro);
                    System.out.println("Livro cadastrado com sucesso!");
                }

                case 3 -> {
                    System.out.print("CPF do cliente: ");
                    String cpf = scanner.nextLine();

                    System.out.print("Título do livro: ");
                    String titulo = scanner.nextLine();

                    boolean emprestado = biblioteca.realizarEmprestimo(cpf, titulo);
                    if (emprestado) {
                        System.out.println("Empréstimo realizado com sucesso!");
                    } else {
                        System.out.println("Não foi possível realizar o empréstimo.");
                    }
                    if (emprestado) {
                        System.out.println("Empréstimo realizado com sucesso!");
                    } else {
                        System.out.println("Não foi possível realizar o empréstimo.");
                    }
                }

                case 4 -> {
                    System.out.print("Título do livro: ");
                    String titulo = scanner.nextLine();

                    double multa = biblioteca.realizarDevolucao(titulo);
                    System.out.println("Sua multa é de: R$ " + multa);
                }

                case 5 -> {
                    System.out.print("Título do livro: ");
                    String titulo = scanner.nextLine();
                    boolean disponivel = biblioteca.verificarDisponibilidade(titulo);
                    System.out.println("Disponível? " + (disponivel ? "Sim" : "Não"));
                }

                case 6 -> {
                    System.out.println("\n === Clientes ===");
                    biblioteca.getClientes().forEach(Cliente::exibirDados);

                    System.out.println("\n === Livros ===");
                    biblioteca.getLivros().forEach(l -> 
                        System.out.println("Título: " + l.getTitulo() + ", Autor: " + l.getAutor())
                    );
                }

                case 7 -> System.out.println("Saindo...");

                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 7);
        scanner.close();
    }
}