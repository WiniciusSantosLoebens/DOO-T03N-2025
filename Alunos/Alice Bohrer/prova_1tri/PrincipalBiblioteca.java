package Main;

import java.util.Optional;
import java.util.Scanner;

import objetos.Cliente;
import objetos.Livro;
import objetos.LivroComum;
import objetos.LivroRaro;
import sistemaBiblioteca.Biblioteca;



public class PrincipalBiblioteca {
	
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		CadastroDeLivrosEClientes();
		menu();

	}
	
	
	
	
	public static void menu () {
		
		System.out.println("o que deseja fazer?");
		
		System.out.println("1 - ver acervo de livros");
		System.out.println("2- buscar por autor");
		System.out.println("3 - buscar por titulo");
		System.out.println("4 - emprestar");
		System.out.println("5 - devolver");
		System.out.println(" 0 - sair ");
		
		int escolha = sc.nextInt();
		
		switch (escolha) {
		
		
		
		case 0 : 
		
		System.exit(0);
			
			
		case 1 : 
			
			verAcervo();
			break;
			
		case 2 :
			
			buscarPorAutor();
			break;
		
		case 3 :
			
			buscarPorTitulo();
			break;
			
		case 4 :
		
			realizarEmprestimo();
		    break;
		    
		case 5 :
			
			realizarDevolucao();
			break;
			
		
		}


		
	
	}
	
	



	public static void CadastroDeLivrosEClientes() {
		
		
		LivroComum Livro1 = new LivroComum ("joão e o pé de feijão", "joao");
		LivroComum Livro2 = new LivroComum ("joão e maria", "maria");
		LivroComum Livro3 = new LivroComum ("cinderela", "pedro");
		
		Biblioteca.CadastarLivro(Livro1);
		Biblioteca.CadastarLivro(Livro2);
		Biblioteca.CadastarLivro(Livro3);
		
		LivroRaro LivroR1 = new LivroRaro (" as aventuras de maria", "maria", "existem somente 2 copias no mundo");
		LivroRaro LivroR2 = new LivroRaro (" as aventuras de joao", "maria", "é a unuca obra no brasil");
		LivroRaro LivroR3 = new LivroRaro (" as aventuras de alice", "maria", "copia unica, escrito a mão");
		
		Biblioteca.CadastarLivro(LivroR1);
		Biblioteca.CadastarLivro(LivroR2);
		Biblioteca.CadastarLivro(LivroR3);
		
		

	}
	
	public static void verAcervo() {
	    for (Livro livro : Biblioteca.livros) {
	        String tipo = (livro instanceof LivroRaro) ? "Raro" : "Comum";
	        String infoRaridade = "";
	        
	        if (livro instanceof LivroRaro) {
	            LivroRaro raro = (LivroRaro) livro;
	            infoRaridade = " | Raridade: " + raro.getExRaridade();
	        }

	        System.out.println("- " + livro.getNome() +
	                " | Autor: " + livro.getAutor() +
	                " | Tipo: " + tipo +
	                " | Disponível: " + (livro.isDisponibilidade() ? "Sim" : "Não") +
	                infoRaridade);
	    }
			
	    menu();

	}	
		
		
		
		
	
	
	public static void buscarPorAutor() {
		System.out.print("Digite o título do livro: ");
		String titulo = sc.nextLine();

		
		Optional<Livro> resultado = Biblioteca.BuscarPorTitulo(titulo);

		
		resultado.ifPresentOrElse(
		    l -> System.out.println("Livro encontrado: " + l.getNome()),
		    () -> System.out.println("Livro não encontrado.")
		);

	    menu(); 
	}

	public static void buscarPorTitulo() {
	    System.out.print("Digite o título do livro: ");
	    String titulo = sc.nextLine();
	    Optional<Livro> resultado = Biblioteca.BuscarPorTitulo(titulo);

	    resultado.ifPresentOrElse(
	        l -> System.out.println("Livro encontrado: " + l.getNome()),
	        () -> System.out.println("Livro não encontrado.")
	    );

	    menu(); 
	
	}
	    public static void realizarEmprestimo() {
	        System.out.print("Digite o CPF do cliente: ");
	        String cpf = sc.nextLine();

	        Cliente cliente = Biblioteca.clientes.stream()
	                .filter(c -> c.getCpf().equals(cpf))
	                .findFirst()
	                .orElse(null);

	        if (cliente == null) {
	            System.out.println("Cliente não encontrado.");
	            return;
	        }

	        System.out.print("Digite o título do livro a ser emprestado: ");
	        String titulo = sc.nextLine();

	        Optional<Livro> livroOpt = Biblioteca.BuscarPorTitulo(titulo);
	        if (livroOpt.isEmpty()) {
	            System.out.println("Livro não encontrado.");
	            return;
	        }

	        Biblioteca.RealizarEmprestimo(livroOpt.get(), cliente);
	        
	        menu();
	    }

	    public static void realizarDevolucao() {
	        System.out.print("Digite o título do livro a ser devolvido: ");
	        String titulo = sc.nextLine();

	        Optional<Livro> livroOpt = Biblioteca.BuscarPorTitulo(titulo);
	        if (livroOpt.isEmpty()) {
	            System.out.println("Livro não encontrado.");
	            return;
	        }

	        Biblioteca.realizarDevolucao(livroOpt.get());
	        
	        menu();
	    }
	
	    
	    
	
	    
	    
	     
	    }
	    
