import javax.swing.*;
import java.awt.*;

public class Calculadora extends JFrame {
    private JTextField campoNumero1;
    private JTextField campoNumero2;
    private JLabel resultadoLabel;

    public Calculadora() {
        super("Calculadora");

        setLayout(new GridLayout(5, 2, 10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        campoNumero1 = new JTextField();
        campoNumero2 = new JTextField();
        resultadoLabel = new JLabel("Resultado: ");
        resultadoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultadoLabel.setForeground(new Color(0, 102, 204));
        resultadoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultadoLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JButton btnSomar = new JButton("+");
        JButton btnSubtrair = new JButton("-");
        JButton btnMultiplicar = new JButton("×");
        JButton btnDividir = new JButton("÷");

        add(new JLabel("Digite o primeiro valor:"));
        add(campoNumero1);
        add(new JLabel("Digite o segundo valor:"));
        add(campoNumero2);

        add(btnSomar);
        add(btnSubtrair);
        add(btnMultiplicar);
        add(btnDividir);

        add(resultadoLabel);

        btnSomar.addActionListener(e -> calcular('+'));
        btnSubtrair.addActionListener(e -> calcular('-'));
        btnMultiplicar.addActionListener(e -> calcular('*'));
        btnDividir.addActionListener(e -> calcular('/'));

        setVisible(true);
    }

    private void calcular(char operacao) {
        try {
            double num1 = parseEntrada(campoNumero1.getText());
            double num2 = parseEntrada(campoNumero2.getText());

            double resultado;

            switch (operacao) {
                case '+':
                    resultado = num1 + num2;
                    break;
                case '-':
                    resultado = num1 - num2;
                    break;
                case '*':
                    resultado = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        throw new CalculadoraException("Erro: Divisão por zero!");
                    }
                    resultado = num1 / num2;
                    break;
                default:
                    throw new CalculadoraException("Operação inválida.");
            }

            resultadoLabel.setText("Resultado: " + resultado);
        } catch (CalculadoraException e) {
            mostrarErro(e.getMessage());
        } catch (Exception e) {
            mostrarErro("Entrada inválida! Digite apenas números.");
        }
    }

    private double parseEntrada(String texto) throws CalculadoraException {
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            throw new CalculadoraException("Erro: Entrada inválida. Digite um número.");
        }
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
