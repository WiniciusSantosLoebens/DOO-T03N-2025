// CadastroCliente.java
public class CadastroCliente {
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private int idade;

    public CadastroCliente(String nome, String endereco, String telefone, String email, int idade) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.idade = idade;
    }

    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Endereço: " + endereco);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
        System.out.println("Idade: " + idade);
    }

    public String getNome() {
        return nome;
    }
}