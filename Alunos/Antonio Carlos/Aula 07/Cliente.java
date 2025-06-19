package objetosLoja;

// Representa um Cliente, estendendo Pessoa.
public class Cliente extends Pessoa {
	
	// Construtor que chama o construtor da superclasse (Pessoa)
    public Cliente(String nome, int idade, Endereco endereco) {
        super(nome, idade, endereco);
    }
    
    
    // Implementa a apresentação específica para Cliente.
    @Override
    public void apresentarSe() {
        System.out.println("Cliente: " + getNome() + ", Idade: " + getIdade());
        getEndereco().apresentarLogradouro(); // Mostra o endereço
    }

}
