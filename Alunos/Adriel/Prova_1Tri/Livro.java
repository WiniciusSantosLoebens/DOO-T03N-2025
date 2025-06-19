public abstract class Livro implements Emprestavel {
    protected String titulo;
    protected String autor;
    protected boolean disponivel;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
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

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Livro other = (Livro) obj;
        return titulo.equalsIgnoreCase(other.titulo) && 
               autor.equalsIgnoreCase(other.autor);
    }

    @Override
    public int hashCode() {
        return titulo.toLowerCase().hashCode() + autor.toLowerCase().hashCode();
    }
}
