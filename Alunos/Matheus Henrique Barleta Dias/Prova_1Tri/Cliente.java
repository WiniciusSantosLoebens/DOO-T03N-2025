import java.util.List;

public class Cliente {
    private String nome;
    private int cpf;
    private List<Livro> emprestado;

    public Cliente(String nome, int cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String toString(){
        return "Nome:" + nome + "\nCPF: " + cpf;
    }

    public void emprestarLivro(Livro livro){
        emprestado.add(livro);
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

}
