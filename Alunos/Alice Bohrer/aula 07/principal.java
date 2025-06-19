package main;

import classes.Vendedor;

import classes.Cliente;
import classes.Item;
import classes.Venda; 
import classes.RelatorioVenda;
import classes.Loja;
import classes.Pedido;
import classes.ProcessarPedido;
import classes.Gerente;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;


public class principal {

	private static Venda Vendas = new Venda();
	private static RelatorioVenda relatorios = new RelatorioVenda();
	public static Scanner scan = new Scanner(System.in);
	public static ArrayList <Venda> ListaDevendas = new ArrayList <Venda> ();
	public static ArrayList<RelatorioVenda> ListaDeRelatorios = new ArrayList<RelatorioVenda>();
	
	private static final DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	

	public static void main(String[] args ) {

		
		executarMenu();
	}
	
	

	public static void executarMenu (){
		
		
		System.out.println("-----MENU-----");
		System.out.println("1 - CALCULAR VALOR TOTAL DA COMPRA");
		System.out.println("2 - VER RELATORIOS ");
		System.out.println("3 - SAIR DO SISTEMA");
		System.out.println("4 - INFORMAÇÕES DA LOJA");
		System.out.println("5 - PEDIDO FAKE");
		
		int escolhaUsuario = scan.nextInt ();
		
		switch (escolhaUsuario) {
		
		case 1 :  
			
			CalcularTotal();
			break;
			
		case 2 : 
			
			VerRelatorio(relatorios);
			break;
			
		case 3 : 
			
			System.out.println("SAINDO DO SISTEMA...");
			System.out.println("SISTEMA ENCERRADO!");
			break;
			
		case 4 :
			
			InformaçõesLoja();
			break;
			
		case 5 : 
			
			PedidoFake();
			break;
			
	  default :
				
	 System.out.println("OPÇÃO INVALIDA, TENTE OUTRA VEZ");
	executarMenu();
				
		}
		
		
		}
	
	
	public static void CalcularTotal() {
		
		
		Venda vendas = new Venda ();
		 
		 
		System.out.println("\n");
		System.out.println("DIGITE A QUANTIDADE DE PRODUTO \n");
	
		
		 try {
		        vendas.setQuantProduto(scan.nextDouble());
		    } catch (InputMismatchException e) {
		        System.out.println("Entrada inválida. Digite um número.");
		        scan.nextLine();
		        CalcularTotal();
		        return;
		    }
		
		
		
		relatorios.setQuantPlantasVendidas(relatorios.getQuantPlantasVendidas() + vendas.getQuantProduto());
		
		System.out.println("DIGITE O VALOR UNITARIO DESTE PRODUTO");
		
		try {
	         vendas.setValorUnitario(scan.nextDouble());
	    } catch (InputMismatchException e) {
	            System.out.println("Entrada inválida. Digite um número.");
	            scan.nextLine();
	            CalcularTotal();
	            return;
	    }
		
		System.out.println("Digite a data da venda (dd/MM/yyyy):");
	    String dataString = scan.next();

	    try {
	        LocalDate dataVenda = LocalDate.parse(dataString, formatoData);
	        vendas.setDataVenda(dataVenda);
		
		
		vendas.setValorDaCompra(vendas.getQuantProduto() * vendas.getValorUnitario());
		
		if ( vendas.getQuantProduto() > 10) {
			
			vendas.setValorDaCompraComDesc(vendas.getValorDaCompra() * 0.95f);
			relatorios.setValorTotalVendas(relatorios.getValorTotalVendas() + vendas.getValorDaCompraComDesc()); 
		    relatorios.setQuantidadeDeDescontos(relatorios.getQuantidadeDeDescontos() + 1);
			
		}else {
			
			relatorios.setValorTotalVendas(relatorios.getValorTotalVendas() + vendas.getValorDaCompra()); 
			
		}
		
		System.out.printf(" O VALOR TOTAL DA COMPRA É : %.2f \n ", vendas.getValorDaCompra());
		System.out.printf(" O VALOR COM DESCONTO APLICADO É : %.2f \n ", vendas.getValorDaCompraComDesc());
		
		
		
	    
		ListaDevendas.add(vendas);	
		CalcularTroco(vendas);
		executarMenu();
		
	    } catch (DateTimeParseException e) {
	        System.out.println("Formato de data inválido. Use dd/MM/aaaa. ");
	        CalcularTotal(); 
	    }
	}
	
