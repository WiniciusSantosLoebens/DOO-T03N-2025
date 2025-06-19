package objetos;

public abstract class Pessoa extends Endereco {
    private String nome;
    private int idade;
    public Pessoa(String nome, int idade, String cidade, String rua, String bairro, String complemento, String numero) {
        super(cidade, rua, bairro, complemento, numero);

        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return this.nome;
    }

    public int getIdade(){
        return this.idade;
    }
}