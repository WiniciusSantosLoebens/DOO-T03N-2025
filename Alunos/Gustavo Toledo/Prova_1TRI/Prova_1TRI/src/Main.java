import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Cadastrar livro comum");
            System.out.println("3. Cadastrar livro raro");
            System.out.println("4. Realizar empréstimo");
            System.out.println("5. Realizar devolução");
            System.out.println("6. Verificar disponibilidade de livro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("Nome do cliente: ");
                    String nome = scanner.nextLine();
                    System.out.print("ID do cliente: ");
                    String id = scanner.nextLine();
                    biblioteca.cadastrarCliente(nome, id);
                    System.out.println("Cliente cadastrado com sucesso.");
                    break;

                case 2:
                    biblioteca.mostrarLivros();
                    System.out.print("Título do livro comum: ");
                    String tituloComum = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autorComum = scanner.nextLine();
                    biblioteca.cadastrarLivroComum(tituloComum, autorComum);
                    break;

                case 3:
                    biblioteca.mostrarLivros();
                    System.out.print("Título do livro raro: ");
                    String tituloRaro = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autorRaro = scanner.nextLine();
                    System.out.print("Motivo da raridade: ");
                    String motivo = scanner.nextLine();
                    biblioteca.cadastrarLivroRaro(tituloRaro, autorRaro, motivo);
                    break;

                case 4:
                    biblioteca.mostrarLivros();
                    System.out.print("Número do livro para empréstimo (ou 0 para voltar): ");
                    int indexEmp = Integer.parseInt(scanner.nextLine()) - 1;
                    if (indexEmp == -1) {
                        break;
                    }
                    System.out.print("ID do cliente: ");
                    String idEmp = scanner.nextLine();
                    System.out.print("Data do empréstimo (dd/MM/yyyy): ");
                    String dataEmprestimo = scanner.nextLine();
                    if (biblioteca.realizarEmprestimo(indexEmp, idEmp, dataEmprestimo)) {
                        System.out.println("Empréstimo realizado com sucesso.");
                    } else {
                        System.out.println("Não foi possível realizar o empréstimo.");
                    }
                    break;

                case 5:
                    biblioteca.mostrarLivros();
                    System.out.print("Número do livro para devolução (ou 0 para voltar): ");
                    int indexDev = Integer.parseInt(scanner.nextLine()) - 1;
                    if (indexDev == -1) {
                        break; 
                    }
                    System.out.print("Data de devolução (dd/MM/yyyy): ");
                    String dataDevolucao = scanner.nextLine();
                    double multa = biblioteca.realizarDevolucao(indexDev, dataDevolucao);
                    if (multa >= 0) {
                        System.out.printf("Devolução realizada. Multa: R$ %.2f\n", multa);
                    } else {
                        System.out.println("Este livro não estava emprestado.");
                    }
                    break;

                case 6:
                    biblioteca.mostrarLivros();
                    System.out.print("Número do livro para verificar disponibilidade (ou 0 para voltar): ");
                    int indexVer = Integer.parseInt(scanner.nextLine()) - 1;
                    if (indexVer == -1) {
                        break;
                    }
                    if (biblioteca.verificarDisponibilidade(indexVer)) {
                        System.out.println("O livro está disponível para empréstimo.");
                    } else {
                        System.out.println("O livro não está disponível para empréstimo.");
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
