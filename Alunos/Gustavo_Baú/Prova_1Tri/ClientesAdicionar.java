package Alunos.Gustavo_Baú.Prova_1Tri;

import java.util.ArrayList;
import java.util.Scanner;

public class ClientesAdicionar {

    public void clientesadicionar() {
     Scanner scanner = new Scanner(System.in);
     ArrayList<Clientes> clientes = new ArrayList<>();

     System.out.println("Digite o nome do cliente: ");
     String nome = scanner.next();
     System.out.println("Digite o CPF do cliente: ");
     int cpf = scanner.nextInt();
    
     clientes.add(new Clientes(nome, cpf));
     System.out.println("Cliente cadastrado com sucesso!");
    }
    
    void exibirClientes(ArrayList<Clientes> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("Lista de clientes:");
            for (Clientes cliente : clientes) {
                System.out.println("Nome: " + cliente.getNome() + ", CPF: " + cliente.getCpf());
            }
        }
    }
}
