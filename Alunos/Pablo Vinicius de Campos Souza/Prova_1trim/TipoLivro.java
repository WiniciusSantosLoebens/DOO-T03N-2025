public enum TipoLivro {
    COMUM(true),
    RARO(false),
    ;

    private final boolean emprestavel;

    public boolean isEmprestavel() {
        return emprestavel;
    }

    TipoLivro(boolean emprestavel) {
        this.emprestavel = emprestavel;
    }    
}
