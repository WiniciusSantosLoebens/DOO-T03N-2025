package Calculadora;

public class Erros extends Exception {
	private static final long serialVersionUID = 1L;

	public Erros(String mensagem) {
        super(mensagem); 
    }
}
