package biblioteca.objetos;

// Classe que representa cliente e suas caracteristicas.
// Retornando através de metodos publicos as informações de cada Cliente cadastrado.
public class Cliente {
	
	private String nome;
	private String idCliente;
	
	public Cliente(String nome, String idCliente) {
		super();
		this.nome = nome;
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public String getIdCliente() {
		return idCliente;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", idCliente=" + idCliente + "]";
	}

	

}
