import java.time.LocalDate;

public class ProcessaPedido {
    public Pedido criarPedido(LocalDate dataCriacao, LocalDate dataPagamento, LocalDate dataVencimentoReserva, Cliente cliente, Vendedor vendedor, Loja loja, Item[] itens) {
        return new Pedido(dataCriacao, dataPagamento, dataVencimentoReserva, cliente, vendedor, loja, itens);
    }

    public boolean confirmarPagamento(Pedido pedido) {
        return !pedido.getDataPagamento().isAfter(pedido.getDataVencimentoReserva());
    }
}