package Objeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {

    private String nome;
    private static Scanner scan = new Scanner(System.in);
    private static List<Cliente> clientes = new ArrayList<Cliente>();

    public Cliente(String nome){
        this.nome = nome;
    }

    public static void cadastroCliente(){
        System.out.println("digite nome do cliente");
        String nome = scan.next();
        clientes.add(new Cliente(nome));
    }
}