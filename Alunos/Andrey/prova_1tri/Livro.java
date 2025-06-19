package Objeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Livro {

    private String nome;
    private String autor;
    private Integer rare;
    private Bollean disponivel;
    private String explicacao;
    private Integer dataDev;
    private Integer dataRet;
    private static Scanner scan = new Scanner(System.in);
    private static List<Livro> livros = new ArrayList<Livro>();

    public Livro(String nome, String autor, Integer rare){
        this.nome = nome;
        this.autor = autor;
        this.rare = rare;
        this.disponivel = true;
    }

     public Livro(String nome, String autor, Integer rare, String explicacao){
        this.nome = nome;
        this.autor = autor;
        this.rare = rare;
        this.disponivel = true;
        this.explicacao = explicacao;
    }

    public static void cadastroLivro(){
        System.out.println("digite nome do livro");
        String nome = scan.next();
        System.out.println("digite autor do livro");
        String autor = scan.next();
        System.out.println("digite 1 - para livro raro e 2- para livro comum");
        Integer rare = scan.nextInt();
            if(rare = 1){
                System.out.println("digite explicacao");
                String explicacao = scan.next();
                livros.add(new Livro(nome,autor,rare,explicacao));
            }else{
                livros.add(new Livro(nome,autor,rare));
            }
    }

    public static void buscarLivroNome(){
        System.out.println("digite nome do livro");
        String nome = scan.next();
        for(Livro livros : livros){
                if(livros.nome.equalsIgnoreCase(nome)){
                    System.out.println(livros);
                }
        }
    }

    public static void buscarLivroAutor(){
        System.out.println("digite nome do autor");
        String autor = scan.next();
        for(Livro livros : livros){
                if(livros.autor.equalsIgnoreCase(autor)){
                    System.out.println(livros);
                }
        }
    }

    public static String altIndisp(String nome, Integer dataRet){
        for(Livro livros : livros){
        String dev = "Livro emprestado";
                if(livros.nome.equalsIgnoreCase(nome)){
                    if(livros.rare = 1){
                        System.out.println("esse livro nao pode ser emprestado");
                        dev = "nao liberado";
                    }else{
                        livros.disponivel = false;
                        livros.dataRet = dataRet;
                        
                    }
                }
        }
        return dev;
    }

    public static String altDisp(String nome, Integer dataRet){
        for(Livro livros : livros){
                if(livros.nome.equalsIgnoreCase(nome)){
                    livros.disponivel = true;
                     System.out.println("data de retirada do livro " + dataRet + " \n"
                     + "data de devolucao do livro " + dataDev + "\n"
                     + "informe quantidade de dias de emprestimo ");
                     Integer diasEmpre = scan.nextInt();
                        if(diasEmpre > 7){
                            diasEmpre = diasEmpre - 7;
                            System.out.println("valor da multa = " + diasEmpre * 3.5);
                        }
                    livros.dataDev = NULL;
                    livros.dataRet = NULL;
                }
        }
        String dev = "Livro devolvido";
        return dev;
    }

    public static void verificarDisponibilidade(){
        System.out.println("digite nome do livro");
        String nome = scan.next();
        for(Livro livros : livros){
                if(livros.nome.equalsIgnoreCase(nome)){
                    System.out.println(livros.disponivel);
                }
        }
    }
}