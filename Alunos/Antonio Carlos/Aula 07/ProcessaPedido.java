package processos;

import objetosLoja.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

// Classe responsável pela lógica de processamento de pedidos.
// Processa a criação de um novo pedido, definindo datas e adicionando itens.
public class ProcessaPedido {
	
	public Pedido processar(Cliente cliente, Vendedor vendedor, Loja loja, List<Item> itens) {
        // Validação básica de entrada
        if (cliente == null || vendedor == null || loja == null || itens == null || itens.isEmpty()) {
            System.err.println("ERRO: Dados inválidos para processar pedido.");
            return null;
        }
        
        Pedido novoPedido = new Pedido(cliente, vendedor, loja);

        // Adiciona itens ao pedido
        itens.forEach(novoPedido::adicionarItem); // Usando forEach e method reference

        // Define datas
        Date agora = new Date();
        novoPedido.setDataCriacao(agora);

        // Calcula data de vencimento da reserva (ex: 3 dias)
        Calendar cal = Calendar.getInstance();
        cal.setTime(agora);
        cal.add(Calendar.DATE, 3); // Adiciona 3 dias à data atual
        novoPedido.setDataVencimentoReserva(cal.getTime());

        System.out.println("Pedido processado (ID: " + novoPedido.getId() + "). Reserva vence em: " + new
        		SimpleDateFormat("dd/MM/yyyy").format(novoPedido.getDataVencimentoReserva()));
        return novoPedido;
    }
	
	 public boolean confirmarPagamento(Pedido pedido) {
	        if (pedido == null) {
	            System.err.println("ERRO: Pedido inválido.");
	            return false;
	        }
	        if (pedido.getDataPagamento() != null) {
	            System.out.println("INFO: Pedido " + pedido.getId() + " já está pago.");
	            return true; // Já está pago, consideramos sucesso na tentativa
	        }

	        Date agora = new Date();
	        Date vencimento = pedido.getDataVencimentoReserva();

	        // Verifica vencimento
	        if (vencimento != null && agora.after(vencimento)) {
	            System.out.println("AVISO: Reserva do pedido " + pedido.getId() + " venceu em " + new 
	            		SimpleDateFormat("dd/MM/yyyy").format(vencimento) + ". Pagamento não confirmado.");
	            return false;
	        }

	        // Confirma o pagamento
	        pedido.setDataPagamento(agora);
	        System.out.println("Pagamento do pedido " + pedido.getId() + " confirmado com sucesso!");
	        return true;
	    }
	 
	 public void testarConfirmacaoPagamento(Loja lojaTeste) {
         System.out.println("\n--- Iniciando Teste de Confirmação de Pagamento ---");

         // Verifica se há dados suficientes na loja de teste
         if (lojaTeste.getClientes().isEmpty() || lojaTeste.getVendedores().isEmpty()) {
             System.err.println("ERRO no teste: Loja precisa de ao menos 1 cliente e 1 vendedor cadastrados.");
             return;
         }

         Cliente cli = lojaTeste.getClientes().get(0);
         Vendedor vend = lojaTeste.getVendedores().get(0);
         List<Item> items = List.of(new Item("T01", "Item Teste", "Teste", 10.0));

         // 1. Teste com pedido válido
         System.out.println("\n[Teste 1: Pedido Válido]");
         Pedido pedidoValido = processar(cli, vend, lojaTeste, items);
         if (pedidoValido != null) {
             boolean conf1 = confirmarPagamento(pedidoValido);
             System.out.println("Resultado Confirmação 1: " + (conf1 ? "SUCESSO" : "FALHA"));
         } else { System.out.println("Falha ao criar pedido válido."); }

         // 2. Teste com pedido já pago
         System.out.println("\n[Teste 2: Pedido Já Pago]");
         if (pedidoValido != null) { // Reutiliza o pedido válido que já foi pago
             boolean conf2 = confirmarPagamento(pedidoValido);
             System.out.println("Resultado Confirmação 2: " + (conf2 ? "SUCESSO (Já pago)" : "FALHA"));
         } else { System.out.println("Pedido válido não disponível para teste 2."); }

         // 3. Teste com pedido vencido
         System.out.println("\n[Teste 3: Pedido Vencido]");
         Pedido pedidoParaVencer = processar(cli, vend, lojaTeste, items);
         if (pedidoParaVencer != null) {
             // "Força" o vencimento colocando a data no passado
             Calendar cal = Calendar.getInstance();
             cal.setTime(new Date());
             cal.add(Calendar.DATE, -5); // 5 dias atrás
             pedidoParaVencer.setDataVencimentoReserva(cal.getTime());
             System.out.println("Pedido para vencer criado (vencimento forçado para o passado).");
             boolean conf3 = confirmarPagamento(pedidoParaVencer);
             System.out.println("Resultado Confirmação 3: " + (!conf3 ? "SUCESSO (Não confirmou)" : "FALHA"));
         } else { System.out.println("Falha ao criar pedido para teste 3."); }

         System.out.println("\n--- Fim do Teste de Confirmação de Pagamento ---");
    }
	
	

}
