package objetos;

import java.util.concurrent.atomic.AtomicLong;

public class Item {
	private static final AtomicLong idGenerator = new AtomicLong(0);
    private long id;
    private String nome;
    private String tipo;
    private double valor;

    public Item(String nome, String tipo, double valor) {
        this.id = idGenerator.incrementAndGet(); // Gera ID único simples
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public void gerarDescricao() {
        System.out.printf("Item [ID: %d, Nome: %s, Tipo: %s, Valor: R$ %.2f]\n",
                id, nome, tipo, valor);
    }

    // Getters
    public long getId() { return id; }
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public double getValor() { return valor; }
}

