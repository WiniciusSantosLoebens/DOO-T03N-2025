package Aula09;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculadora extends JFrame {

    private final JTextField campoNumero1;
    private final JTextField campoNumero2;
    private final JLabel labelResultado;

    public static class CalculadoraException extends Exception {
        public CalculadoraException(String message) {
            super(message);
        }
    }

    public Calculadora() {
        setTitle("Calculadora Simples");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        campoNumero1 = new JTextField();
        campoNumero2 = new JTextField();
        labelResultado = new JLabel("Resultado: ", SwingConstants.CENTER);

        JButton botaoSoma = new JButton("+");
        JButton botaoSubtrai = new JButton("-");
        JButton botaoMultiplica = new JButton("×");
        JButton botaoDivide = new JButton("÷");

        add(new JLabel("Número 1:"));
        add(campoNumero1);
        add(new JLabel("Número 2:"));
        add(campoNumero2);
        add(botaoSoma);
        add(botaoSubtrai);
        add(botaoMultiplica);
        add(botaoDivide);
        add(labelResultado);

        botaoSoma.addActionListener(new Operacao("+"));
        botaoSubtrai.addActionListener(new Operacao("-"));
        botaoMultiplica.addActionListener(new Operacao("*"));
        botaoDivide.addActionListener(new Operacao("/"));
    }

    private class Operacao implements ActionListener {
        private final String operador;

        public Operacao(String operador) {
            this.operador = operador;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double num1 = parseInput(campoNumero1.getText());
                double num2 = parseInput(campoNumero2.getText());
                double resultado;

                switch (operador) {
                    case "+" -> resultado = num1 + num2;
                    case "-" -> resultado = num1 - num2;
                    case "*" -> resultado = num1 * num2;
                    case "/" -> {
                        if (num2 == 0) {
                            throw new CalculadoraException("Erro: Divisão por zero!");
                        }
                        resultado = num1 / num2;
                    }
                    default -> throw new CalculadoraException("Operador desconhecido.");
                }

                labelResultado.setText("Resultado: " + resultado);

            } catch (CalculadoraException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Erro: Entrada inválida. Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        private double parseInput(String input) throws CalculadoraException {
            if (input == null || input.trim().isEmpty()) {
                throw new CalculadoraException("Erro: Campo vazio!");
            }
            return Double.parseDouble(input);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora calc = new Calculadora();
            calc.setVisible(true);
        });
    }
}
