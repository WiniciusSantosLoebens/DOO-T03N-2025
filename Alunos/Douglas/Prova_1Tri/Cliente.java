public class Cliente {
    int id;
    String nome;
    String cpf;

    public Cliente(int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return 
            "ID: " + id +
            "\nNome: " + nome +
            "\nCPF: " + cpf;
    }

}
