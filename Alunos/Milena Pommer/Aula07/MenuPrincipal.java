package sistemaPrincipal;

import validacoes.Validacoes;
import calculadora.Calculadora;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import objetosMyPlant.*;
import processaPedido.ProcessaPedido;
import java.util.Arrays;
import java.util.InputMismatchException;

public class MenuPrincipal {

	static Scanner scan = new Scanner(System.in);
	static Calculadora calculadora = new Calculadora();
	static final Map<LocalDate, List<Vendas>> Vendas_Por_Data = new HashMap();

	private static Loja loja;
	private static final ProcessaPedido PROCESSADOR_PEDIDO = new ProcessaPedido();
	private static List<Pedido> listaDePedidos = new ArrayList<>();

	public static void main(String[] args) {

		inicializarLoja();

		int opcaoUsuario;

		do {
			exibirMenu();
			opcaoUsuario = Validacoes.lerInteiro(scan, "Escolha uma opção:");
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
					System.out.println("Sistema encerrado");
					break;
				default:
					System.out.println("Opção selecionada é inválida");
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Erro: " + e.getMessage());
			}

		} while (opcaoUsuario != 10);
		scan.close();

	}

	private static void inicializarLoja() {
		Endereco endLoja = new Endereco("PR", "Cascavel", "Parque São Paulo", "Rua do Rosário", "200", "Loja 01");
		loja = new Loja("My Plant", "My Plant Comércio de Plantas Ltda.", "56.783.967/0001-66", endLoja);

		Endereco endVend1 = new Endereco("PR", "Cascavel", "Centro", "Rua Recife", "2478", "");
		loja.adicionarVendedor(new Vendedor("Milena Dias", 20, endVend1, loja, 2000.0));
		Endereco endGer1 = new Endereco("PR", "Cascavel", "Centro", "Rua Belém", "1602", "");

		loja.adicionarGerente(new Gerente("Helena Dias", 50, endGer1, loja, 4000.0));

		Endereco endCli1 = new Endereco("PR", "Cascavel", "Parque São Paulo", "Rua Universitária", "1630", "Sala01");
		loja.adicionarCliente(new Cliente("Marcelo Pommer", 48, endCli1));

		System.out.println("Loja '" + loja.getNomeFantasia() + "' inicializada.");
	}

	static void exibirMenu() {

		System.out.println("Sistema My Plant");
		System.out.println("1. Calcular Preço Total");
		System.out.println("2. Calcular Troco");
		System.out.println("3. Gerenciar Funcionários");
		System.out.println("4. Gerenciar Clientes");
		System.out.println("5. Exibir Informações da Loja");
		System.out.println("6. Criar Pedido");
		System.out.println("7. Exibir Pedidos");
		System.out.println("8. Confirmar Pagamento Pedido");
		System.out.println("9. Testar Confirmação Pagamento");
		System.out.println("10. Sair");
	}

	static void calcularPrecoTotalItem() {

		Item itemExemplo = new Item("AB01", "Maço de rosas", "Flores", 30.00);
		System.out.println("Exemplo de cálculo para o item:");
		itemExemplo.gerarDescricao();

	}

	private static Endereco lerEnderecoCompleto() {
		System.out.println("Digite os dados do Endereço");
		System.out.println("Estado:");
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

	static void calcularTroco() {
		double recebido = Validacoes.lerDouble(scan, "Digite o valor recebido: ");
		double total = Validacoes.lerDouble(scan, "Digite o valor da compra: ");
		double troco = calculadora.calcularTroco(recebido, total);
		System.out.printf("Troco: R$%.2f\n", troco);

	}

	static void gerenciarFuncionarios() {
		int opcaoVendedor;

		do {

			System.out.println("Gerenciador de Funcionários:");
			System.out.println("1. Adicionar Vendedor");
			System.out.println("2. Adicionar Gerente");
			System.out.println("3. Listar Vendedores");
			System.out.println("4. Listar Gerentes");
			System.out.println("5. Calcular Média Salarial Funcionário");
			System.out.println("6. Calcular Bônus Funcionario");
			System.out.println("7. Voltar");

			opcaoVendedor = Validacoes.lerInteiro(scan, "Escolha uma opção:");
			scan.nextLine();

			switch (opcaoVendedor) {
			case 1:
				adicionarFuncionario(true);
				break;
			case 2:
				adicionarFuncionario(false);
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
				System.out.println("Voltando ao menu principal");
				break;
			default:
				System.out.println("A opção informada é inválida");
			}
		} while (opcaoVendedor != 7);
	}

	static void adicionarFuncionario(boolean isVendedor) {
		String tipo = isVendedor ? "Vendedor" : "Gerente";
		System.out.println("\n Adicionar " + tipo + " ---");
		System.out.println("Nome:");
		String nome = scan.nextLine();
		int idade = Validacoes.lerInteiro(scan, "Idade:");
		scan.nextLine();
		double salarioBase = Validacoes.lerDouble(scan, "Salário Base:");
		scan.nextLine();
		Endereco endereco = lerEnderecoCompleto();

		if (isVendedor) {
			Vendedor novoVendedor = new Vendedor(nome, idade, endereco, loja, salarioBase);
			loja.adicionarVendedor(novoVendedor);
		} else {
			Gerente novoGerente = new Gerente(nome, idade, endereco, loja, salarioBase);
			loja.adicionarGerente(novoGerente);
		}
		System.out.println(tipo + " adicionado com sucesso!");
	}

	static void listarFuncionarios(boolean listarVendedores) {
		if (listarVendedores) {
			List<Vendedor> vendedores = loja.getVendedores();
			if (vendedores.isEmpty()) {
				System.out.println("Nenhum vendedor cadastrado");
				return;
			}
			System.out.println("\n Lista de Vendedores");
			vendedores.forEach(Vendedor::apresentarSe);
		} else {
			List<Gerente> gerentes = loja.getGerentes();
			if (gerentes.isEmpty()) {
				System.out.println("Nenhum gerente cadastrado.");
				return;
			}
			System.out.println("\n Lista de Gerentes");
			gerentes.forEach(Gerente::apresentarSe);
		}
	}

	static void calcularMediaSalarialFuncionario() {
		System.out.println("Digite o nome do funcionário:");
		String nome = scan.nextLine();
		Funcionario func = buscarFuncionarioPorNome(nome);

		if (func != null) {
			System.out.printf("Média Salarial de %s: R$%.2f\n", func.getNome(), func.calcularMediaSalarial());
		} else {
			System.out.println("Funcionário '" + nome + "' não encontrado");
		}
	}

	static void calcularBonusFuncionario() {
		System.out.println("Digite o nome do funcionário:");
		String nome = scan.nextLine();
		Funcionario func = buscarFuncionarioPorNome(nome);

		if (func != null) {
			System.out.printf("Bônus de %s: R$%.2f\n", func.getNome(), func.calcularBonus());
		} else {
			System.out.println("Funcionário '" + nome + "' não encontrado");
		}
	}

	private static Funcionario buscarFuncionarioPorNome(String nome) {

		for (Vendedor v : loja.getVendedores()) {
			if (v.getNome().equalsIgnoreCase(nome)) {
				return v;
			}
		}

		for (Gerente g : loja.getGerentes()) {
			if (g.getNome().equalsIgnoreCase(nome)) {
				return g;
			}
		}
		return null;
	}

	static void gerenciarClientes() {

		int opcaoCliente;

		do {

			System.out.println("Gerenciador de Clientes");
			System.out.println("1. Adicionar Cliente");
			System.out.println("2. Listar Cliente");
			System.out.println("3. Voltar");

			opcaoCliente = Validacoes.lerInteiro(scan, "Escolha uma opção:");
			scan.nextLine();

			switch (opcaoCliente) {
			case 1:
				adicionarCliente();
				break;
			case 2:
				listarCliente();
				break;
			case 3:
				System.out.println("Voltando ao menu principal");
				break;
			default:
				System.out.println("A opção informada é inválida");
			}
		} while (opcaoCliente != 3);
	}

	static void adicionarCliente() {
		System.out.println("\n Adicionar Cliente");
		System.out.println("Nome:");
		String nome = scan.nextLine();
		int idade = Validacoes.lerInteiro(scan, "Idade:");
		scan.nextLine();

		Endereco endereco = lerEnderecoCompleto();

		Cliente cliente = new Cliente(nome, idade, endereco);
		loja.adicionarCliente(cliente);
		System.out.println("Cliente adicionado com sucesso!");
	}

	static void listarCliente() {
		List<Cliente> clientes = loja.getClientes();
		if (clientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado");
			return;
		}
		System.out.println("\n Lista de Clientes");
		clientes.forEach(Cliente::apresentarSe);
	}

	static void exibirInformacoesLoja() {
		loja.apresentarSe();
		System.out.println("Número de clientes: " + loja.contarClientes());
		System.out.println("Número de vendedores: " + loja.contarVendedores());
		System.out.println("Número de gerentes: " + loja.contarGerentes());
	}

	static void criarPedidoFake() {
		System.out.println("\n Criando Pedido Fake");

		if (loja.getClientes().isEmpty() || loja.getVendedores().isEmpty()) {
			System.err.println(
					"É necessário ter ao menos um cliente e um vendedor cadastrados para criar um pedido.");
			return;
		}
		Cliente clientePedido = loja.getClientes().get(0);
		Vendedor vendedorPedido = loja.getVendedores().get(0);

		List<Item> itensPedido = Arrays.asList(new Item("AB03", "Bromélias", "Rosas", 100.00),
				new Item("AV01", "Vaso de plantas", "Vaso", 70.00),
				new Item("AD01", "Adubo", "Insumo", 35.00));

		Pedido novoPedido = PROCESSADOR_PEDIDO.processar(clientePedido, vendedorPedido, loja, itensPedido);

		if (novoPedido != null) {
			listaDePedidos.add(novoPedido);
			System.out.println("Pedido fake criado com sucesso!");
			novoPedido.gerarDescricaoVenda();
		} else {
			System.err.println("Falha no processo de criação de pedido fake");
		}
	}

	static void exibirPedidos() {
		if (listaDePedidos.isEmpty()) {
			System.out.println("\nNenhum pedido registrado no sistema");
			return;
		}
		System.out.println("\n Lista de Pedidos Registrados");
		for (Pedido p : listaDePedidos) {
			System.out.println(p);
			System.out.println("Itens:");
			p.getItens().forEach(item -> System.out.println("     - " + item));
		}
	}

	static void confirmarPagamentoPedido() {
		if (listaDePedidos.isEmpty()) {
			System.out.println("Nenhum pedido na lista para confirmar pagamento");
			return;
		}

		System.out.println("\n Confirmar Pagamento de Pedido");
		exibirPedidos();

		System.out.println("Digite o ID do pedido para confirmar o pagamento:");
		String idPedido = scan.nextLine();

		Pedido pedidoParaConfirmar = null;
		for (Pedido p : listaDePedidos) {
			if (p.getId().equalsIgnoreCase(idPedido)) {
				pedidoParaConfirmar = p;
				break;
			}
		}

		if (pedidoParaConfirmar != null) {
			PROCESSADOR_PEDIDO.confirmarPagamento(pedidoParaConfirmar);
		} else {
			System.out.println("Pedido com ID '" + idPedido + "' não encontrado.");
		}
	}

	static void testarConfirmacaoPagamentoMenu() {
		PROCESSADOR_PEDIDO.testarConfirmacaoPagamento(loja);
	}
}
