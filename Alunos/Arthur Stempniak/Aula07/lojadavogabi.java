import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import objetos.cliente;
import objetos.endereco;
import objetos.loja;
import objetos.vendedor;

public class lojadavogabi {

    public static Scanner scan = new Scanner(System.in);

    static ArrayList<String> vendas = new ArrayList<>();
    static HashMap<String, Integer> vendasPorDia = new HashMap<>();
    static ArrayList<loja> lojas = new ArrayList<>();
    static ArrayList<cliente> clientes = new ArrayList<>();
    static loja lojaAtual;

    public static void main(String[] args) {
        boolean loop = true;
        double total = 0;

        // Inicializando uma loja padrão
        loja lojaPadrao = new loja("Vogabi", "Rua das Plantas, 123");
        lojas.add(lojaPadrao);
        lojaAtual = lojaPadrao;

        // Criando vendedores para a loja
        vendedor vendedor1 = new vendedor("Maria", new endereco("Rua das Flores", "Cidade A", "Bairro X", "45", ""), 1);
        vendedor vendedor2 = new vendedor("José", new endereco("Avenida Central", "Cidade B", "Bairro Y", "78", ""), 2);
        lojaPadrao.adicionarVendedor(vendedor1);
        lojaPadrao.adicionarVendedor(vendedor2);

        System.out.println(""" 
            Bom dia, vozinha!! 
            Seja bem-vinda a sua calculadora! 
            """);

        while (loop) {
            System.out.println("""
                Escolha uma das opções abaixo:
                1) Calcular Total
                2) Calcular Troco
                3) Registrar Venda
                4) Exibir Vendas Registradas
                5) Exibir Vendas por Dia
                6) Exibir Vendedores
                7) Criar Nova Loja
                8) Selecionar Loja
                9) Criar Novo Cliente
                10) Exibir Clientes
                11) Exibir Vendas do Cliente
                12) Sair
                """);

            System.out.print("Escolha: ");
            int escolha = scan.nextInt();

            switch (escolha) {
                case 1 -> calcularTotal();
                case 2 -> calcularTroco(total);
                case 3 -> total = registrarVenda();
                case 4 -> exibirVendasRegistradas();
                case 5 -> exibirVendasPorDia();
                case 6 -> exibirVendedores();
                case 7 -> criarNovaLoja();
                case 8 -> selecionarLoja();
                case 9 -> criarNovoCliente();
                case 10 -> exibirClientes();
                case 11 -> exibirVendasDoCliente();
                case 12 -> {
                    System.out.println("Saindo... Até logo!");
                    loop = false;
                }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    public static void calcularTotal() {
        System.out.println("Você escolheu calcular o total.");
        System.out.print("Informe a quantidade de plantas: ");
        int qtdProdutos = scan.nextInt();
        System.out.print("Informe o preço da planta: ");
        double valorProduto = scan.nextDouble();

        double total = valorProduto * qtdProdutos;
        double desconto = 0;

        if (qtdProdutos >= 10) {
            desconto = total * 0.05;
            total -= desconto;
            System.out.printf("Você recebeu um desconto de 5%%! O valor com desconto é: R$ %.2f\n", total);
        }

        System.out.printf("O total da compra é: R$ %.2f\n", total);
    }

    public static void calcularTroco(double total) {
        System.out.println("Você escolheu calcular o troco.");
        System.out.printf("O total da compra deu: R$ %.2f\n", total);

        System.out.print("Insira o valor que o cliente lhe deu: ");
        double valorCliente = scan.nextDouble();

        double troco = valorCliente - total;

        if (valorCliente < total) {
            System.out.printf("Dinheiro entregue pelo cliente não é suficiente.\nFalta a quantia de: R$ %.2f\n", -troco);
        } else if (valorCliente == total) {
            System.out.println("Não há troco! O valor está exato.");
        } else {
            System.out.printf("Você deve retornar o valor de: R$ %.2f\n", troco);
        }
    }

    public static double registrarVenda() {
        System.out.println("Você escolheu registrar uma venda.");

        // Escolher o cliente
        System.out.println("Escolha o cliente:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ") " + clientes.get(i).getNome());
        }
        int escolhaCliente = scan.nextInt();
        cliente clienteEscolhido = clientes.get(escolhaCliente - 1);

        System.out.print("Informe a quantidade de plantas: ");
        int qtdProdutos = scan.nextInt();
        System.out.print("Informe o preço da planta: ");
        double valorProduto = scan.nextDouble();

        double total = valorProduto * qtdProdutos;
        double desconto = 0;

        if (qtdProdutos >= 10) {
            desconto = total * 0.05;
            total -= desconto;
        }

        System.out.print("Informe o dia (DD): ");
        String dia = scan.next();
        System.out.print("Informe o mês (MM): ");
        String mes = scan.next();
        String chave = dia + "-" + mes;

        vendasPorDia.put(chave, vendasPorDia.getOrDefault(chave, 0) + qtdProdutos);

        String venda = "Venda: Quantidade = " + qtdProdutos + ", Total = R$ " + total + ", Desconto = R$ " + desconto;

        // Registrar a venda no cliente
        clienteEscolhido.adicionarVenda(venda);
        vendas.add(venda);
        System.out.printf("Venda registrada com sucesso para o cliente %s! Total com desconto (se houver): R$ %.2f\n", clienteEscolhido.getNome(), total);

        return total;
    }

    public static void exibirVendasRegistradas() {
        System.out.println("Exibindo vendas registradas:");
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada até o momento.");
        } else {
            for (String venda : vendas) {
                System.out.println(venda);
            }
        }
    }

    public static void exibirVendasPorDia() {
        System.out.println("Exibindo vendas por dia:");
        if (vendasPorDia.isEmpty()) {
            System.out.println("Nenhuma venda registrada até o momento.");
        } else {
            vendasPorDia.forEach((dia, qtd) -> System.out.println("Dia " + dia + ": " + qtd + " vendas"));
        }
    }

    public static void exibirVendedores() {
        System.out.println("Exibindo vendedores da loja atual:");
        lojaAtual.exibirVendedores();
    }

    public static void criarNovaLoja() {
        System.out.println("Criando uma nova loja...");
        System.out.print("Informe o nome da loja: ");
        scan.nextLine(); // Consumir a linha restante
        String nomeLoja = scan.nextLine();
        System.out.print("Informe o endereço da loja: ");
        String enderecoLoja = scan.nextLine();

        loja novaLoja = new loja(nomeLoja, enderecoLoja);
        lojas.add(novaLoja);
        System.out.println("Loja criada com sucesso!");
    }

    public static void selecionarLoja() {
        System.out.println("Selecionando uma loja...");
        for (int i = 0; i < lojas.size(); i++) {
            System.out.println((i + 1) + ") " + lojas.get(i).getNome());
        }
        int escolhaLoja = scan.nextInt();
        lojaAtual = lojas.get(escolhaLoja - 1);
        System.out.println("Loja selecionada: " + lojaAtual.getNome());
    }

    public static void criarNovoCliente() {
        System.out.println("Criando um novo cliente...");
        System.out.print("Informe o nome do cliente: ");
        scan.nextLine(); // Consumir a linha restante
        String nomecliente = scan.nextLine();
        System.out.print("Informe o CPF do cliente: ");
        String cpfcliente = scan.nextLine();
        System.out.print("Informe o telefone do cliente: ");
        String telefonecliente = scan.nextLine();
        System.out.print("Informe o endereço do cliente: ");
        String enderecocliente = scan.nextLine();

        endereco enderecoClienteObj = new endereco("Estado", "Cidade", "Bairro", "123", ""); // Ajustar conforme necessário
        cliente novocliente = new cliente(nomecliente, enderecoClienteObj, cpfcliente, telefonecliente);
        clientes.add(novocliente);
        System.out.println("Cliente criado com sucesso!");
    }

    public static void exibirClientes() {
        System.out.println("Exibindo clientes:");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (cliente c : clientes) {
                System.out.println(c.getNome());
            }
        }
    }

    public static void exibirVendasDoCliente() {
        System.out.println("Escolha o cliente para exibir as vendas:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ") " + clientes.get(i).getNome());
        }
        int escolhaCliente = scan.nextInt();
        cliente clienteEscolhido = clientes.get(escolhaCliente - 1);
        clienteEscolhido.exibirVendas();
    }
}
