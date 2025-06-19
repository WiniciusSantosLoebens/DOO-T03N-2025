import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    private static List<Comuns> listComum = new ArrayList<>();
    private static List<Raros> listRaro = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int escolha = 0;
        
        while(escolha != 5) {
            System.out.println("\nMENU\n[1] Cadastrar Livro\n[2] Pesquisar por Titulo\n[3] Pesquisar por Autor\n[4] Pegar Emprestimo\n[5] Sair");
            escolha = sc.nextInt();
            switch(escolha) {
                case 1:
                    System.out.println("\nQual a raridade do livro que vc quer cadastrar:\n[1] Comum\n[2] Raro");
                    int pergunta = sc.nextInt();
                    if(pergunta == 1) {
                        cadastrarComum();
                    } else if(pergunta == 2) {
                        cadastrarRaro();
                    }
                    break;

                case 2:
                    System.out.println("\nQual a raridade do livro que vc quer pesquisar:\n[1] Comum\n[2] Raro");
                    pergunta = sc.nextInt();
                    if(pergunta == 1) {
                        tituloComum();
                    } else {
                        tituloRaro();
                    }
                    break;

                case 3:
                    System.out.println("\nQual a raridade do livro que vc quer pesquisar:\n[1] Comum\n[2] Raro");
                    pergunta = sc.nextInt();
                    if(pergunta == 1) {
                        autorComum();
                    } else {
                        autorRaro();
                    }
                    break;

                case 4:
                    System.out.println("\nQual a raridade do livro que vc quer pegar emprestado:\n[1] Comum\n[2] Raro");
                    pergunta = sc.nextInt();
                    if(pergunta == 1) {
                        emprestarComum();
                    } else {
                        emprestarRaro();
                    }
                    break;

                case 5:
                    System.out.println("me mataste");
                    break;

                default:
                    System.out.println("escolha direito");
            }
        }
        sc.close();
    }

    //cadastro comu
    public static void cadastrarComum() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Título do livro");
        String titulo = sc.nextLine();
        System.out.println("Autor do livro");
        String autor = sc.nextLine();
        
        Comuns comumCriado = new Comuns(titulo, autor);
        listComum.add(comumCriado);
    }
    //cad raro
    public static void cadastrarRaro() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Título do livro:");
        String titulo = sc.nextLine();
        System.out.println("Autor do livro:");
        String autor = sc.nextLine();
        System.out.println("Descrição do livro:");
        String descricao = sc.nextLine();
        
        Raros raroCriado = new Raros(titulo, autor, descricao);
        listRaro.add(raroCriado);
    }

    //pesquisa por título
    public static void tituloComum() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o título do livro para pesquisar: ");
        String nomeDigitado = scanner.nextLine();
        
        for (Comuns comum : listComum) {
            if (nomeDigitado.equalsIgnoreCase(comum.getTitulo())) {
                System.out.println("Livro encontrado: " + comum);
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }

    public static void tituloRaro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o título do livro para pesquisar: ");
        String nomeDigitado = scanner.nextLine();
        
        for (Raros raro : listRaro) {
            if (nomeDigitado.equalsIgnoreCase(raro.getTitulo())) {
                System.out.println("Livro encontrado: " + raro);
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }

    // autor pesquisa com
    public static void autorComum() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do autor para pesquisar: ");
        String nomeDigitado = scanner.nextLine();
        
        for (Comuns comum : listComum) {
            if (nomeDigitado.equalsIgnoreCase(comum.getAutor())) {
                System.out.println("Livro encontrado: " + comum);
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }
    // autor pesquisa raro
    public static void autorRaro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do autor para pesquisar: ");
        String nomeDigitado = scanner.nextLine();
        
        for (Raros raro : listRaro) {
            if (nomeDigitado.equalsIgnoreCase(raro.getAutor())) {
                System.out.println("Livro encontrado: " + raro);
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }

    // emprestimo. Obs: desculpa professor, não sei fazer como devolve
    public static void emprestarComum() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o título do livro para pegar emprestado: ");
        String nomeDigitado = scanner.nextLine();

        for (Comuns comum : listComum) {
            if (nomeDigitado.equalsIgnoreCase(comum.getTitulo())) {
                if (comum.isEmprestado()) {
                    System.out.println("O livro já está emprestado.");
                } else {
                    comum.emprestar();
                    System.out.println("Empréstimo realizado com sucesso!");
                }
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }

    public static void emprestarRaro() {
        System.out.println("Livros raros não podem ser emprestados.");
    }
}
