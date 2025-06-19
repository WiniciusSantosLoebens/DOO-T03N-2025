import java.util.Date;
import java.util.List;

public class ProcessaPedido {
    public Pedido processar(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva, Cliente cliente, Vendedor vendedor, String loja, List<Item> itens) {
        if (confirmarPagamento(dataVencimentoReserva)) {
            return new Pedido(id, dataCriacao, dataPagamento, dataVencimentoReserva, cliente, vendedor, loja, itens);
        } else {
            System.out.println("Pagamento recusado: Reserva vencida.");
            return null;
        }
    }

    private boolean confirmarPagamento(Date dataVencimentoReserva) {
        return new Date().before(dataVencimentoReserva);
    }
}