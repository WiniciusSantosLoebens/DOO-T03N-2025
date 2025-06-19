import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        System.out.println("Seja bem vindo! Selecione a opção que deseja:");
        System.out.println("1 - Cadastrar Cliente.");
        System.out.println("2 - Cadastrar Livro.");
        System.out.println("3 - Buscar Livros por Titulo.");
        System.out.println("4 - Buscar Livros por Autor.");
        System.out.println("5 - Realizar emprestimo de livro.");
        System.out.println("6 - Realizar devolução de livro.");
        System.out.println("7 - Sair.");
        Integer opcao = sc.nextInt();
        sc.nextLine();
        switch (opcao) {
            case 1:
                cadastrarCliente();
                break;
            case 2:
                cadastrarLivro();
                break;
            case 3:
                buscarLivroPorNome();
                break;
            case 4:
                buscarLivroPorAutor();
                break;
            case 5:
                realizarEmprestimo();
                break;
            case 6:
                realizarDevolucao();
                break;
            case 7:
                break;

                default: System.out.println("Opção inexistente!");
                    menu();
                    break;
        }

    }

    public static void cadastrarCliente() {
        System.out.println("Digite o nome do cliente: ");
        String nome = sc.nextLine();

        Cliente.clientes.add(new Cliente(nome));
        menu();
    }

    public static void cadastrarLivro() {
        System.out.println("Digite o nome do livro: ");
        String nome = sc.nextLine();
        System.out.println("Digite o nome do autor do livro: ");
        String autor = sc.nextLine();
        System.out.println("O livro é raro?\nDigite 1 para sim. Digite 2 para não.");
        int id = sc.nextInt();
        sc.nextLine();

        if (id == 1) {
            String raridade = "Raro";
            System.out.println("Digite o porque deste livro ser raro: ");
            String descricao = sc.nextLine();
            String disponibilidade = "raro";
            String dataDeEntrada = "nenhum";
            String clienteAlugou = "nenhum";
            Livro.livros.add(new Livro(nome, autor, raridade, descricao, disponibilidade, dataDeEntrada, clienteAlugou));
        } else if (id == 2) {
            String raridade = "Comum";
            System.out.println("Digite a descricao desse livro: ");
            String descricao = sc.nextLine();
            String disponibilidade = "sim";
            String dataDeEntrada = "nenhum";
            String clienteAlugou = "nenhum";
            Livro.livros.add(new Livro(nome, autor, raridade, descricao, disponibilidade, dataDeEntrada, clienteAlugou));
        }
        menu();
    }

    public static void buscarLivroPorNome() {
        System.out.println("Digite o nome do livro: ");
        String nome = sc.nextLine();
        if (Livro.livros.isEmpty()){
            System.out.println("Tente adicionar um livro primeiro.");
            menu();
        }
        else {
            System.out.println("Livros disponiveis: ");
            for (Livro l : Livro.livros) {
                if (l.disponibilidade == "sim") {
                    if (l.nome.equalsIgnoreCase(nome)) {
                        System.out.println(l.nome);
                    }
                }
            }
            System.out.println("Livros indisponiveis: ");
            for (Livro l : Livro.livros) {
                if (l.disponibilidade == "raro" || l.disponibilidade == "nao") {
                    System.out.println(l.nome);
                    System.out.println("Por que esta indisponivel?");
                    System.out.println(l.descricao);
                }
            }
        }
        menu();
    }

    public static void buscarLivroPorAutor() {
        System.out.println("Digite o nome do autor: ");
        String autor = sc.nextLine();
        if (Livro.livros.isEmpty()){
            System.out.println("Tente adicionar um livro primeiro.");
            menu();
        }
        else {
            System.out.println("Livros disponiveis: ");
            for (Livro l : Livro.livros) {
                if (l.disponibilidade == "sim") {
                    if (l.autor.equalsIgnoreCase(autor)) {
                        System.out.println(l.autor);
                    }
                }
            }
            System.out.println("Livros indisponiveis: ");
            for (Livro l : Livro.livros) {
                if (l.disponibilidade == "raro" || l.disponibilidade == "nao") {
                    System.out.println(l.nome);
                    System.out.println("Por que esta indisponivel?");
                    System.out.println(l.descricao);
                }
            }
        }
        menu();
    }

    public static void realizarEmprestimo() {
        System.out.println("Livros disponiveis:");
        if (Livro.livros.isEmpty()) {
            System.out.println("Tente adicionar um livro primeiro!");
        }
        for (Livro l : Livro.livros) {
            if (l.disponibilidade.equalsIgnoreCase("sim")) {
                System.out.println(l.nome);
            }
        }
        System.out.println("Emprestimo de livros - Selecione um livro digitando o nome dele: ");
        for (Livro l : Livro.livros) {
            if (l.disponibilidade.equalsIgnoreCase("sim")) {
                System.out.println(l.nome);
                String nome = sc.nextLine();
                if (l.nome.equalsIgnoreCase(nome)) {
                    l.disponibilidade = "nao";
                    LocalDate dataDeAluguel = LocalDate.now();
                    l.data = dataDeAluguel.toString();
                    for (Cliente c : Cliente.clientes) {
                        System.out.println("Digite o nome do Cliente que esta alugando o livro:");
                        System.out.println(c.nome);
                        String nomeCliente = sc.nextLine();
                        l.clienteAlugou = nomeCliente;
                    }
                    System.out.println("Livro emprestado com sucesso! Voce tem até 7 dias para fazer a devolução, apos isso sera cobrado taxa!");
                }
            }
        }
        menu();
    }

    public static void realizarDevolucao() {
        System.out.println("Livros Emprestados:");
        if (Livro.livros.isEmpty()) {
            System.out.println("Tente adicionar um livro primeiro!");
        }
        for (Livro l : Livro.livros) {
            if (l.disponibilidade.equalsIgnoreCase("nao")) {
                System.out.println(l.nome);
            }
        }
        System.out.println("Digite o nome do livro para realizar a devolução e conferir se esta na data:");
        for (Livro l : Livro.livros) {
            if (l.disponibilidade.equalsIgnoreCase("nao")) {
                System.out.println(l.nome);
                String nome = sc.nextLine();
                if (l.nome.equalsIgnoreCase(nome)) {
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    System.out.print("Digite a data de devolução (dd/MM/yyyy): ");
                    String input = sc.nextLine();
                    try {
                        LocalDate dataDeAluguel = LocalDate.parse(l.data);
                        LocalDate dataLimite = dataDeAluguel.plusDays(7);
                        LocalDate dataDevolucao = LocalDate.parse(input, formato);

                        if (dataDevolucao.isBefore(dataLimite) || dataDevolucao.isEqual(dataLimite)) {
                            System.out.println("Livro devolvido no prazo!");
                        } else {
                            System.out.println("Livro devolvido com atraso. Cobrar R$3,50 por dia atrasado!");
                        }

                        l.clienteAlugou = "nenhum";
                        l.disponibilidade = "sim";

                    } catch (Exception e) {
                        System.out.println("Data inválida! Use o formato dd/MM/yyyy.");
                    }
                }
            }
        }
        menu();
    }
}
