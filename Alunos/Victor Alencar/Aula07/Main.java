package kdkak;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static loja minhaLoja;

    public static void main(String[] args) {
        
        endereco endV1 = new endereco("SP", "São Paulo", "Centro", 100, "Apto 101");
        endereco endV2 = new endereco("SP", "São Paulo", "Centro", 200, "Casa");
        endereco endC1 = new endereco("SP", "São Paulo", "Centro", 300, "Loja");
        endereco endC2 = new endereco("SP", "São Paulo", "Centro", 400, "Fundos");
        endereco endLoja = new endereco("SP", "São Paulo", "Centro", 500, "Sede");

        
        vendedor v1 = new vendedor("Carlos Silva", 30, endV1, "Loja XYZ", 2500);
        vendedor v2 = new vendedor("Ana Souza", 28, endV2, "Loja XYZ", 2800);

        
        cliente c1 = new cliente("João Oliveira", 45, endC1);
        cliente c2 = new cliente("Maria Pereira", 34, endC2);

        
        minhaLoja = new loja("Loja XYZ", "XYZ Comércio Ltda.", "12.345.678/0001-99", endLoja);
        minhaLoja.adicionarVendedor(v1);
        minhaLoja.adicionarVendedor(v2);
        minhaLoja.adicionarCliente(c1);
        minhaLoja.adicionarCliente(c2);

        menu();
    }

    public static void menu() {
        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Mostrar informações dos vendedores");
            System.out.println("2. Mostrar informações dos clientes");
            System.out.println("3. Mostrar informações da loja");
            System.out.println("4. Calcular média de salários");
            System.out.println("5. Calcular bônus dos vendedores");
            System.out.println("6. Mostrar gerente");
            System.out.println("7. Criar pedido");
            System.out.println("8. Sair");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    for (vendedor v : minhaLoja.vendedores) {
                        v.apresentarse();
                        System.out.println("---");
                    }
                    break;
                case 2:
                    for (cliente c : minhaLoja.clientes) {
                        c.apresentarse();
                        System.out.println("---");
                    }
                    break;
                case 3:
                    minhaLoja.apresentarse();
                    System.out.println("Clientes: " + minhaLoja.contarClientes());
                    System.out.println("Vendedores: " + minhaLoja.contarVendedores());
                    break;
                case 4:
                    for (vendedor v : minhaLoja.vendedores) {
                        System.out.println("Média de salário de " + v.nome + ": R$" + v.calcularMedia());
                    }
                    break;
                case 5:
                    for (vendedor v : minhaLoja.vendedores) {
                        System.out.println("Bônus de " + v.nome + ": R$" + v.calcularBonus());
                    }
                    break;
                case 6:
                    endereco endG = new endereco("SP", "São Paulo", "Centro", 999, "Cobertura");
                    gerente g = new gerente("Laura Gerente", 40, endG, "Loja XYZ", 5000);
                    g.apresentarse();
                    System.out.println("Média salarial: R$" + g.calcularMedia());
                    System.out.println("Bônus: R$" + g.calcularBonus());
                    break;
                case 7:
                   
                    ArrayList<item> itens = new ArrayList<>();
                    itens.add(new item(1, "Planta Suculenta", "Decorativa", 29.90));
                    itens.add(new item(2, "Terra Adubada", "Substrato", 15.50));

                    processaPedido pp = new processaPedido();
                    Date vencimento = new Date(System.currentTimeMillis() + 86400000); // +1 dia

                    pedido novoPedido = pp.processar(1, minhaLoja.clientes.get(0), minhaLoja.vendedores.get(0), minhaLoja, itens, vencimento);

                    if (novoPedido != null) {
                        novoPedido.gerarDescricaoVenda();
                    }
                    break;
                case 8:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
