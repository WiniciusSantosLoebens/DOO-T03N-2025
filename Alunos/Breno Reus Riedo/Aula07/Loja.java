import java.util.List;

public class Loja {

    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private Endereco endereco;
    List<Vendedor> vendedores;
    List<Sujeito> clientes;
    List<Gerente> gerentes;

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco, List<Vendedor> vendedores, List<Sujeito> clientes, List<Gerente> gerentes) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.vendedores = vendedores;
        this.clientes = clientes;
        this.gerentes = gerentes;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public void addVendedor(Vendedor vendedor) {
        this.vendedores.add(vendedor);
    }

    public void contarVendedores(){
        System.out.println("Total de vendedores: " + vendedores.size());
    }

    public List<Sujeito> getClientes() {
        return clientes;
    }

    public void setClientes(List<Sujeito> clientes) {
        this.clientes = clientes;
    }

    public void addCliente(Sujeito cliente) {
        this.clientes.add(cliente);
    }

    public void contarClientes() {
       System.out.println("Total de clientes: " + clientes.size());
    }

    public List<Gerente> getGerentes() {
        return gerentes;
    }

    public void setGerentes(List<Gerente> gerentes) {
        this.gerentes = gerentes;
    }

    public void addGerente(Gerente gerente) {
        this.gerentes.add(gerente);
    }

    public void apresentarSe(){
        System.out.println("Nome fantasia: " + nomeFantasia + " \n" + "CNPJ: " + cnpj);
        endereco.apresentarLogradouro();
    }
}
