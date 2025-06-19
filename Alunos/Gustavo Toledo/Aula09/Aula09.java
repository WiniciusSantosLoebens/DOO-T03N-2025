import javax.swing.*;
import java.awt.*;

class InputException extends Exception {
    public InputException(String message) {
        super(message);
    }
}

public class Aula09 extends JFrame {
    private JTextField campo1;
    private JTextField campo2;
    private JLabel resultado;

    public Aula09() {
        setTitle("Calculadora Simples");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));

        JPanel painelNum1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelNum1.add(new JLabel("Número 1:"));
        campo1 = new JTextField(10);
        painelNum1.add(campo1);

        JPanel painelNum2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelNum2.add(new JLabel("Número 2:"));
        campo2 = new JTextField(10);
        painelNum2.add(campo2);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton somaBtn = new JButton("+");
        JButton subBtn = new JButton("-");
        JButton multBtn = new JButton("*");
        JButton divBtn = new JButton("/");

        painelBotoes.add(somaBtn);
        painelBotoes.add(subBtn);
        painelBotoes.add(multBtn);
        painelBotoes.add(divBtn);

        JPanel painelResultado = new JPanel(new FlowLayout(FlowLayout.LEFT));
        resultado = new JLabel("Resultado: ");
        painelResultado.add(resultado);

        painelPrincipal.add(painelNum1);
        painelPrincipal.add(painelNum2);
        painelPrincipal.add(painelBotoes);
        painelPrincipal.add(painelResultado);

        add(painelPrincipal, BorderLayout.CENTER);

        somaBtn.addActionListener(e -> calcular('+'));
        subBtn.addActionListener(e -> calcular('-'));
        multBtn.addActionListener(e -> calcular('*'));
        divBtn.addActionListener(e -> calcular('/'));
    }

    private void calcular(char operacao) {
        try {
            double num1 = parseInput(campo1.getText());
            double num2 = parseInput(campo2.getText());

            double res;

            switch (operacao) {
                case '+': res = num1 + num2; break;
                case '-': res = num1 - num2; break;
                case '*': res = num1 * num2; break;
                case '/':
                    if (num2 == 0) throw new InputException("Divisão por zero não é permitida.");
                    res = num1 / num2;
                    break;
                default: res = 0;
            }

            resultado.setText("Resultado: " + res);
        } catch (InputException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double parseInput(String input) throws InputException {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InputException("Entrada inválida! Use apenas números.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Aula09 calc = new Aula09();
            calc.setVisible(true);
        });
    }
}
