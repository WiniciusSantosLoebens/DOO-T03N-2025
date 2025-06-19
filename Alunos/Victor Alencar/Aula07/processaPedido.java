package kdkak;

import java.util.ArrayList;
import java.util.Date;

public class processaPedido {

    public pedido processar(int id, cliente cliente, vendedor vendedor, loja loja, ArrayList<item> itens, Date vencimento) {
        Date agora = new Date();
        if (confirmarPagamento(vencimento, agora)) {
            return new pedido(id, agora, agora, vencimento, cliente, vendedor, loja, itens);
        } else {
            System.out.println("Pagamento não confirmado. Reserva vencida.");
            return null;
        }
    }

    private boolean confirmarPagamento(Date vencimento, Date atual) {
        return !atual.after(vencimento);
    }
}
