public class Cliente {
    private String nome;
    private String CPF;
    private String telefone;

    public Cliente(String nome, String CPF, String telefone) {
        this.nome = nome; 
        this.CPF = CPF; 
        this.telefone = telefone;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}