import java.util.*;
import java.time.*;

public class SistemaBiblioteca {
    private static Biblioteca biblioteca = new Biblioteca();
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n=== Menu da Biblioteca ===");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Cadastrar livro");
            System.out.println("3. Realizar empréstimo");
            System.out.println("4. Realizar devolução");
            System.out.println("5. Ver multas de um cliente");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> cadastrarLivro();
                case 3 -> realizarEmprestimo();
                case 4 -> realizarDevolucao();
                case 5 -> verMultas();
                case 6 -> System.out.println("Encerrando sistema...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 6);
    }

    private static void cadastrarCliente() {
        System.out.print("ID do cliente: ");
        String id = scanner.nextLine();
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        Cliente cliente = new Cliente(id, nome);
        biblioteca.cadastrarCliente(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void cadastrarLivro() {
        System.out.print("Título do livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor do livro: ");
        String autor = scanner.nextLine();
        System.out.print("É um livro raro? (s/n): ");
        String tipo = scanner.nextLine();
        Livro livro;
        if (tipo.equalsIgnoreCase("s")) {
            System.out.print("Motivo da raridade: ");
            String motivo = scanner.nextLine();
            livro = new LivroRaro(titulo, autor, motivo);
        } else {
            livro = new LivroComum(titulo, autor);
        }
        biblioteca.cadastrarLivro(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    private static void realizarEmprestimo() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        Cliente cliente = buscarCliente(nome);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Título do livro: ");
        String titulo = scanner.nextLine();
        Livro livro = buscarLivro(titulo);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        LocalDate data = LocalDate.now().minusDays(random.nextInt(5)); // data aleatória recente
        boolean sucesso = biblioteca.realizarEmprestimo(cliente, livro, data);
        System.out.println(sucesso ? "Empréstimo realizado." : "Não foi possível realizar o empréstimo.");
    }

    private static void realizarDevolucao() {
        System.out.print("Título do livro: ");
        String titulo = scanner.nextLine();
        Livro livro = buscarLivro(titulo);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        LocalDate data = LocalDate.now().plusDays(random.nextInt(10)); // data aleatória futura
        double multa = biblioteca.realizarDevolucao(livro, data);
        System.out.printf("Devolução realizada. Multa: R$ %.2f%n", multa);
    }

    private static void verMultas() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        Cliente cliente = buscarCliente(nome);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        double totalMulta = 0.0;
        for (Emprestimo emp : biblioteca.getEmprestimos()) {
            if (!emp.getLivro().isDisponivel()) {
                totalMulta += emp.getMulta();
            }
        }
        System.out.printf("Multas em aberto para %s: R$ %.2f%n", cliente.getNome(), totalMulta);
    }

    private static Cliente buscarCliente(String nome) {
        for (Cliente c : biblioteca.getClientes()) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        return null;
    }

    private static Livro buscarLivro(String titulo) {
        for (Livro l : biblioteca.getLivros()) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                return l;
            }
        }
        return null;
    }
}
