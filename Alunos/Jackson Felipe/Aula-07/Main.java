import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Pedido> pedidos = new ArrayList<>();
        ArrayList<Gerente> gerentes = new ArrayList<>();
        ArrayList<Item> itens = new ArrayList<>();
        ArrayList<Endereco> enderecos = new ArrayList<>();

        int opcao;
        do {
            System.out.println("\n======== MENU ========");
            System.out.println("[1] - Cadastrar Gerente");
            System.out.println("[2] - Cadastrar Item");
            System.out.println("[3] - Cadastrar Endereço");
            System.out.println("[4] - Cadastrar Pedido");
            System.out.println("[5] - Listar Pedidos");
            System.out.println("[6] - Sair");
            System.out.println("[7] - Criar Pedido (fake)");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Idade: ");
                    int idade = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Loja: ");
                    String loja = sc.nextLine();
                    System.out.print("Salário base: ");
                    double salario = sc.nextDouble();

                    Gerente gerente = new Gerente(nome, idade, loja, salario);
                    gerentes.add(gerente);
                    System.out.println("Gerente cadastrado!");
                    break;

                case 2:
                    System.out.print("ID: ");
                    int idItem = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nome: ");
                    String nomeItem = sc.nextLine();
                    System.out.print("Tipo: ");
                    String tipoItem = sc.nextLine();
                    System.out.print("Valor: ");
                    double valorItem = sc.nextDouble();

                    Item item = new Item(idItem, nomeItem, tipoItem, valorItem);
                    itens.add(item);
                    System.out.println("Item cadastrado!");
                    break;

                case 3:
                    System.out.print("Cidade: ");
                    String cidade = sc.nextLine();
                    System.out.print("Bairro: ");
                    String bairro = sc.nextLine();
                    System.out.print("Rua: ");
                    String rua = sc.nextLine();

                    Endereco endereco = new Endereco(cidade, bairro, rua);
                    enderecos.add(endereco);
                    System.out.println("Endereço cadastrado!");
                    break;

                case 4:
                    if (gerentes.isEmpty() || enderecos.isEmpty() || itens.isEmpty()) {
                        System.out.println("Cadastre pelo menos um gerente, endereço e item antes.");
                        break;
                    }

                    Pedido novoPedido = new Pedido(
                            pedidos.size() + 1,
                            new Date(),
                            new Date(System.currentTimeMillis() + 86400000),
                            new Date(System.currentTimeMillis() + 172800000),
                            "Cliente Teste",
                            gerentes.get(0),
                            enderecos.get(0),
                            itens.toArray(new Item[0])
                    );

                    pedidos.add(novoPedido);
                    System.out.println("Pedido criado!");
                    break;

                case 5:
                    for (Pedido p : pedidos) {
                        p.gerarDescricaoVenda();
                    }
                    break;

                case 6:
                    System.out.println("Encerrando...");
                    break;

                case 7:
                    // Dados fake para teste
                    Gerente gerenteFake = new Gerente("Gerente Fictício", 35, "Loja XPTO", 5000);
                    Endereco enderecoFake = new Endereco("Cascavel", "Centro", "Rua dos Testes");
                    Item[] itensFake = {
                            new Item(1, "Produto A", "Categoria 1", 10.5),
                            new Item(2, "Produto B", "Categoria 2", 20.75)
                    };

                    Pedido pedidoFake = new Pedido(
                            pedidos.size() + 1,
                            new Date(),
                            new Date(System.currentTimeMillis() + 86400000),
                            new Date(System.currentTimeMillis() + 172800000),
                            "Cliente Fictício",
                            gerenteFake,
                            enderecoFake,
                            itensFake
                    );

                    pedidos.add(pedidoFake);
                    System.out.println("Pedido fake criado com sucesso!");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 6);

        sc.close();
    }
}
