
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CalculadoraGabrielinha {

    static Map<String, Integer> vendas = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Soma");
            System.out.println("2. Subtração");
            System.out.println("3. Multiplicação");
            System.out.println("4. Divisão");
            System.out.println("5. Salvar vendas");
            System.out.println("6. Buscar vendas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    realizarOperacao(scanner, "soma");
                    break;
                case 2:
                    realizarOperacao(scanner, "subtracao");
                    break;
                case 3:
                    realizarOperacao(scanner, "multiplicacao");
                    break;
                case 4:
                    realizarOperacao(scanner, "divisao");
                    break;
                case 5:
                    salvarVendas(scanner);
                    break;
                case 6:
                    buscarVendas(scanner);
                    break;
                case 0:
                    System.out.println("Saindo... Até mais, Dona Gabrielinha!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }

    public static void realizarOperacao(Scanner scanner, String operacao) {
        System.out.print("Digite o primeiro número: ");
        double num1 = scanner.nextDouble();
        System.out.print("Digite o segundo número: ");
        double num2 = scanner.nextDouble();
        double resultado = 0;

        switch (operacao) {
            case "soma":
                resultado = num1 + num2;
                break;
            case "subtracao":
                resultado = num1 - num2;
                break;
            case "multiplicacao":
                resultado = num1 * num2;
                break;
            case "divisao":
                if (num2 != 0) {
                    resultado = num1 / num2;
                } else {
                    System.out.println("Erro: divisão por zero!");
                    return;
                }
                break;
        }
        System.out.println("Resultado: " + resultado);
    }

    public static void salvarVendas(Scanner scanner) {
        System.out.print("Digite o mês (1-12): ");
        int mes = scanner.nextInt();
        System.out.print("Digite o dia (1-31): ");
        int dia = scanner.nextInt();
        System.out.print("Digite a quantidade de vendas: ");
        int quantidade = scanner.nextInt();

        String chave = mes + "-" + dia;
        vendas.put(chave, quantidade);
        System.out.println("Vendas salvas com sucesso!");
    }

    public static void buscarVendas(Scanner scanner) {
        System.out.print("Digite o mês (1-12): ");
        int mes = scanner.nextInt();
        System.out.print("Digite o dia (1-31): ");
        int dia = scanner.nextInt();

        String chave = mes + "-" + dia;
        if (vendas.containsKey(chave)) {
            int quantidade = vendas.get(chave);
            System.out.println("Quantidade de vendas em " + dia + "/" + mes + ": " + quantidade);
        } else {
            System.out.println("Nenhuma venda registrada para esta data.");
        }
    }
}
