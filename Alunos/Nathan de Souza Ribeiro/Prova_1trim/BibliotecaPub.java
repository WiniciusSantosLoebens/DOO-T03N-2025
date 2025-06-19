import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BibliotecaPub {
    static List<String> livros = new ArrayList<>();
    static List<String> autores = new ArrayList<>();
    static List<String> clientes = new ArrayList<>();
    static List<Boolean> livroDisponivel = new ArrayList<>();
    static List<String> emprestimos = new ArrayList<>();
    static List<LocalDate> datasEmprestimo = new ArrayList<>();
    static List<String> clientesEmprestimos = new ArrayList<>();
    static List<String> raridadeLivros = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("=== Biblioteca Pub da Matilde ===");
            System.out.println("[1] - Novo Cliente");
            System.out.println("[2] - Novo Livro");
            System.out.println("[3] - Buscar Livro Por Nome");
            System.out.println("[4] - Buscar livro Por Autor");
            System.out.println("[5] - Fazer Empréstimo");
            System.out.println("[6] - Fazer Devolução");
            System.out.println("[7] - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    NovoCliente(scanner);
                    break;
                case 2:
                    NovoLivro(scanner);
                    break;
                case 3:
                    BuscarLivroPorNome(scanner);
                    break;
                case 4:
                    BuscarLivroPorAutor(scanner);
                    break;
                case 5:
                    FazerEmpréstimo(scanner);
                    break;
                case 6:
                    FazerDevolucao(scanner);
                    break;
                case 7:
                    System.out.println("Muito obrigado por nos visitar, volte sempre!");
                    break;
                default:
                    System.out.println("Opa, Ocorreu um erro, tente novamente!");
            }
        } while (opcao != 7);

        scanner.close();
    }

    public static void NovoCliente(Scanner scanner) {
        System.out.print("Insira Seu Nome: ");
        String nomeCliente = scanner.nextLine();
        clientes.add(nomeCliente);
        System.out.println("Cliente " + nomeCliente + " cadastrado!");
    }

    public static void NovoLivro(Scanner scanner) {
        System.out.print("Nome do livro: ");
        String nomeLivro = scanner.nextLine();
    
        System.out.print("Nome do autor: ");
        String autorLivro = scanner.nextLine();
    
        livros.add(nomeLivro);
        autores.add(autorLivro);
        livroDisponivel.add(true);
    
        System.out.print("Seu livro é raro ou comum? ");
        String raridade = scanner.nextLine();
    
        raridadeLivros.add(raridade);
    
        if (raridade.equalsIgnoreCase("raro")) {
            System.out.println("Parabéns, seu livro é raro!");
        } else {
            System.out.println("Ok, seu livro é comum.");
        }
    }

    public static void BuscarLivroPorNome(Scanner scanner) {
        System.out.print("Insira o nome do livro: ");
        String nomeLivro = scanner.nextLine();
        
        boolean encontrado = false;
        for (String livro : livros) {
            if (livro.equalsIgnoreCase(nomeLivro)) {
                System.out.println("Livro encontrado: " + livro);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Livro não encontrado.");
        }
    }

    public static void BuscarLivroPorAutor(Scanner scanner) {
        System.out.print("Insira o nome do autor: ");
        String nomeAutor = scanner.nextLine();
        
        boolean encontrado = false;
        for (int i = 0; i < autores.size(); i++) {
            if (autores.get(i).equalsIgnoreCase(nomeAutor)) {
                System.out.println("Livro do autor " + nomeAutor + ": " + livros.get(i));
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Autor não encontrado.");
        }
    }

    public static void FazerEmpréstimo(Scanner scanner) {
        System.out.print("Deseja fazer o empréstimo no nome de quem? ");
        String nomeCliente = scanner.nextLine();
    
        if (!clientes.contains(nomeCliente)) {
            System.out.println("Cliente não cadastrado. Por favor, cadastre-se primeiro.");
            return;
        }
    
        System.out.print("Insira o nome do livro para emprestar: ");
        String nomeLivro = scanner.nextLine();
    
        int indiceLivro = -1;
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).equalsIgnoreCase(nomeLivro)) {
                indiceLivro = i;
                break;
            }
        }
    
        if (indiceLivro == -1) {
            System.out.println("Livro não encontrado.");
            return;
        }
    
        if (!livroDisponivel.get(indiceLivro)) {
            System.out.println("Este livro já está emprestado.");
        } else {
            if (raridadeLivros.get(indiceLivro).equalsIgnoreCase("raro")) {
                System.out.println("Este livro é raro e por ordem do autor não pode ser emprestado.");
            } else {
                livroDisponivel.set(indiceLivro, false);
                emprestimos.add("Cliente: " + nomeCliente + " - Livro: " + nomeLivro);
                clientesEmprestimos.add(nomeCliente);
                datasEmprestimo.add(LocalDate.now());
                System.out.println("Empréstimo realizado com sucesso!");
            }
        }
    }

    public static void FazerDevolucao(Scanner scanner) {
        System.out.print("Digite seu nome para a devolução: ");
        String nomeCliente = scanner.nextLine();

        if (!clientes.contains(nomeCliente)) {
            System.out.println("Cliente não cadastrado. Por favor, cadastre-se primeiro.");
            return;
        }

        System.out.print("Insira o nome do livro a ser devolvido: ");
        String nomeLivro = scanner.nextLine();

        int indiceLivro = -1;
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).equalsIgnoreCase(nomeLivro)) {
                indiceLivro = i;
                break;
            }
        }

        if (indiceLivro == -1) {
            System.out.println("Livro não encontrado.");
            return;
        }

        if (livroDisponivel.get(indiceLivro)) {
            System.out.println("Este livro não foi emprestado.");
        } else {
            if (!clientesEmprestimos.get(indiceLivro).equals(nomeCliente)) {
                System.out.println("Este livro não foi emprestado para você.");
                return;
            }

            LocalDate dataDevolucao = LocalDate.now();
            long diasAtraso = ChronoUnit.DAYS.between(datasEmprestimo.get(indiceLivro), dataDevolucao);

            if (diasAtraso > 7) {
                double multa = 3.50 * (diasAtraso - 7);
                System.out.println("Você está " + diasAtraso + " dias atrasado. A multa é: R$ " + multa);
            } else if (diasAtraso <= 0) {
                System.out.println("Você devolveu o livro na data correta ou antes. Sem multa!");
            } else {
                System.out.println("Devolução realizada com sucesso, sem multa!");
            }

            livroDisponivel.set(indiceLivro, true);
        }
    }
}