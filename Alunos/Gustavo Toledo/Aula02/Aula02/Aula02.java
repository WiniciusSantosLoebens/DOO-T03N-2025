import java.util.Scanner;

public class Aula02 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        do {
            exibirMenu();
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    calcularPrecoTotal(scanner);
                    break;

                case 2:
                    calcularTroco(scanner);
                    break;

                case 3:
                    System.out.println("Saindo da calculadora. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (opcao != 3); 

        scanner.close();
    }

    public static void exibirMenu() {
        System.out.println("\n--- Menu Calculadora ---");
        System.out.println("[1] - Calcular Preço Total");
        System.out.println("[2] - Calcular Troco");
        System.out.println("[3] - Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade da planta: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário da planta: R$ ");
        double precoUnitario = scanner.nextDouble();
        
        double precoTotal = quantidade * precoUnitario;
        System.out.println("O preço total da venda é: R$ " + precoTotal);
    }

    public static void calcularTroco(Scanner scanner) {
        System.out.print("Digite o valor recebido do cliente: R$ ");
        double valorRecebido = scanner.nextDouble();
        System.out.print("Digite o valor total da compra: R$ ");
        double valorTotal = scanner.nextDouble();

        if (valorRecebido >= valorTotal) {
            double troco = valorRecebido - valorTotal;
            System.out.println("O troco a ser dado ao cliente é: R$ " + troco);
        } else {
            System.out.println("Valor recebido é insuficiente para a compra.");
        }
    }
}
