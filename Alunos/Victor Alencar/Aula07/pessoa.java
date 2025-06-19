package kdkak;

public class pessoa {
    protected String nome;
    protected int idade;
    protected endereco endereco;

    public pessoa(String nome, int idade, endereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        endereco.apresentarLogradouro();
    }
}
