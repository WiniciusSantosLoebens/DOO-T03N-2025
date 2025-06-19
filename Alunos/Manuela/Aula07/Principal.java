package aula07;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import objetos.*; // Importa todas as classes do pacote objetos

public class Principal {

    static Scanner scanner = new Scanner(System.in);
    static List<Pedido> registroPedidos = new ArrayList<>(); 
    static List<Item> itensDisponiveis = new ArrayList<>(); 
    static Loja loja; // A loja será inicializada
    static ProcessaPedido processador = new ProcessaPedido(); 

    public static void main(String[] args) {
        inicializarSistema();
        menuPrincipal(); 
        scanner.close(); 
    }

     public static void inicializarSistema() {
        System.out.println("Inicializando sistema My Plant...");

        // Criar endereço da loja
        Endereco enderecoLoja = new Endereco("SP", "Cidade Flor", "Centro", "123", "Loja A");
        // Inicializar a loja
        loja = new Loja("My Plant", "My Plant Comércio de Plantas Ltda", "12.345.678/0001-90", enderecoLoja);

        // Adicionar itens fakes disponíveis
        itensDisponiveis.add(new Item("Samambaia", "Folhagem", 25.00));
        itensDisponiveis.add(new Item("Orquídea", "Flor", 75.50));
        itensDisponiveis.add(new Item("Cacto", "Suculenta", 45.00));
        itensDisponiveis.add(new Item("Bonsai", "Árvore Miniatura", 150.00));

         // Adicionar um cliente, vendedor e gerente fakes para teste rápido
         Endereco endCliFake = new Endereco("SP", "Cidade Flor", "Jardins", "500", "Apto 101");
         Cliente cliFake = new Cliente("Cliente Padrão", 30, endCliFake);
         loja.adicionaCliente(cliFake);

         Endereco endVendFake = new Endereco("SP", "Cidade Flor", "Centro", "400", null);
         Vendedor vendFake = new Vendedor("Vendedor Padrão", 25, loja.getNomeFantasia(), endVendFake, 2000.0);
         vendFake.adicionarSalario(2100); vendFake.adicionarSalario(2050); vendFake.adicionarSalario(2150);
         loja.adicionaVendedor(vendFake);

         Endereco endGerFake = new Endereco("SP", "Cidade Flor", "Alto da Serra", "10", "Casa");
         Gerente gerFake = new Gerente("Gerente Padrão", 40, loja.getNomeFantasia(), endGerFake, 5000.0);
         gerFake.adicionarSalario(5100); gerFake.adicionarSalario(5250); gerFake.adicionarSalario(5050);
         loja.adicionaGerente(gerFake);


        System.out.println("Sistema inicializado. Loja e dados de exemplo criados.");
    }

