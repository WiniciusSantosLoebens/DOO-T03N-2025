package objetos;

import java.util.Date;

public class ProcessaPedido {
    public Pedido processar(int id, String cliente, String vendedor, String loja, Date dataVencimentoReserva) {
        Date dataCriacao = new Date();
        Pedido pedido = new Pedido(id, dataCriacao, cliente, vendedor, loja);
        if (confirmarPagamento(dataVencimentoReserva)) {
            pedido.setDataPagamento(dataCriacao);
            System.out.println("Pagamento confirmado para o pedido ID: " + id);
        } else {
            System.out.println("Pagamento não confirmado. A reserva está vencida.");
        }
        return pedido;
    }

    private boolean confirmarPagamento(Date dataVencimentoReserva) {
        Date dataAtual = new Date();
        return dataAtual.compareTo(dataVencimentoReserva) <= 0;
    }
}