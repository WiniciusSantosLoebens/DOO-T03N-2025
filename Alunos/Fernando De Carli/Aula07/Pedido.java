import java.util.Date;
import java.util.List;

public class Pedido {
     int id;
     Date dataCriacao, dataPagamento, dataVencimentoReserva;
     Cliente cliente;
     Vendedor vendedor;
     String loja;
     List<Item> itens;

    public Pedido(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva, Cliente cliente, Vendedor vendedor, String loja, List<Item> itens) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;
    }

    public double calcularValorTotal() {
        return itens.stream().mapToDouble(Item::getValor).sum();
    }

    public void gerarDescricaoVenda() {
        System.out.println("Data do Pedido: " + dataCriacao + ", Valor Total: R$" + calcularValorTotal());
    }

}