    public static void menuPrincipal() {
        System.out.println("\n--- My Plant - Menu Principal ---");
        System.out.println("Loja: " + (loja != null ? loja.getNomeFantasia() : "N/A"));
        System.out.println("---------------------------------");
        System.out.println("[1] - Calcular Preço (Simples)");
        System.out.println("[2] - Calcular Troco");
        System.out.println("[3] - Criar Novo Pedido"); // Nova opção
        System.out.println("[4] - Listar Pedidos Registrados"); // Nova opção (substitui RegistroVenda)
        System.out.println("[5] - Confirmar Pagamento de Pedido"); // Nova opção
        System.out.println("[6] - Cadastrar Cliente"); // Mantido (Refatorado)
        System.out.println("[7] - Cadastrar Vendedor"); // Mantido (Refatorado)
        System.out.println("[8] - Cadastrar Gerente"); // Nova opção
        System.out.println("[9] - Exibir Informações da Loja"); // Mantido (Refatorado)
        System.out.println("[10] - Mostrar Quantidade Pessoas"); // Mantido (Refatorado)
        System.out.println("[11] - Mostrar Salários Vendedor"); // Mantido (Refatorado)
        System.out.println("[12] - Mostrar Salários Gerente"); // Nova opção
        System.out.println("[13] - Listar Itens Disponíveis"); // Nova opção
        System.out.println("[14] - Sair");
        System.out.println("---------------------------------");
        System.out.print("Escolha uma opção: ");

        int op = lerInteiro(); // Usando função auxiliar para robustez

        if (op == 1) {
            calcularPrecoSimples(); // Renomeado para clareza
        } else if (op == 2) {
            calcularTroco();
        } else if (op == 3) {
            criarPedido(); // Nova função
        } else if (op == 4) {
            listarPedidos(); // Antiga RegistroVenda, agora usa Pedido
        } else if (op == 5) {
            confirmarPagamento(); // Nova função
        } else if (op == 6) {
            cadastrarCliente(); // Refatorado para usar Endereco
        } else if (op == 7) {
            cadastrarVendedor(); // Refatorado para usar Endereco
        } else if (op == 8) {
            cadastrarGerente(); // Nova função
        } else if (op == 9) {
            mostrarLoja(); // Refatorado para usar Endereco
        } else if (op == 10) {
            mostrarContagens(); // Refatorado para incluir Gerentes
        } else if (op == 11) {
            mostrarSalariosVendedor(); // Antiga mostrarMediaEBonus
        } else if (op == 12) {
             mostrarSalariosGerente(); // Nova função
        } else if (op == 13) {
             listarItensDisponiveis(); // Nova função
        } else if (op == 14) {
            System.out.println("\nSistema encerrado! Obrigado por usar o My Plant!");
            // scanner.close(); // O fechamento ideal seria após um loop, não aqui na recursão
            System.exit(0);
        } else {
            System.out.println("ERRO - Digite uma opção válida!");
            menuPrincipal(); // Chama de volta em caso de erro
        }
    }

    // --- Métodos Auxiliares de Leitura ---
     private static int lerInteiro() {
        while (true) {
            try {
                int valor = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha
                return valor;
            } catch (InputMismatchException e) {
                System.out.print("Entrada inválida. Por favor, digite um número inteiro: ");
                scanner.nextLine(); // Limpar buffer
            }
        }
    }

