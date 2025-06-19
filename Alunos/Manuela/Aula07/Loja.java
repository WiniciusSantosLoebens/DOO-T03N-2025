package objetos;

import java.util.ArrayList;
import java.util.List;

public class Loja {
	private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private Endereco endereco; // Usando Endereco
    private List<Cliente> clientes;
    private List<Vendedor> vendedores;
    private List<Gerente> gerentes; // Adicionado lista de gerentes

    // Construtor atualizado
    public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.clientes = new ArrayList<>();
        this.vendedores = new ArrayList<>();
        this.gerentes = new ArrayList<>(); 
    }

    public void adicionaCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void adicionaVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

     public void adicionaGerente(Gerente gerente) { // Método para adicionar gerente
        gerentes.add(gerente);
    }

    public int contarClientes() {
        return clientes.size();
    }

    public int contarVendedores() {
        return vendedores.size();
    }

    public int contarGerentes() { // Método para contar gerentes
        return gerentes.size();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public List<Gerente> getGerentes() { // Método para obter gerentes
        return gerentes;
    }


    public void apresentarse() {
         System.out.println("--------------------");
         System.out.println("Loja: " + nomeFantasia);
         System.out.println("Razão Social: " + razaoSocial);
         System.out.println("CNPJ: " + cnpj);
         if (endereco != null) {
            endereco.apresentarLogradouro();
         } else {
             System.out.println("Endereço da loja não informado.");
         }
         System.out.println("--------------------");
    }

    // Getters
    public String getNomeFantasia() { return nomeFantasia; }
    public String getRazaoSocial() { return razaoSocial; }
    public String getCnpj() { return cnpj; }
    public Endereco getEndereco() { return endereco; }
}
