package objetos;

public class item {
    private int id;
    private String nome;
    private String tipo;
    private double valor;

    // Construtor
    public item(int id, String nome, String tipo, double valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    // Método getter para acessar o valor
    public double getValor() {
        return valor;
    }

    // Método para gerar a descrição do item
    public void gerarDescricao() {
        System.out.println("ID: " + id + ", Nome: " + nome + ", Tipo: " + tipo + ", Valor: R$ " + valor);
    }
}

