package objetos;

public class LivroRaro extends Livro {
    private String motivoDeSerRaro;

    public LivroRaro(String titulo, String autor, String motivo) {
        super(titulo, autor);
        this.motivoDeSerRaro = motivo;
        this.disponivel = false;
    }

    @Override
    public boolean podeSerEmprestado() {
        return false;
    }

    public String getMotivoDeSerRaro() {
        return motivoDeSerRaro;
    }
}
