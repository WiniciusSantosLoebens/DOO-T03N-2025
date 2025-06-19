package fag.calculadora;

public class CalculadoraException  extends Exception {
	
	 // Construtor que aceita uma mensagem de erro
   public CalculadoraException(String message) {
       super(message); // Passa a mensagem para o construtor da classe pai (Exception)
   }

   // Construtor opcional que aceita mensagem e a causa original
   public CalculadoraException(String message, Throwable cause) {
       super(message, cause);
   }

}