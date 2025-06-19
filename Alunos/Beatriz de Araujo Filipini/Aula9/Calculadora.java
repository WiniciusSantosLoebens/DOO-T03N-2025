public class Calculadora {
    public static double calcular(double num1, double num2, String operacao) throws Exception {
        switch (operacao) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new Exception("Erro: Divisão por zero!");
                }
                return num1 / num2;
            default:
                throw new Exception("Operação inválida!");
        }
    }
}
