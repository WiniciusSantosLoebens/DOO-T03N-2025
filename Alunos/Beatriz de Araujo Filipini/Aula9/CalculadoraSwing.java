import javax.swing.*;
import java.awt.event.*;

public class CalculadoraSwing extends JFrame {
    private JTextField campo1, campo2;
    private JButton btnSoma, btnSub, btnMul, btnDiv;
    private JLabel resultado;

    public CalculadoraSwing() {
        setTitle("Calculadora");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        campo1 = new JTextField();
        campo2 = new JTextField();
        btnSoma = new JButton("+");
        btnSub = new JButton("-");
        btnMul = new JButton("*");
        btnDiv = new JButton("/");
        resultado = new JLabel("Resultado:");

        campo1.setBounds(30, 20, 100, 25);
        campo2.setBounds(150, 20, 100, 25);
        btnSoma.setBounds(30, 60, 50, 30);
        btnSub.setBounds(90, 60, 50, 30);
        btnMul.setBounds(150, 60, 50, 30);
        btnDiv.setBounds(210, 60, 50, 30);
        resultado.setBounds(30, 110, 230, 25);

        
        add(campo1);
        
        add(btnSoma);
        add(btnSub);
        add(btnMul);
        add(btnDiv);
        add(campo2);
        add(resultado);

        ActionListener acao = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(campo1.getText());
                    double num2 = Double.parseDouble(campo2.getText());
                    String operacao = e.getActionCommand();

                    double res = Calculadora.calcular(num1, num2, operacao);
                    resultado.setText("Resultado: " + res);

                } catch (NumberFormatException ex) {
                    resultado.setText("Erro: Entrada inválida!");
                } catch (Exception ex) {
                    resultado.setText(ex.getMessage());
                }
            }
        };

        btnSoma.addActionListener(acao);
        btnSub.addActionListener(acao);
        btnMul.addActionListener(acao);
        btnDiv.addActionListener(acao);

        setVisible(true);
    }

    public static void main(String[] args) {
        new CalculadoraSwing();
    }
}
