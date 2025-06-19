public abstract class Livro {
  protected String titulo;
  protected String autor;
  protected boolean disponivel = true;

  public Livro(String titulo, String autor) {
      this.titulo = titulo;
      this.autor = autor;
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

  public abstract boolean podeSerEmprestado();

  public abstract void apresentarse();
}
