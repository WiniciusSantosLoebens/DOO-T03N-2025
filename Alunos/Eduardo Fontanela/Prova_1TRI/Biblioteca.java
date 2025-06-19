import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();

    public void cadastrarCliente(String nome) {
        clientes.add(new Cliente(nome));
    }

    public void cadastrarLivro(String titulo, String autor, boolean raro, String motivoRaridade) {
        livros.add(new Livro(titulo, autor, raro, motivoRaridade));
    }

    public Livro buscarPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public Livro buscarPorAutor(String autor) {
        for (Livro livro : livros) {
            if (livro.getAutor().equalsIgnoreCase(autor)) {
                return livro;
            }
        }
        return null;
    }

    public boolean emprestarLivro(String titulo){
        Livro livro = buscarPorTitulo(titulo);
        if (livro != null && !livro.isRaro() && !livro.isEmprestado()) {
            livro.emprestar();
            return true;
        }
        return false;
    }

    public boolean devolverLivro(String titulo) {
        Livro livro = buscarPorTitulo(titulo);
        if (livro != null && livro.isEmprestado()) {
            livro.devolver();
            return true;
        }
        return false;
    }

    public boolean estaDisponivel(String titulo) {
        Livro livro = buscarPorTitulo(titulo);
        return livro != null && !livro.isEmprestado();
    }
}
