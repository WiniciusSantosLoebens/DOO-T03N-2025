public class Comuns extends Livros {

    public Comuns(String titulo, String autor) {
        super(titulo, autor);
    }

    
    public boolean podeSerEmprestado() {
        return !isEmprestado(); 
    }
}
