package objetos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Leitor {
    private String nome;
    private String ID;
    private String livroEmprestado;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private String telefone;
    private double multaPendente;

    public Leitor(String nome, String ID, String telefone) {
        this.nome = nome;
        this.ID = ID;
        this.telefone = telefone;
        this.livroEmprestado = "Nenhum";
        this.multaPendente = 0;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public String getID() { return ID; }
    public String getLivroEmprestado() { return livroEmprestado; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public String getTelefone() { return telefone; }
    public double getMultaPendente() { return multaPendente; }

    public void setLivroEmprestado(String livroID, LocalDate dataEmprestimo) { 
        this.livroEmprestado = livroID;
        this.dataEmprestimo = dataEmprestimo;
    }

    public double finalizarEmprestimo(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
        this.livroEmprestado = "Nenhum";
        
        long diasAtraso = ChronoUnit.DAYS.between(dataEmprestimo, dataDevolucao) - 7;
        double multa = diasAtraso > 0 ? diasAtraso * 3.50 : 0;
        this.multaPendente += multa;
        
        this.dataEmprestimo = null;
        return multa;
    }

    public void pagarMulta(double valor) {
        this.multaPendente -= valor;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataEmp = dataEmprestimo != null ? dataEmprestimo.format(formatter) : "Nenhum";
        String dataDev = dataDevolucao != null ? dataDevolucao.format(formatter) : "Nenhum";
        
        return String.format("""
            Leitor: %s
            ID: %s | Telefone: %s
            Livro Emprestado: %s
            Data Empréstimo: %s
            Data Devolução: %s
            Multa Pendente: R$ %.2f
            """, nome, ID, telefone, livroEmprestado, dataEmp, dataDev, multaPendente);
    }
}