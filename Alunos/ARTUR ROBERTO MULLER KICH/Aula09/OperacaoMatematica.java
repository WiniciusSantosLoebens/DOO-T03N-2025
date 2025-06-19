package org.example.model;


public class OperacaoMatematica {
    private double primeiroNumero;
    private double segundoNumero;
    private double resultado;
    private String operacao;



    public double getPrimeiroNumero() {
        return primeiroNumero;
    }


    public void setPrimeiroNumero(double primeiroNumero) {
        this.primeiroNumero = primeiroNumero;
    }


    public double getSegundoNumero() {
        return segundoNumero;
    }


    public void setSegundoNumero(double segundoNumero) {
        this.segundoNumero = segundoNumero;
    }


    public double getResultado() {
        return resultado;
    }


    public void setResultado(double resultado) {
        this.resultado = resultado;
    }


    public String getOperacao() {
        return operacao;
    }


    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }
    

    @Override
    public String toString() {
        return "Operação: " + primeiroNumero + " " + operacao + " " + segundoNumero + " = " + resultado;
    }
}
