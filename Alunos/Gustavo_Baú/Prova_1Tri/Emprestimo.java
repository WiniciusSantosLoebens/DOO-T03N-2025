package Alunos.Gustavo_Baú.Prova_1Tri;

import java.util.Scanner;
import java.util.List;


public class Emprestimo {
    private List<Livros> livros;
    private List<Clientes> clientes;
    private Scanner scanner = new Scanner(System.in);

    public void setLivros(List<Livros> livros) {
        this.livros = livros;
    }

    public void setClientes(List<Clientes> clientes) {
        this.clientes = clientes;
    }

    public void emprestimoLivros() {
        System.out.println("Lista de livros disponíveis:");
        for (Livros livro : livros) {
            if (!livro.isEmprestado()) {
                System.out.println("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor());
            }
        }

        System.out.println("Digite o título do livro que deseja emprestar: ");
        String tituloEmprestimo = scanner.nextLine();
        boolean livroEncontrado = false;

        for (Livros livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(tituloEmprestimo) && !livro.isEmprestado()) {
                livro.setEmprestado(true);
                livroEncontrado = true;
                System.out.println("Livro emprestado com sucesso!");
                break;
            }
        }

        if (!livroEncontrado) {
            System.out.println("Livro não encontrado ou já emprestado.");
        }
    }

    public void Devolver() {
        System.out.println("Digite o título do livro que deseja devolver: ");
        String tituloDevolucao = scanner.nextLine();
        boolean livroEncontrado = false;

        for (Livros livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(tituloDevolucao) && livro.isEmprestado()) {
                livro.setEmprestado(false);
                livroEncontrado = true;
                System.out.println("Livro devolvido com sucesso!");
                break;
            }
        }

        if (!livroEncontrado) {
            System.out.println("Livro não encontrado ou não estava emprestado.");
        }
    }
}