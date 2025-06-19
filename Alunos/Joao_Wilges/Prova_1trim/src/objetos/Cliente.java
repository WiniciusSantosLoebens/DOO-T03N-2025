package objetos;

public class Cliente {
    private String nome;
    private int anoNascimento;
    private int cpf;
    private int rg;

    public Cliente(String nome, int anoNascimento, int cpf, int rg) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.cpf = cpf;
        this.rg = rg;
    }

    public int getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }
}