public class Cliente {
    private static int contadorID = 1;
    private final int id;
    private final String nome;

    public Cliente(String nome) {
        this.id = contadorID++;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome;
    }
}
