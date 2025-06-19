package objetos.gerenciadores;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import objetos.Livro;
import objetos.LivroRaro;

public class LivroManager {
    private ArrayList<Livro> livrosComuns;
    private ArrayList<LivroRaro> livrosRaros;

    public LivroManager() {
        this.livrosComuns = new ArrayList<>();
        this.livrosRaros = new ArrayList<>();
    }

    public void cadastrarLivroComum(String titulo,String autor,String editora,int anoPublicacao,int numeroDePaginas, String isbn){
        livrosComuns.add(new Livro(titulo,autor,editora,anoPublicacao,numeroDePaginas,isbn));
        System.out.println("Livro comum cadastrado!");
    }
    public void cadastrarLivroRaro(String titulo,String autor,String editora,int anoPublicacao,int numeroDePaginas, String isbn, String motivoRaridade){
        livrosRaros.add(new LivroRaro(titulo,autor,editora,anoPublicacao, numeroDePaginas, isbn, motivoRaridade));
        System.out.println("Livro raro cadastrado!");
    }
    public void listarLivros(){
        for(Livro livro : livrosComuns) {
            System.out.println(livro.toString());
        }
        for(LivroRaro livro : livrosRaros) {
            System.out.println(livro.toString());
        }
    }

    public void emprestarLivro(String isbn){
        Livro livro = encontrarLivroPorISBN(isbn);
        livro.emprestarLivro();
        System.out.println("Livro " + isbn + " foi emprestado!");
    }
    public void devolverLivro(String isbn){
        Livro livro = encontrarLivroPorISBN(isbn);
        Date dataEmprestimo = livro.getDataEmprestimo();
        Date dataDevolucao = new Date();
        long diferencaEmMillis = dataDevolucao.getTime() - dataEmprestimo.getTime();
        int diferenca = (int) TimeUnit.DAYS.convert(diferencaEmMillis, TimeUnit.MILLISECONDS);
        if((diferenca - 7) > 7){
            System.out.println("Cobre: R$" + (diferenca * 3.50) + " por ter ficado " + diferenca + "dias com o livro");
        }
        livro.devolverLivro();
        System.out.println("Tudo certo, livro devolvido");
    }
    public void removerLivro(String titulo_isbn){
        // remocao por titulo
        if(livrosComuns.remove(encontrarLivroPorTitulo(titulo_isbn)) || livrosRaros.remove(encontrarLivroPorTitulo(titulo_isbn)) || livrosComuns.remove(encontrarLivroPorISBN(titulo_isbn)) || livrosComuns.remove(encontrarLivroPorISBN(titulo_isbn))){
            System.out.println("Livro removido");
        } else {
            System.out.println("Livro não encontrado");
        }

    }

    public String dadosLivroPorTitulo(String titulo){
        for(Livro livro : livrosComuns) {
            if(livro.getTitulo().equals(titulo)){
                return livro.toString();
            }
        }
        for(LivroRaro livro : livrosRaros) {
            if(livro.getTitulo().equals(titulo)){
                return livro.toString();
            }
        }
        return "Livro não encontrado";
    }

    public String dadosLivroPorISBN(String isbn){
        for(Livro livro : livrosComuns) {
            if(livro.getTitulo().equals(isbn)){
                return livro.toString();
            }
        }
        for(LivroRaro livro : livrosRaros) {
            if(livro.getTitulo().equals(isbn)){
                return livro.toString();
            }
        }
        return "Livro não encontrado";
    }

    private Livro encontrarLivroPorTitulo(String titulo){
        for(Livro livro : livrosComuns) {
            if(livro.getTitulo().equals(titulo)){
                return livro;
            }
        }
        for(LivroRaro livro : livrosRaros) {
            if(livro.getTitulo().equals(titulo)){
                return livro;
            }
        }
        return null;
    }

    private Livro encontrarLivroPorISBN(String isbn){
        for(Livro livro : livrosComuns) {
            if(livro.getTitulo().equals(isbn)){
                return livro;
            }
        }
        for(LivroRaro livro : livrosRaros) {
            if(livro.getTitulo().equals(isbn)){
                return livro;
            }
        }
        return null;
    }



}