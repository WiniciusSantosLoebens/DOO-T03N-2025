import java.util.Scanner;

public class SistemaPrincipal {
    static Biblioteca biblioteca = new Biblioteca();
    static Scanner sc = new Scanner(System.in);
    public static void fluxoBiblioteca(){
        int i;
        int y;
        do {
			System.out.println("\n ----- Bem vindo a Biblioteca -----\n");
			System.out.println("\n Digite o número da opção desejada: ");
	    	System.out.println("1- Gerenciamento da Biblioteca (adicionar, buscar)");
	    	System.out.println("2- Reservar Livro");
	    	System.out.println("3- Devolver Livro");
	    	System.out.println("4- Sair");
	    	i = sc.nextInt();

            System.out.println("\n");
            System.out.println("\n");

            if (i==1) {
                
                do{
                    System.out.println("1- Listar livros");
	    	        System.out.println("2- Adicionar Livros");
	    	        System.out.println("3- Listar livros disponiveis");
	    	        System.out.println("4- Listar livros indisponiveis");
                    System.out.println("5- Sair");
                    y = sc.nextInt();

                    if (y==1) {
                        Biblioteca.listarLivros();                        
                    }

                    if (y==2) {
                        Biblioteca.cadastrarLivro();
                    }

                    if (y==3) {
                        Biblioteca.listarLivrosDisponiveis();
                    }

                    if (y==4) {
                        Biblioteca.listarLivrosIndisponiveis();
                    }

                }while(y!=5); 
                
            }

            if (i==2) {
                Biblioteca.reservarLivro();
            }

            if (i==3) {
                Biblioteca.devolucaoLivro();
            }
            

        }while(i!=4);    
    	System.out.println("SISTEMA ENCERRADO!");
    	sc.close();
    }

    public static void main(String[] args) {

        
        fluxoBiblioteca();
    }
}
