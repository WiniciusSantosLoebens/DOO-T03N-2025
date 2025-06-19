package prova_1tri;
import objeto.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        while (true) {
            System.out.printf("\nDigite o número da opção que deseja:\n");
            System.out.println("1. Cadastrar cliente;");
            System.out.println("2. Cadastrar livro comum;");
            System.out.println("3. Cadastrar livro raro;");
            System.out.println("4. Buscar livro por título;");
            System.out.println("5. Buscar livro por autor;");
            System.out.println("6. Emprestar livro;");
            System.out.println("7. Devolver livro;");
            System.out.println("8. Verificar disponibilidade de livro;");
            System.out.println("9. Sair.");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();

            
            switch (opcao) {
                case 1:
                	System.out.print("Entre com o nome do cliente: ");
                    String nome = scanner.nextLine();

                    String cpf;
                    while (true) {
                        System.out.print("Entre com o CPF do cliente (apenas números): ");
                        cpf = scanner.nextLine();
                     
                        if (validarCpf(cpf)) {
                            break; 
                        } else {
                            System.out.println("Erro! CPF inválido. O CPF deve conter apenas números e ter 11 dígitos.");
                        }
                    }

                    biblioteca.cadastrarCliente(new Cliente(nome, cpf));
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;
                    
                case 2:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    biblioteca.cadastrarLivro(new Livro(titulo, autor));
                    System.out.println("Livro cadastrado como tipo comum!");
                    break;
                    
                case 3:
                    System.out.print("Título: ");
                    String tituloRaro = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autorRaro = scanner.nextLine();
                    System.out.print("Justificativa: ");
                    String justificativa = scanner.nextLine();
                    biblioteca.cadastrarLivro(new LivroRaro(tituloRaro, autorRaro, justificativa));
                    System.out.println("Livro cadastrado como tipo raro!");
                    break;
                    
                case 4:
                    System.out.print("Título: ");
                    String tituloBusca = scanner.nextLine();
                    Livro l1 = biblioteca.buscarPorTitulo(tituloBusca);
                    System.out.println(l1 != null ? "Encontrado: " + l1.getTitulo() : "Não encontrado.");
                    break;

                case 5:
                    System.out.print("Autor: ");
                    String autorBusca = scanner.nextLine();
                    Livro l2 = biblioteca.buscarPorAutor(autorBusca);
                    System.out.println(l2 != null ? "Encontrado: " + l2.getTitulo() : "Não encontrado.");
                    break;
                    
                case 6:
                	System.out.print("Qual o título do livro que deseja emprestar? ");
                	String tituloEmprestar = scanner.nextLine();
                	Livro livroEmprestar = biblioteca.buscarPorTitulo(tituloEmprestar);

                	if (livroEmprestar != null) {
                	    System.out.print("CPF do cliente que deseja emprestar o livro:(Somente números)");
                	    String cpfCliente = scanner.nextLine();
                	    Cliente cliente = biblioteca.buscarClientePorCpf(cpfCliente);

                	    if (cliente != null) {
                	        biblioteca.emprestarLivro(livroEmprestar, cliente); 
                	    } else {
                	        System.out.println("Cliente não encontrado. Primeiro registre o cliente!");
                	    }
                	} else {
                	    System.out.println("Livro não encontrado.");
                	}
                	break;
                    
                case 7:
                    System.out.print("Qual é o título do livro para devolução:");
                    String tituloDevolver = scanner.nextLine();
                    Livro l4 = biblioteca.buscarPorTitulo(tituloDevolver);
                    if (l4 != null) {
                    	biblioteca.devolverLivro(l4); }
                    else {
                    	System.out.println("Livro não encontrado.");  }
                    break;
                    
                case 8:
                    System.out.print("Verificaremos a disponibilidade do livro desejado. Insira o título:");
                    String tituloDisp = scanner.nextLine();
                    boolean disponivel = biblioteca.estaDisponivel(tituloDisp);
                    System.out.println(disponivel ? "Disponível para empréstimo." : "Não disponível.");
                    break;
                    
                case 9:
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        
    }
    public static boolean validarCpf(String cpf) {
        return cpf.matches("[0-9]{11}");
    }
    }
