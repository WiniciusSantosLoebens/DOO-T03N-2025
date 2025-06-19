package objetos;

public class vendedor extends pessoa {
    private int id;
    private double totalVendas;

    public vendedor(String nome, endereco endereco, int id) {
        super(nome, endereco);
        this.id = id;
        this.totalVendas = 0;
    }

    public void adicionarVenda(double valor) {
        this.totalVendas += valor;
    }

    public double getTotalVendas() {
        return totalVendas;
    }

    public String toString() {
        return "Vendedor: " + nome + ", ID: " + id + ", Total de Vendas: R$ " + totalVendas;
    }
}
