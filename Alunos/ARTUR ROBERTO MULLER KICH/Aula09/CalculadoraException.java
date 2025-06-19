package org.example.exception;

/**
 * Exceção personalizada para tratar erros específicos da calculadora
 * Esta classe estende Exception para criar nosso próprio tipo de exceção
 * que será lançada em situações de erro na calculadora
 */
public class CalculadoraException extends Exception {
    
    /**
     * Construtor que recebe uma mensagem de erro personalizada
     * @param mensagem A mensagem detalhando o erro ocorrido
     */
    public CalculadoraException(String mensagem) {
        super(mensagem);
    }
}
