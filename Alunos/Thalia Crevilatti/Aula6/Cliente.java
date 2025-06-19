// Cliente.java
public class Cliente {
    private String nome;
    private int idade;
    private Endereco endereco;

    public Cliente(String nome, int idade, Endereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Cidade: " + endereco.getCidade());
        System.out.println("Bairro: " + endereco.getBairro());
        System.out.println("Rua: " + endereco.getRua());
    }
}