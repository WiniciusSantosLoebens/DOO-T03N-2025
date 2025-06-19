package Alunos.Gustavo_Baú.Prova_1Tri;
import java.util.Scanner;
import java.util.List;


public class LivrosAdicionar {
    private List<Livros> livros;
    private Scanner scanner = new Scanner(System.in);

    public void setLivros(List<Livros> livros) {
        this.livros = livros;
    }

    public void AddLivro() {
        System.out.println("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        System.out.println("Digite o autor do livro: ");
        String autor = scanner.nextLine();
        System.out.println("Digite a raridade do livro: ");
        String raridade = scanner.nextLine();
        
        livros.add(new Livros(titulo, autor, raridade));
        System.out.println("Livro cadastrado com sucesso!");
    }

    public void livrosDisp() {
        System.out.println("Livros disponíveis:");
        for (Livros livro : livros) {
            System.out.println(livro.toString());
        }
    }
}