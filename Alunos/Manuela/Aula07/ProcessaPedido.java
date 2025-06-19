package objetos;

import java.time.LocalDate;
import java.util.List;

public class ProcessaPedido {

	// Processa a criação de um pedido
    public Pedido processar(Cliente cliente, Vendedor vendedor, Loja loja, List<Item> itens, int quantidadeTotalParaDesconto) {
        if (cliente == null || vendedor == null || loja == null || itens == null || itens.isEmpty()) {
            System.out.println("ERRO: Dados insuficientes para criar o pedido.");
            return null;
        }

        // Calcula o valor bruto somando o valor de cada item na lista
        double valorBruto = 0;
        for (Item item : itens) {
            valorBruto += item.getValor();
        }

        // Aplica desconto se a quantidade total informada for maior que 10
        double valorFinal = valorBruto;
        boolean descontoAplicado = false;
        if (quantidadeTotalParaDesconto > 10) {
            valorFinal *= 0.95; // 5% de desconto
            descontoAplicado = true;
        }

        // Cria e retorna o pedido
        Pedido novoPedido = new Pedido(cliente, vendedor, loja, itens, valorFinal, descontoAplicado);
        return novoPedido;
    }

    // Confirma o pagamento se a reserva não estiver vencida
    public boolean confirmarPagamento(Pedido pedido) {
        if (pedido == null) {
            System.out.println("ERRO: Pedido inválido.");
            return false;
        }
        if (pedido.isPago()) {
            System.out.println("INFO: Pedido ID " + pedido.getId() + " já está pago.");
            return true;
        }

        LocalDate hoje = LocalDate.now();
        if (hoje.isAfter(pedido.getDataVencimentoReserva())) {
            System.out.println("ERRO: Não é possível confirmar o pagamento do pedido ID " + pedido.getId() + ". Reserva vencida em " + pedido.getDataVencimentoReserva().format(Pedido.DATE_FORMATTER));
            return false;
        } else {
            pedido.setDataPagamento(hoje);
            System.out.println("SUCESSO: Pagamento do pedido ID " + pedido.getId() + " confirmado em " + hoje.format(Pedido.DATE_FORMATTER) + ".");
            return true;
        }
    }
	}
