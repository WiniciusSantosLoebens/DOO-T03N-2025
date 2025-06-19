import java.awt.*;
import javax.swing.*;

public class Calculator extends JFrame {
    private JTextField num1Field, num2Field, resultField;
    
    // Exceção personalizada
    public class CalculatorException extends Exception {
        public CalculatorException(String message) {
            super(message);
        }
    }

    public Calculator() {
        // Configuração da janela
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 20, 20));
        setSize(500, 200);
        
        // Componentes da interface
        add(new JLabel("Número 1:"));
        num1Field = new JTextField();
        add(num1Field);
        
        add(new JLabel("Número 2:"));
        num2Field = new JTextField();
        add(num2Field);
        
        add(new JLabel("Resultado:"));
        resultField = new JTextField();
        resultField.setEditable(false);
        add(resultField);
        
        // Botões de operações
        JButton addButton = new JButton("+");
        JButton subtractButton = new JButton("-");
        JButton multiplyButton = new JButton("×");
        JButton divideButton = new JButton("÷");
        
        // Painel para botões
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(divideButton);
        
        add(new JLabel("Operações:"));
        add(buttonPanel);
        
        // Listeners para os botões
        addButton.addActionListener(e -> performOperation('+'));
        subtractButton.addActionListener(e -> performOperation('-'));
        multiplyButton.addActionListener(e -> performOperation('×'));
        divideButton.addActionListener(e -> performOperation('÷'));
        
        setLocationRelativeTo(null);
    }
    
    private void performOperation(char operation) {
        try {
            // Validação e conversão dos números
            String num1Text = num1Field.getText().trim();
            String num2Text = num2Field.getText().trim();
            
            if (num1Text.isEmpty() || num2Text.isEmpty()) {
                throw new CalculatorException("Por favor, preencha ambos os campos.");
            }
            
            double num1, num2;
            try {
                num1 = Double.parseDouble(num1Text);
                num2 = Double.parseDouble(num2Text);
            } catch (NumberFormatException e) {
                throw new CalculatorException("Insira apenas números válidos.");
            }
            
            // Realizar operação
            double result;
            switch (operation) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '×':
                    result = num1 * num2;
                    break;
                case '÷':
                    if (num2 == 0) {
                        throw new CalculatorException("Divisão por zero não permitida.");
                    }
                    result = num1 / num2;
                    break;
                default:
                    throw new CalculatorException("Operação inválida.");
            }
            
            // Exibir resultado
            resultField.setText(String.format("%.2f", result));
            
        } catch (CalculatorException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculator().setVisible(true);
        });
    }
}