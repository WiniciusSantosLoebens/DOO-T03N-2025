public class Cliente {
    private String nome;
    private int id;

    public Cliente(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                '}';
    }
}