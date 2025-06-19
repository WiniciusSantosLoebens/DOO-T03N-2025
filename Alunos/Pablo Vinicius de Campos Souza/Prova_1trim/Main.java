
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Biblioteca biblioteca = new Biblioteca(); // biblioteca vazia


    public static void main(String[] args) {

        Livro livro1 = new Livro("Cracking the code interview", null, new Autor(" Gayle Laakmann McDowell"), TipoLivro.COMUM);
        biblioteca.adicionarLivro(livro1);

        Autor martin = new Autor("Martin Fowler");
        Livro livro2 = new Livro("Padrões de Arquitetura de Aplicações Corporativas", null, martin, TipoLivro.COMUM);
        biblioteca.adicionarLivro(livro2);

        Livro livro3 = new Livro("Refatoração", "Um dos melhores!!!", new Autor("Martin Fowler"), TipoLivro.RARO);
        biblioteca.adicionarLivro(livro3);

        while (true) {                
            System.out.println();
            System.out.println("=== MENU ===");
            System.out.println("(0) Sair");
            System.out.println("(1) Cadastrar cliente");
            System.out.println("(2) Cadastrar livro");
            System.out.println("(3) Buscar livro por título");
            System.out.println("(4) Buscar livro por autor");
            System.out.println("(5) Realizar empréstimo");
            System.out.println("(6) Realizar devolução");
            System.out.println("(7) Verificar disponibilidade");
            System.out.println("(8) Ver catálogo de livros");

            System.out.println("Selecione uma opção:");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "0":
                    return;
                case "1":
                    cadastrarCliente();
                    break;
                case "2":
                    cadastrarLivro();
                    break;
                case "3":
                    System.out.println("Insira o título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.println(biblioteca.buscarPorTitulo(titulo));
                    break;
                case "4":
                    System.out.println("Insira o nome do autor: ");
                    String autor = scanner.nextLine();
                    System.out.println(biblioteca.buscarPorNomeAutor(autor));
                    break;
                case "5":
                    realizarEmprestimo();
                    break;
                case "6":
                    realizarDevolucao();
                    break;
                case "7":
                    verificarDisponibilidade();
                    break;
                case "8":
                    biblioteca.imprimirTodosOsLivros();
                    break;
                default:
                    break;
            }

        }
    }

    private static void cadastrarLivro() {
        System.out.println("Insira o título do livro: ");
        String titulo = scanner.nextLine();

        System.out.println("Insira a descrição do livro: ");
        String descricao = scanner.nextLine();

        System.out.println("Insira o nome do autor: ");
        String nomeAutor = scanner.nextLine();
        Autor autor = new Autor(nomeAutor);

        System.out.println("Informe se o livro é raro ou comum [R/C]: ");
        String tipo = scanner.nextLine();
        TipoLivro tipoLivro; 
        tipoLivro = switch (tipo.toUpperCase()) {
            case "C" -> TipoLivro.COMUM;
            case "R" -> TipoLivro.RARO;
            default -> null;
        };

        biblioteca.adicionarLivro(new Livro(titulo, descricao, autor, tipoLivro));
        System.out.println("Livro cadastrado com sucesso.");
    }

    private static void cadastrarCliente() {
        System.out.println("Insira o nome do cliente: ");
        String nome = scanner.nextLine();

        biblioteca.adicionarCliente(new Cliente(nome));
        System.out.println("Cliente cadastrado com sucesso.");
    }

    private static void realizarEmprestimo() {
        Livro livro = encontrarLivroPorId();

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        
        if (!livro.podeEmprestar()) {
            System.out.println("Livro não pode ser emprestado.");
            return;
        }

        System.out.println("Digite o nome do cliente:");
        String nome = scanner.nextLine();
        Cliente cliente = new Cliente(nome);

        livro.emprestar(cliente);
        System.out.println("Livro emprestado com sucesso para: " + cliente.getNome());
    }

    private static void realizarDevolucao() {
        Livro livro = encontrarLivroPorId();

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        if (livro.devolver() != null) {
            System.out.println("Livro devolvido com sucesso.");
        }
    }

    
    private static void verificarDisponibilidade() {
        Livro livro = encontrarLivroPorId();
        System.out.println(livro.podeEmprestar() ? "Livro disponível para empréstimo." : "Livro indisponível para empréstimo.");
    }

    private static Livro encontrarLivroPorId() {
        biblioteca.imprimirTodosOsLivros();
        System.out.println("Digite o id do livro:");
        String id = scanner.nextLine();
        int idLivro = Integer.parseInt(id);
        return biblioteca.encontraLivroPorId(idLivro);
    }
}