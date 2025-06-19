import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import objetos.cliente;
import objetos.loja;
import objetos.vendedor;

public class lojadavogabi {

    public static Scanner scan = new Scanner(System.in);

    static ArrayList<String> vendas = new ArrayList<>();
    static HashMap<String, Integer> vendasPorDia = new HashMap<>();
    static ArrayList<loja> lojas = new ArrayList<>();
    static loja lojaAtual;  // Loja atual selecionada
    static ArrayList<cliente> clientes = new ArrayList<>();  // Lista de clientes

    public static void main(String[] args) {

        boolean loop = true;
        double valorProduto = 0;
        double total = 0;

        // Inicializando uma loja padrão
        loja lojaPadrao = new loja("Vogabi", "Rua das Plantas, 123");
        lojas.add(lojaPadrao);
        lojaAtual = lojaPadrao;

        // Criando vendedores para a loja
        vendedor vendedor1 = new vendedor("Maria", 1);
        vendedor vendedor2 = new vendedor("José", 2);
        lojaPadrao.adicionarVendedor(vendedor1);
        lojaPadrao.adicionarVendedor(vendedor2);

        System.out.println("Bom dia, vozinha!!" +
                "\nSeja bem-vinda a sua calculadora!");

        while (loop) {
            int escolha;

            System.out.println("\nEscolha uma das opcoes abaixo" +
                    "\n1) Calcular Total" +
                    "\n2) Calcular Troco" +
                    "\n3) Registrar Venda" +
                    "\n4) Exibir Vendas Registradas" +
                    "\n5) Exibir Vendas por Dia" +
                    "\n6) Exibir Vendedores" +
                    "\n7) Criar Nova Loja" +
                    "\n8) Selecionar Loja" +
                    "\n9) Criar Novo Cliente" +
                    "\n10) Exibir Clientes" +
                    "\n11) Exibir Vendas do Cliente" +
                    "\n12) Sair");

            escolha = scan.nextInt();

            if (escolha == 1) {
                System.out.println("Voce escolheu calcular o total.");
                System.out.print("Informe a quantidade de plantas: ");

                int qtdProdutos = scan.nextInt();
                System.out.print("Informe o preco da planta: ");
                valorProduto = scan.nextDouble();

                total = valorProduto * qtdProdutos;
                double desconto = 0;

                if (qtdProdutos >= 10) {
                    desconto = total * 0.05;
                    total -= desconto;
                    System.out.printf("Voce recebeu um desconto de 5%%! O valor com desconto e: R$ %.2f\n", total);
                }

                vendas.add("Venda: Quantidade = " + qtdProdutos + ", Total = R$ " + total + ", Desconto = R$ " + desconto);
                System.out.printf("O total da compra e: R$ %.2f\n", total);
            }

            else if (escolha == 2) {
                System.out.println("Você escolheu calcular o troco.");

                System.out.printf("O total da compra deu: R$ %.2f\n", total);

                System.out.println("Insira o valor que o cliente lhe deu: ");
                double valorCliente = scan.nextDouble();

                double troco = valorCliente - total;

                if (valorCliente < total) {
                    System.out.printf("Dinheiro entregue pelo cliente não é suficiente.\nFalta a quantia de: R$ %.2f\n", -troco);
                } else if (valorCliente == total) {
                    System.out.println("Não há troco! O valor está exato.");
                } else {
                    System.out.printf("Você deve retornar o valor de: R$ %.2f\n", troco);
                }

            } else if (escolha == 3) {
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
                valorProduto = scan.nextDouble();

                total = valorProduto * qtdProdutos;
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
            }

            else if (escolha == 4) {
                System.out.println("Exibindo vendas registradas:");
                if (vendas.isEmpty()) {
                    System.out.println("Nenhuma venda registrada até o momento.");
                } else {
                    for (String venda : vendas) {
                        System.out.println(venda);
                    }
                }
            }

            else if (escolha == 9) {
                System.out.println("Criando um novo cliente...");
                System.out.print("Informe o nome do cliente: ");
                scan.nextLine(); // Para consumir a linha restante
                String nomeCliente = scan.nextLine();
                System.out.print("Informe o CPF do cliente: ");
                String cpfCliente = scan.nextLine();
                System.out.print("Informe o telefone do cliente: ");
                String telefoneCliente = scan.nextLine();
                System.out.print("Informe o endereço do cliente: ");
                String enderecoCliente = scan.nextLine();

                cliente novoCliente = new cliente(nomeCliente, cpfCliente, telefoneCliente, enderecoCliente);
                clientes.add(novoCliente);
                System.out.println("Cliente criado com sucesso!");
            }

            else if (escolha == 11) {
                // Exibir vendas de um cliente
                System.out.println("Escolha o cliente para exibir as vendas:");
                for (int i = 0; i < clientes.size(); i++) {
                    System.out.println((i + 1) + ") " + clientes.get(i).getNome());
                }
                int escolhaCliente = scan.nextInt();
                cliente clienteEscolhido = clientes.get(escolhaCliente - 1);
                clienteEscolhido.exibirVendas();
            }

            else if (escolha == 12) {
                System.out.println("Saindo... Até logo!");
                loop = false;
            }

            else {
                System.out.println("Opção inválida, tente novamente.");
            }
        }
    }
}
