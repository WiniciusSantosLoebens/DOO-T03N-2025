public class Pessoa {    
    private static int CONTADOR = 0;

    private final int id;

    private String nome;

    public Pessoa(String nome) {
        id = CONTADOR++;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }
}
