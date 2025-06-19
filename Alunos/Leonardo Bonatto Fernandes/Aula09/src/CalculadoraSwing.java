import javax.swing.*;
import javax.swing.text.AbstractDocument;

public class CalculadoraSwing extends JFrame {
    public JTextField campo1, campo2;
    public JLabel resultado;

    public CalculadoraSwing() {
        setTitle("Calculadora");
        setSize(200, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        campo1 = new JTextField(10); ((AbstractDocument) campo1.getDocument()).setDocumentFilter(new BloqueadoraDeCaracteres());
        campo2 = new JTextField(10); ((AbstractDocument) campo2.getDocument()).setDocumentFilter(new BloqueadoraDeCaracteres());
        resultado = new JLabel("Resultado = ");

        JButton btnSoma = new JButton("+");
        JButton btnSubtrai = new JButton("-");
        JButton btnMultiplica = new JButton("×");
        JButton btnDivide = new JButton("÷");

        JPanel painel = new JPanel();
        painel.add(new JLabel("Número 1:"));
        painel.add(campo1);
        painel.add(new JLabel("Número 2:"));
        painel.add(campo2);
        painel.add(btnSoma);
        painel.add(btnSubtrai);
        painel.add(btnMultiplica);
        painel.add(btnDivide);
        painel.add(resultado);

        add(painel);

        btnSoma.addActionListener(e -> executarCalculo("+"));
        btnSubtrai.addActionListener(e -> executarCalculo("-"));
        btnMultiplica.addActionListener(e -> executarCalculo("*"));
        btnDivide.addActionListener(e -> executarCalculo("/"));
    }

    private void executarCalculo(String operacao) {
        try {
            double num1 = Calculadora.parseInput(campo1.getText());
            double num2 = Calculadora.parseInput(campo2.getText());
            double res = Calculadora.calcular(num1, num2, operacao);
            resultado.setText("Resultado: " + res);
        } catch (InputInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
