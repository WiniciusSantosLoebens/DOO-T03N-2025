package calculadora;

public class Calculadora {

	public double calcularPrecoTotal(int quantidade, double precoUnitario) {
		if (quantidade < 0 || precoUnitario < 0) {
			throw new IllegalArgumentException("A quantidade e o preço unitário devem ter valores positivos");
		}
		return quantidade * precoUnitario;
	}

	public double calcularDesconto(int quantidade, double valorTotal) {
		if (quantidade > 10) {
			return valorTotal * 0.05;
		}
		return 0;
	}

	public double calcularTroco(double valorRecebido, double valorTotal) {
		if (valorRecebido < 0 || valorTotal < 0) {
			throw new IllegalArgumentException("Os valores para o cálculo do troco devem ser positivos");
		}

		if (valorRecebido < valorTotal) {
			throw new IllegalArgumentException("O valor recebido é insuficiente");
		}
		return valorRecebido - valorTotal;
	}
}
