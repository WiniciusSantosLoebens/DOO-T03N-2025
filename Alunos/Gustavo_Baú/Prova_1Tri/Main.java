package Alunos.Gustavo_Baú.Prova_1Tri;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean condition = true;

        ArrayList<Clientes> clientes = new ArrayList<>();
        ArrayList<Livros> livros = new ArrayList<>();

        livros.add(new Livros("Cracking the coding interview", "Gayle Laakmann McDowell", "Normal"));
        livros.add(new Livros("Códice de Leicester", "Leonardo da Vinci", "Raro"));

        while (condition) {
            System.out.println("\n1-Cadastrar Cliente");
            System.out.println("2-Cadastrar Livro");
            System.out.println("3-Empréstimo");
            System.out.println("4-Devolução");
            System.out.println("5-Verificar livros disponíveis");
            System.out.println("6-Sair");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    ClientesAdicionar clientesAdicionar = new ClientesAdicionar();
                    clientesAdicionar.clientesadicionar();
                    break;
                case 2:
                    LivrosAdicionar livrosAdicionar = new LivrosAdicionar();
                    livrosAdicionar.setLivros(livros);
                    livrosAdicionar.AddLivro();
                    break;
                case 3:
                    Emprestimo emp = new Emprestimo();
                    emp.setLivros(livros);
                    emp.setClientes(clientes);
                    emp.emprestimoLivros();
                    break;
                case 4:
                    Emprestimo dev = new Emprestimo();
                    dev.setLivros(livros);
                    dev.Devolver();
                    break;
                case 5:
                    LivrosAdicionar livrosDisp = new LivrosAdicionar();
                    livrosDisp.setLivros(livros);
                    livrosDisp.livrosDisp();
                    break;
                case 6:
                    condition = false;
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
