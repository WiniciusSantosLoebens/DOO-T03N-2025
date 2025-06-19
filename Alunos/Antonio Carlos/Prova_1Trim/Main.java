package principal;

import biblioteca.objetos.Livro;
import biblioteca.objetos.Cliente;
import biblioteca.objetos.LivroComum;
import biblioteca.objetos.LivroRaro;
import gerenciador.Biblioteca;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	static final Scanner scan = new Scanner(System.in);
    static final Biblioteca biblioteca = new Biblioteca("Biblioteca Pública Municipal");


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int opcao;
		do {
        	exibirMenuPrincipal();
        	System.out.println("Escolha uma opção: ");
        	opcao = scan.nextInt();
        	scan.nextLine();
        	
        	switch (opcao) {
            case 1:
                cadastrarCliente();
                break;
            case 2:
                gerenciamentoLivros();
                break;
            case 3:
                gerenciamentoEmprestimos();
                break;
            case 4:
                System.out.println("Saindo do sistema...");
                break;
            default:
                System.out.println("Opção inválida.");
        }
        
        if (opcao != 4) {
             System.out.println("\nPressione Enter para continuar...");
             scan.nextLine();
        }
      } while (opcao !=4);
        scan.close();
        System.out.println("Sistema Oficina encerrado.");
        
	}
		
	
	static void exibirMenuPrincipal() {
		
		System.out.println("\n--- BibliotecPub ---");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Gerenciar Livros");
        System.out.println("3. Gerenciar Empréstimos");
        System.out.println("4. Sair do sistema");
		
	}
	
	
	static void exibirSubMenuLivro() {
		
		System.out.println("\n--- Gerenciamento de Livros ---");
        System.out.println("1. Cadastrar Livro");
        System.out.println("2. Buscar Livro por título");
        System.out.println("3. Buscar Livro por ator");
        System.out.println("4. Voltar ao Menu Principal");
		
	}
	
	static void exibirSubMenuEmprestimo() {
		
		System.out.println("\n--- Gerenciamento de Empréstimos ---");
        System.out.println("1. Realizar Empréstimo");
        System.out.println("2. Realizar Devolução");
        System.out.println("3. Verificar Disponibilidade de Livro");
        System.out.println("4. Voltar ao Menu Principal");
			
	}
	
	static void cadastrarCliente() {
        System.out.println("\n--- Cadastro de Cliente ---");
        System.out.print("Nome do Cliente: ");
        String nome = scan.nextLine();
        System.out.print("ID do Cliente (ex: CLI003): ");
        String id = scan.nextLine();
        
        biblioteca.cadastrarCliente(new Cliente(nome, id));
    }
	
	static void gerenciamentoLivros() {
        int opcaoSub;
        do {
            exibirSubMenuLivro();
            System.out.print("Escolha uma opção: ");
            opcaoSub = scan.nextInt();
            scan.nextLine(); // Consome newline

            switch (opcaoSub) {
                case 1:
                    cadastrarLivro(); 
                    break;
                case 2:
                    buscarLivroPorTitulo(); 
                    break;
                case 3:
                    buscarLivroPorAutor(); 
                    break;
                case 4:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
            if (opcaoSub != 4) {
                System.out.println("\nPressione Enter para continuar...");
                scan.nextLine();
            }
        } while (opcaoSub != 4);
    }

	
	 static void gerenciamentoEmprestimos() {
	        int opcaoSub;
	        do {
	            exibirSubMenuEmprestimo();
	            System.out.print("Escolha uma opção: ");
	            opcaoSub = scan.nextInt();
	            scan.nextLine(); // Consome newline

	            switch (opcaoSub) {
	                case 1:
	                    realizarEmprestimo(); // Chama ação de emprestar
	                    break;
	                case 2:
	                    realizarDevolucao(); // Chama ação de devolver
	                    break;
	                case 3:
	                    verificarDisponibilidade(); // Chama ação de verificar
	                    break;
	                case 4:
	                    System.out.println("Retornando ao menu principal...");
	                    break;
	                default:
	                    System.out.println("Opção inválida.");
	            }
	            if (opcaoSub != 4) {
	                System.out.println("\nPressione Enter para continuar...");
	                scan.nextLine();
	            }
	        } while (opcaoSub != 4);
	    }
	 

	    // Métodos de Ação Específica (Chamados pelos Gerenciadores de Submenu)
	 static void cadastrarLivro() {
	        System.out.println("\n--- Cadastro de Livro ---");
	        System.out.print("Título: ");
	        String titulo = scan.nextLine();
	        System.out.print("Autor: ");
	        String autor = scan.nextLine();
	        System.out.print("Tipo (1 - Comum, 2 - Raro): ");
	        int tipo = scan.nextInt();
	        scan.nextLine(); 

	        if (tipo == 1) {
	         
	            biblioteca.cadastrarLivro(new LivroComum(titulo, autor));
	        } else if (tipo == 2) {
	            System.out.print("Motivo da Raridade: ");
	            String motivo = scan.nextLine();
	          
	            biblioteca.cadastrarLivro(new LivroRaro(titulo, autor, motivo));
	        } else {
	            System.out.println("Tipo inválido.");
	        }
	    }
	 
	 
	// Metodo para buscar Livro por Título (chamado por gerenciamentoLivros)
	    static void buscarLivroPorTitulo() {
	        System.out.println("\n--- Buscar Livro por Título ---");
	        System.out.print("Digite o Título: ");
	        String titulo = scan.nextLine();
	        
	        Livro livro = biblioteca.buscarLivroPorTitulo(titulo);
	        if (livro != null) {
	            System.out.println("Encontrado: " + livro); 
	        } else {
	            System.out.println("Livro não encontrado.");
	        }
	    }

	    // Metodo para buscar Livro por Autor (chamado por gerenciamentoLivros)
	    static void buscarLivroPorAutor() {
	        System.out.println("\n--- Buscar Livro por Autor ---");
	        System.out.print("Digite o Autor: ");
	        String autor = scan.nextLine();
	        // DELEGAÇÃO
	        Livro livro = biblioteca.buscarLivroPorAutor(autor);
	        if (livro != null) {
	            System.out.println("Encontrado: " + livro);
	        } else {
	            System.out.println("Nenhum livro encontrado para este autor.");
	        }
	    }

	    // Metodo para realizar Empréstimo (chamado por gerenciamentoEmprestimos)
	    static void realizarEmprestimo() {
	        System.out.println("\n--- Realizar Empréstimo ---");
	        System.out.print("ID do Cliente: ");
	        String idCliente = scan.nextLine();
	        System.out.print("Título do Livro: ");
	        String titulo = scan.nextLine();
	        
	        biblioteca.realizarEmprestimo(idCliente, titulo);
	    }
	    
	    static void realizarDevolucao() {
	        System.out.println("\n--- Realizar Devolução ---");
	        System.out.print("Título do Livro a devolver: ");
	        String titulo = scan.nextLine();
	        
	        biblioteca.realizarDevolucao(titulo);
	    }

	    // Metodo para verificar Disponibilidade (chamado por gerenciamentoEmprestimos)
	    static void verificarDisponibilidade() {
	        System.out.println("\n--- Verificar Disponibilidade ---");
	        System.out.print("Título do Livro: ");
	        String titulo = scan.nextLine();
	        
	        biblioteca.verificarDisponibilidade(titulo);
	    }
	 



}
