import java.util.ArrayList;

public class Livros_raros extends Livros{
    private String explicao;
    public static ArrayList<Livros_raros > Books_raros = new ArrayList<>();

    public Livros_raros(String titulo, String autor, String tipo, int quantidade, String explicao, String disponivel) {
        super(titulo, autor, tipo, quantidade,disponivel);
        this.explicao = explicao;
    }

    public String getExplicao() {
        return explicao;
    }

    public static ArrayList<Livros_raros> getBooks_raros() {
        return Books_raros;
    }

    @Override
    public String toString() {
        super.toString();
        return "Livros_raros{" +
                "explicao='" + explicao + '\'' +
                '}';
    }

    public void AddLivros_raros(Livros_raros r){
        Books_raros.add(r);
    }
    public void Buscar_T_R() {
        System.out.println("Digite o titulo do livro");
        String titul = scanner.nextLine();
        for (Livros cm : Books) {
            if (cm.getTitulo().equalsIgnoreCase(titul)) {
                System.out.println(cm);
            }
        }
    }
    public void Buscar_Autor_() {
        System.out.println("Digite o autor do livro");
        String Autor = scanner.nextLine();
        for (Livros y : Books) {
            if (y.getTitulo().equalsIgnoreCase(Autor)) {
                System.out.println(y);
            }
        }
    }
}
