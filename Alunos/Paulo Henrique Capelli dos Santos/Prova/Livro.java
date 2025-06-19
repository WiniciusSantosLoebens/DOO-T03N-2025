public class Livro {
    private String nome;
    private String autor;
    private String tipo;
    private boolean status;

    public Livro(String nome, String autor, String tipo) {
        this.nome = nome;
        this.autor = autor;
        this.tipo = tipo; 
        this.status = true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Livro Nome: " + nome + ", Autor: " + autor + ", Tipo: " + tipo;
    }

      
    
}
