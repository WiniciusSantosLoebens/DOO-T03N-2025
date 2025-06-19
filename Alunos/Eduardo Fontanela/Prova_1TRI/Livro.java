public class Livro {
    private String titulo;
    private String autor;
    private boolean raro;
    private String motivoRaridade;
    private boolean emprestado;

    public Livro(String titulo, String autor, boolean raro, String motivoRaridade){
        this.titulo = titulo;
        this.autor = autor;
        this.raro = raro;
        this.motivoRaridade = motivoRaridade;
        this.emprestado = false;
    }

    public String getTitulo (){
        return titulo;
    }
    public String getAutor (){
        return autor;
    }
    public boolean isRaro (){
        return raro;
    }
    public String getmotivoRaridade (){
        return motivoRaridade;
    }
    public boolean isEmprestado (){
        return emprestado;
    }
    public void emprestar(){
        emprestado = true;
    }
    public void devolver(){
        emprestado = false;
    }
}
