package objetosMyPlant;

import java.util.Objects;

public class Item {

	private String id;
	private String nome;
	private String tipo;
	private double valor;

	public Item(String id, String nome, String tipo, double valor) {
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.valor = valor;
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getTipo() {
		return tipo;
	}

	public double getValor() {
		return valor;
	}

	public void gerarDescricao() {
		System.out.printf("Item [ID=%s | Nome=%s | Tipo=%s | Valor=R$%.2f]\n", id, nome, tipo, valor);
	}

	@Override
	public String toString() {
		return nome + " (R$" + String.format("%.2f", valor) + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Item item = (Item) o;
		return Objects.equals(id, item.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
