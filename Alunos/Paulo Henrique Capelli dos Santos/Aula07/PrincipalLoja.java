import java.util.*;
;
public class PrincipalLoja {;
 ;
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 RegistroVendas registroVendas = new RegistroVendas();
 Loja loja = new Loja("Plantas Vivas", "Plantas LTDA", "12.345.678/0001-90", "São Paulo", "Centro", "Rua das Flores, 123");
 int opcao;
 
 do {;
 System.out.println("\n[1] Registrar Venda");
 System.out.println("[2] Histórico de Vendas");
 System.out.println("[3] Consultar Vendas por Dia");
 System.out.println("[4] Consultar Vendas por Mês");
 System.out.println("[5] Gerenciar Clientes");
 System.out.println("[6] Gerenciar Funcionários");
 System.out.println("[7] Informações da Loja");
 System.out.println("[8] Sair");
 System.out.print("Escolha uma opção: ");
 opcao = sc.nextInt();

 switch (opcao) {
 case 1:;
 System.out.println("Digite o CPF do cliente:");
 String cpf = sc.next();
 Cliente cliente = loja.buscarClientePorCpf(cpf);
 if (cliente == null) {;
 System.out.println("Cliente não encontrado. Vamos cadastrá-lo.");
 sc.nextLine(); // limpar buffer;
 System.out.print("Nome: ");
 String nome = sc.nextLine();
 System.out.print("Idade: ");
 int idade = sc.nextInt();
 sc.nextLine();
 System.out.print("Cidade: ");
 String cidade = sc.nextLine();
 System.out.print("Bairro: ");
 String bairro = sc.nextLine();
 System.out.print("Rua: ");
 String rua = sc.nextLine();
 cliente = new Cliente(nome, idade, cidade, bairro, rua, cpf);
 loja.adicionaCliente(cliente);
 }
 registroVendas.registrarVenda(sc, cliente);
 
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

 case 5:;
 System.out.println("[1] Listar clientes");
 System.out.println("[2] Adicionar novo cliente");
 int opCliente = sc.nextInt();
 sc.nextLine();
 if (opCliente == 1) {
 loja.listarClientes();
 } else if (opCliente == 2) {;
 System.out.print("Nome: ");
 String nome = sc.nextLine();
 System.out.print("Idade: ");
 int idade = sc.nextInt();
 sc.nextLine();
 System.out.print("Cidade: ");
 String cidade = sc.nextLine();
 System.out.print("Bairro: ");
 String bairro = sc.nextLine();
 System.out.print("Rua: ");
 String rua = sc.nextLine();
 System.out.print("CPF: ");
 String cpfNovo = sc.nextLine();
 loja.adicionaCliente(new Cliente(nome, idade, cidade, bairro, rua, cpfNovo));
 }
 
 break;

 case 6:;
 System.out.println("[1] Listar vendedores");
 System.out.println("[2] Adicionar novo vendedor");
 int opVendedor = sc.nextInt();
 sc.nextLine();
 if (opVendedor == 1) {
 loja.listarVendedores();
 } else if (opVendedor == 2) {;
 System.out.print("Nome: ");
 String nome = sc.nextLine();
 System.out.print("Idade: ");
 int idade = sc.nextInt();
 sc.nextLine();
 System.out.print("Cidade: ");
 String cidade = sc.nextLine();
 System.out.print("Bairro: ");
 String bairro = sc.nextLine();
 System.out.print("Rua: ");
 String rua = sc.nextLine();
 System.out.print("Salário base: ");
 double salario = sc.nextDouble();
 loja.adicionaVendedor(new Vendedor(nome, idade, loja.getNomeFantasia(), cidade, bairro, rua, salario));
 }
 
 break;

 case 7:
 
 
 break;

 case 8:;
 System.out.println("Você saiu.");
 
 break;

 default:;
 System.out.println("Opção inválida!");
 }
 } while (opcao != 8);

 sc.close();
 }
}