package interfaceUsuario;
 
import tratamentos.TratamentoDeErros;
import calculadora.Calculadora;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate; // Mantido para Vendas_Por_Data legado
import java.time.format.DateTimeFormatter; // Mantido para Vendas_Por_Data legado
import java.time.format.DateTimeParseException; // Mantido para Vendas_Por_Data legado
import java.util.HashMap; // Mantido para Vendas_Por_Data legado
import java.util.Map; // Mantido para Vendas_Por_Data legado
import objetosLoja.*; // Importa todas as classes do pacote objetosLoja
import processos.ProcessaPedido; 
import java.util.Arrays; // Para criar lista de itens fake
import java.util.InputMismatchException;


/*Classe principal que gerencia o menu interativo do sistema My Plant. Exibe um menu principal com opções para cálculos, registro de vendas, 
* busca de vendas (por data e mês), gerenciamento de vendedores e clientes, e exibição de informações da loja
* Coordena a interação do usuário, lendo as opções e direcionando para os métodos correspondentes
*/

public class MenuPrincipal {
	
	static Scanner scan = new Scanner(System.in);
	static Calculadora calculadora = new Calculadora();
	//Lista para armazenar as vendas com a data.
	static final Map<LocalDate, List<Vendas>> Vendas_Por_Data = new HashMap();
	
	private static Loja loja; // Instância da loja sendo gerenciada pelo sistema
	private static final ProcessaPedido PROCESSADOR_PEDIDO = new ProcessaPedido();
	 private static List<Pedido> listaDePedidos = new ArrayList<>();
	
