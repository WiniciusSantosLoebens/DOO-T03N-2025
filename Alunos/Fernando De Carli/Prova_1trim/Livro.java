public class Livro {
    private String titulo;
    private String autor;
    private boolean disponivel;
    private boolean raro;


    public Livro(String titulo, String autor, boolean raro) {
        this.titulo = titulo;
        this.autor = autor;
        this.raro = raro;
        this.disponivel = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void emprestar() {
        if (disponivel && !raro) {
            disponivel = false;
        }
    }

    public void devolver() {
        disponivel = true;
    }

    public boolean isRaro() {
        return raro;
    }


    @Override
    public String toString() {
        return "Título: " + titulo + " | Autor: " + autor + (raro ? " | Raro" : " | Comum");
    }
}