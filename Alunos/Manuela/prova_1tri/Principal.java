	package prova_1tri;
import java.util.Scanner;
import objeto.Biblioteca;
import objeto.Cliente;
import objeto.Livro;
import objeto.LivroComum;
import objeto.LivroRaro;


public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	       Scanner scanner = new Scanner(System.in);
	        Biblioteca biblioteca = new Biblioteca();

	        while (true) {
	            System.out.println(" BibliotecaPub - Menu");
	            System.out.println("1. Cadastrar cliente");
	            System.out.println("2. Cadastrar livro comum");
	            System.out.println("3. Cadastrar livro raro");
	            System.out.println("4. Buscar livro por título");
	            System.out.println("5. Buscar livro por autor");
	            System.out.println("6. Realizar empréstimo");
	            System.out.println("7. Realizar devolução");
	            System.out.println("8. Verificar disponibilidade de um título");
	            System.out.println("0. Sair");
	            System.out.print("Escolha uma opção: ");

	            int opcao = scanner.nextInt();
	            scanner.nextLine(); 

	            switch (opcao) {
	                case 1 -> {
	                    System.out.print("Nome do cliente: ");
	                    String nome = scanner.nextLine();
	                    System.out.print("CPF do cliente: ");
	                    String cpf = scanner.nextLine();
	                    biblioteca.cadastrarCliente(new Cliente(nome, cpf));
	                    System.out.println("Cliente cadastrado com sucesso!");
	                }
	                case 2 -> {
	                    System.out.print("Título do livro: ");
	                    String titulo = scanner.nextLine();
	                    System.out.print("Autor do livro: ");
	                    String autor = scanner.nextLine();
	                    biblioteca.cadastrarLivro(new LivroComum(titulo, autor));
	                    System.out.println("Livro comum cadastrado!");
	                }
	                case 3 -> {
	                    System.out.print("Título do livro raro: ");
	                    String titulo = scanner.nextLine();
	                    System.out.print("Autor do livro raro: ");
	                    String autor = scanner.nextLine();
	                    System.out.print("Descrição da rareza: ");
	                    String descricao = scanner.nextLine();
	                    biblioteca.cadastrarLivro(new LivroRaro(titulo, autor, descricao));
	                    System.out.println("Livro raro cadastrado!");
	                }
	                case 4 -> {
	                    System.out.print("Digite o título: ");
	                    String titulo = scanner.nextLine();
	                    Livro livro = biblioteca.buscarLivroPorTitulo(titulo);
	                    if (livro != null)
	                        System.out.println("Livro encontrado: " + livro.getTitulo() + " - " + livro.getAutor());
	                    else
	                        System.out.println("Livro não encontrado.");
	                }
	                case 5 -> {
	                    System.out.print("Digite o autor: ");
	                    String autor = scanner.nextLine();
	                    Livro livro = biblioteca.buscarLivroPorAutor(autor);
	                    if (livro != null)
	                        System.out.println("Livro encontrado: " + livro.getTitulo() + " - " + livro.getAutor());
	                    else
	                        System.out.println("Livro não encontrado.");
	                }
	                case 6 -> {
	                    System.out.print("CPF do cliente: ");
	                    String cpf = scanner.nextLine();

	                    Cliente cliente = null;
	                    for (Cliente c : biblioteca.clientes) {
	                        if (c.getCpf().equals(cpf)) {
	                            cliente = c;
	                            break;
	                        }
	                    }

	                    if (cliente == null) {
	                        System.out.println("Cliente não encontrado.");
	                        break;
	                    }

	                    System.out.print("Título do livro: ");
	                    String titulo = scanner.nextLine();
	                    Livro livro = biblioteca.buscarLivroPorTitulo(titulo);

	                    if (biblioteca.realizarEmprestimo(cliente, livro)) {
	                        System.out.println("Empréstimo realizado com sucesso!");
	                    } else {
	                        System.out.println("Não foi possível realizar o empréstimo.");
	                    }
	                }
	                case 7 -> {
	                    System.out.print("Título do livro a devolver: ");
	                    String titulo = scanner.nextLine();
	                    Livro livro = biblioteca.buscarLivroPorTitulo(titulo);

	                    if (livro != null && livro.isEmprestado()) {
	                        System.out.print("Quantos dias se passaram desde o empréstimo? ");
	                        int dias = scanner.nextInt();
	                        scanner.nextLine(); 

	                        double multa = biblioteca.realizarDevolucao(livro, dias);
	                        if (multa > 0) {
	                            System.out.printf("Livro devolvido com multa de R$ %.2f%n", multa);
	                        } else {
	                            System.out.println("Livro devolvido sem multa.");
	                        }
	                    } else {
	                        System.out.println("Livro não encontrado ou não está emprestado.");
	                    }
	                }
	                case 8 -> {
	                    System.out.print("Digite o título: ");
	                    String titulo = scanner.nextLine();
	                    if (biblioteca.verificarDisponibilidade(titulo)) {
	                        System.out.println("O livro está disponível para empréstimo.");
	                    } else {
	                        System.out.println("O livro não está disponível.");
	                    }
	                }
	                case 0 -> {
	                    System.out.println("Encerrando o sistema. Até logo!");
	                    scanner.close();
	                    return;
	                }
	                default -> System.out.println("Opção inválida!");
	            }
	        }
	    }
	}