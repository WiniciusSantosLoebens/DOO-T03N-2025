// Cliente.java - 
public class Cliente extends CadastroCliente {
    private String cpf;

    public Cliente(String nome, String endereco, String telefone, String email, int idade, String cpf) {
        super(nome, endereco, telefone, email, idade);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("CPF: " + cpf);
    }
}
