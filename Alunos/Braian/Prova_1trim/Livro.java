package Alunos.Braian.Prova_1trim;

import java.util.ArrayList;
import java.util.Scanner;

public class Livro {
    public static Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Livro> livros = new ArrayList<>();
    private final String nomeLivro;
    private final String autor;
    private final String genero;
    private final Integer anoPublicacao;
    private boolean disponivel;

    public Livro(String nomeLivro, String autor, String genero, Integer anoPublicacao) {
        this.nomeLivro = nomeLivro;
        this.autor = autor;
        this.genero = genero;
        this.anoPublicacao = anoPublicacao;
        this.disponivel = true; // Inicialmente todos os livros são disponíveis
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public boolean podeSerEmprestado() {
        return true; // Método padrão para livros comuns, que pode ser sobrescrito pelas subclasses
    }

    @Override
    public String toString() {
        return "Livro: " + nomeLivro + " | Autor: " + autor + " | Ano: " + anoPublicacao + " | Disponibilidade: " + (disponivel ? "Disponível" : "Indisponível");
    }
    public static void cadastrarLivro() {

        System.out.print("Digite o nome do livro: ");
        String nomeLivro = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();
        System.out.print("Digite o gênero do livro: ");
        String genero = scanner.nextLine();
        System.out.print("Digite o ano de publicação do livro: ");
        int ano = Integer.parseInt(scanner.nextLine());

        System.out.print("Esse livro é raro? (s/n): ");
        String resposta = scanner.nextLine();

        Livro livro;
        if (resposta.equalsIgnoreCase("s")) {
            System.out.print("Digite a explicação da raridade do livro: ");
            String explicacao = scanner.nextLine();
            livro = new LivroRaro(nomeLivro, autor, genero, ano, explicacao);
        } else {
            livro = new LivroComum(nomeLivro, autor, genero, ano);
        }
        livros.add(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    public static void buscarPorTitulo() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();

        for (Livro livro : livros) {
            if (livro.getNomeLivro().equalsIgnoreCase(titulo)) {
                System.out.println(livro);
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }

    public static void buscarPorAutor() {
        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();

        for (Livro livro : livros) {
            if (livro.getAutor().equalsIgnoreCase(autor)) {
                System.out.println(livro);
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }

    public static void emprestarLivro() {
        System.out.print("Digite o nome do livro que deseja emprestar: ");
        String titulo = scanner.nextLine();

        for (Livro livro : livros) {
            if (livro.getNomeLivro().equalsIgnoreCase(titulo)) {
                if (livro.podeSerEmprestado()) {
                    livro.setDisponivel(false);
                    System.out.println("Empréstimo realizado com sucesso!");
                    return;
                } else {
                    System.out.println("Este livro não pode ser emprestado.");
                    return;
                }
            }
        }
        System.out.println("Livro não encontrado.");
    }

    public static void devolverLivro() {
        System.out.print("Digite o nome do livro que deseja devolver: ");
        String titulo = scanner.nextLine();

        for (Livro livro : livros) {
            if (livro.getNomeLivro().equalsIgnoreCase(titulo)) {
                if (!livro.isDisponivel()) {
                    System.out.print("Quantos dias de atraso? ");
                    int diasAtraso = Integer.parseInt(scanner.nextLine());
                    if (diasAtraso > 7) {
                        double multa = (diasAtraso - 7) * 3.50;
                        System.out.println("Multa de R$ " + multa + " gerada.");
                    }
                    livro.setDisponivel(true);
                    System.out.println("Devolução realizada com sucesso!");
                    return;
                } else {
                    System.out.println("Este livro não foi emprestado.");
                    return;
                }
            }
        }
        System.out.println("Livro não encontrado.");
    }

    public static void verificarDisponibilidade() {
        System.out.print("Digite o nome do livro: ");
        String titulo = scanner.nextLine();

        for (Livro livro : livros) {
            if (livro.getNomeLivro().equalsIgnoreCase(titulo)) {
                System.out.println(livro.isDisponivel() ? "Livro disponível." : "Livro não disponível.");
                return;
            }
        }
        System.out.println("Livro não encontrado.");
    }
}
