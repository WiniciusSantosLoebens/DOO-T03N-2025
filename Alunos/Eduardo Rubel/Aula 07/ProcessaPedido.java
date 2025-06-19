
import java.time.LocalDate;
import java.util.List;

class ProcessaPedido {
    public static Pedido processar(int id, LocalDate dataCriacao, LocalDate dataPagamento, LocalDate dataVencimento,
                                   Cliente cliente, Vendedor vendedor, Loja loja, List<Item> itens) {
        if (confirmarPagamento(dataVencimento)) {
            return new Pedido(id, dataCriacao, dataPagamento, dataVencimento, cliente, vendedor, loja, itens);
        }
        return null;
    }

    private static boolean confirmarPagamento(LocalDate dataVencimentoReserva) {
        return LocalDate.now().isBefore(dataVencimentoReserva);
    }
}
