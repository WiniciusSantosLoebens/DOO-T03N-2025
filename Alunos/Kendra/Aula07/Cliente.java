package objetos;

public class Cliente extends Pessoa {

	// Construtor atualizado
    public Cliente(String nome, int idade, Endereco endereco) {
        super(nome, idade, endereco);
    }

    @Override
    public void apresentarse() {
        System.out.println("--------------------");
        System.out.println("Cliente: " + getNome());
        System.out.println("Idade: " + getIdade());
        if (getEndereco() != null) {
            getEndereco().apresentarLogradouro();
        } else {
            System.out.println("Endereço não informado.");
        }
        System.out.println("--------------------");
    }

}