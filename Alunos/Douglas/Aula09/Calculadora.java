import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CalculadoraException extends Exception {
    public CalculadoraException(String mensagem) {
        super(mensagem);
    }
}

public class Calculadora {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora");
        frame.setSize(300, 250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(panel);

        JTextField numero1 = new JTextField();
        JTextField numero2 = new JTextField();
        panel.add(numero1);
        panel.add(numero2);

        JPanel botoesPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        JButton somaBtn = new JButton("+");
        JButton subBtn = new JButton("-");
        JButton multBtn = new JButton("×");
        JButton divBtn = new JButton("÷");
        botoesPanel.add(somaBtn);
        botoesPanel.add(subBtn);
        botoesPanel.add(multBtn);
        botoesPanel.add(divBtn);
        panel.add(botoesPanel);

        JLabel resultadoLabel = new JLabel("Resultado: ", SwingConstants.CENTER);
        panel.add(resultadoLabel);

        ActionListener operacaoListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto1 = numero1.getText();
                String texto2 = numero2.getText();
                String operacao = e.getActionCommand();

                try {
                    double num1 = Double.parseDouble(texto1);
                    double num2 = Double.parseDouble(texto2);
                    double resultado;

                    switch (operacao) {
                        case "+":
                            resultado = num1 + num2;
                            break;
                        case "-":
                            resultado = num1 - num2;
                            break;
                        case "×":
                            resultado = num1 * num2;
                            break;
                        case "÷":
                            if (num2 == 0) {
                                throw new CalculadoraException("Erro: Divisão por zero!");
                            }
                            resultado = num1 / num2;
                            break;
                        default:
                            throw new CalculadoraException("Operação inválida.");
                    }

                    resultadoLabel.setText("Resultado: " + resultado);

                } catch (NumberFormatException ex) {
                    resultadoLabel.setText("Erro: entrada inválida.");
                } catch (CalculadoraException ex) {
                    resultadoLabel.setText(ex.getMessage());
                } catch (Exception ex) {
                    resultadoLabel.setText("Erro inesperado.");
                }
            }
        };

        somaBtn.addActionListener(operacaoListener);
        subBtn.addActionListener(operacaoListener);
        multBtn.addActionListener(operacaoListener);
        divBtn.addActionListener(operacaoListener);

        frame.setVisible(true);
    }
}