	public static void main(String[] args) {
		
		inicializarLoja();
		
		int opcaoUsuario;
		
		do {
			exibirMenu();
			opcaoUsuario = TratamentoDeErros.lerInteiro(scan, "Escolha uma opção:");
			scan.nextLine();
			
			try {
				switch (opcaoUsuario) {
				case 1:
					 calcularPrecoTotalItem();
					break;
				case 2:
					calcularTroco();
					break;
				case 3:
					gerenciarFuncionarios();
					break;
				case 4:
					gerenciarClientes();
					break;
				case 5:
					exibirInformacoesLoja();
					break;
				case 6:
					criarPedidoFake();
					break;
				case 7:
					exibirPedidos();
					break;
				case 8:
					confirmarPagamentoPedido();
					break;
				case 9:
					testarConfirmacaoPagamentoMenu();
					break;
				case 10:
					System.out.println("Saindo do sistema...");
					System.out.println("Sistema encerrado!");
					break;
					default:
						System.out.println("Opção invlálida!");
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Erro: " + e.getMessage());
			}

	} while (opcaoUsuario !=10);
		scan.close();
	
	}
	
	// Inicializa a loja com um endereço e funcionários/clientes de exemplo.
	private static void inicializarLoja() {
        Endereco endLoja = new Endereco("PR", "Cascavel", "Centro", "Rua Manoel Ribas", "123", "Loja 01");
        loja = new Loja("My Plant", "My Plant Comércio de Plantas Ltda.", "12.345.678/0001-99", endLoja);
        
     // Adiciona funcionários iniciais
        Endereco endVend1 = new Endereco("PR", "Cascavel", "Maria Luiza", "Rua das Orquídeas", "45", "");
        loja.adicionarVendedor(new Vendedor("João Silva", 28, endVend1, loja, 2100.0));
        Endereco endGer1 = new Endereco("PR", "Cascavel", "Centro", "Av. Brasil", "1000", "Sala 10");
        
        loja.adicionarGerente(new Gerente("Mariana Costa", 35, endGer1, loja, 5500.0));

        // Adiciona cliente inicial
        Endereco endCli1 = new Endereco("PR", "Toledo", "Jardim La Salle", "Rua Barão do Rio Branco", "500", "Casa");
        loja.adicionarCliente(new Cliente("Ana Paula", 42, endCli1));

        System.out.println("INFO: Loja '" + loja.getNomeFantasia() + "' inicializada.");
	}

	
	static void exibirMenu() {
		
		
		System.out.println("\n|--------------------------------|");
        System.out.println("|            My Plant            |");
        System.out.println("|--------------------------------|");
        System.out.println("| 1. Calcular Preço Total        |");
        System.out.println("| 2. Calcular Troco              |");
        System.out.println("| 3. Gerenciar Funcionários      |");
        System.out.println("| 4. Gerenciar Clientes          |");
        System.out.println("| 5. Exibir Informações da Loja  |");
        System.out.println("| 6. Criar Pedido                |");
        System.out.println("| 7. Exibir Pedidos              |");
        System.out.println("| 8. Confirmar Pagamento Pedido  |");
        System.out.println("| 9. Testar Confirmação Pagamento|");
        System.out.println("| 10. Sair                       |");
        System.out.println("|--------------------------------|");
	}
	
	static void calcularPrecoTotalItem() {
	    
        Item itemExemplo = new Item("EXM01", "Fertilizante Líquido", "Insumo", 22.50);
        System.out.println("Exemplo de cálculo para o item:");
        itemExemplo.gerarDescricao(); 
     
	}
	

     //Lê os dados de um endereço completo do usuário.
    private static Endereco lerEnderecoCompleto() {
        System.out.println("--- Digite os dados do Endereço ---");
        System.out.println("Estado (UF):");
        String estado = scan.nextLine();
        System.out.println("Cidade:");
        String cidade = scan.nextLine();
        System.out.println("Bairro:");
        String bairro = scan.nextLine();
        System.out.println("Rua:");
        String rua = scan.nextLine();
        System.out.println("Número:");
        String numero = scan.nextLine();
        System.out.println("Complemento (opcional):");
        String complemento = scan.nextLine();
        return new Endereco(estado, cidade, bairro, rua, numero, complemento);
    }
    
 // Calcula e exibe o troco
 	static void calcularTroco() {
         double recebido = TratamentoDeErros.lerDouble(scan,"Digite o valor recebido: ");
         double total = TratamentoDeErros.lerDouble(scan, "Digite o valor da compra: ");
         double troco = calculadora.calcularTroco(recebido, total);
         System.out.printf("Troco: R$%.2f\n", troco);

     }
	
    /*
    // Registra uma nova venda
	static void registrarVenda() {
		int quantidade = TratamentoDeErros.lerInteiro(scan, "Digite a quantidade de plantas: ");
		double precoUnitario = TratamentoDeErros.lerDouble(scan, "Digite o preço unitário: ");
		
		LocalDate dataVenda = lerDataVenda(); //Lê data da venda.
		
		double valorVenda = calculadora.calcularPrecoTotal(quantidade, precoUnitario);
		double desconto = calculadora.calcularDesconto(quantidade,  valorVenda); // Calcula o desconto
		double valorFinal = valorVenda - desconto; // Valor final com desconto
		
		Vendas venda = new Vendas(quantidade, precoUnitario, valorVenda, desconto, dataVenda);
		
		Vendas_Por_Data.computeIfAbsent(dataVenda, k -> new ArrayList<>()).add(venda); // Adiciona a venda com a data

		
		System.out.printf("Venda registrada com sucesso! Valor final: R$%.2f\n", valorFinal);
		
	}*/
	
    /*
	// Método que lê e valida a data de venda inserida pelo usuário.
	static LocalDate lerDataVenda() {
		while (true) {
			System.out.println("Digite a data da vanda (dd/mm/aaa):");
			String dataString = scan.next();
			try {
				return LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				
			} catch (DateTimeParseException e) {
				System.out.println("Data inválida. Use o formato (dd/mm/aaaa).");
			}
		}
		
	}*/
	
    /*
	// Método para bscar vendas por data.
	static void buscarVendasPorData() {
		LocalDate dataBusca = lerDataVenda();
		
		List<Vendas> listaVendas = Vendas_Por_Data.get(dataBusca);
		
		if (listaVendas == null || listaVendas.isEmpty()) {
			System.out.println("Nenhuma vanda encontrada para esta data.");
			return;
		}
		
		System.out.println("---- Vendas em " + dataBusca.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " ----");
	
	
	double totalVendas = 0;
	
	for (Vendas venda : listaVendas) {
		System.out.println(venda);
		totalVendas += venda.getValorVenda() - venda.getDesconto();
	}
	
	System.out.printf("Total de vendas na data: R$%.2f\n", totalVendas);
	}*/
	
    
	
    /*
	// Método para buscar vendas por mês.
	static void buscarVendasPorMes() {
		int mes = TratamentoDeErros.lerInteiro(scan, "Digite o mês (1-12):");
		
		if (mes < 1 || mes > 12) {
			System.out.println("Mês inválido.");
			return;
		}
		
		double totalVendasNoMes = 0;
		for (Map.Entry<LocalDate, List<Vendas>> entry : Vendas_Por_Data.entrySet()) {
            if (entry.getKey().getMonthValue() == mes) {
                for (Vendas venda : entry.getValue()) {
                    totalVendasNoMes += venda.getValorVenda()- venda.getDesconto();
                }
            }
        }
		
		if (totalVendasNoMes == 0) {
            System.out.println("Nenhuma venda encontrada para este mês.");
        } else {
            System.out.printf("Total de vendas no mês %d: R$%.2f\n", mes, totalVendasNoMes);
        }
		
		
	}*/
    
    
	/*
	// Exibe o histórico de vendas
	static void exibirVenda() {
		if (Vendas_Por_Data.isEmpty()) {
			System.out.println("Nenhuma venda registrada ainda.");
			return;
		}
		System.out.println("-----Histórico de Vendas-----");
		for (List<Vendas> vendasDoDia : Vendas_Por_Data.values()) {
		    for (Vendas venda : vendasDoDia) { // Itera sobre as vendas e imprime os detalhes
			System.out.println(venda);
			
		    }
		}
	}*/
    
	
	//  Gerencia o submenu para adicionar e listar funcionários (Vendedores e Gerentes),
    // e calcular suas métricas (média salarial, bônus).
	static void gerenciarFuncionarios() {
		int opcaoVendedor;
		
		do {
		
			System.out.println("\n|----- Gerenciador de Funcionários -----|");
            System.out.println("| 1. Adicionar Vendedor             |");
            System.out.println("| 2. Adicionar Gerente              |");
            System.out.println("| 3. Listar Vendedores              |");
            System.out.println("| 4. Listar Gerentes                |");
            System.out.println("| 5. Calcular Média Salarial Func.  |");
            System.out.println("| 6. Calcular Bônus Funcionario     |");
            System.out.println("| 7. Voltar                         |");
            System.out.println("|-----------------------------------|");
        
        opcaoVendedor = TratamentoDeErros.lerInteiro(scan, "Escolha uma opção:");
        scan.nextLine();

        // Direciona para a função do submenu escolhida
        switch (opcaoVendedor) {
            case 1:
            	adicionarFuncionario(true); 
                break;
            case 2:
            	adicionarFuncionario(false); // Chama método unificado para Gerente
                break;
            case 3:
            	listarFuncionarios(true); 
                break;
            case 4:
            	listarFuncionarios(false); 
                break;
            case 5:
            	calcularMediaSalarialFuncionario(); 
                break;
            case 6:
            	calcularBonusFuncionario();
                break;
            case 7:
                System.out.println("Voltando ao menu principal.");
                break;
            default:
                System.out.println("Opção inválida, tente novamente!");
        }
    } while (opcaoVendedor != 7);
}
		
	static void adicionarFuncionario(boolean isVendedor) {
        String tipo = isVendedor ? "Vendedor" : "Gerente";
        System.out.println("\n--- Adicionar " + tipo + " ---");
        System.out.println("Nome:");
        String nome = scan.nextLine();
        int idade = TratamentoDeErros.lerInteiro(scan, "Idade:");
        scan.nextLine();
        double salarioBase = TratamentoDeErros.lerDouble(scan, "Salário Base:");
        scan.nextLine();
        Endereco endereco = lerEnderecoCompleto(); // Lê o endereço completo

        if (isVendedor) {
            Vendedor novoVendedor = new Vendedor(nome, idade, endereco, loja, salarioBase);
            loja.adicionarVendedor(novoVendedor);
        } else {
            Gerente novoGerente = new Gerente(nome, idade, endereco, loja, salarioBase);
            loja.adicionarGerente(novoGerente);
        }
        System.out.println(tipo + " adicionado(a) com sucesso!");
    }
	    
	    
	 static void listarFuncionarios(boolean listarVendedores) {
	        if (listarVendedores) {
	            List<Vendedor> vendedores = loja.getVendedores();
	            if (vendedores.isEmpty()) {
	                System.out.println("Nenhum vendedor cadastrado."); return;
	            }
	            System.out.println("\n--- Lista de Vendedores ---");
	            vendedores.forEach(Vendedor::apresentarSe); // Lambda para chamar apresentarSe
	        } else {
	            List<Gerente> gerentes = loja.getGerentes();
	            if (gerentes.isEmpty()) {
	                 System.out.println("Nenhum gerente cadastrado."); return;
	            }
	            System.out.println("\n--- Lista de Gerentes ---");
	            gerentes.forEach(Gerente::apresentarSe); // Lambda para chamar apresentarSe
	        }
	    }
	    
     static void calcularMediaSalarialFuncionario() {
         System.out.println("Digite o nome do funcionário (Vendedor ou Gerente):");
         String nome = scan.nextLine();
         Funcionario func = buscarFuncionarioPorNome(nome); // Usa método auxiliar de busca

         if (func != null) {
             System.out.printf("Média Salarial de %s: R$%.2f\n", func.getNome(), func.calcularMediaSalarial());
         } else {
             System.out.println("Funcionário '" + nome + "' não encontrado.");
         }
      }

		
     static void calcularBonusFuncionario() {
         System.out.println("Digite o nome do funcionário (Vendedor ou Gerente):");
         String nome = scan.nextLine();
         Funcionario func = buscarFuncionarioPorNome(nome); // Usa método auxiliar de busca

         if (func != null) {
             System.out.printf("Bônus de %s: R$%.2f\n", func.getNome(), func.calcularBonus());
         } else {
             System.out.println("Funcionário '" + nome + "' não encontrado.");
         }
    }
     
     /**
      * Método auxiliar para buscar um funcionário (Vendedor ou Gerente) pelo nome.
      * Percorre ambas as listas.
      */
     private static Funcionario buscarFuncionarioPorNome(String nome) {
         // Procura na lista de Vendedores
         for (Vendedor v : loja.getVendedores()) {
             if (v.getNome().equalsIgnoreCase(nome)) {
                 return v; // Retorna o Vendedor encontrado
             }
         }
         // Se não achou Vendedor, procura na lista de Gerentes
         for (Gerente g : loja.getGerentes()) {
             if (g.getNome().equalsIgnoreCase(nome)) {
                 return g; // Retorna o Gerente encontrado
             }
         }
         return null; // Retorna null se não encontrar em nenhuma lista
     }

     // Gerencia o submenu para adicionar e listar clientes.
	 static void gerenciarClientes() {
		 
		 int opcaoCliente;
			
			do {
			
			System.out.println("\n|--------------------------------|");
	        System.out.println("|   Gerenciador de Clientes      |");
	        System.out.println("|--------------------------------|");
	        System.out.println("| 1. Adicionar Cliente           |");
	        System.out.println("| 2. Listar Cliente              |");
	        System.out.println("| 3. Voltar                      |");
	        System.out.println("|--------------------------------|");
	        
	        opcaoCliente = TratamentoDeErros.lerInteiro(scan, "Escolha uma opção:");
	        scan.nextLine();

	        switch (opcaoCliente) {
	            case 1:
	                adicionarCliente(); 
	                break;
	            case 2:
	                listarCliente();
	                break;
	            case 3:
	                System.out.println("Voltando ao menu principal.");
	                break;
	            default:
	                System.out.println("Opção inválida, tente novamente!");
	        }
	    } while (opcaoCliente != 3);
	}
	 
		 
	 static void adicionarCliente() {
			System.out.println("\n--- Adicionar Cliente ---");
	        System.out.println("Nome:");
	        String nome = scan.nextLine();
	        int idade = TratamentoDeErros.lerInteiro(scan, "Idade:");
	        scan.nextLine(); // Limpa buffer

	        Endereco endereco = lerEnderecoCompleto(); // Reutiliza a leitura de endereço

	        Cliente cliente = new Cliente(nome, idade, endereco);
	        loja.adicionarCliente(cliente);
	        System.out.println("Cliente adicionado com sucesso!");
		}

	    /**
	     * Exibe a lista de clientes cadastrados na loja.
	     */
		static void listarCliente() {
			List<Cliente> clientes = loja.getClientes();
		    if (clientes.isEmpty()) {
		        System.out.println("Nenhum cliente cadastrado.");
		        return;
		    }
		    System.out.println("\n--- Lista de Clientes ---");
		    clientes.forEach(Cliente::apresentarSe); // Usa lambda para apresentar
		}
		
	
		static void exibirInformacoesLoja() {
			loja.apresentarSe(); // Mostra dados da loja
	        System.out.println("Número de clientes: " + loja.contarClientes());
	        System.out.println("Número de vendedores: " + loja.contarVendedores());
	        System.out.println("Número de gerentes: " + loja.contarGerentes()); // Inclui contagem de gerentes
		}
		
		
		static void criarPedidoFake() {
	        System.out.println("\n--- Criando Pedido Fake ---");

	        // Valida se existem cliente e vendedor para criar o pedido
	        if (loja.getClientes().isEmpty() || loja.getVendedores().isEmpty()) {
	            System.err.println("ERRO: É necessário ter ao menos um cliente e um vendedor cadastrados para criar um pedido.");
	            return;
	        }
	        Cliente clientePedido = loja.getClientes().get(0); // Pega o primeiro cliente
	        Vendedor vendedorPedido = loja.getVendedores().get(0); // Pega o primeiro vendedor

	        // Lista de itens fake para o pedido
	        List<Item> itensPedido = Arrays.asList(
	                new Item("PLT-01", "Monstera Deliciosa", "Planta Tropical", 120.00),
	                new Item("VAS-02", "Vaso Auto Irrigável G", "Acessório", 85.50),
	                new Item("SUB-01", "Substrato Premium 5kg", "Insumo", 35.00)
	        );

	        // Processa o pedido usando a classe ProcessaPedido
	        Pedido novoPedido = PROCESSADOR_PEDIDO.processar(clientePedido, vendedorPedido, loja, itensPedido);

	        // Se o pedido foi criado com sucesso, adiciona à lista e mostra info
	        if (novoPedido != null) {
	            listaDePedidos.add(novoPedido);
	            System.out.println("Pedido fake criado com sucesso!");
	            novoPedido.gerarDescricaoVenda(); // Mostra a descrição básica
	        } else {
	            System.err.println("Falha ao processar o pedido fake.");
	        }
	    }

	    /**
	     * Exibe a lista de todos os pedidos que foram criados e armazenados.
	     */
	    static void exibirPedidos() {
	        if (listaDePedidos.isEmpty()) {
	            System.out.println("\nNenhum pedido registrado no sistema.");
	            return;
	        }
	        System.out.println("\n--- Lista de Pedidos Registrados ---");
	        for (Pedido p : listaDePedidos) {
	            System.out.println(p); // Usa o toString() otimizado do Pedido
	            System.out.println("   Itens:");
	            // Mostra os itens de forma mais legível
	            p.getItens().forEach(item -> System.out.println("     - " + item)); // Usa o toString() do Item
	            System.out.println("------------------------------------");
	        }
	    }

	    /**
	     * Permite ao usuário selecionar um pedido pelo ID e tentar confirmar o pagamento.
	     */
	    static void confirmarPagamentoPedido() {
	        if (listaDePedidos.isEmpty()) {
	            System.out.println("Nenhum pedido na lista para confirmar pagamento.");
	            return;
	        }

	        System.out.println("\n--- Confirmar Pagamento de Pedido ---");
	        exibirPedidos(); // Mostra os pedidos para facilitar a escolha do ID

	        System.out.println("Digite o ID do pedido para confirmar o pagamento:");
	        String idPedido = scan.nextLine();

	        Pedido pedidoParaConfirmar = null;
	        // Busca o pedido pelo ID na lista
	        for (Pedido p : listaDePedidos) {
	            if (p.getId().equalsIgnoreCase(idPedido)) {
	                pedidoParaConfirmar = p;
	                break;
	            }
	        }

	        // Se encontrou o pedido, tenta confirmar o pagamento
	        if (pedidoParaConfirmar != null) {
	            PROCESSADOR_PEDIDO.confirmarPagamento(pedidoParaConfirmar); // Chama o método do processador
	        } else {
	            System.out.println("Pedido com ID '" + idPedido + "' não encontrado.");
	        }
	    }

	    /**
	     * Chama o método de teste da classe ProcessaPedido para verificar a lógica
	     * de confirmação de pagamento em diferentes cenários.
	     */
	    static void testarConfirmacaoPagamentoMenu() {
	        PROCESSADOR_PEDIDO.testarConfirmacaoPagamento(loja); // Passa a loja atual para o teste
	    }
		
}
