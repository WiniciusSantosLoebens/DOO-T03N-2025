package objetos.gerenciadores;

import java.util.ArrayList;
import objetos.Cliente;

public class ClienteManager{
    private ArrayList<Cliente> clientes;

    public ClienteManager() {
        this.clientes = new ArrayList<>();
    }

    public void cadastrarCliente(String nome, int anoNascimento, int cpf, int rg){
        clientes.add(new Cliente(nome, anoNascimento, cpf, rg));
        System.out.println("Cliente cadastrado!");
    };
    public void excluirCliente(int cpf) {
        Cliente clienteParaRemover = encontrarClientePorCPF(cpf);
        if (clienteParaRemover != null) {
            clientes.remove(clienteParaRemover);
            System.out.println("Cliente de CPF:" + cpf + " foi removido com sucesso.");
        } else {
            System.out.println("Cliente com CPF:" + cpf + " não encontrado.");
        }
    }

    private Cliente encontrarClientePorCPF(int cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf() == cpf) {
                return cliente;
            }
        }
        return null;
    }

}