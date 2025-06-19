package fag;
import objetos.Livro;
import objetos.gerenciadores.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner((System.in));
        System.out.println("Seja bem vindo a biblioteca!");
        System.out.println("Oque deseja fazer ?");
        ClienteManager clienteManager = new ClienteManager();;
        LivroManager livroManager = new LivroManager();
        Boolean flag = true;
        while(flag) {
            System.out.println("[1] Cadastrar Livros\n" +
                    "[2] Excluir Livros\n" +
                    "[3] Buscar Livros (Titulo ou ISBN)\n" +
                    "[4] Realizar Empréstimo\n" +
                    "[5] Realizar Devolução\n" +
                    "[6] Cadastrar Clientes\n" +
                    "[7] Excluir Clientes\n" +
                    "[8] Sair\n");
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1 -> cadastrarLivro(scanner, livroManager);
                case 2 -> excluirLivro(scanner, livroManager);
                case 3 -> buscarLivro(scanner, livroManager);
                case 4 -> realizarEmprestimo(scanner, livroManager);
                case 5 -> realizarDevolucao(scanner, livroManager);
                case 6 -> cadastrarClientes(scanner, clienteManager);
                case 7 -> excluirClientes(scanner, clienteManager);
                case 8 -> flag = false;
                default -> System.out.println("Opção inválida");
            }
        }
    }
        private static void excluirClientes(Scanner scanner, ClienteManager clienteManager) {
            System.out.println("Informe o CPF do cliente para remover");
            clienteManager.excluirCliente(scanner.nextInt());
        }

        private static void cadastrarClientes (Scanner scanner, ClienteManager clienteManager) {
            System.out.print("Informe o nome do cliente: ");
            String nome = scanner.next();
            System.out.print("\nQual o ano de nascimento: ");
            int anoNascimento = scanner.nextInt();
            System.out.print("\nQual o CPF do cliente: ");
            int cpf = scanner.nextInt();
            System.out.print("\nInforme o RG do cliente: ");
            int rg = scanner.nextInt();
            clienteManager.cadastrarCliente(nome, anoNascimento, cpf, rg);
    }

        private static void realizarDevolucao (Scanner scanner, LivroManager livroManager) {
            System.out.println("Informe o ISBN do livro por favor: ");
            String isbn = scanner.next();
            livroManager.devolverLivro(isbn);
    }

        private static void realizarEmprestimo (Scanner scanner, LivroManager livroManager) {
            System.out.println("Informe o ISBN do livro por favor: ");
            String isbn = scanner.next();
            livroManager.emprestarLivro(isbn);
        }

        private static void buscarLivro (Scanner scanner, LivroManager livroManager) {
            System.out.println("Voce quer buscar por título ou ISBN ? Digite 1 para Título e 2 para ISBN");
            int opcao = scanner.nextInt();
            switch(opcao){
                case 1 -> {
                    System.out.print("Informe o título da obra: ");
                    String titulo = scanner.next();
                    System.out.println(livroManager.dadosLivroPorTitulo(titulo));}
                case 2 -> {
                    System.out.print("Informe o ISBN da obra: ");
                    String isbn = scanner.next();
                    System.out.println(livroManager.dadosLivroPorISBN(isbn));}
                default -> System.out.println("Opção Inválida!");
                }
            }

        private static void excluirLivro(Scanner scanner, LivroManager livroManager) {
            System.out.println("Informe o título ou ISBN da obra");
            String dados_obra = scanner.next();
            livroManager.removerLivro(dados_obra);
        }

        private static void cadastrarLivro (Scanner scanner, LivroManager livroManager) {
            System.out.print("Informe o titulo do livro: ");
            String titulo = scanner.next();
            System.out.print("\nInforme autor: ");
            String autor = scanner.next();
            System.out.print("\nInforme editora: ");
            String editora = scanner.next();
            System.out.print("\nInforme ano de publicacao: ");
            int anoPublicacao = scanner.nextInt();
            System.out.print("\nInforme numero de paginas: ");
            int numeroPaginas = scanner.nextInt();
            System.out.print("\nInforme o ISBN: ");
            String isbn = scanner.next();
            System.out.println("------ É um livro especial (S/N)? ------");
            String opcao = scanner.next();
            if(opcao.equalsIgnoreCase("N")){
                livroManager.cadastrarLivroComum(titulo,autor,editora,anoPublicacao,numeroPaginas,isbn);
            } else {
                System.out.print("\nInforme o motivo dele ser especial: ");
                String motivo = scanner.next();
                livroManager.cadastrarLivroRaro(titulo,autor,editora,anoPublicacao,numeroPaginas,isbn,motivo);
            }
        }
    }

