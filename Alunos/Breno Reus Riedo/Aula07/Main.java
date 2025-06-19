import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        Loja lojaMyPlant = new Loja("My plant",
                "MY PLANT LTDA",
                "70.289.962/0001-98",
                new Endereco("Rio de Janeiro", "Barra da Tijuca", "R. Álvaro Moreira", 123, "Loja 1"),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>());

        Sujeito clienteGael = new Sujeito("Gael Martin Nascimento",
                57,
                new Endereco("Rio de Janeiro", "Bangu", "Caminho da Carranca", 456, "Casa 1"));

        List<BigDecimal> salarioRecebidoBrenno = new ArrayList<>();
        salarioRecebidoBrenno.add(new BigDecimal("1500"));
        salarioRecebidoBrenno.add(new BigDecimal("3000"));
        salarioRecebidoBrenno.add(new BigDecimal("5000"));

        List<BigDecimal> salarioRecebidoGerente = new ArrayList<>();
        salarioRecebidoGerente.add(new BigDecimal("5500"));
        salarioRecebidoGerente.add(new BigDecimal("6000"));
        salarioRecebidoGerente.add(new BigDecimal("7000"));

        Vendedor vendedorBrenno = new Vendedor("Brenno César Bernardes",
                44,
                lojaMyPlant,
                new Endereco("Rio de Janeiro", "Santa Cruz", "Rua Vinte e Cinco", 789, "Casa 2"),
                new BigDecimal("5000"),
                salarioRecebidoBrenno);

        Gerente gerenteTiago = new Gerente("Tiago Henrique Vasconcelos",
                29,
                lojaMyPlant,
                new Endereco("Rio de Janeiro", "Tijuca", "Rua Conde de Bonfim", 456, "Ap 701"),
                new BigDecimal("8800"),
                salarioRecebidoGerente);

        lojaMyPlant.addCliente(clienteGael);
        lojaMyPlant.addVendedor(vendedorBrenno);
        lojaMyPlant.addGerente(gerenteTiago);

        //CÓDIGOS ASCII PARA MUDAR PARA NEGRITO E COR DO TEXTO PARA BRANCO BRILHANDO
        System.out.println("\033[1;97;5mMétodo apresentarSe da lojaMyPlant:\033[0m ");
        lojaMyPlant.apresentarSe();

        System.out.println("\033[1;97;5mMétodo contarClientes da lojaMyPlant:\033[0m ");
        lojaMyPlant.contarClientes();

        System.out.println("\033[1;97;5mMétodo contarVendedores da lojaMyPlant:\033[0m ");
        lojaMyPlant.contarVendedores();

        System.out.println("\033[1;97;5mMétodo apresentaSe do clienteGael:\033[0m ");
        clienteGael.apresentarSe();

        System.out.println("\033[1;97;5mMétodo apresentaSe do vendedorBrenno:\033[0m ");
        vendedorBrenno.apresentarSe();

        System.out.println("\033[1;97;5mMétodo calcularMedia do vendedorBrenno:\033[0m ");
        System.out.println("R$" + vendedorBrenno.calcularMedia() + " Média");

        System.out.println("\033[1;97;5mMétodo calcularBonus do vendedorBrenno:\033[0m ");
        System.out.println("R$" + vendedorBrenno.calcularBonus() + " Bônus");

        System.out.println("\033[1;97;5mMétodo apresentaSe do gerenteTiago:\033[0m ");
        gerenteTiago.apresentarSe();

        System.out.println("\033[1;97;5mMétodo calcularMedia do gerenteTiago:\033[0m ");
        System.out.println("R$" + gerenteTiago.calcularMedia() + " Média");

        System.out.println("\033[1;97;5mMétodo calcularBonus do gerenteTiago:\033[0m ");
        System.out.println("R$" + gerenteTiago.calcularBonus() + " Bônus");

        Item item1 = new Item(1, "Suculenta", "Planta", new BigDecimal("29.90"));
        Item item2 = new Item(2, "Samambaia", "Planta", new BigDecimal("49.90"));

        item1.gerarDescricao();
        item2.gerarDescricao();

        System.out.println("\033[1;97;5mTeste método confirmarPagamento:\033[0m ");

        {Pedido pedido = ProcessarPedido.processar(123, LocalDate.now(), LocalDate.now().minusDays(2), clienteGael, vendedorBrenno, lojaMyPlant);}

        while (true) {
            printMenu();
            int opcao = readInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o ID do pedido:");
                    int id = readInt();

                    LocalDate dataAtual = LocalDate.now();

                    System.out.println("Data de vencimento da reserva:");
                    LocalDate dataVencimento = dataAtual.plusDays(5);

                    Pedido pedido = ProcessarPedido.processar(id, dataAtual, dataVencimento, clienteGael, vendedorBrenno, lojaMyPlant);

                    System.out.println("Deseja adicionar a Suculenta? (1-Sim, 2-Não)");
                    if (readInt() == 1) {
                        pedido.adicionarItem(item1);
                    }

                    System.out.println("Deseja adicionar a Samambaia? (1-Sim, 2-Não)");
                    if (readInt() == 1) {
                        pedido.adicionarItem(item2);
                    }

                    pedido.gerarDescricaoVenda();
                    break;
                case 2:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void printMenu() {
        System.out.println(
                """
                \n[1] - Criar Pedido
                [2] - Sair
                """);
    }

    private static int readInt() throws IOException {
        return Integer.parseInt(input.readLine());
    }

}