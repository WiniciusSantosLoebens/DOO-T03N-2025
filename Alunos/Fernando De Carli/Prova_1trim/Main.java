import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        Cliente cliente1 = new Cliente("João Silva", 1);
        Cliente cliente2 = new Cliente("Maria Souza", 2);
        biblioteca.adicionarCliente(cliente1);
        biblioteca.adicionarCliente(cliente2);

        Livro livro1 = new Livro("Os tres porquinhos", "Gustavo", false);
        Livro livro2 = new Livro("Lobo mal", "Silvana", false);
        Livro livro3 = new LivroRaro("Formula 1", "Senna");
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        biblioteca.adicionarLivro(livro3);

        int op;

        while (true) {
            System.out.println("\n---- BIBLIOTECA DA MATIEL ----");
            System.out.println("1 - Buscar livro por título");
            System.out.println("2 - Buscar livro por autor");
            System.out.println("3 - Empréstimo");
            System.out.println("4 - Devolução");
            System.out.println("5 - Verificar livro disponivel");
            System.out.println("6 - Sair");
            System.out.println("-----------");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Digite o título do livro: ");
                    String titulo = sc.nextLine();
                    Livro livroPorTitulo = biblioteca.buscarLivroPorTitulo(titulo);
                    if (livroPorTitulo != null) {
                        System.out.println("Livro encontrado: " + livroPorTitulo);
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;

                case 2:
                    System.out.print("Digite o nome do autor: ");
                    String autor = sc.nextLine();
                    Livro livroPorAutor = biblioteca.buscarLivroPorAutor(autor);
                    if (livroPorAutor != null) {
                        System.out.println("Livro encontrado: " + livroPorAutor);
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o título do livro para emprestar: ");
                    String tituloEmprestimo = sc.nextLine();
                    Livro livroEmprestimo = biblioteca.buscarLivroPorTitulo(tituloEmprestimo);
                    if (livroEmprestimo != null && livroEmprestimo.isDisponivel() && !livroEmprestimo.isRaro()) {
                        biblioteca.emprestarLivro(livroEmprestimo);
                    } else {
                        System.out.println("Livro não disponível ou é raro e não pode ser emprestado.");
                    }
                    break;

                case 4:
                    System.out.print("Digite o título do livro para devolução: ");
                    String tituloDevolucao = sc.nextLine();
                    System.out.print("Digite o número de dias de atraso: ");
                    int diasAtraso = sc.nextInt();
                    sc.nextLine();
                    Livro livroDevolucao = biblioteca.buscarLivroPorTitulo(tituloDevolucao);
                    if (livroDevolucao != null && !livroDevolucao.isDisponivel()) {
                        biblioteca.devolverLivro(livroDevolucao, diasAtraso);
                    }
                    break;

                case 5:
                    System.out.print("Digite o título do livro para verificar disponibilidade: ");
                    String tituloDisponibilidade = sc.nextLine();
                    Livro livroDisponibilidade = biblioteca.buscarLivroPorTitulo(tituloDisponibilidade);
                    if (livroDisponibilidade != null) {
                        if (livroDisponibilidade.isDisponivel()) {
                            System.out.println("O livro está disponível para empréstimo.");
                        } else {
                            System.out.println("O livro não está disponível para empréstimo.");
                        }
                    }
                    break;

                case 6:
                    System.out.println("Saindo...");
                    return;

                default:
                    System.out.println("Opção inválida!!");
            }
        }
    }
}