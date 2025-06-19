package atividadeAula06Objetos;
import java.util.ArrayList;
import java.util.List;

public class Loja {

	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private String cidade;
	private String bairro;
	private String rua;
	private List <Vendedor> listaVendedores = new ArrayList<>();
	private List <Cliente> listaClientes = new ArrayList<>();
	
	public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
		super();
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
	}
	
	public int contarClientes() {
		
		return listaClientes.size;
	}
	
	public int contarVendedores() {
		
		return listaVendedores.size();
	}
	
	public void apresentarse() {
		
		System.out.println("Nome Fantasia: "+ nomeFantasia);
		System.out.println("Cnpj: "+ cnpj);
		System.out.println("Endereço: "+ rua +", "+ bairro +", "+ cidade +".");
	}
	
	public void adicionarClientes(Cliente cliente) {
		
		listaClientes.add(cliente);
	}
	
	public void adicionarVendedor(Vendedor vendedor) {
		
		listaVendedores.add(vendedor);
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public List<Vendedor> getVendedores() {
        return listaVendedores;
    }

    public List<Cliente> getClientes() {
        return listaClientes;
    }
}
