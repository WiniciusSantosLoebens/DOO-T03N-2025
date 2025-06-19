package fag.calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGUI extends JFrame implements ActionListener {

    private JTextField campoExibicao;
    private JButton[] botoesNumeros;

    private JButton botaoDecimal;
    private JButton botaoIgual;
    private JButton botaoLimpar;
    private JButton botaoSoma, botaoSubtracao, botaoMultiplicacao, botaoDivisao;

    private JPanel painelPrincipal;
    private JPanel painelAreaBotoes;
    private JPanel painelTecladoNumerico;
    private JPanel painelOperacoes;

    private double numero1 = 0;
    private String operador = null;

    /**
     * Construtor da classe CalculadoraGUI.
     * Configura a janela, cria os componentes e define o layout.
     */
    public CalculadoraGUI() {
        this.setTitle("Calculadora Simples");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 500);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        painelPrincipal = new JPanel();
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelPrincipal.setLayout(new BorderLayout(10, 10));
        painelPrincipal.setBackground(new Color(30, 30, 30));

        campoExibicao = new JTextField();
        campoExibicao.setFont(new Font("Arial", Font.BOLD, 30));
        campoExibicao.setEditable(false);
        campoExibicao.setHorizontalAlignment(JTextField.RIGHT);
        campoExibicao.setBackground(new Color(60, 60, 60));
        campoExibicao.setForeground(Color.WHITE);
        campoExibicao.setCaretColor(Color.WHITE);


        botoesNumeros = new JButton[10];
        for (int i = 0; i < 10; i++) {
            botoesNumeros[i] = new JButton(String.valueOf(i));
            botoesNumeros[i].addActionListener(this);
            botoesNumeros[i].setFont(new Font("Arial", Font.BOLD, 20));
            botoesNumeros[i].setFocusable(false);
            botoesNumeros[i].setBackground(new Color(100, 100, 100));
            botoesNumeros[i].setForeground(Color.WHITE);
            botoesNumeros[i].setOpaque(true);
            botoesNumeros[i].setBorderPainted(false);
        }

        botaoDecimal = new JButton(".");
        botaoIgual = new JButton("=");
        botaoLimpar = new JButton("C");
        botaoSoma = new JButton("+");
        botaoSubtracao = new JButton("-");
        botaoMultiplicacao = new JButton("×");
        botaoDivisao = new JButton("÷");

        JButton[] todosBotoesFuncao = {
                botaoDecimal, botaoIgual, botaoLimpar,
                botaoSoma, botaoSubtracao, botaoMultiplicacao, botaoDivisao
        };

        for (JButton button : todosBotoesFuncao) {
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setFocusable(false);
            button.setOpaque(true);
            button.setBorderPainted(false);

             if (button == botaoIgual) {
                 button.setBackground(new Color(255, 165, 0));
                 button.setForeground(Color.WHITE);
             } else if (button == botaoLimpar) {
                  button.setBackground(new Color(255, 0, 0));
                  button.setForeground(Color.WHITE);
             } else if (button == botaoSoma || button == botaoSubtracao ||
                        button == botaoMultiplicacao || button == botaoDivisao) {
                  button.setBackground(new Color(80, 100, 120));
                  button.setForeground(Color.WHITE);
             } else if (button == botaoDecimal) {
                  button.setBackground(new Color(100, 100, 100));
                  button.setForeground(Color.WHITE);
             }
        }

        painelTecladoNumerico = new JPanel();
        painelTecladoNumerico.setLayout(new GridLayout(4, 3, 10, 10));
        painelTecladoNumerico.setBackground(new Color(50, 50, 50));

        painelTecladoNumerico.add(botoesNumeros[7]);
        painelTecladoNumerico.add(botoesNumeros[8]);
        painelTecladoNumerico.add(botoesNumeros[9]);

        painelTecladoNumerico.add(botoesNumeros[4]);
        painelTecladoNumerico.add(botoesNumeros[5]);
        painelTecladoNumerico.add(botoesNumeros[6]);

        painelTecladoNumerico.add(botoesNumeros[1]);
        painelTecladoNumerico.add(botoesNumeros[2]);
        painelTecladoNumerico.add(botoesNumeros[3]);

        painelTecladoNumerico.add(botoesNumeros[0]);
        painelTecladoNumerico.add(botaoDecimal);
        painelTecladoNumerico.add(botaoIgual);


        painelOperacoes = new JPanel();
        painelOperacoes.setLayout(new GridLayout(5, 1, 10, 10));
        painelOperacoes.setBackground(new Color(50, 50, 50));

        painelOperacoes.add(botaoLimpar);
        painelOperacoes.add(botaoDivisao);
        painelOperacoes.add(botaoMultiplicacao);
        painelOperacoes.add(botaoSubtracao);
        painelOperacoes.add(botaoSoma);


        painelAreaBotoes = new JPanel();
        painelAreaBotoes.setLayout(new BorderLayout(10, 0));
        painelAreaBotoes.setBackground(new Color(50, 50, 50));

        painelAreaBotoes.add(painelTecladoNumerico, BorderLayout.CENTER);
        painelAreaBotoes.add(painelOperacoes, BorderLayout.EAST);


        painelPrincipal.add(campoExibicao, BorderLayout.NORTH);
        painelPrincipal.add(painelAreaBotoes, BorderLayout.CENTER);

        this.add(painelPrincipal, BorderLayout.CENTER);


        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /*
     * Método chamado automaticamente quando um evento de ação ocorre
     * (ex: um botão é clicado).
     * Implementa a lógica da calculadora e o tratamento de exceções.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if ("0123456789.".contains(comando)) {
            if (comando.equals(".") && campoExibicao.getText().contains(".")) {
                return;
            }
            if (campoExibicao.getText().startsWith("Erro") || campoExibicao.getText().equals("Operação incompleta")) {
                 campoExibicao.setText("");
            }
            campoExibicao.setText(campoExibicao.getText().concat(comando));
            return;
        }

        if ("+-\u00d7\u00f7".contains(comando)) {
             try {
                 if (!campoExibicao.getText().isEmpty() &&
                     !campoExibicao.getText().startsWith("Erro") &&
                     !campoExibicao.getText().equals("Operação incompleta")) {
                     numero1 = Double.parseDouble(campoExibicao.getText());
                     operador = comando;
                     campoExibicao.setText("");
                 } else {
                     return;
                 }
             } catch (NumberFormatException ex) {
                 campoExibicao.setText("Erro: Entrada inicial inválida");
                 numero1 = 0;
                 operador = null;
             }
            return;
        }

        if (e.getSource() == botaoIgual) {
            try {
                if (operador == null || campoExibicao.getText().isEmpty()) {
                    throw new CalculadoraException("Operação incompleta");
                }

                double numero2 = 0;
                try {
                   numero2 = Double.parseDouble(campoExibicao.getText());
                } catch (NumberFormatException ex) {
                   throw new CalculadoraException("Erro na entrada do segundo número", ex);
                }

                double resultado = 0;
                boolean calculado = false;

                switch (operador) {
                    case "+":
                        resultado = numero1 + numero2;
                        calculado = true;
                        break;
                    case "-":
                        resultado = numero1 - numero2;
                        calculado = true;
                        break;
                    case "×":
                        resultado = numero1 * numero2;
                        calculado = true;
                        break;
                    case "÷":
                        if (numero2 == 0) {
                            throw new CalculadoraException("Erro: Divisão por zero!");
                        }
                        resultado = numero1 / numero2;
                        calculado = true;
                        break;
                }

                if (calculado) {
                   if (resultado == (int) resultado) {
                       campoExibicao.setText(String.valueOf((int) resultado));
                   } else {
                       campoExibicao.setText(String.valueOf(resultado));
                   }
                }

                numero1 = resultado;
                operador = null;

            } catch (CalculadoraException ex) {
                campoExibicao.setText(ex.getMessage());
                numero1 = 0;
                operador = null;
            } catch (Exception ex) {
                 campoExibicao.setText("Erro inesperado!");
                 ex.printStackTrace();
                 numero1 = 0;
                 operador = null;
            }
            return;
        }

        if (e.getSource() == botaoLimpar) {
            campoExibicao.setText("");
            numero1 = 0;
            operador = null;
            return;
        }
    }

    /*
     * Método principal da aplicação.
     * Cria e exibe a interface gráfica.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculadoraGUI();
            }
        });
    }
}