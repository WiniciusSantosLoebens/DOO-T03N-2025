package objetosLoja;

import java.util.ArrayList;
import java.util.List;

// Representa a Loja, agora usando Endereco e gerenciando Gerentes também.
public class Loja {
	
	private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private Endereco endereco; // Usa a classe Endereco
    private List<Vendedor> vendedores;
    private List<Cliente> clientes;
    private List<Gerente> gerentes;
    
    public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.vendedores = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.gerentes = new ArrayList<>();
    }

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public List<Vendedor> getVendedores() {
		return vendedores;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public List<Gerente> getGerentes() {
		return gerentes;
	}
        
	// Métodos para adicionar
    public void adicionarVendedor(Vendedor vendedor) { this.vendedores.add(vendedor); }
    public void adicionarCliente(Cliente cliente) { this.clientes.add(cliente); }
    public void adicionarGerente(Gerente gerente) { this.gerentes.add(gerente); }

    // Métodos de contagem
    public int contarClientes() { return clientes.size(); }
    public int contarVendedores() { return vendedores.size(); }
    public int contarGerentes() { return gerentes.size(); }

    
     // Apresenta os dados da loja.
    public void apresentarSe() {
        System.out.println("\n--- Loja: " + nomeFantasia + " ---");
        System.out.println("Razão Social: " + razaoSocial);
        System.out.println("CNPJ: " + cnpj);
        endereco.apresentarLogradouro();
        System.out.println("-----------------------------");
    }
   
}
