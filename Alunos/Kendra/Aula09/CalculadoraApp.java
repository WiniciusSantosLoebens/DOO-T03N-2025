package calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class CalculadoraApp extends JFrame {
    private JTextField campoNumero1, campoNumero2;
    private JLabel resultadoLabel;

    public CalculadoraApp() {
        setTitle("Calculadora Simples");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        campoNumero1 = new JTextField();
        campoNumero2 = new JTextField();
        resultadoLabel = new JLabel("Resultado: ");
        resultadoLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton botaoSoma = new JButton("+");
        JButton botaoSubtrai = new JButton("-");
        JButton botaoMultiplica = new JButton("×");
        JButton botaoDivide = new JButton("÷");

        botaoSoma.addActionListener(e -> calcular('+'));
        botaoSubtrai.addActionListener(e -> calcular('-'));
        botaoMultiplica.addActionListener(e -> calcular('*'));
        botaoDivide.addActionListener(e -> calcular('/'));

        add(new JLabel("Número 1:"));
        add(campoNumero1);
        add(new JLabel("Número 2:"));
        add(campoNumero2);

        add(botaoSoma);
        add(botaoSubtrai);
        add(botaoMultiplica);
        add(botaoDivide);

        add(resultadoLabel);
    }

    private void calcular(char operador) {
        try {
            double num1 = lerNumero(campoNumero1.getText());
            double num2 = lerNumero(campoNumero2.getText());

            double resultado = switch (operador) {
                case '+' -> num1 + num2;
                case '-' -> num1 - num2;
                case '*' -> num1 * num2;
                case '/' -> {
                    if (num2 == 0) {
                        throw new EntradaInvalidaException("Divisão por zero não é permitida.");
                    }
                    yield num1 / num2;
                }
                default -> 0;
            };

            resultadoLabel.setText("Resultado: " + resultado);
        } catch (EntradaInvalidaException ex) {
            mostrarErro(ex.getMessage());
        } catch (NumberFormatException ex) {
            mostrarErro("Entrada inválida! Por favor, digite números válidos.");
        } catch (Exception ex) {
            mostrarErro("Erro inesperado: " + ex.getMessage());
        }
    }

    private double lerNumero(String texto) throws EntradaInvalidaException {
        if (texto == null || texto.isBlank()) {
            throw new EntradaInvalidaException("Campo vazio não é permitido.");
        }

        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException("Digite apenas números válidos.");
        }
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
        resultadoLabel.setText("Resultado: Erro");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraApp app = new CalculadoraApp();
            app.setVisible(true);
        });
    }
}