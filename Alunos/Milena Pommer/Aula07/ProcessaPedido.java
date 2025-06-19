package processaPedido;

import objetosMyPlant.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProcessaPedido {

	public Pedido processar(Cliente cliente, Vendedor vendedor, Loja loja, List<Item> itens) {

		if (cliente == null || vendedor == null || loja == null || itens == null || itens.isEmpty()) {
			System.err.println("Os dados são inválidos");
			return null;
		}

		Pedido novoPedido = new Pedido(cliente, vendedor, loja);

		itens.forEach(novoPedido::adicionarItem);

		Date agora = new Date();
		novoPedido.setDataCriacao(agora);

		Calendar cal = Calendar.getInstance();
		cal.setTime(agora);
		cal.add(Calendar.DATE, 3);
		novoPedido.setDataVencimentoReserva(cal.getTime());

		System.out.println("Pedido processado (ID: " + novoPedido.getId() + "). Reserva vence em: "
				+ new SimpleDateFormat("dd/MM/yyyy").format(novoPedido.getDataVencimentoReserva()));
		return novoPedido;
	}

	public boolean confirmarPagamento(Pedido pedido) {
		if (pedido == null) {
			System.err.println("Pedido inválido.");
			return false;
		}
		if (pedido.getDataPagamento() != null) {
			System.out.println("Pedido " + pedido.getId() + " já está pago.");
			return true;
		}

		Date agora = new Date();
		Date vencimento = pedido.getDataVencimentoReserva();

		if (vencimento != null && agora.after(vencimento)) {
			System.out.println("Reserva do pedido " + pedido.getId() + " venceu em "
					+ new SimpleDateFormat("dd/MM/yyyy").format(vencimento) + ". Pagamento não confirmado.");
			return false;
		}

		pedido.setDataPagamento(agora);
		System.out.println("Pagamento do pedido " + pedido.getId() + " confirmado com sucesso!");
		return true;
	}

	public void testarConfirmacaoPagamento(Loja lojaTeste) {
		System.out.println("\n--- Iniciando Teste de Confirmação de Pagamento ---");

		if (lojaTeste.getClientes().isEmpty() || lojaTeste.getVendedores().isEmpty()) {
			System.err.println("Loja precisa de ao menos 1 cliente e 1 vendedor cadastrados.");
			return;
		}

		Cliente cli = lojaTeste.getClientes().get(0);
		Vendedor vend = lojaTeste.getVendedores().get(0);
		List<Item> items = List.of(new Item("T01", "Item Teste", "Teste", 10.0));

		System.out.println("\nTeste 1: Pedido Válido");
		Pedido pedidoValido = processar(cli, vend, lojaTeste, items);
		if (pedidoValido != null) {
			boolean conf1 = confirmarPagamento(pedidoValido);
			System.out.println("Resultado Confirmação 1: " + (conf1 ? "SUCESSO" : "FALHA"));
		} else {
			System.out.println("Falha ao criar pedido válido.");
		}

		System.out.println("\nTeste 2: Pedido Já Pago");
		if (pedidoValido != null) {
			boolean conf2 = confirmarPagamento(pedidoValido);
			System.out.println("Resultado Confirmação 2: " + (conf2 ? "SUCESSO (Já pago)" : "FALHA"));
		} else {
			System.out.println("Pedido válido não disponível para teste 2.");
		}

		System.out.println("\nTeste 3: Pedido Vencido");
		Pedido pedidoParaVencer = processar(cli, vend, lojaTeste, items);
		if (pedidoParaVencer != null) {

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, -5);
			pedidoParaVencer.setDataVencimentoReserva(cal.getTime());
			System.out.println("Pedido para vencer criado (vencimento forçado para o passado).");
			boolean conf3 = confirmarPagamento(pedidoParaVencer);
			System.out.println("Resultado Confirmação 3: " + (!conf3 ? "SUCESSO (Não confirmou)" : "FALHA"));
		} else {
			System.out.println("Falha ao criar pedido para teste 3.");
		}
	}
}
