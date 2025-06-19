
class Item {
    private int id;
    private String nome, tipo;
    private double valor;

    public Item(int id, String nome, String tipo, double valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public double getValor() { return valor; }

    public void gerarDescricao() {
        System.out.printf("Item #%d - %s (%s): R$ %.2f\n", id, nome, tipo, valor);
    }
}