     private static double lerDouble() {
        while (true) {
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine(); // Consumir a nova linha
                return valor;
            } catch (InputMismatchException e) {
                System.out.print("Entrada inválida. Por favor, digite um número (use vírgula ou ponto): ");
                scanner.nextLine(); // Limpar buffer
            }
        }
    }

      private static String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

      private static void pressioneEnterParaContinuar() {
         System.out.print("\nPressione Enter para continuar...");
         scanner.nextLine();
     }


    // --- Funcionalidades do Menu ---

    // Opção 1: Calcular Preço Simples (igual ao original 'calcularPreco')
    public static void calcularPrecoSimples() {
        System.out.println("\n--- Calcular Preço Simples (com desconto) ---");
        System.out.print("Digite a quantidade da planta: ");
        int quant = lerInteiro();
        System.out.print("Digite o valor unitário: R$ ");
        double valoruni = lerDouble();

        if (quant <= 0 || valoruni <= 0) {
             System.out.println("ERRO: Quantidade e valor devem ser positivos.");
             pressioneEnterParaContinuar();
             menuPrincipal();
             return;
         }

        double total = quant * valoruni;
        double desc = 0;

        if (quant > 10) {
            desc = total * 0.05; // 5% de desconto
            System.out.println("INFO: Desconto de 5% aplicado!");
        }

        double totaldesc = total - desc;

        System.out.printf("Valor Total: R$ %.2f\n", totaldesc);
        System.out.printf("Desconto Concedido: R$ %.2f\n", desc);
        pressioneEnterParaContinuar();
        menuPrincipal();
    }

    // Opção 2: Calcular Troco (igual ao original)
    public static void calcularTroco() {
         System.out.println("\n--- Calcular Troco ---");
        System.out.print("Digite o valor recebido pelo cliente: R$ ");
        double valorp = lerDouble();
        System.out.print("Digite o valor total da compra: R$ ");
        double valorc = lerDouble();

        if (valorp < valorc) {
            System.out.printf("ERRO: Valor recebido (R$ %.2f) é insuficiente para a compra (R$ %.2f).\n", valorp, valorc);
        } else {
            double troco = valorp - valorc;
            System.out.printf("Troco a ser dado: R$ %.2f\n", troco);
        }
        pressioneEnterParaContinuar();
        menuPrincipal();
    }

    // Opção 3: Criar Pedido (Nova)
    public static void criarPedido() {
        System.out.println("\n--- Criar Novo Pedido ---");

        if (loja.getClientes().isEmpty()) {
            System.out.println("ERRO: Cadastre pelo menos um cliente antes de criar um pedido.");
            pressioneEnterParaContinuar();
            menuPrincipal();
            return;
        }
        if (loja.getVendedores().isEmpty()) {
            System.out.println("ERRO: Cadastre pelo menos um vendedor antes de criar um pedido.");
            pressioneEnterParaContinuar();
            menuPrincipal();
            return;
        }
         if (itensDisponiveis.isEmpty()) {
            System.out.println("ERRO: Não há itens disponíveis para venda no momento.");
             pressioneEnterParaContinuar();
            menuPrincipal();
            return;
        }

        // 1. Selecionar Cliente
        System.out.println("Selecione o Cliente:");
        for (int i = 0; i < loja.getClientes().size(); i++) {
            System.out.printf("[%d] %s\n", i, loja.getClientes().get(i).getNome());
        }
        System.out.print("Digite o número do cliente: ");
        int indiceCliente = lerInteiro();
        if (indiceCliente < 0 || indiceCliente >= loja.getClientes().size()) {
            System.out.println("ERRO: Índice de cliente inválido.");
            pressioneEnterParaContinuar();
            menuPrincipal();
            return;
        }
        Cliente clienteSelecionado = loja.getClientes().get(indiceCliente);

        // 2. Selecionar Vendedor
        System.out.println("Selecione o Vendedor:");
        for (int i = 0; i < loja.getVendedores().size(); i++) {
            System.out.printf("[%d] %s\n", i, loja.getVendedores().get(i).getNome());
        }
        System.out.print("Digite o número do vendedor: ");
        int indiceVendedor = lerInteiro();
         if (indiceVendedor < 0 || indiceVendedor >= loja.getVendedores().size()) {
            System.out.println("ERRO: Índice de vendedor inválido.");
            pressioneEnterParaContinuar();
            menuPrincipal();
            return;
        }
        Vendedor vendedorSelecionado = loja.getVendedores().get(indiceVendedor);

        // 3. Selecionar Itens
        List<Item> itensDoPedido = new ArrayList<>();
        int quantidadeTotalItens = 0; // Para regra de desconto
        int indiceItem;
        do {
            System.out.println("\nItens Disponíveis:");
            for (int i = 0; i < itensDisponiveis.size(); i++) {
                System.out.printf("[%d] %s - R$ %.2f\n", i, itensDisponiveis.get(i).getNome(), itensDisponiveis.get(i).getValor());
            }
            System.out.printf("[%d] Finalizar seleção de itens\n", itensDisponiveis.size());
            System.out.print("Digite o número do item para adicionar (ou " + itensDisponiveis.size() + " para finalizar): ");
            indiceItem = lerInteiro();

            if (indiceItem >= 0 && indiceItem < itensDisponiveis.size()) {
                Item itemEscolhido = itensDisponiveis.get(indiceItem);
                itensDoPedido.add(itemEscolhido);
                quantidadeTotalItens++; // Incrementa a quantidade total
                System.out.println("'" + itemEscolhido.getNome() + "' adicionado ao pedido.");
            } else if (indiceItem != itensDisponiveis.size()) {
                System.out.println("ERRO: Índice de item inválido.");
            }

        } while (indiceItem != itensDisponiveis.size());

        if (itensDoPedido.isEmpty()) {
            System.out.println("ERRO: Nenhum item foi selecionado para o pedido.");
             pressioneEnterParaContinuar();
            menuPrincipal();
            return;
        }

        // 4. Processar o Pedido
        System.out.println("\nProcessando pedido com " + quantidadeTotalItens + " item(ns)...");
        Pedido novoPedido = processador.processar(clienteSelecionado, vendedorSelecionado, loja, itensDoPedido, quantidadeTotalItens);

        if (novoPedido != null) {
            registroPedidos.add(novoPedido);
            System.out.println("SUCESSO: Pedido ID " + novoPedido.getId() + " criado e registrado!");
            novoPedido.gerarDescricaoVenda(); // Mostra detalhes do pedido criado
        } else {
            System.out.println("ERRO: Falha ao processar o pedido.");
        }
        pressioneEnterParaContinuar();
        menuPrincipal();
    }

     // Opção 4: Listar Pedidos (Antiga RegistroVenda)
    public static void listarPedidos() {
        System.out.println("\n--- Registro de Pedidos ---");
        if (registroPedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado ainda.");
        } else {
            System.out.println("Total de pedidos: " + registroPedidos.size());
            for (Pedido pedido : registroPedidos) {
                pedido.gerarDescricaoVenda(); // Usa o método do objeto Pedido
            }
        }
        pressioneEnterParaContinuar();
        menuPrincipal();
    }

     // Opção 5: Confirmar Pagamento (Nova)
     public static void confirmarPagamento() {
         System.out.println("\n--- Confirmar Pagamento de Pedido ---");
         if (registroPedidos.isEmpty()) {
             System.out.println("Nenhum pedido registrado para confirmar.");
              pressioneEnterParaContinuar();
             menuPrincipal();
             return;
         }

         System.out.println("Pedidos Pendentes ou Pagos:");
         for (Pedido p : registroPedidos) {
              System.out.printf("ID: %d | Cliente: %s | Valor: R$ %.2f | Status: %s | Venc. Reserva: %s\n",
                    p.getId(),
                    p.getCliente().getNome(),
                    p.getValorTotal(),
                    (p.isPago() ? "Pago" : "Pendente"),
                    p.getDataVencimentoReserva()
              );
         }

         System.out.print("Digite o ID do pedido para confirmar o pagamento: ");
         long idPedido = -1;
         // Tratamento simples para ID longo
         try {
            idPedido = scanner.nextLong();
            scanner.nextLine(); // Consome newline
         } catch(InputMismatchException e) {
             System.out.println("ERRO: ID inválido.");
             scanner.nextLine(); // Limpa buffer
             pressioneEnterParaContinuar();
             menuPrincipal();
             return;
         }


         Pedido pedidoEncontrado = null;
         for (Pedido p : registroPedidos) {
             if (p.getId() == idPedido) {
                 pedidoEncontrado = p;
                 break;
             }
         }

         if (pedidoEncontrado != null) {
             processador.confirmarPagamento(pedidoEncontrado); // Tenta confirmar
             // Mostra o status atualizado
             System.out.println("Status atual do pedido:");
             pedidoEncontrado.gerarDescricaoVenda();
         } else {
             System.out.println("ERRO: Pedido com ID " + idPedido + " não encontrado.");
         }
         pressioneEnterParaContinuar();
         menuPrincipal();
     }


    // Opção 6: Cadastrar Cliente (Refatorado com Endereco)
    public static void cadastrarCliente() {
        System.out.println("\n--- Cadastrar Novo Cliente ---");
        String nome = lerString("Nome do cliente: ");
        System.out.print("Idade: ");
        int idade = lerInteiro();

        System.out.println("--- Endereço do Cliente ---");
        String estado = lerString("Estado (Sigla, ex: SP): ");
        String cidade = lerString("Cidade: ");
        String bairro = lerString("Bairro: ");
        String numero = lerString("Número: ");
        String complemento = lerString("Complemento (deixe em branco se não houver): ");

        // Cria o objeto Endereco
        Endereco enderecoCliente = new Endereco(estado, cidade, bairro, numero, complemento);

        // Cria o Cliente com o Endereco
        Cliente cliente = new Cliente(nome, idade, enderecoCliente);
        loja.adicionaCliente(cliente); // Adiciona o cliente à lista da loja
        System.out.println("\nSUCESSO: Cliente cadastrado!");
        cliente.apresentarse(); // Mostra os dados cadastrados

        pressioneEnterParaContinuar();
        menuPrincipal();
    }

    // Opção 7: Cadastrar Vendedor (Refatorado com Endereco)
    public static void cadastrarVendedor() {
        System.out.println("\n--- Cadastrar Novo Vendedor ---");
        String nome = lerString("Nome do vendedor: ");
        System.out.print("Idade: ");
        int idade = lerInteiro();
        System.out.print("Salário base: R$ ");
        double salarioBase = lerDouble();

        System.out.println("--- Endereço do Vendedor ---");
        String estado = lerString("Estado (Sigla, ex: SP): ");
        String cidade = lerString("Cidade: ");
        String bairro = lerString("Bairro: ");
        String numero = lerString("Número: ");
        String complemento = lerString("Complemento (deixe em branco se não houver): ");

        Endereco enderecoVendedor = new Endereco(estado, cidade, bairro, numero, complemento);

        // Cria o Vendedor, passando a loja atual e o endereço
        Vendedor vendedor = new Vendedor(nome, idade, loja.getNomeFantasia(), enderecoVendedor, salarioBase);

        // Adiciona 3 salários recebidos (conforme exemplo original)
        System.out.println("Insira os 3 últimos salários recebidos:");
        for (int i = 0; i < 3; i++) {
            System.out.printf("Digite o salário %d: R$ ", i + 1);
            double salarioRecebido = lerDouble();
            vendedor.adicionarSalario(salarioRecebido);
        }

        loja.adicionaVendedor(vendedor);
        System.out.println("\nSUCESSO: Vendedor cadastrado!");
        vendedor.apresentarse();

        pressioneEnterParaContinuar();
        menuPrincipal();
    }

     // Opção 8: Cadastrar Gerente (Nova)
    public static void cadastrarGerente() {
         System.out.println("\n--- Cadastrar Novo Gerente ---");
         String nome = lerString("Nome do gerente: ");
         System.out.print("Idade: ");
         int idade = lerInteiro();
         System.out.print("Salário base: R$ ");
         double salarioBase = lerDouble();

         System.out.println("--- Endereço do Gerente ---");
         String estado = lerString("Estado (Sigla, ex: SP): ");
         String cidade = lerString("Cidade: ");
         String bairro = lerString("Bairro: ");
         String numero = lerString("Número: ");
         String complemento = lerString("Complemento (deixe em branco se não houver): ");

         Endereco enderecoGerente = new Endereco(estado, cidade, bairro, numero, complemento);

         // Cria o Gerente
         Gerente gerente = new Gerente(nome, idade, loja.getNomeFantasia(), enderecoGerente, salarioBase);

         // Adiciona 3 salários recebidos
         System.out.println("Insira os 3 últimos salários recebidos:");
         for (int i = 0; i < 3; i++) {
             System.out.printf("Digite o salário %d: R$ ", i + 1);
             double salarioRecebido = lerDouble();
             gerente.adicionarSalario(salarioRecebido);
         }

         loja.adicionaGerente(gerente); // Adiciona à loja
         System.out.println("\nSUCESSO: Gerente cadastrado!");
         gerente.apresentarse();

         pressioneEnterParaContinuar();
         menuPrincipal();
     }


    // Opção 9: Mostrar Informações da Loja (Refatorado com Endereco)
    public static void mostrarLoja() {
        System.out.println("\n--- Informações da Loja ---");
        loja.apresentarse(); // O método apresentarse da Loja já usa o Endereco
        pressioneEnterParaContinuar();
        menuPrincipal();
    }

    // Opção 10: Mostrar Quantidade de Pessoas (Refatorado para incluir Gerente)
    public static void mostrarContagens() {
        System.out.println("\n--- Quantidade de Pessoas Cadastradas ---");
        System.out.println("Clientes: " + loja.contarClientes());
        System.out.println("Vendedores: " + loja.contarVendedores());
        System.out.println("Gerentes: " + loja.contarGerentes()); // Inclui contagem de gerentes
        pressioneEnterParaContinuar();
        menuPrincipal();
    }

    // Opção 11: Mostrar Salários Vendedor (Antiga mostrarMediaEBonus)
    public static void mostrarSalariosVendedor() {
        System.out.println("\n--- Salários e Bônus do Vendedor ---");
        if (loja.getVendedores().isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado!");
        } else {
            System.out.println("Selecione o Vendedor:");
             for (int i = 0; i < loja.getVendedores().size(); i++) {
                 System.out.printf("[%d] %s\n", i, loja.getVendedores().get(i).getNome());
             }
             System.out.print("Digite o número do vendedor: ");
             int indice = lerInteiro();

             if (indice >= 0 && indice < loja.getVendedores().size()) {
                 Vendedor vendedor = loja.getVendedores().get(indice);
                 vendedor.apresentarse(); // Mostra dados básicos

                 System.out.println("\nSalários Recebidos:");
                 if(vendedor.getSalariosRecebidos().isEmpty()){
                     System.out.println("(Nenhum salário registrado)");
                 } else {
                    vendedor.getSalariosRecebidos().forEach(s -> System.out.printf(" - R$ %.2f\n", s));
                    double media = vendedor.calcularMedia();
                    System.out.printf("Média Salarial (Recebidos): R$ %.2f\n", media);
                 }

                 double bonus = vendedor.calcularBonus(); // Calcula bônus
                 System.out.printf("Bônus Calculado (sobre Salário Base): R$ %.2f\n", bonus);

             } else {
                 System.out.println("ERRO: Índice inválido.");
             }
        }
        pressioneEnterParaContinuar();
        menuPrincipal();
    }

    // Opção 12: Mostrar Salários Gerente (Nova)
    public static void mostrarSalariosGerente() {
         System.out.println("\n--- Salários e Bônus do Gerente ---");
         if (loja.getGerentes().isEmpty()) {
             System.out.println("Nenhum gerente cadastrado!");
         } else {
             System.out.println("Selecione o Gerente:");
              for (int i = 0; i < loja.getGerentes().size(); i++) {
                  System.out.printf("[%d] %s\n", i, loja.getGerentes().get(i).getNome());
              }
              System.out.print("Digite o número do gerente: ");
              int indice = lerInteiro();

              if (indice >= 0 && indice < loja.getGerentes().size()) {
                  Gerente gerente = loja.getGerentes().get(indice);
                  gerente.apresentarse(); // Mostra dados básicos

                  System.out.println("\nSalários Recebidos:");
                  if(gerente.getSalarioRecebido().isEmpty()){
                       System.out.println("(Nenhum salário registrado)");
                  } else {
                     gerente.getSalarioRecebido().forEach(s -> System.out.printf(" - R$ %.2f\n", s));
                     double media = gerente.calcularMedia(); // Calcula média
                     System.out.printf("Média Salarial (Recebidos): R$ %.2f\n", media);
                  }

                  double bonus = gerente.calcularBonus(); // Calcula bônus
                  System.out.printf("Bônus Calculado (35%% do Salário Base): R$ %.2f\n", bonus);

              } else {
                  System.out.println("ERRO: Índice inválido.");
              }
         }
         pressioneEnterParaContinuar();
         menuPrincipal();
     }

    // Opção 13: Listar Itens Disponíveis (Nova)
    public static void listarItensDisponiveis() {
        System.out.println("\n--- Itens Disponíveis para Venda ---");
        if (itensDisponiveis.isEmpty()) {
            System.out.println("Nenhum item cadastrado no momento.");
        } else {
            for (Item item : itensDisponiveis) {
                item.gerarDescricao(); // Usa o método do objeto Item
            }
        }
        pressioneEnterParaContinuar();
        menuPrincipal();
    }

    // A funcionalidade de buscar venda por data foi removida pois foi substituída
    // pela listagem de Pedidos, que já contém data. Poderia ser reimplementada
    // para filtrar a lista de Pedidos se necessário.

}
