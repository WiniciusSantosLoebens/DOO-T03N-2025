package objetos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Livros {
    private String nome;
    private String ID;
    private String autor;
    private String ano;
    private boolean disponivel;
    private boolean raro;
    private String motivoRaridade;
    private String leitorAtual;
    private LocalDate dataEmprestimo;

    public Livros(String nome, String ID, String autor, String ano, boolean raro, String motivoRaridade) {
        this.nome = nome;
        this.ID = ID;
        this.autor = autor;
        this.ano = ano;
        this.disponivel = true;
        this.raro = raro;
        this.motivoRaridade = raro ? motivoRaridade : "Não é raro";
        this.leitorAtual = "Nenhum";
    }

    // Getters e Setters
    public String getNome() { 
        return nome; }
        
    public String getID() { 
        return ID; }

    public String getAutor() { 
        return autor; }

    public String getAno() { 
        return ano; }

    public boolean isDisponivel() { 
        return disponivel; }

    public boolean isRaro() { 
        return raro; }

    public String getMotivoRaridade() { 
        return motivoRaridade; }

    public String getLeitorAtual() { 
        return leitorAtual; }

    public LocalDate getDataEmprestimo() { 
        return dataEmprestimo; }

    public void emprestar(String leitorID, LocalDate dataEmprestimo) {
        if (!this.raro) {
            this.disponivel = false;
            this.leitorAtual = leitorID;
            this.dataEmprestimo = dataEmprestimo;
        }
    }

    public void devolver() {
        this.disponivel = true;
        this.leitorAtual = "Nenhum";
        this.dataEmprestimo = null;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataEmp = dataEmprestimo != null ? dataEmprestimo.format(formatter) : "Não emprestado";
        
        return String.format("""
            Livro: %s
            ID: %s | Autor: %s | Ano: %s
            Tipo: %s
            Status: %s
            Data Empréstimo: %s
            %s
            """, 
            nome, ID, autor, ano,
            raro ? "Raro (" + motivoRaridade + ")" : "Comum",
            disponivel ? "Disponível" : "Emprestado para: " + leitorAtual,
            dataEmp,
            raro ? "Este livro não pode ser emprestado" : "");
    }
}