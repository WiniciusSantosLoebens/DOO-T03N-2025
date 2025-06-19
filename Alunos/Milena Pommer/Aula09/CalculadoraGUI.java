package fag.calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGUI extends JFrame implements ActionListener {

    // Componentes da Interface Gráfica (GUI)
    private JTextField campoExibicao;
    private JButton[] botoesNumeros;

    private JButton botaoDecimal;
    private JButton botaoIgual;
    private JButton botaoLimpar;
    private JButton botaoSoma, botaoSubtracao, botaoMultiplicacao, botaoDivisao;

    //Painéis para organizar a disposição dos componentes
    private JPanel painelPrincipal;
    private JPanel painelAreaBotoes;
    private JPanel painelTecladoNumerico;
    private JPanel painelLinhaInferior;
    private JPanel painelOperacoes;

    private double numero1 = 0;
    private String operador = null;

    
    public CalculadoraGUI() {
        // Configurações da janela principal (JFrame)
        this.setTitle("Calculadora Simples");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 480);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        painelPrincipal = new JPanel();
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelPrincipal.setLayout(new BorderLayout(10, 10));
        painelPrincipal.setBackground(new Color(45, 45, 45));

        campoExibicao = new JTextField();
        campoExibicao.setFont(new Font("Arial", Font.BOLD, 30));
        campoExibicao.setEditable(false);
        campoExibicao.setHorizontalAlignment(JTextField.RIGHT);
        campoExibicao.setBackground(new Color(60, 60, 60));
        campoExibicao.setForeground(Color.WHITE);
        campoExibicao.setCaretColor(Color.WHITE);


        // Criação dos botões numéricos (0 a 9)
        botoesNumeros = new JButton[10];
        for (int i = 0; i < 10; i++) {
            botoesNumeros[i] = new JButton(String.valueOf(i));
            botoesNumeros[i].addActionListener(this);
            botoesNumeros[i].setFont(new Font("Arial", Font.BOLD, 20));
            botoesNumeros[i].setFocusable(false);
            botoesNumeros[i].setBackground(new Color(80, 80, 80));
            botoesNumeros[i].setForeground(Color.WHITE);
            botoesNumeros[i].setOpaque(true);
            botoesNumeros[i].setBorderPainted(false);
        }

        // Criação dos botões de função específicos
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
                 button.setBackground(new Color(255, 140, 0));
                 button.setForeground(Color.WHITE);
             } else if (button == botaoLimpar) {
                  button.setBackground(new Color(220, 20, 60));
                  button.setForeground(Color.WHITE);
             } else if (button == botaoSoma || button == botaoSubtracao ||
                        button == botaoMultiplicacao || button == botaoDivisao) {
                  button.setBackground(new Color(100, 100, 100));
                  button.setForeground(Color.WHITE);
             } else if (button == botaoDecimal) {
                  button.setBackground(new Color(80, 80, 80));
                  button.setForeground(Color.WHITE);
             }
        }

        // Organizando os painéis que contêm os botões

        painelTecladoNumerico = new JPanel();
        painelTecladoNumerico.setLayout(new GridLayout(3, 3, 10, 10));
        painelTecladoNumerico.setBackground(new Color(55, 55, 55));

        painelTecladoNumerico.add(botoesNumeros[7]);
        painelTecladoNumerico.add(botoesNumeros[8]);
        painelTecladoNumerico.add(botoesNumeros[9]);

        painelTecladoNumerico.add(botoesNumeros[4]);
        painelTecladoNumerico.add(botoesNumeros[5]);
        painelTecladoNumerico.add(botoesNumeros[6]);

        painelTecladoNumerico.add(botoesNumeros[1]);
        painelTecladoNumerico.add(botoesNumeros[2]);
        painelTecladoNumerico.add(botoesNumeros[3]);


        painelLinhaInferior = new JPanel();
        painelLinhaInferior.setLayout(new GridLayout(1, 3, 10, 10));
        painelLinhaInferior.setBackground(new Color(55, 55, 55));

        painelLinhaInferior.add(botoesNumeros[0]);
        painelLinhaInferior.add(botaoDecimal);
        painelLinhaInferior.add(botaoIgual);


        JPanel painelLadoEsquerdoBotoes = new JPanel();
        painelLadoEsquerdoBotoes.setLayout(new BorderLayout(0, 10));
        painelLadoEsquerdoBotoes.setBackground(new Color(55, 55, 55));

        painelLadoEsquerdoBotoes.add(painelTecladoNumerico, BorderLayout.CENTER);
        painelLadoEsquerdoBotoes.add(painelLinhaInferior, BorderLayout.SOUTH);


        painelOperacoes = new JPanel();
        painelOperacoes.setLayout(new GridLayout(5, 1, 10, 10));
        painelOperacoes.setBackground(new Color(55, 55, 55));

        painelOperacoes.add(botaoLimpar);
        painelOperacoes.add(botaoSoma);
        painelOperacoes.add(botaoSubtracao);
        painelOperacoes.add(botaoMultiplicacao);
        painelOperacoes.add(botaoDivisao);


        painelAreaBotoes = new JPanel();
        painelAreaBotoes.setLayout(new BorderLayout(10, 0));
        painelAreaBotoes.setBackground(new Color(55, 55, 55));

        painelAreaBotoes.add(painelLadoEsquerdoBotoes, BorderLayout.CENTER);
        painelAreaBotoes.add(painelOperacoes, BorderLayout.EAST);


        painelPrincipal.add(campoExibicao, BorderLayout.NORTH);
        painelPrincipal.add(painelAreaBotoes, BorderLayout.CENTER);

        this.add(painelPrincipal, BorderLayout.CENTER);


        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

 
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        // Lógica para botões numéricos e o ponto decimal
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

        // Lógica para o botão de igual (=)
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

                // Realiza a operação matemática com base no operador armazenado
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

                // Exibe o resultado
                if (calculado) {
                   if (resultado == (int) resultado) {
                       campoExibicao.setText(String.valueOf((int) resultado));
                   } else {
                       campoExibicao.setText(String.valueOf(resultado));
                   }
                }

                // Prepara para a próxima operação
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

        // Lógica para o botão Limpar (C)
        if (e.getSource() == botaoLimpar) {
            campoExibicao.setText("");
            numero1 = 0;
            operador = null;
            return;
        }
    }

   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculadoraGUI(); 
            }
        });
    }
}