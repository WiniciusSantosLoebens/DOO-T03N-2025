import javax.swing.*;
import java.awt.*;

public class Calculadora extends JFrame {
    private JTextField num1Field, num2Field, resultadoField;
    private JButton btnSoma, btnSubtracao, btnMultiplicacao, btnDivisao, btnLimpar;

    public Calculadora() {
        // Configuração da janela
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Inicialização dos componentes
        JLabel num1Label = new JLabel("Número 1:");
        JLabel num2Label = new JLabel("Número 2:");
        JLabel resultadoLabel = new JLabel("Resultado:");

        num1Field = new JTextField(15);
        num2Field = new JTextField(15);
        resultadoField = new JTextField(15);
        resultadoField.setEditable(false);

        btnSoma = new JButton("+");
        btnSubtracao = new JButton("-");
        btnMultiplicacao = new JButton("×");
        btnDivisao = new JButton("÷");
        btnLimpar = new JButton("Limpar");

        // Layout dos componentes
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Primeira linha
        gbc.gridx = 0; gbc.gridy = 0;
        add(num1Label, gbc);
        gbc.gridx = 1;
        add(num1Field, gbc);

        // Segunda linha
        gbc.gridx = 0; gbc.gridy = 1;
        add(num2Label, gbc);
        gbc.gridx = 1;
        add(num2Field, gbc);

        // Terceira linha (botões)
        JPanel botoesPanel = new JPanel(new FlowLayout());
        botoesPanel.add(btnSoma);
        botoesPanel.add(btnSubtracao);
        botoesPanel.add(btnMultiplicacao);
        botoesPanel.add(btnDivisao);
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(botoesPanel, gbc);

        // Quarta linha
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(resultadoLabel, gbc);
        gbc.gridx = 1;
        add(resultadoField, gbc);

        // Quinta linha
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(btnLimpar, gbc);

        // Adicionar listeners aos botões
        btnSoma.addActionListener(e -> realizarOperacao('+'));
        btnSubtracao.addActionListener(e -> realizarOperacao('-'));
        btnMultiplicacao.addActionListener(e -> realizarOperacao('×'));
        btnDivisao.addActionListener(e -> realizarOperacao('÷'));
        btnLimpar.addActionListener(e -> limparCampos());
    }

    private void realizarOperacao(char operacao) {
        try {
            validarEntradas();
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = Double.parseDouble(num2Field.getText());
            double resultado = 0;

            switch (operacao) {
                case '+':
                    resultado = num1 + num2;
                    break;
                case '-':
                    resultado = num1 - num2;
                    break;
                case '×':
                    resultado = num1 * num2;
                    break;
                case '÷':
                    if (num2 == 0) {
                        throw new CalculadoraException("Erro: Divisão por zero não é permitida!");
                    }
                    resultado = num1 / num2;
                    break;
            }

            resultadoField.setText(String.format("%.2f", resultado));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Erro: Por favor, insira apenas números válidos!",
                "Erro de Entrada",
                JOptionPane.ERROR_MESSAGE);
        } catch (CalculadoraException e) {
            JOptionPane.showMessageDialog(this,
                e.getMessage(),
                "Erro de Operação",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validarEntradas() throws CalculadoraException {
        String num1 = num1Field.getText().trim();
        String num2 = num2Field.getText().trim();

        if (num1.isEmpty() || num2.isEmpty()) {
            throw new CalculadoraException("Erro: Todos os campos devem ser preenchidos!");
        }

        try {
            Double.parseDouble(num1);
            Double.parseDouble(num2);
        } catch (NumberFormatException e) {
            throw new CalculadoraException("Erro: Por favor, insira apenas números válidos!");
        }
    }

    private void limparCampos() {
        num1Field.setText("");
        num2Field.setText("");
        resultadoField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculadora().setVisible(true);
        });
    }
} 