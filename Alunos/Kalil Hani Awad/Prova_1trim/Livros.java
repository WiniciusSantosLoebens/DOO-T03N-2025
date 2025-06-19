import java.util.ArrayList;
import java.util.Scanner;

public class Livros {
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Livros> Books = new ArrayList<>();
    private String titulo;
    private String autor;
    private String tipo;
    private int quantidade;
    private String disponivel;

    public Livros(String titulo, String autor, String tipo, int quantidade, String disponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.disponivel = disponivel;
    }

    public static ArrayList<Livros> getBooks() {
        return Books;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getTipo() {
        return tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Livros{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }


    public void AddLivros_comuns(Livros l) {
        Books.add(l);
    }

    public void Buscar_T() {
        System.out.println("Digite o titulo do livro");
        String titul = scanner.nextLine();
        scanner.nextLine();
        for (Livros cm : Books) {
            if (cm.getTitulo().equalsIgnoreCase(titul)) {
                System.out.println(cm);
            }
        }
    }
    public void Buscar_Autor() {
        System.out.println("Digite o autor do livro");
        String Autor = scanner.nextLine();
        for (Livros y : Books) {
            if (y.getTitulo().equalsIgnoreCase(Autor)) {
                System.out.println(y);
            }
        }
    }
    public void Emprestimo_comuns(){
        System.out.println("Digite o titulo do livro");
        String titul = scanner.nextLine();

        System.out.println("Digite o autor do livro");
        String Autor = scanner.nextLine();

        for (Livros t : Books) {
            if(t.getTitulo().equalsIgnoreCase(titul) && t.getAutor().equalsIgnoreCase(Autor)){
                System.out.println("Livro pego emprestado");
                setDisponivel("Ocupado");
            }
        }
    }
    public void Devoluc_comuns(){
        System.out.println("Digite o titulo do livro");
        String titul = scanner.nextLine();

        System.out.println("Digite o autor do livro");
        String Autor = scanner.nextLine();

        for (Livros t : Books) {
            if(t.getTitulo().equalsIgnoreCase(titul) && t.getAutor().equalsIgnoreCase(Autor)){
                System.out.println("Livro devolvido");
                setDisponivel("Diponivel");
            }
        }
    }



}


/*
Buscar livro por titulo, retornando o primeiro registro encontrado. (ex: Cracking the code interview)
Buscar livro por autor, retornando o primeiro registro encontrado. (ex: Gayle Laakmann)
Realizar empréstimo.
Realizar devolução.
 */