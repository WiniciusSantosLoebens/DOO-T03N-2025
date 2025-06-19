// classe loja

//public class Loja {
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String cidade;
    private String bairro;
    private String rua;
    
    private Vendedor[] vendedores;
    private Cliente[] clientes;

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua,
                Vendedor[] vendedores, Cliente[] clientes) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.vendedores = vendedores;
        this.clientes = clientes;
    }

    public void apresentarse() {
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("Razão Social: " + razaoSocial);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereço: " + rua + ", " + bairro + ", " + cidade);
    }

    public int contarClientes() {
        int total = clientes != null ? clientes.length : 0;
        System.out.println("Quantidade de clientes: " + total);
        return total;
    }

    public int contarVendedores() {
        int total = vendedores != null ? vendedores.length : 0;
        System.out.println("Quantidade de vendedores: " + total);
        return total;
    }

    public static void main(String[] args) {
        // Criando clientes e vendedores simples
        Cliente cliente1 = new Cliente("Maria", 30, "São Paulo", "Centro", "Rua A");
        Cliente cliente2 = new Cliente("João", 28, "São Paulo", "Bela Vista", "Rua B");

        Vendedor vendedor1 = new Vendedor("Carlos", 35, "Loja Central", "São Paulo", "Centro", "Rua A", 2500, new int[]{2500, 2600, 2700});
        Vendedor vendedor2 = new Vendedor("Julia", 29, "Loja Central", "São Paulo", "Centro", "Rua A", 2600, new int[]{2600, 2700, 2800});

        Loja loja = new Loja(
            "MyPlant",
            "MyPlant Flores Ltda",
            "12.345.678/0001-99",
            "São Paulo",
            "Centro",
            "Av. Principal",
            new Vendedor[]{vendedor1, vendedor2},
            new Cliente[]{cliente1, cliente2}
        );

        loja.apresentarse();
        loja.contarClientes();
        loja.contarVendedores();
    }
}
