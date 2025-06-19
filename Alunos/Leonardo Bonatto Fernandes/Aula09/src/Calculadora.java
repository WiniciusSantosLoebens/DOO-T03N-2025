class InputInvalidoException extends Exception {
    public InputInvalidoException(String mensagem) {
        super(mensagem);
    }
}

public class Calculadora {

    public static double parseInput(String input) throws InputInvalidoException {
            return Double.parseDouble(input);
    }

    public static double calcular(double num1, double num2, String operacao) throws InputInvalidoException {
        switch (operacao) {
            case "+" -> {
                return num1 + num2;
            }
            case "-" -> {
                return num1 - num2;
            }
            case "*" -> {
                return num1 * num2;
            }
            case "/" -> {
                if (num2 == 0) throw new InputInvalidoException("Erro: Divisão por nulo não é permitida.");
                return num1 / num2;
            }
            default -> throw new InputInvalidoException("Operação inválida..");
        }
    }
}