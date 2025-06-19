import java.util.*;

@SuppressWarnings("unused")
public class Cliente {
    private String nome;
    private int idade;
    private String cidade;
    private String bairro;
    private String rua;
    private String cpf;

    public Cliente(String nome, int idade, String cidade, String bairro, String rua, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.cpf = cpf;
    }

    public void apresentarse() {
        System.out.println("Cliente: " + nome + " | Idade: " + idade);
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }
}