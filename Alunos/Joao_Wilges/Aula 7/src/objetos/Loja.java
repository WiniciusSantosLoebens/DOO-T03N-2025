package objetos;

import objetos.managers.ClienteManager;
import objetos.managers.PedidoManager;
import objetos.managers.VendedorManager;
import java.util.Date;

public class Loja {
    private String nome;
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String cidade;
    private String bairro;
    private String rua;
    private VendedorManager vendedorManager;
    private ClienteManager clienteManager;
    private PedidoManager pedidoManager;

    public Loja(String nome, String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
        this.nome = nome;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.vendedorManager = new VendedorManager();
        this.clienteManager = new ClienteManager();
        this.pedidoManager = new PedidoManager();
    }

    public int contarVendedores() {
        return vendedorManager.contarVendedores();
    }

    public int contarClientes() {
        return clienteManager.contarClientes();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void cadastrarVendedor(String nome, int idade, String cidade, String rua, String bairro, String complemento, String numero, double salarioBase) {
        vendedorManager.cadastrarVendedor(nome, this, idade, cidade, rua, bairro, complemento, numero, salarioBase);
    }

    public void excluirVendedor(String nome) {
        vendedorManager.excluirVendedor(nome);
    }

    public void cadastrarCliente(String nome, int idade, String cidade, String bairro, String rua, String complemento, String numero) {
        clienteManager.cadastrarCliente(nome, idade, cidade, bairro, rua, complemento, numero);
    }

    public void excluirCliente(String nome) {
        clienteManager.excluirCliente(nome);
    }

    public void criarPedido(int id, String nomeCliente, String vendedor, Date dataVencimentoReserva) {
        if (clienteManager.getClientes().stream().anyMatch(cliente -> cliente.getNome().equals(nomeCliente))) {
            ProcessaPedido processaPedido = new ProcessaPedido();
            Pedido pedido = processaPedido.processar(id, nomeCliente, vendedor, this.nome, dataVencimentoReserva);
            pedidoManager.adicionarPedido(pedido);
        } else {
            System.out.println("Cliente " + nomeCliente + " não encontrado.");
        }
    }

    public boolean removerPedido(int id) {
        return pedidoManager.removerPedido(id);
    }

    public int contarPedidos() {
        return pedidoManager.contarPedidos();
    }
}
