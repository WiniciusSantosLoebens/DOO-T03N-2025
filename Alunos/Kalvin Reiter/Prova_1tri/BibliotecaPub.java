
import java.util.*;

class Cliente {
    String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Nome = " + nome;
    }
}

class Livro {
    String titulo;
    String autor;
    boolean emprestado;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.emprestado = false;
    }

    public boolean isDisponivel() {
        return !emprestado;
    }
}

class LivroComum extends Livro {
    public LivroComum(String titulo, String autor) {
        super(titulo, autor);
    }
}

class LivroRaro extends Livro {
    String descricaoRaridade;

    public LivroRaro(String titulo, String autor, String descricaoRaridade) {
        super(titulo, autor);
        this.descricaoRaridade = descricaoRaridade;
    }

    @Override
    public boolean isDisponivel() {
        return false; // Não pode ser emprestado
    }
}

class Biblioteca {
    List<Cliente> clientes = new ArrayList<>();
    List<Livro> livros = new ArrayList<>();
    Map<Livro, Date> emprestimos = new HashMap<>();

    public void cadastrarCliente(String nome) {
        clientes.add(new Cliente(nome));
    }

    public void cadastraLivro(Livro livro) {
        livros.add(livro);
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        return livros.stream().filter(l -> l.titulo.equalsIgnoreCase(titulo)).findFirst().orElse(null);
    }

    public Livro buscarLivroPorAutor(String autor) {
        return livros.stream().filter(l -> l.autor.equalsIgnoreCase(autor)).findFirst().orElse(null);
    }

    public boolean emprestarLivro(Livro livro) {
        if (livro.isDisponivel()) {
            livro.emprestado = true;
            emprestimos.put(livro, new Date());
            return true;
        }
        return false;
    }

    public double devolverLivro(Livro livro) {
        Date dataEmprestimo = emprestimos.get(livro);

        if (dataEmprestimo != null) {
            long diasEmprestado = (new Date().getTime() - dataEmprestimo.getTime()) / (1000 * 60 * 60 * 24);
            livro.emprestado = false;
            emprestimos.remove(livro);

            if (diasEmprestado > 7) {
                return (diasEmprestado - 7) * 3.50;
            }
        }

        return 0.0;
    }
}

public class BibliotecaPub {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        biblioteca.cadastrarCliente("Matilde");
        biblioteca.cadastraLivro(new LivroComum("Habitos Atomicos", "James Clear"));
        biblioteca.cadastraLivro(new LivroRaro("Livro Antigo", "Autor Desconhecido", "Primeira edição impressa há séculos"));

        while (true) {
            System.out.println("\n=== BibliotecaPub ===");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Cadastrar livro");
            System.out.println("3. Buscar por título");
            System.out.println("4. Buscar por autor");
            System.out.println("5. Realizar empréstimo");
            System.out.println("6. Realizar devolução");
            System.out.println("7. Visualizar Livros Disponíveis");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do cliente: ");
                    String nome = scanner.nextLine();
                    biblioteca.cadastrarCliente(nome);
                    System.out.println("Cliente cadastrado: " + nome);
                    break;

                case 2:
                    System.out.print("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite o autor do livro: ");
                    String autor = scanner.nextLine();
                    System.out.print("Digite a raridade do livro (1 - Raro, 2 - Comum): ");
                    int tipoLivro = scanner.nextInt();
                    scanner.nextLine(); // consumir quebra de linha

                    Livro novoLivro;
                    if (tipoLivro == 1) {
                        System.out.print("Descrição da raridade: ");
                        String raridade = scanner.nextLine();
                        novoLivro = new LivroRaro(titulo, autor, raridade);
                    } else {
                        novoLivro = new LivroComum(titulo, autor);
                    }

                    biblioteca.cadastraLivro(novoLivro);
                    System.out.println("Livro cadastrado: " + titulo);
                    break;

                case 3:
                    System.out.print("Digite o título do livro: ");
                    titulo = scanner.nextLine();
                    Livro livroPorTitulo = biblioteca.buscarLivroPorTitulo(titulo);
                    System.out.println(livroPorTitulo != null ? "Livro encontrado: " + livroPorTitulo.titulo : "Livro não encontrado.");
                    break;

                case 4:
                    System.out.print("Digite o autor do livro: ");
                    autor = scanner.nextLine();
                    Livro livroPorAutor = biblioteca.buscarLivroPorAutor(autor);
                    System.out.println(livroPorAutor != null ? "Livro encontrado: " + livroPorAutor.titulo : "Livro não encontrado.");
                    break;

                case 5:
                    System.out.print("Digite o título do livro para empréstimo: ");
                    String tituloEmprestimo = scanner.nextLine();
                    Livro livroEmprestar = biblioteca.buscarLivroPorTitulo(tituloEmprestimo);
                    if (livroEmprestar != null && biblioteca.emprestarLivro(livroEmprestar)) {
                        System.out.println("Empréstimo realizado!");
                    } else {
                        System.out.println("Não foi possível emprestar este livro.");
                    }
                    break;

                case 6:
                    System.out.print("Digite o título do livro para devolução: ");
                    String tituloDevolucao = scanner.nextLine();
                    Livro livroDevolver = biblioteca.buscarLivroPorTitulo(tituloDevolucao);
                    if (livroDevolver != null) {
                        double multa = biblioteca.devolverLivro(livroDevolver);
                        System.out.println("Devolução concluída. Multa: R$ " + multa);
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;

                case 7:
                    System.out.println("\n=== Livros Disponíveis ===");
                    for (Livro livro : biblioteca.livros) {
                        if (livro.isDisponivel()) {
                            System.out.println("- " + livro.titulo + " | Autor: " + livro.autor);
                        }
                    }
                    break;

                case 8:
                    System.out.println("Encerrando sistema...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}



