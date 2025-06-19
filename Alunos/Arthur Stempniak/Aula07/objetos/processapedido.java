package objetos;

import java.util.Date;

public class processapedido {

    public pedido processar(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva, cliente cliente, vendedor vendedor, loja loja) {
        pedido pedido = new pedido(id, dataCriacao, dataPagamento, dataVencimentoReserva, cliente, vendedor, loja);
        return pedido;
    }

    private boolean confirmarPagamento(Date dataPagamento, Date dataVencimentoReserva) {
        return !dataPagamento.after(dataVencimentoReserva);
    }
}
