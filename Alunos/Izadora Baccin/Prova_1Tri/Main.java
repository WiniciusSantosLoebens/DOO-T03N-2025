package prova;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Biblioteca biblioteca = new Biblioteca();
		
		 biblioteca.cadastrarLivroRaro(new LivroRaro(
	                "É Assim Que Acaba", "Colen Hover", "A história é muito triste!"));
	        biblioteca.cadastrarLivroRaro(new LivroRaro(
	                "O Lado Feio Do Amor ", "Colen Hover", "Traumatizado e traumatiza os outros"));
		
	        biblioteca.cadastrarLivroComun(new LivroComun(
	                "O Hobbit ", "JRR"));
	        biblioteca.cadastrarLivroComun(new LivroComun(
	                "É Assim Que Começa", "Colen Hover"));
	        
		int opcao;
		
		do {
		System.out.println("\n===Biblioteca Pug===");
		System.out.println("1-Cadastrar Cliente: ");
		System.out.println("2-Cadastrar Livros Comuns");
		System.out.println("3-Cadastrar Livros Raros");
		System.out.println("4-Procurar por Autor");
		System.out.println("5-Procurar por Título ");
		System.out.println("6-Calcular Multa por Atraso");
		System.out.println("7-Sair");
		opcao = scan.nextInt();
		scan.nextLine();
		
		switch(opcao){
		case 1: 
			Cliente novoCliente = Cliente.cadastrar(scan);
			biblioteca.cadastrarCliente(novoCliente);
		break;
			
		case 2:
			LivroComun novoLivroComun = LivroComun.cadastrar(scan);
			biblioteca.cadastrarLivroComun(novoLivroComun);
		break;
		
		case 3: 
			LivroRaro novoLivroRaro = LivroRaro.cadastrar(scan);
			biblioteca.cadastrarLivroRaro(novoLivroRaro);
		break;
		
		case 4:
            System.out.print("Nome do autor: ");
            String autora = scan.nextLine();
            biblioteca.buscarLivroAutora(autora);
            break;
            
		case 5: 
            System.out.print("Título do livro: ");
            String titulo = scan.nextLine();
            biblioteca.buscarLivroTitulo(titulo);
            break;
            
		case 6:
            System.out.print("Quantos dias de atraso? ");
            int diasAtraso = scan.nextInt();
            
            double multa = biblioteca.calcularMulta(diasAtraso);
            System.out.printf("Multa: R$ %.2f\n", multa);
         break;

        case 7:
            System.out.println("Saindo do sistema...");
        break;
        
        default:
            System.out.println("Opção inválida!");
		 }
		}while(opcao != 7);
       scan.close();
	}

}
