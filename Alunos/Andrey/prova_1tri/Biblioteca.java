package Objeto;

import java.util.Scanner;

public class Biblioteca{
    private static Scanner scan = new Scanner(System.in);

    public static void emprestarLivro(){
        System.out.println("digite nome do livro");
        String nome = scan.next();
        System.out.println("digite data de retirada");
        Integer dataRet = scan.nextInt();
        System.out.println(Livro.altIndisp(nome, dataRet));
    }

    public static void devolverLivro(){
        System.out.println("digite nome do livro");
        String nome = scan.next();
        System.out.println("digite data de devolucao");
        Integer dataDev = scan.nextInt();
        System.out.println(Livro.altIndisp(nome, dataDev));
    }
}