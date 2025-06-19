public class Comum extends Livro {

    public Comum(String titulo, String autor) {
        super(titulo, autor);
    }

    @Override
    public boolean podeSerEmprestado(){
        return true;
    }

    public void descricao(){
    }
}

