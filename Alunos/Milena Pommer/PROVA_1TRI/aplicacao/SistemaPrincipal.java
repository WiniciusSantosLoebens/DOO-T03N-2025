package aplicacao;

import java.time.LocalDate;
import java.util.Scanner;

import entidades.Biblioteca;
import entidades.Cliente;
import entidades.Livro;
import entidades.LivroComum;
import entidades.LivroRaro;

public class SistemaPrincipal {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Biblioteca biblioteca = new Biblioteca();

		while (true) {
			System.out.println("----------------------Sistema Biblioteca----------------------");
			System.out.println("----------Digite o número de acordo com o que deseja:---------");
			System.out.println("------------Digite 1 para CADASTRAR CLIENTE-------------------");
			System.out.println("------------Digite 2 para CADASTRAR LIVRO---------------------");
			System.out.println("------------Digite 3 para BUSCAR LIVRO POR TITULO-------------");
			System.out.println("------------Digite 4 para BUSCAR LIVRO POR AUTOR--------------");
			System.out.println("------------Digite 5 para REALIZAR EMPRESTIMO-----------------");
			System.out.println("------------Digite 6 para REALIZAR DEVOLUÇÃO------------------");
			System.out.println("------------Digite 7 para VERIFICAR DISPONIBILIDADE DE LIVRO--");
			System.out.println("------------Digite 8 para VER RARIDADE DE LIVRO RARO----------");
			System.out.println("------------Digite 9 para ENCERRAR O SISTEMA------------------");
			int opcaoUsuario = sc.nextInt();
			sc.nextLine();

			switch (opcaoUsuario) {
			case 1:
				System.out.print("Nome do cliente: ");
				String nomeCliente = sc.nextLine();
				System.out.print("ID do cliente: ");
				int idCliente = sc.nextInt();
				Cliente cliente = new Cliente(idCliente, nomeCliente, null);
				biblioteca.cadastrarCliente(cliente);
				System.out.println("Cliente cadastrado com sucesso.");
				break;

			case 2:
				System.out.print("ID do livro: ");
				int idLivro = sc.nextInt();
				sc.nextLine();
				System.out.print("Título do livro: ");
				String titulo = sc.nextLine();
				System.out.print("Autor do livro: ");
				String autor = sc.nextLine();
				System.out.print("Tipo do livro (1 - Comum | 2 - Raro): ");
				int tipo = sc.nextInt();
				Livro livro;
				if (tipo == 1) {
					livro = new LivroComum(idLivro, titulo, autor, true);
				} else {
					livro = new LivroRaro(idLivro, titulo, autor, true);
				}
				biblioteca.adicionarLivro(livro);
				System.out.println("Livro cadastrado com sucesso.");
				break;

			case 3:
				System.out.print("Digite o título: ");
				String tituloBusca = sc.nextLine();
				Livro l1 = biblioteca.buscarLivroTitulo(tituloBusca);
				System.out.println(l1 != null ? l1 : "Livro não encontrado.");
				break;

			case 4:
				System.out.print("Digite o autor: ");
				String autorBusca = sc.nextLine();
				Livro l2 = biblioteca.buscarLivroAutor(autorBusca);
				System.out.println(l2 != null ? l2 : "Livro não encontrado.");
				break;

			case 5:
				System.out.print("ID do cliente: ");
				int idC = sc.nextInt();
				sc.nextLine();
				System.out.print("Título do livro: ");
				String tituloEmp = sc.nextLine();
				Cliente cliEmp = biblioteca.buscarClientePorId(idC);
				Livro livEmp = biblioteca.buscarLivroTitulo(tituloEmp);
				if (cliEmp != null && livEmp != null) {
					biblioteca.realizarEmprestimo(cliEmp, livEmp);
				} else {
					System.out.println("Cliente ou livro não encontrado.");
				}
				break;

			case 6:
				System.out.print("ID do cliente: ");
				int idDev = sc.nextInt();
				sc.nextLine();
				System.out.print("Título do livro: ");
				String tituloDev = sc.nextLine();
				Cliente cliDev = biblioteca.buscarClientePorId(idDev);
				Livro livDev = biblioteca.buscarLivroTitulo(tituloDev);
				if (cliDev != null && livDev != null) {
					biblioteca.realizarDevolucao(cliDev, livDev, LocalDate.now());
				} else {
					System.out.println("Cliente ou livro não encontrado.");
				}
				break;

			case 7:
				System.out.print("Título do livro: ");
				String tituloDisp = sc.nextLine();
				Livro livDisp = biblioteca.buscarLivroTitulo(tituloDisp);
				if (livDisp != null) {
					System.out.println("Disponibilidade: " + (livDisp.isDisponibilidade() ? "Sim" : "Não"));
				} else {
					System.out.println("Livro não encontrado.");
				}
				break;

			case 8:
				System.out.print("Título do livro raro: ");
			    String tituloRaro = sc.nextLine();
			    Livro livroRaro = biblioteca.buscarLivroTitulo(tituloRaro);
			    if (livroRaro != null && livroRaro instanceof LivroRaro) {
			        System.out.println(((LivroRaro) livroRaro).raridade());
			    } else if (livroRaro != null) {
			        System.out.println("Este livro não é classificado como raro.");
			    } else {
			        System.out.println("Livro não encontrado.");
			    }
			    break;
			case 9:
				System.out.println("Sistema encerrado.");
				sc.close();
				return;

			default:
				System.out.println("Opção inválida.");
			}
		}
	}
}
