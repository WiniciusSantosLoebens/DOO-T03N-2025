package calculadora;

import java.util.Scanner; 
import java.util.List;
import java.util.ArrayList;
import atividadeAula06Objetos.Vendedor;
import atividadeAula06Objetos.Cliente;
import atividadeAula06Objetos.Loja;

public class Calculadora {
	
	static Scanner scan = new Scanner (System.in);
	static List<VendaGabrielinha> listaVendas = new ArrayList<>();
	private static Loja loja;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		inicializarLoja();
		menuPrincipal();
	}
	
	private static void inicializarLoja() {
        loja = new Loja("My Plant", "My Plant Floricultura Ltda.", "65.522.309/0001-55", "Cascavel", "Centro", "Rua Rio Grande do Sul, 530");
    }

	public static void menuPrincipal() {
		
		System.out.println("------------ Menu Principal ------------");
		System.out.println("Informe o número correspondente à ação que deseja realizar:");
		System.out.println(" ");
		System.out.println("Digite 1 para calcular o preço total:");
		System.out.println("Digite 2 para registrar venda:");
		System.out.println("Digite 3 para calcular o troco:");
		System.out.println("Digite 4 para exibir vendas:");
		System.out.println("Digite 5 para gerenciar vendedores:");
		System.out.println("Digite 6 para gerenciar clientes");
		System.out.println("Digite 7 para exibir informações da loja:");
		System.out.println("Digite 8 para sair do sistema");

		
		int escolhaUsuario = scan.nextInt();
		scan.nextLine();
		
		if (escolhaUsuario == 1) {
			calcularPreçoTotal();
		} else if (escolhaUsuario == 2) {
			registrarVenda();
		} else if (escolhaUsuario == 3) {
			calcularTroco();
		}  else if (escolhaUsuario == 4) {
			exibirVendas();
		} else if (escolhaUsuario == 5) {
			gerenciarVendedores();
		} else if (escolhaUsuario == 6) {
			gerenciarClientes();
		} else if (escolhaUsuario == 7) {
			informacoesEmpresa();
		} else if (escolhaUsuario == 8) {
			System.out.println("Sistema encerrado.");
			System.exit(0);
		} else {
			System.out.println("Número informado é inválido!");
			System.out.println("Sistema encerrado.");
			System.exit(0);
		}
	}
	
	public static void calcularPreçoTotal() {
		
		System.out.println("------------ Calculando preço total ------------");
		System.out.println(" ");
		
		System.out.println("Informe a quantidade da planta:");
		int quantidadePlanta = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Você informou a quantidade: "+ quantidadePlanta);
		System.out.println(" ");
		
		System.out.println("Informe o valor da planta:");
		float precoPlanta = scan.nextFloat();
		scan.nextLine();
		
		System.out.println("Você informou o valor: R$"+ precoPlanta);
		System.out.println(" ");
		
		float valorTotal = (quantidadePlanta*precoPlanta);
		
		System.out.println("O valor total é: R$"+ valorTotal);
		System.out.println(" ");
		
		
		menuPrincipal();
	}
	
	public static double calcularValorComDesconto(int quantidade, double preco) {
		double valorTotal = quantidade * preco;
		if (quantidade > 10) {
			valorTotal *= 0.95;
		}
		return valorTotal;
	}
	
	public static void registrarVenda() {
		System.out.println("----Registrando Venda----");
		
		System.out.println("Informe a quantidade da planta:");
		int quantidade = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Informe o valor da planta:");
		float preco = scan.nextFloat();
		scan.nextLine();
	

        double valorTotal = calcularValorComDesconto(quantidade, preco);
        double desconto = (quantidade > 10) ? 0.05f * quantidade * preco : 0;


        listaVendas.add(new VendaGabrielinha(quantidade, preco, desconto, valorTotal));

        System.out.println("Venda registrada com sucesso! Valor total: R$" + valorTotal);
        
        menuPrincipal();
	}
	
	public static void exibirVendas() {
		if (listaVendas.isEmpty()) {
			System.out.println("Nenhuma venda registrada ainda.");
			return;
		}
		System.out.println("-----Histórico de Vendas-----");
		for (VendaGabrielinha venda : listaVendas) { // Itera sobre as vendas e imprime os detalhes
			System.out.println(venda);
		}
		
		menuPrincipal();
	}
	
	
	public static void calcularTroco() {
		
		System.out.println("------------ Calculando troco ------------");
		System.out.println(" ");
		
		System.out.println("Informe o valor recebido pelo cliente:");
		float valorRecebido = scan.nextFloat();
		scan.nextLine();
		
		System.out.println("Você informou a quantidade: R$"+ valorRecebido);
		System.out.println(" ");
		
		System.out.println("Informe o valor total da compra:");
		float valorCompra = scan.nextFloat();
		scan.nextLine();
		
		System.out.println("Você informou o valor total da compra: R$"+ valorCompra);
		System.out.println(" ");
		
		float valorTroco = (valorRecebido - valorCompra);
		
		if (valorTroco > 0) {
			System.out.println("O troco do cliente é de: R$"+ valorTroco);
		} else if (valorTroco == 0) {
			System.out.println("O valor recebido é correspondente ao cobrado, não possui troco.");
		} else {
			System.out.println("O valor recebido não é correspondente ao cobrado.");
		}
		
		System.out.println(" ");
		System.out.println(" ");
		menuPrincipal();
	}
	
	static void gerenciarVendedores() {
		
		System.out.println("------------ Menu Vendedores ------------");
		System.out.println("Informe o número correspondente à ação que deseja realizar:");
		System.out.println(" ");
		System.out.println("Digite 1 para adicionar vendedor:");
		System.out.println("Digite 2 para listar vendedores:");
		System.out.println("Digite 3 para calcular média salarial:");
		System.out.println("Digite 4 para calcular bônus salarial:");
		System.out.println("Digite 5 para sair do sistema");

		
		int escolhaUsuario = scan.nextInt();
		scan.nextLine();
		
		if (escolhaUsuario == 1) {
			adicionarVendedor();
		} else if (escolhaUsuario == 2) {
			listarVendedores();
		} else if (escolhaUsuario == 3) {
			calcularMediaSalarial();
		}  else if (escolhaUsuario == 4) {
			calcularBonus();
		} else if (escolhaUsuario == 5) {
			System.out.println("Sistema encerrado.");
			System.exit(0);
		} else {
			System.out.println("Número informado é inválido!");
			System.out.println("Sistema encerrado.");
			System.exit(0);
		}	
	}
	
	static void adicionarVendedor() {
		
		System.out.println("Digite o nome do vendedor:");
        String nome = scan.next();
        scan.nextLine();

        int idade = scan.nextInt();
        System.out.println("Informe a idade do vendedor:");
        System.out.println("Digite a cidade do vendedor:");
        String cidade = scan.next();
        scan.nextLine();
        System.out.println("Digite o bairro do vendedor:");
        String bairro = scan.next();
        scan.nextLine();

        System.out.println("Digite a rua do vendedor:");
        String rua = scan.next();
        scan.nextLine();

        double salarioBase = scan.nextDouble();
        System.out.println("Informe o salário base do vendedor:");


        Vendedor vendedor = new Vendedor(nome, idade, loja, cidade, bairro, rua, salarioBase);


        loja.adicionarVendedor(vendedor);
        System.out.println("Vendedor adicionado com sucesso!");
	}
	
	static void listarVendedores() {
		
		List<Vendedor> vendedores = loja.getVendedores();
        if (vendedores.isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado na loja.");
            return;
        }
        System.out.println("---- Vendedores da Loja " + loja.getNomeFantasia() + " ----");
        for (Vendedor listaVendedor : vendedores) {
            listaVendedor.apresentarse();
        } 
	}
	
	static void calcularMediaSalarial() {
		
		List<Vendedor> vendedores = loja.getVendedores();


        if (vendedores.isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado para calcular a média salarial.");
            return;
        }
        
        System.out.println("Digite o nome do vendedor para calcular a média salarial:");
        String nomeVendedor = scan.next();
        scan.nextLine();

        for (Vendedor vendedor : vendedores) {
            if (vendedor.getNome().equalsIgnoreCase(nomeVendedor)) { 

                double mediaSalarial = vendedor.calcularMediaSalarial();
                System.out.printf("Média salarial de %s: R$%.2f\n", vendedor.getNome(), mediaSalarial);
                return;
            }
        }

        System.out.println("Vendedor não encontrado.");  
	}
	
	static void calcularBonus() {
		
		List<Vendedor> vendedores = loja.getVendedores();
        if (vendedores.isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado para calcular o bônus.");
            return;
        }


        System.out.println("Digite o nome do vendedor para calcular o bônus:");

        String nomeVendedor = scan.next();
        scan.nextLine();


        for (Vendedor vendedor : vendedores) {

            if (vendedor.getNome().equalsIgnoreCase(nomeVendedor)) {

                double bonus = vendedor.calcularBonus();

                System.out.printf("Bônus de %s: R$%.2f\n", vendedor.getNome(), bonus);
                return;
            }
        }

        System.out.println("Vendedor não encontrado.");
	}
	
	static void gerenciarClientes() {
		
		System.out.println("------------ Menu Clientes ------------");
		System.out.println("Informe o número correspondente à ação que deseja realizar:");
		System.out.println(" ");
		System.out.println("Digite 1 para adicionar cliente:");
		System.out.println("Digite 2 para listar clientes:");
		System.out.println("Digite 3 para sair do sistema");

		
		int escolhaUsuario = scan.nextInt();
		scan.nextLine();
		
		if (escolhaUsuario == 1) {
			adicionarCliente();
		} else if (escolhaUsuario == 2) {
			listarClientes();
		} else if (escolhaUsuario == 3) {
			System.out.println("Sistema encerrado.");
			System.exit(0);
		} else {
			System.out.println("Número informado é inválido!");
			System.out.println("Sistema encerrado.");
			System.exit(0);
		}	
	}
	
	static void adicionarCliente() {
		
		System.out.println("Digite o nome do cliente:");

        String nome = scan.next();
        scan.nextLine();

        int idade = scan.nextInt();
        System.out.println("Informe a idade do cliente:");

        System.out.println("Digite a cidade do cliente:");

        String cidade = scan.next();
        scan.nextLine();

        System.out.println("Digite o bairro do cliente:");

        String bairro = scan.next();
        scan.nextLine();


        System.out.println("Digite a rua do cliente:");

        String rua = scan.next();
        scan.nextLine();

        Cliente cliente = new Cliente(nome, idade, cidade, bairro, rua);

        loja.adicionarCliente(cliente);

        System.out.println("Cliente adicionado com sucesso!");
		
	}
	
	static void listarClientes() {
		
		List<Cliente> clientes = loja.getClientes();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado na loja.");
            return;
        }
        System.out.println("---- Clientes da Loja " + loja.getNomeFantasia() + " ----");
        for (Cliente cliente : clientes) {
            cliente.apresentarse();
        }
	}
	
	static void informacoesEmpresa() {
		
		loja.apresentarse();
        System.out.println("Número de clientes: " + loja.contarClientes());
        System.out.println("Número de vendedores: " + loja.contarVendedores());
	}
}
