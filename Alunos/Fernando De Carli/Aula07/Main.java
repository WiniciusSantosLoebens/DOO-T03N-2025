import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static List<historico> registroDeVendas = new ArrayList<>();
    public static Map<String, Integer> vendasPorData = new HashMap<>();

    public static void main(String[] args) {
        int op = 0;

        do {
            menu();
            op = sc.nextInt();
            switch (op) {
                case 1:
                    CalcularPreco();
                    break;
                case 2:
                    CalcularTroco();
                    break;
                case 3:
                    descontoEspecial();
                    break;
                case 4:
                    registroDeVendas();
                    break;
                case 5:
                    registrarVendaPorDia();
                    break;
                case 6:
                    consultarVendasPorDia();
                    break;
                case 7:
                    consultarVendasPorMes();
                    break;
                case 8:
                    testarLoja();
                case 9:
                    criarPedidoFake();
                case 10:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção invalida!!");
            }
        } while (op != 10);
    }
    public static void menu(){
        System.out.println("------BEM-VINDO------");
        System.out.println("\n[1] - Calcular preço total");
        System.out.println("\n[2] - Calcular troco");
        System.out.println("\n[3] - Desconto especial compras a cima de 10 quantidade");
        System.out.println("\n[4] - Registro de vendas recentes");
        System.out.println("\n[5] - Realizar vendas");
        System.out.println("\n[6] - Visualizar vendas por dia");
        System.out.println("\n[7] - Visualizar vendas por mes");
        System.out.println("\n[8] - Testar loja, vendedores e clientes");
        System.out.println("\n[9] - Criar pedido");
        System.out.println("\n[10] - Sair");
        System.out.println("---------------------");
    }

    public static void CalcularPreco (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Quantas plantas comprou?");
        int planta = sc.nextInt();

        System.out.println("Qual valor unitario dela:");
        double valorUnitario = sc.nextDouble();

        double valorTotal =+ planta * valorUnitario;
        System.out.println("Preço total da compra foi : "+valorTotal);
        System.out.println("---------------------");
    }
    public static void CalcularTroco(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Valor recebido pelo cliente;");
        double valorRecebido = sc.nextDouble();

        System.out.println("Valor total da compra:");
        double valorTotal = sc.nextDouble();

        double Troco = valorTotal - valorRecebido;

        if (valorRecebido < valorTotal) {
            System.out.println("Valor insuficiente");
        }
        else {
            System.out.println("Valor do troco: "+Troco);
        }
        System.out.println("---------------------");
    }
    public static void descontoEspecial(){
        System.out.println("Quantas plantas esta comprando? \n");
        int quant = sc.nextInt();
        System.out.println("Qual valor unitario: \n");
        double valorUnitario = sc.nextDouble();

        historico Historico = new historico(quant,valorUnitario);
        registroDeVendas.add(Historico);

        System.out.println("\nVenda realizada com sucesso:");
        Historico.imprimir();
        System.out.println("---------------------");
    }
    public static void registroDeVendas(){
        if(registroDeVendas.isEmpty()){
            System.out.println("Nenhuma venda feita!!");
        }else {
            System.out.println("Registro de vendas ");
            for(historico h : registroDeVendas){
                h.imprimir();
            }
        }
    }
    public static void registrarVendaPorDia(){
        System.out.println("Digite o dia da venda :");
        int day = sc.nextInt();
        System.out.println("Digite o mês da venda :");
        int month = sc.nextInt();
        System.out.println("Digite a quantidade de vendas:");
        int quant = sc.nextInt();

        String data = day + "/" + month;
        vendasPorData.put(data, vendasPorData.getOrDefault(data, 0) + quant);

        System.out.println("Venda registrada com sucesso!!\n");
        System.out.println("---------------------");
    }
    public static void consultarVendasPorDia(){
        System.out.println("Digite o dia da consulta:");
        int day = sc.nextInt();
        System.out.println("Digite o mês da consulta:");
        int month = sc.nextInt();

        String data = day + "/" + month;
        int total = vendasPorData.getOrDefault(data, 0);

        System.out.println("Total de vendas no dia " + data + ": " + total);
    }
    public static void consultarVendasPorMes(){
        System.out.println("Digite o mês de consulta:");
        int month = sc.nextInt();

        int totalVendas = 0;
        for (Map.Entry<String, Integer> entry : vendasPorData.entrySet()) {
            if (entry.getKey().endsWith("/" + month)) {
                totalVendas += entry.getValue();
            }
        }
        System.out.println("Total de vendas no mês " + month + ": " + totalVendas);
    }
    public static void testarLoja() {
        /*
        Loja loja = new Loja("Flor da Vida", "Flor da Vida LTDA", "12.345.678/0001-99", "São Paulo", "Centro", "Rua das Flores");

        Vendedor v1 = new Vendedor("Ana", 30, loja, "São Paulo", "Centro", "Rua A", 3000);
        Vendedor v2 = new Vendedor("Carlos", 28, loja, "São Paulo", "Centro", "Rua B", 3200);
        loja.adicionaVendedor(v1);
        loja.adicionaVendedor(v2);

        Cliente c1 = new Cliente("João", 40, "São Paulo", "Centro", "Rua X");
        Cliente c2 = new Cliente("Maria", 35, "São Paulo", "Centro", "Rua Y");
        loja.adicionaCliente(c1);
        loja.adicionaCliente(c2);

        System.out.println("------ Loja ------");
        loja.apresentarse();

        System.out.println("------ Vendedores ------");
        for (Vendedor v : loja.vendedores) {
            v.apresentarse();
            System.out.println("Média salarial: " + v.calcularMedia());
            System.out.println("Bônus: " + v.calcularBonus());
            System.out.println("---------------------");
        }

        System.out.println("------ Clientes ------");
        for (Cliente c : loja.clientes) {
            c.apresentarse();
        }

        System.out.println("Total de clientes: " + loja.contarClientes());
        System.out.println("Total de vendedores: " + loja.contarVendedores());
        System.out.println("---------------------");

         */
    }

    public static void criarPedidoFake() {

        Endereco enderecoCliente = new Endereco("SP", "São Paulo", "Centro", "Rua das Plantas", 123, "Apto 101");
        Endereco enderecoVendedor = new Endereco("SP", "São Paulo", "Vila Verde", "Av. Floricultura", 456, "Sala 202");

        ArrayList<Double> salarios = new ArrayList<>();
        salarios.add(1.600);
        salarios.add(1.700);
        salarios.add(1.800);

        Loja loja = new Loja("My Plant - Matriz","My Plant ltda", "12345", enderecoCliente);

        Vendedor vendedor = new Vendedor("Carlos Mendes", 35, loja, enderecoVendedor,1.500, salarios);
        Cliente cliente = new Cliente("Joana Silva", 28, enderecoCliente, loja);
        Gerente gerente = new Gerente("Douglas", 25,loja, enderecoVendedor,1.500, salarios);

        Item item1 = new Item(1, "Vaso de Cerâmica", "Decoração", 80.0);
        Item item2 = new Item(2, "Suculenta Mini", "Planta", 20.0);
        List<Item> itens = Arrays.asList(item1, item2);

        Date agora = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(agora);
        cal.add(Calendar.DATE, 2);


        ProcessaPedido processador = new ProcessaPedido();
        Pedido pedido = processador.processar(
                1001,
                agora,
                agora,
                cal.getTime(),
                cliente,
                vendedor,
                "My Plant - Matriz",
                itens
        );

        if (pedido != null) {
            System.out.println("\n Pedido criado com sucesso:");
            pedido.gerarDescricaoVenda();
            System.out.println("Itens do pedido:");
            item1.gerarDescricao();
            item2.gerarDescricao();
            System.out.println("Cliente: ");
            cliente.apresentarse();
            System.out.println("---------");
            System.out.println("Vendedor: ");
            vendedor.apresentarse();
            System.out.println("---------");
            System.out.println("Loja: ");
            loja.apresentarse();
            System.out.println("---------");
            System.out.println("Gerente: ");
            gerente.apresentarse();
            System.out.println("---------");
        } else {
            System.out.println(" Falha ao criar o pedido.");
        }
    }
}