import java.util.*;

public class BibliotecaPubApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        Livro livro1 = new LivroComum("Harry Potter", "J.K. ");
        Livro livro2 = new LivroComum("O Hobbit", "J.R. ");
        Livro livro3 = new LivroComum("Percy Jackson", "Rick Riordan");
        
        Livro livro4 = new LivroRaro("1984", "George Orwell", "Primeira edição inglesa");
        Livro livro5 = new LivroRaro("Dom Quixote", "Miguel de Cervantes", "Edição original de 1605");
        
        biblioteca.cadastrarLivro(livro1);
        biblioteca.cadastrarLivro(livro2);
        biblioteca.cadastrarLivro(livro3);
        biblioteca.cadastrarLivro(livro4);
        biblioteca.cadastrarLivro(livro5);
        
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Buscar Livro por Título");
            System.out.println("3. Buscar Livro por Autor");
            System.out.println("4. Realizar Empréstimo");
            System.out.println("5. Realizar Devolução");
            System.out.println("6. Verificar Disponibilidade");
            System.out.println("7. Exibir Livros");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do cliente: ");
                    String nome = scanner.nextLine();
                    biblioteca.cadastrarCliente(nome);
                    break;
                case 2:
                    System.out.print("Título do livro: ");
                    String tituloBusca = scanner.nextLine();
                    Livro livroTitulo = biblioteca.buscarPorTitulo(tituloBusca);
                    System.out.println(livroTitulo != null ? livroTitulo.detalhes() : "Livro não encontrado.");
                    break;
                case 3:
                    System.out.print("Autor: ");
                    String autorBusca = scanner.nextLine();
                    Livro livroAutor = biblioteca.buscarPorAutor(autorBusca);
                    System.out.println(livroAutor != null ? livroAutor.detalhes() : "Livro não encontrado.");
                    break;
                case 4:
                System.out.println("Livros disponíveis:");
                biblioteca.exibirLivros();
            
                System.out.print("ID do cliente: ");
                int idCliente = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Título do livro: ");
                String tituloEmprestimo = scanner.nextLine();
                Cliente clienteEmp = biblioteca.getClientes().stream().filter(c -> c.getId() == idCliente).findFirst().orElse(null);
                Livro livroEmp = biblioteca.buscarPorTitulo(tituloEmprestimo);
                if (clienteEmp != null && livroEmp != null)
                    biblioteca.realizarEmprestimo(clienteEmp, livroEmp);
                else
                    System.out.println("Cliente ou livro não encontrado.");
                    break;
                case 5:
                System.out.println("Livros cadastrados:");
                biblioteca.exibirLivros();
            
                System.out.print("ID do cliente: ");
                int idDev = scanner.nextInt();
                scanner.nextLine();
                    System.out.print("Título do livro: ");
                String tituloDev = scanner.nextLine();
                    System.out.print("Dias de atraso: ");
                int atraso = scanner.nextInt();
                Cliente clienteDev = biblioteca.getClientes().stream().filter(c -> c.getId() == idDev).findFirst().orElse(null);
                Livro livroDev = biblioteca.buscarPorTitulo(tituloDev);
                if (clienteDev != null && livroDev != null)
                    biblioteca.realizarDevolucao(clienteDev, livroDev, atraso);
                else
                    System.out.println("Cliente ou livro não encontrado.");
                case 6:
                     System.out.println("Livros disponíveis para verificação:");
                 biblioteca.exibirLivros();
            
                    System.out.print("Título do livro: ");
                 String tituloDisp = scanner.nextLine();
                 Livro livroDisp = biblioteca.buscarPorTitulo(tituloDisp);
                if (livroDisp != null)
                    System.out.println(livroDisp.isEmprestado() ? "Não disponível" : "Disponível");
                else
                    System.out.println("Livro não encontrado.");
                    break;
                case 7:
                    biblioteca.exibirLivros();
                    break;
                case 8:
                    System.out.println("Encerrando...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
