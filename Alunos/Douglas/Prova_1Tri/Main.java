import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Livro> livros = new ArrayList<>();
    static ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        menu();
    }

    public static void menu() {
        System.out.println("-- Menu --\n" +
        "1 - Cadastrar Cliente\n" +
        "2 - Cadastrar Livro\n" +
        "3 - Buscar Livro por Título\n" +
        "4 - Buscar Livro por Autor\n" +
        "5 - Fazer empréstimo\n" +
        "6 - Realizar Devolução\n" +
        "7 - Listar Livros\n" +
        "8 - Listar Clientes\n" +
        "9 - Listar Empréstimos\n" +
        "----------");

        System.out.print("Digite a opção desejada: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 0:
                System.exit(0);
            case 1:
                cadastrarCliente();
                break;
            case 2:
                cadastrarLivro();
                break;
            case 3:
                buscarPorTitulo();
                break; 
            case 4:
                buscarPorAutor();
                break;
            case 5:
                realizarEmprestimo();
                break;
            case 6:
                realizarDevolucao();
                break;
            case 7:
                listarLivros();
                break;
            case 8:
                listarClientes();
                break;
            case 9:
                listarEmprestimos();
                break;
            default:
                System.out.println("Opção Inválida!");
                break;
        }

        menu();
    }

    public static void cadastrarCliente() {
        int id;

        System.out.println("-- Cadastro de Cliente --");
        scanner.nextLine();
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o cpf do cliente: ");
        String cpf = scanner.nextLine();

        if (clientes.isEmpty()) {
            id = 1;
        } else {
            id = clientes.size() + 1;
        }
        
        Cliente cliente = new Cliente(id, nome, cpf);
        clientes.add(cliente);

        System.out.println("Cadastro realizado com sucesso!");
        System.out.println("------------------------");
    }

    public static void cadastrarLivro() {
        int id = 0;

        System.out.println("-- Cadastro de Livro --");
        scanner.nextLine();
        System.out.print("Digite o tipo de livro (1 - Comum, 2 - Raro) : ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (livros.isEmpty()) {
            id = 1;
        } else {
            id = livros.size() + 1;
        }

        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();
        System.out.print("Digite o ano de publicação do livro: ");
        int anoPublicacao = scanner.nextInt();
        scanner.nextLine();

        switch (tipo) {
            case 1:  
                Livro livro = new Livro(id, titulo, autor, anoPublicacao);
                livros.add(livro);
                break;
            case 2:
                Raro livroRaro = new Raro(id, titulo, autor, anoPublicacao);
                System.out.println("Digite a explicação do livro:");
                String explicacao = scanner.nextLine();
                livroRaro.adicionarExplicacao(explicacao);
                livros.add(livroRaro);
                break;
            default:
                System.out.println("Tipo inválido!");
                cadastrarLivro();
                break;
        }

        System.out.println("Cadastro realizado com sucesso!");
        System.out.println("------------------------");
    }

    public static void buscarPorTitulo() {
        int contador = 0;

        scanner.nextLine();
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        for (Livro livro : livros) {
            if (livro.titulo.equals(titulo)) {
                System.out.println("Livro encontrado: ");
                System.out.println(livro);
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("Nenhum livro com esse nome!");
        }
    }

    public static void buscarPorAutor() {
        int contador = 0;

        scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();
        for (Livro livro : livros) {
            if (livro.autor.equals(autor)) {
                System.out.println("Livro encontrado: ");
                System.out.println(livro);
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("Nenhum livro com esse autor!");
        }
    }

    public static void realizarEmprestimo() {
        int id = 0;

        scanner.nextLine();
        System.out.println("-- Empréstimo de Livros --");
        listarClientes();
        System.out.print("Digite o ID do cliente: ");
        int idCliente = scanner.nextInt();
        Cliente cliente = escolherCliente(idCliente);
        scanner.nextLine();
        listarLivros();
        System.out.print("Digite o ID do Livro: ");
        int idLivro = scanner.nextInt();
        Livro livro = escolherLivro(idLivro);

        if (livro.emprestado) {
            System.out.println("Livro não disponível!");
        } else {
            scanner.nextLine();

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            System.out.print("Digite a data de empréstimo (dd/MM/yyyy): ");
            String dataDeSaida = scanner.nextLine();
            LocalDate dataSaida = LocalDate.parse(dataDeSaida, formato);

            if (emprestimos.isEmpty()) {
                id = 1;
            } else {
                id = emprestimos.size() + 1;
            }

            Emprestimo emprestimo = new Emprestimo(id, cliente, livro, dataSaida);
            emprestimos.add(emprestimo);

            emprestimo.livro.emprestado = true;

            System.out.println("Emprésimo realizado com sucesso!");
            System.out.println("------------------------");
        }
        
    }

    public static void realizarDevolucao() {
        scanner.nextLine();
        System.out.println("-- Devolução de Livros --");
        listarEmprestimos();
        System.out.print("Digite o ID do seu empréstimo: ");
        int id = scanner.nextInt();
        Emprestimo emprestimo = escolherEmprestimo(id);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        scanner.nextLine();
        System.out.print("Digite a data de devolução (dd/MM/yyyy): ");
        String dataDeDevolucao = scanner.nextLine();
        LocalDate dataDevolucao = LocalDate.parse(dataDeDevolucao, formato);
        
        emprestimo.dataDevolucao = dataDevolucao;
        emprestimo.multa = emprestimo.contagemDias();
        emprestimo.livro.emprestado = false;

        System.out.println("Devolução realizada com sucesso!");
        System.out.println(emprestimo);
        if (emprestimo.multa > 0) {
            System.out.println("Atenção! Por motivos de atraso da devolução, houve uma multa de R$" + emprestimo.multa);
        }
        System.out.println("------------------------");
    }

    public static void listarClientes() {
        System.out.println("-- Clientes --");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
            System.out.println("----");
        }
        System.out.println("------------");
    }

    public static void listarLivros() {
        System.out.println("-- Livros --");
        for (Livro livro : livros) {
            System.out.println(livro);
            System.out.println("----");
        }
        System.out.println("------------");
    }

    public static void listarEmprestimos() {
        System.out.println("-- Empréstimos --");
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println(emprestimo);
            System.out.println("----");
        }
        System.out.println("------------");
    }

    public static Cliente escolherCliente(int id) {
        Cliente clienteSelecionado = null;
        for (Cliente cliente : clientes) {
            if (cliente.id == id) {
                clienteSelecionado = cliente;
            }
        }
        return clienteSelecionado;
    }

    public static Livro escolherLivro(int id) {
        Livro livroSelecionado = null;
        for (Livro livro : livros) {
            if (livro.id == id) {
                livroSelecionado = livro;
            }
        }
        return livroSelecionado;
    }

    public static Emprestimo escolherEmprestimo(int id) {
        Emprestimo emprestimoSelecionado = null;
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.id == id) {
                emprestimoSelecionado = emprestimo;
            }
        }
        return emprestimoSelecionado;
    }

}
