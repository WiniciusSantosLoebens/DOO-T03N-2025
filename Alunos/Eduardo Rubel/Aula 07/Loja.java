
import java.util.ArrayList;
import java.util.List;

class Loja {
    private String nomeFantasia, razaoSocial, cnpj;
    private Endereco endereco;
    private List<Vendedor> vendedores = new ArrayList<>();
    private List<Gerente> gerentes = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    public void adicionarVendedor(Vendedor v) { vendedores.add(v); }
    public void adicionarGerente(Gerente g) { gerentes.add(g); }
    public void adicionarCliente(Cliente c) { clientes.add(c); }

    public List<Vendedor> getVendedores() { return vendedores; }
    public List<Cliente> getClientes() { return clientes; }
    public String getNomeFantasia() { return nomeFantasia; }
}
