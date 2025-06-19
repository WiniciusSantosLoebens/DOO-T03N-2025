
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Livro {
    private static int CONTADOR = 0;

    private final int id;

    private String titulo;

    private String descricao;

    private Autor autor;

    private TipoLivro tipo;

    private Emprestimo emprestimoAtual;

    public Livro(String titulo, String descricao, Autor autor, TipoLivro tipo) {
        id = CONTADOR++;
        this.titulo = titulo;
        this.descricao = descricao;
        this.autor = autor;
        this.tipo = tipo;
    }

    // Ações
    public boolean emprestar(Cliente cliente) {
        if (podeEmprestar()) {
            emprestimoAtual = new Emprestimo(cliente);
            return true; // livro emprestado com sucesso
        }
        return false; // livro não emprestado
    }

    // retorna o valor da multa, caso houver
    public Double devolver() {
        if (emprestimoAtual == null) {
            System.out.println("Livro não tem empréstimo cadastrado");
            return null;
        }
        long diasDeDiferenca = ChronoUnit.DAYS.between(emprestimoAtual.getDataEmprestimo(), LocalDate.now());

        emprestimoAtual.setAtivo(false);
        emprestimoAtual.setEmprestador(null);
        emprestimoAtual = null;

        if (diasDeDiferenca > 7) {
            return (diasDeDiferenca - 7) * 3.5;
        } else {
            return 0.0;
        }
    }

    // Outros atributos
    public boolean podeEmprestar() {
        return tipo.isEmprestavel() && emprestimoAtual == null;
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public TipoLivro getTipo() {
        return tipo;
    }

    public void setTipo(TipoLivro tipo) {
        this.tipo = tipo;
    }

    public Emprestimo getEmprestimoAtual() {
        return emprestimoAtual;
    }

    public void setEmprestimoAtual(Emprestimo emprestimoAtual) {
        this.emprestimoAtual = emprestimoAtual;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Título: %s%nDescrição: %s%nAutor: %s%n", titulo, descricao, autor.getNome());
    }
}
