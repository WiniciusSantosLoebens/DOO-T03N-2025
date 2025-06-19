package fag;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import objetos.Loja;

public class Main {
    public static void main(String[] args) {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        int percent_desconto = 0;
        ArrayList<Loja> lista_lojas = new ArrayList<>();

        scanner.nextLine();
        while (flag) {
            System.out.println("""
                    [1] - Menu Lojas
                    [2] - Menu Vendedores
                    [3] - Menu Clientes
                    [4] - Sair"""
            );
            int opcao = scanner.nextInt();
            switch (opcao) {

                case 1 -> exec_menu_lojas(scanner, lista_lojas);
                case 2 -> exec_menu_vendedores(scanner, lista_lojas);
                case 3 -> exec_menu_clientes(scanner, lista_lojas);

                case 4 -> {
                    flag = false;
                }
                default -> {System.out.println("Opção inválida");}
            }
        }
        scanner.close();
    }

    public static void exec_menu_lojas(Scanner scanner, List<Loja> lojas) {
        System.out.println("""
                [1] - Cadastrar Loja
                [2] - Excluir Loja
                [3] - Voltar"""
        );
        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1 -> {
                cadastrar_loja(scanner, lojas);
            }
            case 2 -> {
                excluir_loja(scanner, lojas);
            }
            case 3 -> System.out.println("Voltando ao menu anterior...");
            default -> System.out.println("Sair.");
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
                case 1 -> loja.cadastrarVendedor(scanner, loja);
                case 2 -> loja.excluirVendedor(scanner, loja);
                case 3 -> System.out.println("Voltando ao menu anterior...");
                default -> System.out.println("Sair.");
            }
        }
    }
    public static void exec_menu_clientes(Scanner scanner,List<Loja> lojas){
        System.out.println("Informe o CNPJ da loja: ");
        String cnpj = scanner.next();

        Loja loja = localiza_loja(cnpj, lojas);

        if (loja == null) {
            System.out.println("Loja com CNPJ " + cnpj + " não encontrada.");
        } else {
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
                    loja.cadastrarCliente(nome, idade, cidade, bairro, rua);
                }
                case 2 -> {
                    System.out.println("Informe o nome do cliente: ");
                    String nome = scanner.next();
                    loja.excluirCliente(nome);
                }
                case 3 -> System.out.println("Voltando ao menu anterior...");
                default -> System.out.println("Sair.");
            }
        }
    }

    public static void cadastrar_loja(Scanner scanner, List<Loja> lojas) {
        System.out.println("Informe o nome da loja: ");
        String nome_loja = scanner.next();
        System.out.println("Informe o nome fantasia da loja: ");
        String nome_fantasia = scanner.next();
        System.out.println("Informe a razao social: ");
        String razao_social = scanner.next();
        System.out.println("Informe o CNPJ:");
        String cnpj = scanner.next();
        System.out.println("Informe a cidade: ");
        String cidade = scanner.next();
        System.out.println("Informe o bairro");
        String bairro = scanner.next();
        System.out.println("Informe a rua");
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
        System.out.println("Loja cadastrada !");
    }
    public static void excluir_loja(Scanner scanner, List<Loja> lojas) {
        System.out.println("Informe o CNPJ da Loja: ");
        String cnpj = scanner.next();
        Loja loja = localiza_loja(cnpj, lojas);

        if (loja != null) {
            lojas.remove(loja);
            System.out.println("Loja com CNPJ " + cnpj + " foi removida.");
        } else {
            System.out.println("Loja com CNPJ " + cnpj + " não encontrada.");
        }
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

