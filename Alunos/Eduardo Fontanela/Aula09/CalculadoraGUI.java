import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGUI extends JFrame {
    private JTextField displayField;
    private JTextField inputField1;
    private JTextField inputField2;
    private JButton addButton, subtractButton, multiplyButton, divideButton, clearButton;
    private JLabel resultLabel;

    public CalculadoraGUI() {
        setTitle("Calculadora Simples");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        inputField1 = new JTextField(10);
        inputField2 = new JTextField(10);
        displayField = new JTextField(10);
        displayField.setEditable(false);
        resultLabel = new JLabel("Resultado: ");
        
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("×");
        divideButton = new JButton("÷");
        clearButton = new JButton("Limpar");
        
        add(new JLabel("Número 1:"));
        add(inputField1);
        add(new JLabel("Número 2:"));
        add(inputField2);
        add(addButton);
        add(subtractButton);
        add(multiplyButton);
        add(divideButton);
        add(clearButton);
        add(resultLabel);
        add(displayField);
        
        addButton.addActionListener(new OperationListener("+"));
        subtractButton.addActionListener(new OperationListener("-"));
        multiplyButton.addActionListener(new OperationListener("×"));
        divideButton.addActionListener(new OperationListener("÷"));
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputField1.setText("");
                inputField2.setText("");
                displayField.setText("");
            }
        });
    }
    
    private class OperationListener implements ActionListener {
        private String operation;
        
        public OperationListener(String operation) {
            this.operation = operation;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double num1 = parseInput(inputField1.getText());
                double num2 = parseInput(inputField2.getText());
                
                double result = 0;
                
                switch (operation) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "×":
                        result = num1 * num2;
                        break;
                    case "÷":
                        if (num2 == 0) {
                            throw new ArithmeticException("Erro: Divisão por zero.");
                        }
                        result = num1 / num2;
                        break;
                }
                
                displayField.setText(String.valueOf(result));
            } catch (ArithmeticException ex) {
                displayField.setText("Erro: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                displayField.setText("Erro: Entrada inválida.");
            }
        }
        
        private double parseInput(String input) throws NumberFormatException {
            return Double.parseDouble(input);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculadoraGUI().setVisible(true);
            }
        });
    }
}
