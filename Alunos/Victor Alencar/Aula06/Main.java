package dasd;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static loja minhaLoja;

    public static void main(String[] args) {
        vendedor v1 = new vendedor("Carlos Silva", 30, "Loja XYZ", "São Paulo", "Centro", "Rua A, 100", 2500);
        vendedor v2 = new vendedor("Ana Souza", 28, "Loja XYZ", "São Paulo", "Centro", "Rua B, 200", 2800);
        cliente c1 = new cliente("João Oliveira", 45, "São Paulo", "Centro", "Rua C, 300");
        cliente c2 = new cliente("Maria Pereira", 34, "São Paulo", "Centro", "Rua D, 400");

        minhaLoja = new loja("Loja XYZ", "XYZ Comércio Ltda.", "12.345.678/0001-99", "São Paulo", "Centro", "Rua E, 500");

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
            System.out.println("6. Sair");
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
                        System.out.println("Média de salário: " + v.calcularMedia());
                    }
                    break;
                case 5:
                    for (vendedor v : minhaLoja.vendedores) {
                        System.out.println("Bônus: " + v.calcularBonus());
                    }
                    break;
                case 6:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
