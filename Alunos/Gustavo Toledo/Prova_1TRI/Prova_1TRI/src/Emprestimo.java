public class Emprestimo {
    Livro livro;
    Cliente cliente;
    String dataEmprestimo;
    String dataDevolucao;

    public Emprestimo(Livro livro, Cliente cliente, String dataEmprestimo) {
        this.livro = livro;
        this.cliente = cliente;
        this.dataEmprestimo = dataEmprestimo;
    }

    public double devolver(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
        livro.emprestado = false;

        long diasAtraso = calcularDiasAtraso(dataEmprestimo, dataDevolucao);
        return diasAtraso > 0 ? diasAtraso * 3.50 : 0.0;
    }

    private long calcularDiasAtraso(String dataEmprestimo, String dataDevolucao) {
        String[] emprestimoParts = dataEmprestimo.split("/");
        String[] devolucaoParts = dataDevolucao.split("/");
    
        int diaEmprestimo = Integer.parseInt(emprestimoParts[0]);
        int mesEmprestimo = Integer.parseInt(emprestimoParts[1]);
        int anoEmprestimo = Integer.parseInt(emprestimoParts[2]);
    
        int diaDevolucao = Integer.parseInt(devolucaoParts[0]);
        int mesDevolucao = Integer.parseInt(devolucaoParts[1]);
        int anoDevolucao = Integer.parseInt(devolucaoParts[2]);
    
        java.time.LocalDate dataEmprestimoObj = java.time.LocalDate.of(anoEmprestimo, mesEmprestimo, diaEmprestimo);
        java.time.LocalDate dataDevolucaoObj = java.time.LocalDate.of(anoDevolucao, mesDevolucao, diaDevolucao);
        
        long diasTotais = java.time.temporal.ChronoUnit.DAYS.between(dataEmprestimoObj, dataDevolucaoObj);
        

        long diasAtraso = diasTotais - 7;

        return diasAtraso > 0 ? diasAtraso : 0;
    }
}
