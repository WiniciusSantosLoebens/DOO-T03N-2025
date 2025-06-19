import java.time.LocalDate;

public class ProcessarPedido {

    public static Pedido processar(int id, LocalDate dataAtual, LocalDate dataVencimento, Sujeito clienteGael, Vendedor vendedorBrenno, Loja lojaMyPlant) {
        Pedido pedido = new Pedido(id, dataAtual, dataAtual, dataVencimento, clienteGael, vendedorBrenno, lojaMyPlant);
        confirmarPagamento(pedido);
        return pedido;
    }

    private static void confirmarPagamento(Pedido pedido) {
        if(pedido.getDataVencimentoReserva().isBefore(LocalDate.now())) {
            System.out.println("Data vencida!");
        }
    }
}
