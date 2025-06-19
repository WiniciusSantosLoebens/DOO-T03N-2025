import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

public class Calculadora extends JFrame {

    private JTextField input1, input2, result;
    private JButton addBtn, subBtn, mulBtn, divBtn;

    public Calculadora() {
        setTitle("Calculadora FODA");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 5, 5));

        input1 = new JTextField();
        input2 = new JTextField();
        result = new JTextField();
        result.setEditable(false);

        addBtn = new JButton("+");
        subBtn = new JButton("-");
        mulBtn = new JButton("×");
        divBtn = new JButton("÷");

        add(new JLabel("Número 1:"));
        add(input1);
        add(new JLabel("Número 2:"));
        add(input2);
        add(new JLabel("Resultado:"));
        add(result);
        add(addBtn);
        add(subBtn);
        add(mulBtn);
        add(divBtn);

        addBtn.addActionListener(new OperationListener('+'));
        subBtn.addActionListener(new OperationListener('-'));
        mulBtn.addActionListener(new OperationListener('*'));
        divBtn.addActionListener(new OperationListener('/'));

        setVisible(true);
    }

    private class OperationListener implements ActionListener {
        private char operator;

        public OperationListener(char operator) {
            this.operator = operator;
        }

      
        public void actionPerformed(ActionEvent e) {
            try {
                double num1 = parseInput(input1.getText());
                double num2 = parseInput(input2.getText());
                double res = calculate(num1, num2, operator);
                result.setText(String.valueOf(res));
            } catch (InvalidInputException ex) {
                JOptionPane.showMessageDialog(Calculadora.this, ex.getMessage(), "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Calculadora.this, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        private double parseInput(String input) throws InvalidInputException {
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Entrada inválida! Digite apenas números.");
            }
        }

        private double calculate(double a, double b, char op) throws InvalidInputException {
            switch (op) {
                case '+': return a + b;
                case '-': return a - b;
                case '*': return a * b;
                case '/':
                    if (b == 0) throw new InvalidInputException("Divisão por zero não é permitida.");
                    return a / b;
                default:
                    throw new InvalidInputException("Operação inválida.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculadora::new);
    }
}
