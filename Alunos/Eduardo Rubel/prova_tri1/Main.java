import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Cadastrar livro");
            System.out.println("3. Realizar empréstimo");
            System.out.println("4. Realizar devolução");
            System.out.println("5. Buscar livro por título");
            System.out.println("6. Buscar livro por autor");
            System.out.println("7. Verificar disponibilidade de livro");
            System.out.println("8. Listar todos os livros");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do cliente: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF do cliente: ");
                    String cpf = scanner.nextLine();
                    biblioteca.cadastrarCliente(new Cliente(nome, cpf));
                    System.out.println("Cliente cadastrado!");
                    break;
                case 2:
                    System.out.print("Título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor do livro: ");
                    String autor = scanner.nextLine();
                    System.out.print("O livro é raro? (s/n): ");
                    boolean raro = scanner.nextLine().equalsIgnoreCase("s");
                    String descricao = null;
                    if (raro) {
                        System.out.print("Descrição do porquê o livro é raro: ");
                        descricao = scanner.nextLine();
                    }
                    biblioteca.cadastrarLivro(new Livro(titulo, autor, raro, descricao));
                    System.out.println("Livro cadastrado!");
                    break;
                case 3:
                    System.out.print("Nome do cliente: ");
                    String nomeCliente = scanner.nextLine();
                    Cliente cliente = biblioteca.buscarClientePorNome(nomeCliente);
                    if (cliente == null) {
                        System.out.println("Cliente não encontrado.");
                        break;
                    }
                    System.out.print("Título do livro: ");
                    String livroEmprestimo = scanner.nextLine();
                    System.out.print("Dia do empréstimo (número): ");
                    int diaEmprestimo = scanner.nextInt();
                    scanner.nextLine();
                    if (biblioteca.realizarEmprestimo(livroEmprestimo, cliente, diaEmprestimo)) {
                        System.out.println("Empréstimo realizado com sucesso.");
                    } else {
                        System.out.println("Não foi possível realizar o empréstimo.");
                    }
                    break;
                case 4:
                    System.out.print("Título do livro: ");
                    String livroDevolucao = scanner.nextLine();
                    System.out.print("Dia da devolução (número): ");
                    int diaDevolucao = scanner.nextInt();
                    scanner.nextLine();
                    double multa = biblioteca.realizarDevolucao(livroDevolucao, diaDevolucao);
                    System.out.println("Devolução realizada. Multa: R$ " + multa);
                    break;
                case 5:
                    System.out.print("Título do livro: ");
                    String tituloBusca = scanner.nextLine();
                    Livro encontradoTitulo = biblioteca.buscarPorTitulo(tituloBusca);
                    if (encontradoTitulo != null) {
                        System.out.println("Livro encontrado: " + encontradoTitulo.getTitulo());
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;
                case 6:
                    System.out.print("Autor do livro: ");
                    String autorBusca = scanner.nextLine();
                    Livro encontradoAutor = biblioteca.buscarPorAutor(autorBusca);
                    if (encontradoAutor != null) {
                        System.out.println("Livro encontrado: " + encontradoAutor.getTitulo());
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;
                case 7:
                    System.out.print("Título do livro: ");
                    String tituloDisponibilidade = scanner.nextLine();
                    if (biblioteca.estaDisponivel(tituloDisponibilidade)) {
                        System.out.println("Livro disponível para empréstimo.");
                    } else {
                        System.out.println("Livro não disponível.");
                    }
                    break;
                case 8:
                    biblioteca.listarLivros();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
