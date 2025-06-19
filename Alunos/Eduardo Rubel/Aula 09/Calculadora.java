import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculadora extends JFrame {
    private JTextField campoNumero1, campoNumero2;
    private JButton botaoSoma, botaoSubtracao, botaoMultiplicacao, botaoDivisao;
    private JLabel rotuloResultado;

    public Calculadora() {
        setTitle("Calculadora Simples");
        setSize(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Componentes
        campoNumero1 = new JTextField();
        campoNumero2 = new JTextField();
        rotuloResultado = new JLabel("Resultado: ");
        
        botaoSoma = new JButton("+");
        botaoSubtracao = new JButton("-");
        botaoMultiplicacao = new JButton("×");
        botaoDivisao = new JButton("÷");

        // Adiciona componentes à janela
        add(new JLabel("Número 1:"));
        add(campoNumero1);
        add(new JLabel("Número 2:"));
        add(campoNumero2);

        add(botaoSoma);
        add(botaoSubtracao);
        add(botaoMultiplicacao);
        add(botaoDivisao);
        add(rotuloResultado);

        // Ações dos botões
        botaoSoma.addActionListener(e -> realizarOperacao('+'));
        botaoSubtracao.addActionListener(e -> realizarOperacao('-'));
        botaoMultiplicacao.addActionListener(e -> realizarOperacao('*'));
        botaoDivisao.addActionListener(e -> realizarOperacao('/'));

        setVisible(true);
    }

    private void realizarOperacao(char operador) {
        try {
            double num1 = parseInput(campoNumero1.getText());
            double num2 = parseInput(campoNumero2.getText());
            double resultado;

            switch (operador) {
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
                        throw new CalculadoraException("Divisão por zero não é permitida.");
                    }
                    resultado = num1 / num2;
                    break;
                default:
                    throw new CalculadoraException("Operação inválida.");
            }

            rotuloResultado.setText("Resultado: " + resultado);

        } catch (CalculadoraException ce) {
            JOptionPane.showMessageDialog(this, ce.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Por favor, insira apenas números válidos.", "Erro de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double parseInput(String input) throws CalculadoraException {
        try {
            return Double.parseDouble(input.trim());
        } catch (NumberFormatException e) {
            throw new CalculadoraException("Entrada inválida: '" + input + "'. Use apenas números.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculadora());
    }
}
