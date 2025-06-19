import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Biblioteca {
    static Scanner sc = new Scanner(System.in);
    private static List<Livro> listaDeLivros = new ArrayList<>();


    static void adicionarLivro(String nome, String autor, String tipo) {
		Livro livro = new Livro(nome, autor, tipo);
		listaDeLivros.add(livro);
	}

    static void cadastrarLivro() {
        System.out.print("Quantos livros deseja cadastrar?");
		int quantidade = sc.nextInt();
	    System.out.println("\n");

        for (int i = 0; i < quantidade; i++) {
            System.out.println("\n");
            sc.nextLine();
            System.out.print("Digite o nome do Livro: ");
            String nome = sc.nextLine(); 
            System.out.print("Digite o nome do autor do Livro: ");
            String autor = sc.nextLine();      
            String tipo;   	
            System.out.print("De qual tipo é esse livro? 1-Raro 2-Comum (Digite apenas o número)");
            int opcao = sc.nextInt();
            if (opcao==1) {
                tipo = "Raro";
            } else {
                tipo = "Comum";
            }

            adicionarLivro(nome, autor, tipo);
        }
    }

    static void listarLivros(){
        System.out.println("\n=== Lista de livros ===");
        int j = 1;
        for (Livro livro : listaDeLivros) {
        	System.out.println(j+"- "+livro.toString());
        	
        	j++;
        }
        System.out.println("\n");
    }

    static void listarLivrosDisponiveis(){
        System.out.println("\n=== Lista de livros Disponíveis ===");
        int j = 1;
        for (Livro livro : listaDeLivros) {
            if(livro.isStatus() == true){
                System.out.println(j+"- "+livro.toString());
            }

        	j++;
        }
        System.out.println("\n");
    }

    static void listarLivrosIndisponiveis(){
        System.out.println("\n=== Lista de livros Indisponíveis ===");
        int j = 1;
        for (Livro livro : listaDeLivros) {
            if(livro.isStatus() == false){
                System.out.println(j+"- "+livro.toString());
            }

        	j++;
        }
        System.out.println("\n");
    }

    public static List<Livro> buscarLivroNome(String tituloBusca){
		List<Livro> livrosEncontrados = listaDeLivros.stream()
                .filter(livro -> livro.getNome().contains(tituloBusca))
                .collect(Collectors.toList());	     
		return livrosEncontrados; 
	}

    public static List<Livro> buscarLivroAutor(String tituloBusca){
		List<Livro> livrosEncontrados = listaDeLivros.stream()
                .filter(livro -> livro.getAutor().contains(tituloBusca))
                .collect(Collectors.toList());	     
		return livrosEncontrados; 
	}

    static void listarLivrosEncontradosBuscaAutor(String tituloBusca){

        List<Livro> livrosEncontrados = buscarLivroAutor(tituloBusca);
        System.out.println("\n=== Lista de livros ===");
        int j = 1;
        for (Livro livro : livrosEncontrados) {
        	System.out.println(j+"- "+livro.toString());
        	
        	j++;
        }
        System.out.println("\n");
    }

    static void listarLivrosEncontradosBuscaNome(String tituloBusca){

        List<Livro> livrosEncontrados = buscarLivroNome(tituloBusca);
        System.out.println("\n=== Lista de livros ===");
        int j = 1;
        for (Livro livro : livrosEncontrados) {
        	System.out.println(j+"- "+livro.toString());
        	
        	j++;
        }
        System.out.println("\n");
    }

    static void listarLivrosEncontradosBuscaAutorDisponiveis(String tituloBusca){

        List<Livro> livrosEncontrados = buscarLivroAutor(tituloBusca);
        System.out.println("\n=== Lista de livros ===");
        int j = 1;
        for (Livro livro : livrosEncontrados) {
        	if(livro.isStatus() == true && livro.getTipo().equals("Comum")){
                System.out.println(j+"- "+livro.toString());
            }
        	j++;
        }
        System.out.println("\n");

        if (!livrosEncontrados.isEmpty()) {
            System.out.println("Digite o numero do livro que deseja reservar: ");
            int x = sc.nextInt();

            livrosEncontrados.get(x-1).setStatus(false);

            System.out.println("Reservado!! ");

            System.out.println("\n");
        }
    }

    static void listarLivrosEncontradosBuscaNomeDisponiveis(String tituloBusca){

        List<Livro> livrosEncontrados = buscarLivroNome(tituloBusca);
        System.out.println("\n=== Lista de livros ===");
        int j = 1;
        for (Livro livro : livrosEncontrados) {
        	if(livro.isStatus() == true && livro.getTipo().equals("Comum")){
                System.out.println(j+"- "+livro.toString());
            }
        	j++;
        }
        System.out.println("\n");
        if (!livrosEncontrados.isEmpty()) {
            System.out.println("Digite o numero do livro que deseja reservar: ");
            int x = sc.nextInt();

            livrosEncontrados.get(x-1).setStatus(false);

            System.out.println("Reservado!! ");

            System.out.println("\n");
        }
        
    }

    static void reservarLivro(){
        System.out.println("Você deseja reservar um livro, deseja buscar por: 1-Nome do Autor, 2- Nome do livro");
        int opcao = sc.nextInt();
        if (opcao==1) {
            System.out.println("Digite o nome do Autor: ");
            String titulo = sc.next();
            listarLivrosEncontradosBuscaAutorDisponiveis(titulo);
        } else{
            System.out.println("Digite o nome do Livro: ");
            String titulo = sc.next();
            listarLivrosEncontradosBuscaNomeDisponiveis(titulo);
        }

    }

    static void devolucaoLivro(){
        System.out.println("\n=== Lista de livros para devolver===");
        int j = 1;
        for (Livro livro : listaDeLivros) {
            if(livro.isStatus() == false){
                System.out.println(j+"- "+livro.toString());
            }

        	j++;
        }
        System.out.println("\n");

        if (!listaDeLivros.isEmpty()) {
            System.out.println("Digite o numero do livro que deseja devolver: ");
            int x = sc.nextInt();

            System.out.println("Ultrapassou 7 dias de atraso? 1-Sim 2-Não");
            int y = sc.nextInt();

            if (y==1) {
                System.out.println("Você precisa pagar uma multa de 3,50 por dia atrasado.");
                System.out.println("Atrasou quantos dias?");
                int z = sc.nextInt();

                System.out.println("Sua multa ficou " + z*3.50);

                System.out.println("Pagou? 1-Sim 2-Não");
                y = sc.nextInt();
                if (y==1) {
                    listaDeLivros.get(x-1).setStatus(true);
                    System.out.println("Delvolvido!");
                }else {
                    System.out.println("Não foi possível fazer a devolução, pague a sua multa o quanto antes");
                }
            } else{
                listaDeLivros.get(x-1).setStatus(true);
                System.out.println("Delvolvido!");
            }

            System.out.println("\n");
        }
    }

}
