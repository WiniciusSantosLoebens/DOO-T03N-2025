import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        
        Cliente cliente1 = new Cliente("Samuel");
        Cliente cliente2 = new Cliente("Beatriz");
        biblioteca.cadastrarCliente(cliente1);
        biblioteca.cadastrarCliente(cliente2);

        Livro livro1 = new LivroNormal("Dom Casmurro", "Machado de Assis");
        Livro livro2 = new LivroNormal("O Cortiço", "Aluísio Azevedo");
        Livro livro3 = new LivroRaro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", "Primeira edição rara");
        biblioteca.cadastrarLivro(livro1);
        biblioteca.cadastrarLivro(livro2);
        biblioteca.cadastrarLivro(livro3);

        int opcao;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Livro");
            System.out.println("3. Buscar Livro por Título");
            System.out.println("4. Buscar Livro por Autor");
            System.out.println("5. Realizar Empréstimo");
            System.out.println("6. Realizar Devolução");
            System.out.println("7. Verificar Disponibilidade");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  

                switch (opcao) {
                case 1: {
                    System.out.print("Digite o nome do cliente: ");
                    String nomeCliente = scanner.nextLine();
                    Cliente cliente = new Cliente(nomeCliente);
                    biblioteca.cadastrarCliente(cliente);
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;
                }
                case 2: {
                    System.out.print("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite o autor do livro: ");
                    String autor = scanner.nextLine();
                    System.out.print("O livro é raro? (sim/não): ");
                    String resposta = scanner.nextLine();
                    if (resposta.equalsIgnoreCase("sim")) {
                    System.out.print("Digite a descrição da raridade: ");
                    String descricaoRaridade = scanner.nextLine();
                    Livro livroRaro = new LivroRaro(titulo, autor, descricaoRaridade);
                    biblioteca.cadastrarLivro(livroRaro);
                    System.out.println("Livro raro cadastrado com sucesso!");
                    } else {
                    Livro livroNormal = new LivroNormal(titulo, autor);
                    biblioteca.cadastrarLivro(livroNormal);
                    System.out.println("Livro comum cadastrado com sucesso!");
                    }
                    break;
                }
                case 3: {
                    System.out.print("Digite o título do livro para buscar: ");
                    String tituloBusca = scanner.nextLine();
                    Livro livro = biblioteca.buscarLivroPorTitulo(tituloBusca);
                    if (livro != null) {
                    System.out.println("Livro encontrado: " + livro.getTitulo() + " de " + livro.getAutor());
                    } else {
                    System.out.println("Livro não encontrado.");
                    }
                    break;
                }
                case 4: {
                    System.out.print("Digite o autor para buscar: ");
                    String autorBusca = scanner.nextLine();
                    Livro livro = biblioteca.buscarLivroPorAutor(autorBusca);
                    if (livro != null) {
                    System.out.println("Livro encontrado: " + livro.getTitulo() + " de " + livro.getAutor());
                    } else {
                    System.out.println("Livro não encontrado.");
                    }
                    break;
                }
                case 5: {
                    System.out.print("Digite o título do livro para emprestar: ");
                    String tituloEmprestimo = scanner.nextLine();
                    System.out.print("Digite o nome do cliente: ");
                    String nomeClienteEmprestimo = scanner.nextLine();
                    Cliente clienteEmprestimo = biblioteca.buscarClientePorNome(nomeClienteEmprestimo);
                    if (clienteEmprestimo != null && biblioteca.realizarEmprestimo(tituloEmprestimo, clienteEmprestimo)) {
                    System.out.println("Empréstimo realizado com sucesso!");
                    } else {
                    System.out.println("Não foi possível realizar o empréstimo.");
                    }
                    break;
                }
                case 6: {
                    System.out.print("Digite o título do livro para devolução: ");
                    String tituloDevolucao = scanner.nextLine();
                    double multa = biblioteca.realizarDevolucao(tituloDevolucao);
                    if (multa >= 0) {
                    System.out.println("Devolução realizada com sucesso! Multa: R$ " + multa);
                    } else {
                    System.out.println("Livro não encontrado ou não foi emprestado.");
                    }
                    break;
                }
                case 7: {
                    System.out.print("Digite o título do livro para verificar disponibilidade: ");
                    String tituloVerificacao = scanner.nextLine();
                    boolean disponivel = biblioteca.estaDisponivel(tituloVerificacao);
                    if (disponivel) {
                    System.out.println("O livro está disponível para empréstimo.");
                    } else {
                    System.out.println("O livro não está disponível para empréstimo.");
                    }
                    break;
                }
                case 8: {
                System.out.println("Saindo...");
                    break;
                }
                default:
                System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 8);

        scanner.close();
    }
}
