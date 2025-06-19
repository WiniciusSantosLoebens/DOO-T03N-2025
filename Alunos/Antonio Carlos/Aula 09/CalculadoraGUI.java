package fag.calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGUI extends JFrame implements ActionListener {

    // Componentes da GUI
    private JTextField displayField;
    private JButton[] numberButtons; 
    private JButton decButton; 
    private JButton equButton; 

    private JButton[] operationButtons; 
    private JButton clrButton; 

    // Painéis para organizar os componentes
    private JPanel mainPanel; 
    private JPanel buttonPanel; 
    private JPanel numberPadPanel; 
    private JPanel operationPanel; 

    // Variáveis de estado para o cálculo
    private double num1 = 0;
    private String operator = null;

    public CalculadoraGUI() {
        // Configurações da janela principal (JFrame)
        this.setTitle("Calculadora Simples");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 450); 
        this.setLayout(new BorderLayout()); 
        this.setResizable(false);

        mainPanel = new JPanel();
        // Adiciona um espaçamento interno (padding) ao redor do conteúdo
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BorderLayout(10, 10)); 

        // Campo de exibição
        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 30));
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.RIGHT);

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
            numberButtons[i].setFocusable(false);
        }

        decButton = new JButton(".");
        equButton = new JButton("=");
        // Adiciona ActionListeners e configura fonte para decimal e igual
        decButton.addActionListener(this);
        decButton.setFont(new Font("Arial", Font.BOLD, 20));
        decButton.setFocusable(false);

        equButton.addActionListener(this);
        equButton.setFont(new Font("Arial", Font.BOLD, 20));
        equButton.setFocusable(false);
        equButton.setBackground(Color.ORANGE);
        equButton.setOpaque(true); 
        equButton.setBorderPainted(false); 


        // Criação dos botões de operação (+, -, *, /) e Clear (C)
        operationButtons = new JButton[4];
        operationButtons[0] = new JButton("+");
        operationButtons[1] = new JButton("-");
        operationButtons[2] = new JButton("×");
        operationButtons[3] = new JButton("÷");

        clrButton = new JButton("C");

        // Adiciona ActionListener e configura fonte para botões de operação e Clear
        for (JButton button : operationButtons) {
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setFocusable(false);
            button.setBackground(Color.LIGHT_GRAY); 
            button.setOpaque(true);
            button.setBorderPainted(false);
        }

        clrButton.addActionListener(this);
        clrButton.setFont(new Font("Arial", Font.BOLD, 20));
        clrButton.setFocusable(false);
        clrButton.setBackground(Color.RED); 
        clrButton.setForeground(Color.WHITE); 
        clrButton.setOpaque(true);
        clrButton.setBorderPainted(false);

        // Organizando os painéis de botões
        // Painel para o bloco numérico (3x4)
        numberPadPanel = new JPanel();
        numberPadPanel.setLayout(new GridLayout(4, 3, 10, 10));

        // Adiciona botões ao numberPadPanel na ordem (7,8,9 / 4,5,6 / 1,2,3 / .,0,=)
        numberPadPanel.add(numberButtons[7]);
        numberPadPanel.add(numberButtons[8]);
        numberPadPanel.add(numberButtons[9]);

        numberPadPanel.add(numberButtons[4]);
        numberPadPanel.add(numberButtons[5]);
        numberPadPanel.add(numberButtons[6]);

        numberPadPanel.add(numberButtons[1]);
        numberPadPanel.add(numberButtons[2]);
        numberPadPanel.add(numberButtons[3]);

        numberPadPanel.add(decButton);
        numberPadPanel.add(numberButtons[0]);
        numberPadPanel.add(equButton);


        // Painel para as operações (5x1)
        operationPanel = new JPanel();
        operationPanel.setLayout(new GridLayout(5, 1, 10, 10));

        operationPanel.add(operationButtons[0]); // +
        operationPanel.add(operationButtons[1]); // -
        operationPanel.add(operationButtons[2]); // *
        operationPanel.add(operationButtons[3]); // /
        operationPanel.add(clrButton);         // C


        // Painel que junta o numberPadPanel e o operationPanel
        buttonPanel = new JPanel();
        
        buttonPanel.setLayout(new BorderLayout(10, 0));

        buttonPanel.add(numberPadPanel, BorderLayout.CENTER);
        buttonPanel.add(operationPanel, BorderLayout.EAST);


        // Adiciona o campo de exibição e o painel de botões ao mainPanel
        mainPanel.add(displayField, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Adiciona o mainPanel ao JFrame principal
        this.add(mainPanel, BorderLayout.CENTER);

        this.setLocationRelativeTo(null); // Centraliza a janela na tela
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Lógica para botões numéricos e decimal
        if ("0123456789.".contains(command)) {
            if (command.equals(".") && displayField.getText().contains(".")) {
                return; // Ignora múltiplos pontos decimais
            }
            if (displayField.getText().startsWith("Erro") || displayField.getText().equals("Operação incompleta")) {
                 displayField.setText("");
            }
            displayField.setText(displayField.getText().concat(command));
            return;
        }

        if ("+-\u00d7\u00f7".contains(command)) {
             try {
                 if (!displayField.getText().isEmpty() &&
                     !displayField.getText().startsWith("Erro") &&
                     !displayField.getText().equals("Operação incompleta")) { 
                     num1 = Double.parseDouble(displayField.getText());
                     operator = command; 
                     displayField.setText(""); 
                 } else {
                     return;
                 }
             } catch (NumberFormatException ex) {
                 
                 displayField.setText("Erro: Entrada inicial inválida");
                 num1 = 0;
                 operator = null;
             }
            return;
        }

        if (e.getSource() == equButton) { 
            try {
                if (operator == null || displayField.getText().isEmpty()) {
                    throw new CalculadoraException("Operação incompleta");
                }

                double num2 = 0;
                try {
                   num2 = Double.parseDouble(displayField.getText());
                } catch (NumberFormatException ex) {
                   throw new CalculadoraException("Erro na entrada do segundo número", ex);
                }

                double result = 0;
                boolean calculated = false;

                // Realiza a operação
                switch (operator) {
                    case "+": result = num1 + num2; calculated = true; break;
                    case "-": result = num1 - num2; calculated = true; break;
                    case "×": result = num1 * num2; calculated = true; break;
                    case "÷":
                        if (num2 == 0) {
                            throw new CalculadoraException("Erro: Divisão por zero!");
                        }
                        result = num1 / num2;
                        calculated = true;
                        break;
                }

                // Exibe o resultado
                if (calculated) {
                   // Formata resultado: inteiro se for, decimal caso contrário
                   if (result == (int) result) {
                       displayField.setText(String.valueOf((int) result));
                   } else {
                       displayField.setText(String.valueOf(result));
                   }
                }

              
                num1 = result; 
                operator = null; 

            } catch (CalculadoraException ex) {
                displayField.setText(ex.getMessage());
                num1 = 0;
                operator = null;
            } catch (Exception ex) {
                 displayField.setText("Erro inesperado!");
                 ex.printStackTrace(); 
                 num1 = 0;
                 operator = null;
            }
            return;
        }

        // Lógica para botão Clear (C)
        if (e.getSource() == clrButton) {
            displayField.setText("");
            num1 = 0;
            operator = null;
            return;
        }
    }

    // Método principal para iniciar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraGUI());
    }
}