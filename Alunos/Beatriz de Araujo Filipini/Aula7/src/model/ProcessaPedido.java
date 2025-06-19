package model;

import java.util.ArrayList;
import java.util.Date;

public class ProcessaPedido {

public Pedido processar(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva,
                            Cliente cliente, Vendedor vendedor, String loja, ArrayList<Item> itens) {
if (!confirmarPagamento(dataVencimentoReserva)) {
System.out.println("Reserva vencida! Pagamento não pode ser confirmado.");
return null;
        }

        Pedido pedido = new Pedido(id, dataCriacao, dataPagamento, dataVencimentoReserva,
                                   cliente, vendedor, loja, itens);
        System.out.println("Pedido processado com sucesso!");
        return pedido;
    }

    private boolean confirmarPagamento(Date dataVencimentoReserva) {
    Date dataAtual = new Date();
    return dataAtual.compareTo(dataVencimentoReserva) <= 0;
    }
}
