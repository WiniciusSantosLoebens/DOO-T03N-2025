public class Loja {
    private String nomeFantasia;
    private String razaoSocial;
    private int cnpj;
    private String cidade;
    private String bairro;
    private String rua;
    private Vendedor[] vendedor;
    private Cliente[] cliente;

    public Loja(String nomeFantasia, String razaoSocial, int cnpj, String cidade, String bairro, String rua) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }

    // NOVOS MÉTODOS
    public void setVendedores(Vendedor[] vendedores) {
        this.vendedor = vendedores;
    }

    public void setClientes(Cliente[] clientes) {
        this.cliente = clientes;
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

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int contarClientes() {
        return cliente != null ? cliente.length : 0;
    }

    public int contarVendedores() {
        return vendedor != null ? vendedor.length : 0;
    }

    public void apresentarse() {
        System.out.println("Loja: " + nomeFantasia + " | CNPJ: " + cnpj + " | Endereço: " + rua + ", " + bairro + ", " + cidade);
    }
}
