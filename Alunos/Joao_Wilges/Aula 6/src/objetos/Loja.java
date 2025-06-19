package objetos;

import java.util.ArrayList;
import java.util.Scanner;

public class Loja {
    private String nome;
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String cidade;
    private String bairro;
    private String rua;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<Cliente> clientes;

    public Loja(String nome, String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
        this.nome = nome;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.vendedores = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public ArrayList<Vendedor> getVendedores() {
        return vendedores;
    }

    private ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public int contarVendedores(){
        return this.getVendedores().size();
    }

    public int contarClientes(){
        return this.getClientes().size();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void cadastrarVendedor(Scanner scanner, Loja loja){
        System.out.println("Informe o nome do vendedor: ");
        String nome = scanner.next();
        System.out.println("Informe o salário dele: ");
        Double salario_base = scanner.nextDouble();
        Vendedor novo_vendedor = new Vendedor(nome, salario_base);
    }
    public void excluirVendedor() {
        System.out.println("Informe o nome do novo vendedor: ");
        String nome = scanner.next();
        Vendedor vendedorParaRemover = null;

        for (Vendedor vendedor : vendedores) {
            if (vendedor.getNome().equals(nome)) {
                vendedorParaRemover = vendedor;
                break;
            }
        }

        if (vendedorParaRemover != null) {
            vendedores.remove(vendedorParaRemover);
            System.out.println("Vendedor " + nome + " foi removido com sucesso.");
        } else {
            System.out.println("Vendedor com nome " + nome + " não encontrado.");
        }
    }
    public void cadastrarCliente(String nome,int idade,String cidade,String bairro,String rua) {
        Cliente novoCliente = new Cliente(nome, idade, cidade, bairro, rua);
        clientes.add(novoCliente);
        System.out.println("Cliente " + nome + " cadastrado com sucesso.");
    }

    public void excluirCliente(String nome) {
        Cliente clienteParaRemover = null;
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equals(nome)) {
                clienteParaRemover = cliente;
                break;
            }
        }
        if (clienteParaRemover != null) {
            clientes.remove(clienteParaRemover);
            System.out.println("Cliente " + nome + " foi removido com sucesso.");
        } else {
            System.out.println("Cliente com nome " + nome + " não encontrado.");
        }
    }
}
