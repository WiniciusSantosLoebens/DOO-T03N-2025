import java.util.ArrayList;
import java.util.List;

class Vendedor {
    String nome;
    int idade;
    String loja;
    String cidade;
    String bairro;
    String rua;
    double salarioBase;
    List<Double> salarioRecebido = new ArrayList<>();

    public Vendedor(String nome, int idade, String loja, String cidade, String bairro, String rua, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;

        // adicionando salários fixos
        salarioRecebido.add(2000.0);
        salarioRecebido.add(2200.0);
        salarioRecebido.add(2100.0);
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
    }

    public double calcularMedia() {
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.size();
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}

class Cliente {
    String nome;
    int idade;
    String cidade;
    String bairro;
    String rua;

    public Cliente(String nome, int idade, String cidade, String bairro, String rua) {
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
    }
}

public class Loja {
    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    String cidade;
    String bairro;
    String rua;
    List<Vendedor> vendedores = new ArrayList<>();
    List<Cliente> clientes = new ArrayList<>();

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }

    public void adicionarVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public int contarClientes() {
        return clientes.size();
    }

    public int contarVendedores() {
        return vendedores.size();
    }

    public void apresentarse() {
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereço: " + rua + ", " + bairro + ", " + cidade);
    }

    public static void main(String[] args) {
        Loja loja = new Loja("My Plant", "My Plant LTDA", "00.000.000/0001-00", "Cidade A", "Bairro B", "Rua C");

        Vendedor vendedor = new Vendedor("Gabriel", 28, "My Plant", "Cidade A", "Bairro B", "Rua Vendedor", 2500.0);
        Cliente cliente = new Cliente("Lucia", 35, "Cidade A", "Bairro B", "Rua Cliente");

        loja.adicionarVendedor(vendedor);
        loja.adicionarCliente(cliente);

        System.out.println("\n--- Dados da Loja ---");
        loja.apresentarse();

        System.out.println("\n--- Dados do Vendedor ---");
        vendedor.apresentarse();
        System.out.println("Média Salarial: " + vendedor.calcularMedia());
        System.out.println("Bônus: " + vendedor.calcularBonus());

        System.out.println("\n--- Dados do Cliente ---");
        cliente.apresentarse();

        System.out.println("\nQuantidade de Vendedores: " + loja.contarVendedores());
        System.out.println("Quantidade de Clientes: " + loja.contarClientes());
    }
}
