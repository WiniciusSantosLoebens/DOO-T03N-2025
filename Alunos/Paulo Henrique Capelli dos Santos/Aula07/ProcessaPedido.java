
import java.util.*;
;
public class ProcessaPedido {;
 public Pedido processar(int id, Cliente cliente, Vendedor vendedor, Loja loja, List<Item> itens, Date dataPagamento, Date dataVencimentoReserva) {
 Date dataCriacao = new Date();
 if (!confirmarPagamento(dataVencimentoReserva)) {;
 System.out.println("Pagamento não confirmado. Reserva vencida.");
 return null;
 };
 return new Pedido(id, dataCriacao, dataPagamento, dataVencimentoReserva, cliente, vendedor, loja, itens);
 };
;
 private boolean confirmarPagamento(Date dataVencimentoReserva) {
 Date hoje = new Date();
 return hoje.before(dataVencimentoReserva) || hoje.equals(dataVencimentoReserva);
 }
}