	public static void CalcularTroco (Venda vendas) {
		
	double troco = 0;	
	double valorRecebido = 0;
	
	System.out.println("\n");
	System.out.println(" 1- VOLTAR PARA O MENU ");
	System.out.println(" 2 - CONTINAR ");
	
	 int EscolhaUsuario2;
	 
	 try {
	        EscolhaUsuario2 = scan.nextInt();
	    } catch (InputMismatchException e) {
	        System.out.println("Entrada inválida. Digite um número.");
	        scan.nextLine(); 
	        CalcularTroco(vendas); 
	        return;
	    }
	
	
	
	if(EscolhaUsuario2 == 1 ) {
		
		executarMenu();
		
	}else if (EscolhaUsuario2 == 2 ){
	
	System.out.println(" \n");
	System.out.println(" CALCULAR TROCO");
	
	System.out.println("QUAL O  VALOR RECEBIDO?");
	
	try {
        valorRecebido = scan.nextDouble();
    } catch (InputMismatchException e) {
        System.out.println("Entrada inválida. Digite um número.");
        scan.nextLine();
        CalcularTroco(vendas);
        return;
    }
	
    

		
	if(vendas.getQuantProduto() < 10) {
		
	    troco = valorRecebido - vendas.getValorDaCompra();
	    
	} else {

        troco = valorRecebido - vendas.getValorDaCompraComDesc();

    }
	    
	    if(troco == 0) {
	    	
	    	System.out.println(" NÃO É NECESSARIO TROCO ");
	    	
	    }else if( troco < 0) {
	    	
	    	System.out.printf(" ESTA FALTANDO O VALOR DE %.2f :", troco);
	    }else {
	    	
	    	System.out.printf(" O TROCO É %.2f :", troco);
	    	System.out.printf("\n");
	    }
	    
	}
	
	executarMenu();
		
	}
	public static void VerRelatorio (RelatorioVenda relatorios) {
		
		int contador = 1 ;
		
		System.out.println("\n-----RELATÓRIOS-----");
	    System.out.println("1 - VER RELATÓRIO DE VENDAS INDIVIDUAIS");
	    System.out.println("2 - VER RELATÓRIO GERAL DAS VENDAS");
	    System.out.println("3 - BUSCAR VENDAS POR DATA");
	    System.out.println("4 - BUSCAR VENDAS POR MÊS");
        
	    int escolhaUsuario3 = 0;
	    
	    try {
	         escolhaUsuario3 = scan.nextInt();
	     } catch (InputMismatchException e) {
	         System.out.println("Entrada inválida. Digite um número.");
	         scan.nextLine();
	         VerRelatorio(relatorios);
	         return;
	     }

        if(escolhaUsuario3 == 1) {
		
        	if (ListaDevendas.isEmpty()) {
                System.out.println("Nenhuma venda registrada ainda.");
            } else {
                for (Venda venda : ListaDevendas) {
                    System.out.println(contador + " - " + venda);
                    contador++;
                }
                
            }
        } else if (escolhaUsuario3 == 2) {
        	
        		
    			System.out.println("-----RELATORIO GERAL-----");
    			System.out.printf("TOTAL DE PLANTAS VENDIDAS : %.2f \n", relatorios.getQuantPlantasVendidas());
    			System.out.printf("VALOR TOTAL VENDIDO : %.2f \n", relatorios.getValorTotalVendas());
    			System.out.printf("QUANTIDADE TOTAL DE DESCONTOS APLICADOS : %.2f \n", relatorios.getQuantidadeDeDescontos());
            	
            	System.out.println("\n");
        } else if (escolhaUsuario3 == 3) {
        	
        	System.out.println("Digite a data para a busca (dd/MM/aaaa):");
            String dataString = scan.next();
            try {
                LocalDate dataBusca = LocalDate.parse(dataString, formatoData);
                double totalVendasNaData = 0;

                for (Venda venda : ListaDevendas) {
                    if (venda.getDataVenda().isEqual(dataBusca)) {
                        totalVendasNaData += (venda.getQuantProduto() > 10) ? venda.getValorDaCompraComDesc() : venda.getValorDaCompra();
                    }
                }

                if (totalVendasNaData > 0) {
                    System.out.printf("Vendas em %s: %.2f\n", dataString, totalVendasNaData);
                } else {
                    System.out.println("Nenhuma venda encontrada para a data especificada. ");
                }


            } catch (Exception e) {
                System.out.println("Formato de data inválido. Use dd/MM/aaaa.");
            }
        	
        } else if (escolhaUsuario3 == 4) {
        	
        	try {
                System.out.print("Digite o mês (1-12): ");
                int mes = scan.nextInt();
                System.out.print("Digite o ano (aaaa): ");
                int ano = scan.nextInt();

                double totalVendasNoMes = 0;
                for (Venda venda : ListaDevendas) {
                    if (venda.getDataVenda().getMonthValue() == mes && venda.getDataVenda().getYear() == ano) {
                         totalVendasNoMes += (venda.getQuantProduto() > 10) ? venda.getValorDaCompraComDesc() : venda.getValorDaCompra();
                    }
                }

                if (totalVendasNoMes > 0) {
                    System.out.printf("Total de vendas em %02d/%d: %.2f\n", mes, ano, totalVendasNoMes);
                } else {
                    System.out.println("Não foram encontradas vendas para o mês/ano especificado.");
                }


            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Certifique-se de digitar números inteiros para o mês e o ano.");
                scan.nextLine(); 
            }
        
        	
        }
        
	
        executarMenu();
    	
	}
	 
