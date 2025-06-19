import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CalculadoraGUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField campo1, campo2, resultado;
    private JButton somar, subtrair, multiplicar, dividir;

    public CalculadoraGUI() {
        setTitle("Calculadora");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        campo1 = new JTextField();
        campo2 = new JTextField();
        resultado = new JTextField();
        resultado.setEditable(false);

        somar = new JButton("+");
        subtrair = new JButton("-");
        multiplicar = new JButton("×");
        dividir = new JButton("÷");

        add(new JLabel("Número 1:"));
        add(campo1);
        add(new JLabel("Número 2:"));
        add(campo2);
        add(somar);
        add(subtrair);
        add(multiplicar);
        add(dividir);
        add(new JLabel("Resultado:"));
        add(resultado);

        somar.addActionListener(e -> calcular("+"));
        subtrair.addActionListener(e -> calcular("-"));
        multiplicar.addActionListener(e -> calcular("*"));
        dividir.addActionListener(e -> calcular("/"));
    }

    private void calcular(String operacao) {
        try {
            double num1 = lerNumero(campo1.getText());
            double num2 = lerNumero(campo2.getText());

            double res = switch (operacao) {
                case "+" -> num1 + num2;
                case "-" -> num1 - num2;
                case "*" -> num1 * num2;
                case "/" -> {
                    if (num2 == 0) {
                        throw new EntradaInvalidaException("Erro: Divisão por zero não é permitida.");
                    }
                    yield num1 / num2;
                }
                default -> throw new EntradaInvalidaException("Operação inválida.");
            };

            resultado.setText(String.valueOf(res));
        } catch (EntradaInvalidaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro: Insira apenas números válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double lerNumero(String texto) throws EntradaInvalidaException {
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException("Erro: Entrada inválida - '" + texto + "'");
        }
    }

    public static class EntradaInvalidaException extends Exception {
        public EntradaInvalidaException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraGUI calc = new CalculadoraGUI();
            calc.setVisible(true);
        });
    }
}