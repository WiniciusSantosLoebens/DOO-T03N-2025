package classes;

import java.util.Date;

public class ProcessarPedido {


     public Pedido processarPedido(int id, String cliente, String vendedor, String loja, Item[] itens, Date vencimentoReserva) {
    	 
    	 Date agora = new Date();
    	 Pedido pedido = criarPedido(id, cliente, vendedor, loja, itens, agora, vencimentoReserva);

	        if (reservaValida(pedido)) {
	        	
	            registrarPagamento(pedido, agora);
	            
	            System.out.println("Pagamento confirmado!");
	            
	        } else {
	        	
	            System.out.println("Reserva vencida.");
	        }

	        return pedido;
	    }

     
     
	    private Pedido criarPedido(int id, String cliente, String vendedor, String loja, Item[] itens, Date agora, Date vencimento) {
	    	
	        return new Pedido(id, agora, null, vencimento, cliente, vendedor, loja, itens);
	    }
	    

	    private boolean reservaValida(Pedido pedido) {
	    	
	        return new Date().before(pedido.dataVencimentoReserva);
	        
	    }

	    private void registrarPagamento(Pedido pedido, Date dataPagamento) {
	    	
	        pedido.dataPagamento = dataPagamento;
	        
	    }
	}
	
	