	public static void InformaçõesLoja () {
		
		Cliente cliente1 = new Cliente ("Alice", 18, "Guaraniaçu", "Alto boa vista","Area Rural");
		Cliente cliente2 = new Cliente ("Maria Helena", 56, "Guaraniaçu", "Alto boa vista","Area Rural");
		Cliente cliente3 = new Cliente ("Sergio", 60, "Guaraniaçu", "Alto boa vista","Area Rural");
		
		
		Loja lojas = new Loja ("My Plant", "My Plant LTDA", "10.000.000/0001-00",
				                "Guaraniaçu", " Jardim Real ", "Santa Rita" , new Vendedor[2], new Cliente[]{cliente1, cliente2, cliente3});
		
		Vendedor vendedor1 = new Vendedor("Carlos", 28, lojas , "Guaraniaçu", "Linha pietrobon", "Area rural", 2100);
        Vendedor vendedor2 = new Vendedor("Ana", 32, lojas, "Guaraniaçu", "alto alegre", "Area rural", 2100);
		
        Gerente gerente = new Gerente("Fernanda", 40, lojas, 5000);
		
		lojas.vendedores[0]= vendedor1;
		lojas.vendedores[1]= vendedor2;
		
		System.out.println("DADOS DA LOJA");
		
		
		lojas.apresentarse();
		lojas.contarClientes();
		lojas.contarVendedores();
		
		System.out.println("\n");
		 
		
		System.out.println("DADOS DOS CLIENTES");
	
		
		cliente1.ApresentarSe();
		cliente2.ApresentarSe();
		
		System.out.println("\n");
		
		System.out.println(" DADOS DOS VENDEDORES");
		
		 vendedor1.ApresentarSe();
	     System.out.println("Média Salarial: " + vendedor1.CalcularMedia());
	     System.out.println("Bônus: " + vendedor1.CalcularBonus());
		
	     System.out.println("\n");
	     
	     vendedor2.ApresentarSe();
	     System.out.println("Média Salarial: " + vendedor2.CalcularMedia());
	     System.out.println("Bônus: " + vendedor2.CalcularBonus());
		
	     System.out.println("\n");
	     System.out.println(" DADOS DO GERENTE ");
	     
	     gerente.apresentarse();
	     
	     System.out.println("Média Salarial: " + gerente.calcularMedia());
	     System.out.println("Bônus: " + gerente.calcularBonus());
	         
	}
	     public static void PedidoFake() {
	     
	        int opcao = 0;

	        do {
	        	
	            System.out.println("\n====== MENU ======");
	            System.out.println("1 - CRIAR PEDIDO (DADOS FAKES)");
	            System.out.println("2- SAIR");
	          
	            opcao = scan.nextInt();

	            switch (opcao) {
	                case 1:
	                    criarPedidoFake();
	                    break;
	                case 2:
	                    System.out.println("Saindo...");
	                    break;
	                default:
	                    System.out.println("Opção inválida.");
	            }

	        } while (opcao != 2);

	        scan.close();
	    }

	    public static void criarPedidoFake() {
	    	
	    	
	        Item[] itens = {
	            new Item(1, "Rosa", "Flor", 120.00),
	            new Item(2, "Adubo para rosas", "Fertilizante", 180.00)
	        };

	        Date dataVencimento = new Date(System.currentTimeMillis() + 86400000); 
	        ProcessarPedido processador = new ProcessarPedido();
	        Pedido pedido = processador.processarPedido(2006, "Alice", "Maria", "Loja jardim real", itens, dataVencimento);

	        pedido.gerarDescricaoVenda();
	    }  
	     
	     
	     
	     
	     
	     
	     
	}




