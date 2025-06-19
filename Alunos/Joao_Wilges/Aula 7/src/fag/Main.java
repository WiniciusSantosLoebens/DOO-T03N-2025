package fag;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import objetos.Loja;

public class Main {
    public static void main(String[] args) {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Loja> lista_lojas = new ArrayList<>();

        while (flag) {
            System.out.println("""
                    [1] - Menu Lojas
                    [2] - Menu Vendedores
                    [3] - Menu Clientes
                    [4] - Menu Pedidos
                    [5] - Sair
                    """);
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1 -> exec_menu_lojas(scanner, lista_lojas);
                case 2 -> exec_menu_vendedores(scanner, lista_lojas);
                case 3 -> exec_menu_clientes(scanner, lista_lojas);
                case 4 -> exec_menu_pedidos(scanner, lista_lojas);
                case 5 -> flag = false;
                default -> System.out.println("Opção inválida");
            }
        }
        scanner.close();
    }

    public static void exec_menu_pedidos(Scanner scanner, List<Loja> lojas) {
        System.out.println("Informe o CNPJ da loja: ");
        String cnpj = scanner.next();
        Loja loja = localiza_loja(cnpj, lojas);

        if (loja == null) {
            System.out.println("Loja com CNPJ " + cnpj + " não encontrada.");
            return;
        }

        System.out.println("""
                [1] - Criar Pedido
                [2] - Remover Pedido
                [3] - Contar Pedidos
                [4] - Voltar
                """);
        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1 -> criar_pedido(scanner, loja);
            case 2 -> remover_pedido(scanner, loja);
            case 3 -> contar_pedidos(loja);
            case 4 -> System.out.println("Voltando ao menu anterior...");
            default -> System.out.println("Opção inválida.");
        }
    }

    public static void criar_pedido(Scanner scanner, Loja loja) {
        System.out.println("Informe o ID do pedido: ");
        int id = scanner.nextInt();
        System.out.println("Informe o nome do cliente: ");
        String nomeCliente = scanner.next();
        System.out.println("Informe o vendedor: ");
        String vendedor = scanner.next();
        System.out.println("Informe a data de vencimento da reserva (formato: yyyy-MM-dd): ");
        String dataString = scanner.next();

        Date dataVencimentoReserva;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dataVencimentoReserva = sdf.parse(dataString);
            loja.criarPedido(id, nomeCliente, vendedor, dataVencimentoReserva);
        } catch (ParseException e) {
            System.out.println("Data inválida. Por favor, use o formato yyyy-MM-dd.");
        }
    }

    public static void remover_pedido(Scanner scanner, Loja loja) {
        System.out.println("Informe o ID do pedido a ser removido: ");
        int id = scanner.nextInt();
        if (loja.removerPedido(id)) {
            System.out.println("Pedido removido com sucesso.");
        } else {
            System.out.println("Pedido não encontrado.");
        }
    }

    public static void contar_pedidos(Loja loja) {
        int totalPedidos = loja.contarPedidos();
        System.out.println("Total de pedidos: " + totalPedidos);
    }

    public static void exec_menu_lojas(Scanner scanner, List<Loja> lojas) {
        System.out.println("""
                [1] - Cadastrar Loja
                [2] - Excluir Loja
                [3] - Voltar"""
        );
        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1 -> cadastrar_loja(scanner, lojas);
            case 2 -> excluir_loja(scanner, lojas);
            case 3 -> System.out.println("Voltando ao menu anterior...");
            default -> System.out.println("Opção inválida.");
        }
    }

    public static void exec_menu_vendedores(Scanner scanner, List<Loja> lojas) {
        System.out.println("Informe o CNPJ da loja: ");
        String cnpj = scanner.next();

        Loja loja = localiza_loja(cnpj, lojas);

        if (loja == null) {
            System.out.println("Loja com CNPJ " + cnpj + " não encontrada.");
        } else {
            System.out.println("""
                [1] - Cadastrar Vendedor
                [2] - Excluir Vendedor
                [3] - Voltar
            """);
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1 -> cadastrar_vendedor(scanner, loja);
                case 2 -> excluir_vendedor(scanner, loja);
                case 3 -> System.out.println("Voltando ao menu anterior...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    public static void exec_menu_clientes(Scanner scanner, List<Loja> lojas) {
        System.out.println("Informe o CNPJ da loja: ");
        String cnpj = scanner.next();

        Loja loja = localiza_loja(cnpj, lojas);

        if (loja == null) {
            System.out.println("Loja com CNPJ " + cnpj + " não encontrada.");
            return;
        }

        System.out.println("""
        [1] - Cadastrar Cliente
        [2] - Excluir Cliente
        [3] - Voltar
    """);

        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1 -> {
                System.out.println("Informe o nome do cliente: ");
                String nome = scanner.next();
                System.out.println("Informe a idade do cliente: ");
                int idade = scanner.nextInt();
                System.out.println("Informe a cidade do cliente: ");
                String cidade = scanner.next();
                System.out.println("Informe o bairro do cliente: ");
                String bairro = scanner.next();
                System.out.println("Informe a rua do cliente: ");
                String rua = scanner.next();
                System.out.println("Informe o complemento da residência do cliente: ");
                String complemento = scanner.next();
                System.out.println("Informe o número da residência do cliente: ");
                String numero = scanner.next();

                loja.cadastrarCliente(nome, idade, cidade, bairro, rua, complemento, numero);
            }
            case 2 -> {
                System.out.println("Informe o nome do cliente: ");
                String nome = scanner.next();
                loja.excluirCliente(nome);
            }
            case 3 -> System.out.println("Voltando ao menu anterior...");
            default -> System.out.println("Opção inválida.");
        }
    }

    public static void cadastrar_loja(Scanner scanner, List<Loja> lojas) {
        System.out.println("Informe o nome da loja: ");
        String nome_loja = scanner.next();
        System.out.println("Informe o nome fantasia da loja: ");
        String nome_fantasia = scanner.next();
        System.out.println("Informe a razão social: ");
        String razao_social = scanner.next();
        System.out.println("Informe o CNPJ:");
        String cnpj = scanner.next();
        System.out.println("Informe a cidade: ");
        System.out.println("Informe a cidade: ");
        String cidade = scanner.next();
        System.out.println("Informe o bairro: ");
        String bairro = scanner.next();
        System.out.println("Informe a rua: ");
        String rua = scanner.next();

        lojas.add(new Loja(
                nome_loja,
                nome_fantasia,
                razao_social,
                cnpj,
                cidade,
                bairro,
                rua
        ));
        System.out.println("Loja cadastrada!");
    }

    public static void excluir_loja(Scanner scanner, List<Loja> lojas) {
        System.out.println("Informe o CNPJ da Loja: ");
        String cnpj = scanner.next();
        Loja loja = localiza_loja(cnpj, lojas);

        if (loja == null) {
            System.out.println("Loja com CNPJ " + cnpj + " não encontrada.");
            return;
        }
        lojas.remove(loja);
        System.out.println("Loja com CNPJ " + cnpj + " foi removida.");
    }

    public static void cadastrar_vendedor(Scanner scanner, Loja loja) {
        System.out.println("Informe o nome do vendedor: ");
        String nome = scanner.next();

        System.out.println("Informe a idade do vendedor: ");
        int idade = scanner.nextInt();

        System.out.println("Informe a cidade do vendedor: ");
        String cidade = scanner.next();

        System.out.println("Informe o bairro do vendedor: ");
        String bairro = scanner.next();

        System.out.println("Informe a rua do vendedor: ");
        String rua = scanner.next();

        System.out.println("Informe o complemento do vendedor: ");
        String complemento = scanner.next();

        System.out.println("Informe o número do vendedor: ");
        String numero = scanner.next();

        System.out.println("Informe o salário base do vendedor: ");
        double salarioBase = scanner.nextDouble();
        loja.cadastrarVendedor(nome, idade, cidade, rua, bairro, complemento, numero, salarioBase);
    }


    public static void excluir_vendedor(Scanner scanner, Loja loja) {
        System.out.println("Informe o nome do vendedor: ");
        String nome = scanner.next();
        loja.excluirVendedor(nome);
    }

    public static Loja localiza_loja(String cnpj_procuram, List<Loja> lojas) {
        Iterator<Loja> iterator = lojas.iterator();

        while (iterator.hasNext()) {
            Loja loja = iterator.next();
            if (loja.getCnpj().equals(cnpj_procuram)) {
                return loja;
            }
        }
        return null;
    }
}
