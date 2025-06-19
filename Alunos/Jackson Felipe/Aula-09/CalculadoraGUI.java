import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraGUI extends JFrame {

    private JTextField visor;
    private double num1;
    private String operacao;

    public CalculadoraGUI() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        visor = new JTextField();
        visor.setEditable(false);
        visor.setFont(new Font("Arial", Font.BOLD, 24));
        add(visor, BorderLayout.NORTH);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 4));

        String[] botoes = {
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "X",
                "0", ".", "=", "/",
                "C", "+/-", "", ""
        };

        for (String texto : botoes) {
            if (!texto.isEmpty()) {
                JButton botao = new JButton(texto);
                botao.setFont(new Font("Arial", Font.BOLD, 18));
                botao.addActionListener(new AcaoBotao());
                painel.add(botao);
            } else {
                painel.add(new JLabel());
            }
        }

        add(painel, BorderLayout.CENTER);
        setVisible(true);
    }

    private class AcaoBotao implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String comando = e.getActionCommand();

            if ("0123456789.".contains(comando)) {
                visor.setText(visor.getText() + comando);
            } else if ("+-X/".contains(comando)) {
                try {
                    num1 = Double.parseDouble(visor.getText());
                    operacao = comando;
                    visor.setText("");
                } catch (NumberFormatException ex) {
                    visor.setText("Erro");
                }
            } else if ("=".equals(comando)) {
                try {
                    double num2 = Double.parseDouble(visor.getText());
                    double resultado;

                    switch (operacao) {
                        case "+":
                            resultado = num1 + num2;
                            break;
                        case "-":
                            resultado = num1 - num2;
                            break;
                        case "X":
                            resultado = num1 * num2;
                            break;
                        case "/":
                            if (num2 != 0) {
                                resultado = num1 / num2;
                            } else {
                                visor.setText("Erro");
                                return;
                            }
                            break;
                        default:
                            visor.setText("Erro");
                            return;
                    }

                    if (resultado == (int) resultado) {
                        visor.setText(String.valueOf((int) resultado));
                    } else {
                        visor.setText(String.valueOf(resultado));
                    }
                } catch (NumberFormatException ex) {
                    visor.setText("Erro");
                }
            } else if ("C".equals(comando)) {
                visor.setText("");
                num1 = 0;
                operacao = "";
            } else if ("+/-".equals(comando)) {
                try {
                    double valor = Double.parseDouble(visor.getText());
                    valor *= -1;
                    if (valor == (int) valor) {
                        visor.setText(String.valueOf((int) valor));
                    } else {
                        visor.setText(String.valueOf(valor));
                    }
                } catch (NumberFormatException ex) {
                    visor.setText("Erro");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculadoraGUI::new);
    }
}
