import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyPlantApp {

    private List<Cliente> clientes = new ArrayList<>();
    private List<Vendedor> vendedores = new ArrayList<>();
    private List<Gerente> gerentes = new ArrayList<>();
    private List<Loja> lojas = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();

    public void executar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sistema MyPlant iniciado...");

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Vendedor");
            System.out.println("3. Cadastrar Gerente");
            System.out.println("4. Cadastrar Loja");
            System.out.println("5. Criar Pedido");
            System.out.println("6. Visualizar Pedidos");
            System.out.println("7. Visualizar Vendas");
            System.out.println("8. Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine();  

            switch (escolha) {
                case 1:
                    cadastrarCliente(scanner);
                    break;
                case 2:
                    cadastrarVendedor(scanner);
                    break;
                case 3:
                    cadastrarGerente(scanner);
                    break;
                case 4:
                    cadastrarLoja(scanner);
                    break;
                case 5:
                    criarPedido(scanner);
                    break;
                case 6:
                    visualizarPedidos();
                    break;
                case 7:
                    visualizarVendas();
                    break;
                case 8:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void cadastrarCliente(Scanner scanner) {
        System.out.println("Digite o nome do cliente:");
        String nome = scanner.nextLine();
        System.out.println("Digite a idade do cliente:");
        int idade = scanner.nextInt();
        scanner.nextLine();  
        Endereco endereco = obterEndereco(scanner);
        Cliente cliente = new Cliente(nome, idade, endereco);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void cadastrarVendedor(Scanner scanner) {
        System.out.println("Digite o nome do vendedor:");
        String nome = scanner.nextLine();
        System.out.println("Digite a idade do vendedor:");
        int idade = scanner.nextInt();
        scanner.nextLine();  
        Endereco endereco = obterEndereco(scanner);
        Loja loja = selecionarLoja(scanner);
        System.out.println("Digite o salário base do vendedor:");
        double salarioBase = scanner.nextDouble();
        Vendedor vendedor = new Vendedor(nome, idade, loja, endereco, salarioBase);
        vendedores.add(vendedor);
        System.out.println("Vendedor cadastrado com sucesso!");
    }

    private void cadastrarGerente(Scanner scanner) {
        System.out.println("Digite o nome do gerente:");
        String nome = scanner.nextLine();
        System.out.println("Digite a idade do gerente:");
        int idade = scanner.nextInt();
        scanner.nextLine();  
        Endereco endereco = obterEndereco(scanner);
        Loja loja = selecionarLoja(scanner);
        System.out.println("Digite o salário base do gerente:");
        double salarioBase = scanner.nextDouble();
        Gerente gerente = new Gerente(nome, idade, loja, endereco, salarioBase);
        gerentes.add(gerente);
        System.out.println("Gerente cadastrado com sucesso!");
    }

    private void cadastrarLoja(Scanner scanner) {
        System.out.println("Digite o nome fantasia da loja:");
        String nomeFantasia = scanner.nextLine();
        System.out.println("Digite a razão social da loja:");
        String razaoSocial = scanner.nextLine();
        System.out.println("Digite o CNPJ da loja:");
        String cnpj = scanner.nextLine();
        Endereco endereco = obterEndereco(scanner);
        Loja loja = new Loja(nomeFantasia, razaoSocial, cnpj, endereco);
        lojas.add(loja);
        System.out.println("Loja cadastrada com sucesso!");
    }

    private void criarPedido(Scanner scanner) {
        System.out.println("Digite o ID do pedido:");
        int id = scanner.nextInt();
        scanner.nextLine();  
        System.out.println("Digite a data de criação (formato YYYY-MM-DD):");
        String dataCriacao = scanner.nextLine();
        System.out.println("Digite a data de pagamento (formato YYYY-MM-DD):");
        String dataPagamento = scanner.nextLine();
        System.out.println("Digite a data de vencimento da reserva (formato YYYY-MM-DD):");
        String dataVencimentoReserva = scanner.nextLine();
        Cliente cliente = selecionarCliente(scanner);
        Vendedor vendedor = selecionarVendedor(scanner);
        Loja loja = selecionarLoja(scanner);
        List<Item> itens = selecionarItens(scanner);

        Pedido pedido = new Pedido(id, LocalDate.parse(dataCriacao), LocalDate.parse(dataPagamento),
                LocalDate.parse(dataVencimentoReserva), cliente, vendedor, loja, itens);
        pedidos.add(pedido);
        System.out.println("Pedido criado com sucesso!");
    }

    private void visualizarPedidos() {
        System.out.println("Pedidos registrados:");
        for (Pedido pedido : pedidos) {
            pedido.gerarDescricaoVenda();
        }
    }

    private void visualizarVendas() {
        
        System.out.println("Vendas registradas:");
        for (Pedido pedido : pedidos) {
            pedido.gerarDescricaoVenda();
        }
    }

    private Endereco obterEndereco(Scanner scanner) {
        System.out.println("Digite o estado do endereço:");
        String estado = scanner.nextLine();
        System.out.println("Digite a cidade do endereço:");
        String cidade = scanner.nextLine();
        System.out.println("Digite o bairro do endereço:");
        String bairro = scanner.nextLine();
        System.out.println("Digite o número do endereço:");
        String numero = scanner.nextLine();
        System.out.println("Digite o complemento (se houver):");
        String complemento = scanner.nextLine();
        return new Endereco(estado, cidade, bairro, numero, complemento);
    }

    private Loja selecionarLoja(Scanner scanner) {
        System.out.println("Selecione uma loja (ID):");
        for (int i = 0; i < lojas.size(); i++) {
            System.out.println((i + 1) + ". " + lojas.get(i).getNomeFantasia());
        }
        int escolha = scanner.nextInt() - 1;
        scanner.nextLine();  
        return lojas.get(escolha);
    }

    private Cliente selecionarCliente(Scanner scanner) {
        System.out.println("Selecione um cliente (ID):");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNome());
        }
        int escolha = scanner.nextInt() - 1;
        scanner.nextLine();  
        return clientes.get(escolha);
    }

    private Vendedor selecionarVendedor(Scanner scanner) {
        System.out.println("Selecione um vendedor (ID):");
        for (int i = 0; i < vendedores.size(); i++) {
            System.out.println((i + 1) + ". " + vendedores.get(i).getNome());
        }
        int escolha = scanner.nextInt() - 1;
        scanner.nextLine();  
        return vendedores.get(escolha);
    }

    private List<Item> selecionarItens(Scanner scanner) {
        List<Item> itens = new ArrayList<>();
        System.out.println("Digite o número de itens para o pedido:");
        int numItens = scanner.nextInt();
        scanner.nextLine();  
        for (int i = 0; i < numItens; i++) {
            System.out.println("Digite o ID do item:");
            int id = scanner.nextInt();
            scanner.nextLine();  
            System.out.println("Digite o nome do item:");
            String nome = scanner.nextLine();
            System.out.println("Digite o tipo do item:");
            String tipo = scanner.nextLine();
            System.out.println("Digite o valor do item:");
            double valor = scanner.nextDouble();
            scanner.nextLine();  
            itens.add(new Item(id, nome, tipo, valor));
        }
        return itens;
    }
}
