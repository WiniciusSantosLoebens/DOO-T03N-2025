package objetos;

public class vendedor {
    private String nome;
    private int id;
    private double totalVendas;

    // Construtor
    public vendedor(String nome, int id) {
        this.nome = nome;
        this.id = id;
        this.totalVendas = 0;
    }

    // Métodos para acessar e modificar os atributos
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalVendas() {
        return totalVendas;
    }

    public void adicionarVenda(double valor) {
        this.totalVendas += valor;
    }

    @Override
    public String toString() {
        return "Vendedor: " + nome + ", ID: " + id + ", Total de Vendas: R$ " + totalVendas;
    }
}
