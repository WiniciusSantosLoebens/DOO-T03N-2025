package Alunos.Gabriel.Aula09;

import javax.swing.*;
import java.awt.*;

public class Calculadora extends JFrame {
    private JTextField campo1, campo2, campoResultado;
    private JButton btnSoma, btnSubtrai, btnMultiplica, btnDivide;

    public Calculadora() {
        setTitle("Calculadora");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        
        JPanel painelEntrada = new JPanel(new GridLayout(3, 2, 5, 5));
        campo1 = new JTextField();
        campo2 = new JTextField();
        campoResultado = new JTextField();
        campoResultado.setEditable(false);
        campoResultado.setBackground(Color.LIGHT_GRAY);

        painelEntrada.setBorder(BorderFactory.createTitledBorder("Entradas"));

        painelEntrada.add(new JLabel("Número 1:"));
        painelEntrada.add(campo1);
        painelEntrada.add(new JLabel("Número 2:"));
        painelEntrada.add(campo2);
        painelEntrada.add(new JLabel("Resultado:"));
        painelEntrada.add(campoResultado);

        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnSoma = new JButton("+");
        btnSubtrai = new JButton("-");
        btnMultiplica = new JButton("×");
        btnDivide = new JButton("÷");

        painelBotoes.setBorder(BorderFactory.createTitledBorder("Operações"));

        painelBotoes.add(btnSoma);
        painelBotoes.add(btnSubtrai);
        painelBotoes.add(btnMultiplica);
        painelBotoes.add(btnDivide);

        
        add(painelEntrada, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.SOUTH);

        
        btnSoma.addActionListener(e -> calcular('+'));
        btnSubtrai.addActionListener(e -> calcular('-'));
        btnMultiplica.addActionListener(e -> calcular('*'));
        btnDivide.addActionListener(e -> calcular('/'));
    }

    private void calcular(char operador) {
        try {
            double num1 = parseEntrada(campo1.getText());
            double num2 = parseEntrada(campo2.getText());
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
                    if (num2 == 0) throw new InputInvalidoException("Erro: Divisão por zero!");
                    resultado = num1 / num2;
                    break;
                default:
                    throw new InputInvalidoException("Operação inválida.");
            }

            campoResultado.setText(String.valueOf(resultado));
        } catch (InputInvalidoException ex) {
            mostrarErro(ex.getMessage());
        } catch (Exception ex) {
            mostrarErro("Erro inesperado: " + ex.getMessage());
        }
    }

    private double parseEntrada(String texto) throws InputInvalidoException {
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            throw new InputInvalidoException("Entrada inválida: Digite apenas números.");
        }
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
        campoResultado.setText("Erro");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora calc = new Calculadora();
            calc.setVisible(true);
        });
    }
}
