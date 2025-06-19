import javax.swing.*;
import java.awt.*;

public class Main {


    static int opcao;

    public static void main(String[] args) {
        JFrame janela = new JFrame();


        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setBounds(100, 100, 300, 300);
        janela.setSize(500, 500);
        janela.setLayout(null);
        janela.setTitle("Calculadora");
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);

        JLabel label = new JLabel("Calculadora");
        label.setBounds(190, 5, 300, 80);
        label.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel label1 = new JLabel("Adicione o numero 1");
        label1.setBounds(175, 100, 200, 15);
        label1.setFont(new Font("Arial", Font.BOLD, 15));

        JTextField numero1 = new JTextField();
        numero1.setBounds(190, 125, 100, 30);


        JLabel label2 = new JLabel("Adicione o numero 2");
        label2.setBounds(175, 160, 200, 15);
        label2.setFont(new Font("Arial", Font.BOLD, 15));

        JTextField numero2 = new JTextField();
        numero2.setBounds(190, 185, 100, 30);


        JButton somar = new JButton("+");
        somar.setBounds(190, 225, 100, 20);
        somar.addActionListener(e -> {
            opcao = 1;
        });

        JButton subtrair = new JButton("-");
        subtrair.setBounds(190, 250, 100, 20);
        subtrair.addActionListener(e -> {
            opcao = 2;
        });


        JButton dividir = new JButton("÷");
        dividir.setBounds(190, 275, 100, 20);
        dividir.addActionListener(e -> {
            opcao = 3;
        });


        JButton multiplicacao = new JButton("×");
        multiplicacao.setBounds(190, 300, 100, 20);
        multiplicacao.addActionListener(e -> {
            opcao = 4;
        });

        JTextField resultado = new JTextField();
        resultado.setBounds(145, 390, 200, 30);
        resultado.setEditable(false);
        resultado.setForeground(Color.red);
        resultado.setFont(new Font("Arial", Font.BOLD, 15));


        JButton operacao = new JButton("=");
        operacao.setBounds(180, 330, 125, 40);
        operacao.addActionListener(e -> {
            if (opcao == 1) {

                try {
                    double soma = Double.parseDouble(numero1.getText()) + Double.parseDouble(numero2.getText());
                    resultado.setText(String.valueOf(soma));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Por favor\nDigite apenas números!", "Carácter inválido",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (opcao == 2) {
                try {
                    double sub = Double.parseDouble(numero1.getText()) - Double.parseDouble(numero2.getText());
                    resultado.setText(String.valueOf(sub));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Por favor \nDigite apenas numeros!", "Caracter invalido",
                            JOptionPane.WARNING_MESSAGE);
                }

            } else if (opcao == 3) {
                try {
                    double divisor = Double.parseDouble(numero2.getText());
                    if (divisor == 0) {
                        JOptionPane.showMessageDialog(null,
                                "Divisão por zero não e permitida!", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        double divisao = Double.parseDouble(numero1.getText()) / divisor;
                        resultado.setText(String.valueOf(divisao));
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Por favor \nDigite apenas numeros!", "Caracter invalido",
                            JOptionPane.WARNING_MESSAGE);
                }

            } else if (opcao == 4) {
                try {
                    double mult = Double.parseDouble(numero1.getText()) * Double.parseDouble(numero2.getText());
                    resultado.setText(String.valueOf(mult));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Por favor \nDigite apenas numeros!", "Caracter invalido",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        janela.add(label);
        janela.add(label1);
        janela.add(label2);
        janela.add(numero1);
        janela.add(numero2);
        janela.add(somar);
        janela.add(subtrair);
        janela.add(dividir);
        janela.add(multiplicacao);
        janela.add(operacao);
        janela.add(resultado);

        janela.setVisible(true);
    }

}