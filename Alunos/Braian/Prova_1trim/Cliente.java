package Alunos.Braian.Prova_1trim;

import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
    public static Scanner scanner = new Scanner(System.in);
    public static final ArrayList<Cliente> clientes = new ArrayList<>();
    public String nomeCliente;
    public Integer idadeCliente;
    public String cidadeCliente;
    public String bairroCliente;
    public String ruaCliente;
    public boolean temEmprestimo;

    public Cliente(String nomeCliente, Integer idadeCliente, String cidadeCliente, String bairroCliente, String ruaCliente) {
        this.nomeCliente = nomeCliente;
        this.idadeCliente = idadeCliente;
        this.cidadeCliente = cidadeCliente;
        this.bairroCliente = bairroCliente;
        this.ruaCliente = ruaCliente;
        this.temEmprestimo = false; // Inicialmente o cliente não tem empréstimos pendentes
    }

    // Getters e Setters
    public String getNomeCliente() {
        return nomeCliente;
    }

    public boolean isTemEmprestimo() {
        return temEmprestimo;
    }

    public void setTemEmprestimo(boolean temEmprestimo) {
        this.temEmprestimo = temEmprestimo;
    }

    @Override
    public String toString() {
        return "Cliente: " + nomeCliente;
    }
    public static void cadastrarCliente() {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a idade do cliente: ");
        int idade = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite a cidade do cliente: ");
        String cidade = scanner.nextLine();
        System.out.print("Digite o bairro do cliente: ");
        String bairro = scanner.nextLine();
        System.out.print("Digite a rua do cliente: ");
        String rua = scanner.nextLine();

        Cliente cliente = new Cliente(nome, idade, cidade, bairro, rua);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }
}
