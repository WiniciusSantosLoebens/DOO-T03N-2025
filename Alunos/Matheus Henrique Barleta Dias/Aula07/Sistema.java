import java.util.*;

public class Sistema {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RegistroVendas registroVendas = new RegistroVendas();
        Loja myPlant = new Loja("My Plant", "My Plant LTDA", 123456789, "Centro", "Centro Velho", "Rua das Flores");

        List<Vendedor> vendedores = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();

        int opcao;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("[1] Registrar Venda");
            System.out.println("[2] Histórico de Vendas");
            System.out.println("[3] Consultar Vendas por Dia");
            System.out.println("[4] Consultar Vendas por Mês");
            System.out.println("[5] Registrar Vendedor");
            System.out.println("[6] Registrar Cliente");
            System.out.println("[7] Listar Vendedores");
            System.out.println("[8] Listar Clientes");
            System.out.println("[9] Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    registroVendas.registrarVenda(sc);
                    break;
                case 2:
                    registroVendas.exibirHistorico();
                    break;
                case 3:
                    registroVendas.consultarVendasPorDia(sc);
                    break;
                case 4:
                    registroVendas.consultarVendasPorMes(sc);
                    break;
                case 5:
                    System.out.print("Nome do vendedor: ");
                    String nomeV = sc.nextLine();
                    System.out.print("Idade: ");
                    int idadeV = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Cidade: ");
                    String cidadeV = sc.nextLine();
                    System.out.print("Bairro: ");
                    String bairroV = sc.nextLine();
                    System.out.print("Rua: ");
                    String ruaV = sc.nextLine();
                    System.out.print("Salário base: ");
                    double salarioBase = sc.nextDouble();
                    sc.nextLine();

                    Vendedor v = new Vendedor(nomeV, idadeV, myPlant, cidadeV, bairroV, ruaV, salarioBase);
                    vendedores.add(v);
                    myPlant.setVendedores(vendedores.toArray(new Vendedor[0]));
                    System.out.println("Vendedor registrado com sucesso.");
                    break;
                case 6:
                    System.out.print("Nome do cliente: ");
                    String nomeC = sc.nextLine();
                    System.out.print("Idade: ");
                    int idadeC = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Cidade: ");
                    String cidadeC = sc.nextLine();
                    System.out.print("Bairro: ");
                    String bairroC = sc.nextLine();
                    System.out.print("Rua: ");
                    String ruaC = sc.nextLine();

                    Cliente c = new Cliente(nomeC, idadeC, cidadeC, bairroC, ruaC);
                    clientes.add(c);
                    myPlant.setClientes(clientes.toArray(new Cliente[0]));
                    System.out.println("Cliente registrado com sucesso.");
                    break;
                case 7:
                    if (vendedores.isEmpty()) {
                        System.out.println("Nenhum vendedor cadastrado.");
                    } else {
                        for (Vendedor ven : vendedores) {
                            ven.apresentarSe();
                        }
                    }
                    break;
                case 8:
                    if (clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                    } else {
                        for (Cliente cli : clientes) {
                            cli.apresentarSe();
                        }
                    }
                    break;
                case 9:
                    System.out.println("Você saiu.");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 9);

        sc.close();
    }
}