
package objetos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Pedido {
    private int id;
    private Date dataCriacao;
    private Date dataPagamento;
    private Date dataVencimentoReserva;
    private String cliente;
    private String vendedor;
    private String loja;
    private List<Item> itens;

    public Pedido(int id, Date dataCriacao, String cliente, String vendedor, String loja) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.getValor();
        }
        return total;
    }

    public String gerarDescricaoVenda() {
        double valorTotal = calcularValorTotal();
        return "Data de Criação do Pedido: " + dataCriacao + ", Valor Total: " + valorTotal;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getId() {
        return id;
    }
}
