import java.util.ArrayList;
import java.util.Scanner;

public class Clientes {

    private String nome;
    private int idade;
    private int cpf;

    public Clientes() {
    }

    public Clientes(String nome, int idade, int cpf) {
        this.cpf = cpf;
        this.idade = idade;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }


    public static void cadastrarCliente(ArrayList<Clientes>clientes, Scanner scanner){
        System.out.println("Digite seu nome:");
        System.out.println("Digite sua idade:");
        System.out.println("Digite seu CPF (sem pontos e traços):");
        Clientes cliente = new Clientes(scanner.next(),scanner.nextInt(),scanner.nextInt());
        clientes.add(cliente);
    }

    public static void mostrarCliente(ArrayList<Clientes> clientes){
        for (Clientes clientes2 : clientes) {
          System.out.println("Nome: " + clientes2.getNome());
          System.out.println("Idade: " + clientes2.getIdade());
          System.out.println("CPF: " + clientes2.getCpf());
          System.out.println("-------------------------");

        }
    }



}

