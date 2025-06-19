package objetos.managers;

import objetos.Cliente;

import java.util.ArrayList;

public class ClienteManager {
    private ArrayList<Cliente> clientes;

    public ClienteManager() {
        this.clientes = new ArrayList<>();
    }

    public void cadastrarCliente(String nome, int idade, String cidade, String bairro, String rua, String complemento, String numero) {
        Cliente novoCliente = new Cliente(nome, idade, cidade, bairro, rua, complemento, numero);
        clientes.add(novoCliente);
        System.out.println("Cliente " + nome + " cadastrado com sucesso.");
    }

    public void excluirCliente(String nome) {
        Cliente clienteParaRemover = encontrarClientePorNome(nome);
        if (clienteParaRemover != null) {
            clientes.remove(clienteParaRemover);
            System.out.println("Cliente " + nome + " foi removido com sucesso.");
        } else {
            System.out.println("Cliente com nome " + nome + " não encontrado.");
        }
    }

    public int contarClientes() {
        return clientes.size();
    }

    private Cliente encontrarClientePorNome(String nome) {
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equals(nome)) {
                return cliente;
            }
        }
        return null;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
}