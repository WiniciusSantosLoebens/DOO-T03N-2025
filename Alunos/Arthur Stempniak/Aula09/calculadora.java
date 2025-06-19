import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;


public class calculadora extends JFrame implements ActionListener {
    private final JTextField display;

    public calculadora() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel painel = new JPanel(new GridLayout(5, 4, 5, 5));
        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.BOLD, 20));
            botao.addActionListener(this);
            painel.add(botao);
        }

        add(painel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "=" -> {
                try {
                    String expressao = display.getText();
                    double resultado = avaliarExpressao(expressao);
                    display.setText(String.valueOf(resultado));
                } catch (Exception ex) {
                    display.setText("Erro");
                }
            }
            case "C" -> display.setText("");
            default -> display.setText(display.getText() + comando);
        }
    }

    // Avaliador simples de expressões com precedência
    private double avaliarExpressao(String expr) {
        List<String> tokens = tokenizar(expr);
        Stack<Double> valores = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (String token : tokens) {
            if (token.matches("[0-9.]+")) {
                valores.push(Double.parseDouble(token));
            } else if (token.matches("[\\+\\-\\*/]")) {
                char op = token.charAt(0);
                while (!ops.isEmpty() && precedencia(ops.peek()) >= precedencia(op)) {
                    double b = valores.pop();
                    double a = valores.pop();
                    valores.push(aplicarOperador(ops.pop(), a, b));
                }
                ops.push(op);
            }
        }

        while (!ops.isEmpty()) {
            double b = valores.pop();
            double a = valores.pop();
            valores.push(aplicarOperador(ops.pop(), a, b));
        }

        return valores.pop();
    }

    private List<String> tokenizar(String expr) {
        List<String> tokens = new ArrayList<>();
        StringBuilder numero = new StringBuilder();
        for (char c : expr.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                numero.append(c);
            } else if ("+-*/".indexOf(c) >= 0) {
                if (numero.length() > 0) {
                    tokens.add(numero.toString());
                    numero.setLength(0);
                }
                tokens.add(String.valueOf(c));
            }
        }
        if (numero.length() > 0) tokens.add(numero.toString());
        return tokens;
    }

    private int precedencia(char op) {
        return (op == '+' || op == '-') ? 1 : 2;
    }

    private double aplicarOperador(char op, double a, double b) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> b != 0 ? a / b : Double.NaN;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new calculadora().setVisible(true));
    }
}
