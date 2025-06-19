package Alunos.Braian.Prova_1trim;

import java.util.Scanner;

public class Biblioteca {

    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        exibirMenu();
        }

        public void cadastrarCliente() {
            System.out.println("Cadastro de cliente realizado com sucesso!");
        }

        public static void exibirMenu() {
            int opcao = 0;

            while (opcao != 8) {
                System.out.println("\n===== MENU =====");
                System.out.println("1 - Cadastro de Cliente");
                System.out.println("2 - Cadastro de Livro");
                System.out.println("3 - Buscar Livro por Título");
                System.out.println("4 - Buscar Livro por Autor");
                System.out.println("5 - Empréstimo de Livro");
                System.out.println("6 - Devolução de Livro");
                System.out.println("7 - Verificar Disponibilidade de Livro");
                System.out.println("8 - Sair");
                System.out.print("Escolha uma opção: ");
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> Cliente.cadastrarCliente();
                    case 2 -> Livro.cadastrarLivro();
                    case 3 -> Livro.buscarPorTitulo();
                    case 4 -> Livro.buscarPorAutor();
                    case 5 -> Livro.emprestarLivro();
                    case 6 -> Livro.devolverLivro();
                    case 7 -> Livro.verificarDisponibilidade();
                    case 8 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida!");
                }
            }
        }
    }



