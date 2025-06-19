import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class DivisaoPorZeroException extends Exception {
    public DivisaoPorZeroException() {
        super("Erro: Tentativa de divisão por zero.");
    }
}

class EntradaInvalidaException extends Exception {
    public EntradaInvalidaException(String mensagem) {
        super("Erro de entrada: " + mensagem);
    }
}

public class calculadora extends JFrame implements ActionListener {

    JTextField visor;
    JTextArea historicoArea;
    JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bmais, bmenos, bmultiplicar, bdividir, bigual, bc, bhistorico;
    String primeiro = "", segundo = "";
    int cont = 1;
    String sinal = "";
    ArrayList<String> historico = new ArrayList<>();

    public calculadora() {
        super("Calculadora");
        this.setSize(350, 550);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        visor = new JTextField();
        visor.setBounds(10, 10, 320, 40);
        visor.setEditable(false);
        this.add(visor);

        historicoArea = new JTextArea();
        historicoArea.setBounds(10, 400, 320, 100);
        historicoArea.setEditable(false);
        this.add(historicoArea);

        b1 = new JButton("1");
        b1.setBounds(10, 60, 70, 50);
        b1.addActionListener(this);
        this.add(b1);

        b2 = new JButton("2");
        b2.setBounds(90, 60, 70, 50);
        b2.addActionListener(this);
        this.add(b2);

        b3 = new JButton("3");
        b3.setBounds(170, 60, 70, 50);
        b3.addActionListener(this);
        this.add(b3);

        bdividir = new JButton("/");
        bdividir.setBounds(250, 60, 70, 50);
        bdividir.addActionListener(this);
        this.add(bdividir);

        b4 = new JButton("4");
        b4.setBounds(10, 120, 70, 50);
        b4.addActionListener(this);
        this.add(b4);

        b5 = new JButton("5");
        b5.setBounds(90, 120, 70, 50);
        b5.addActionListener(this);
        this.add(b5);

        b6 = new JButton("6");
        b6.setBounds(170, 120, 70, 50);
        b6.addActionListener(this);
        this.add(b6);

        bmultiplicar = new JButton("*");
        bmultiplicar.setBounds(250, 120, 70, 50);
        bmultiplicar.addActionListener(this);
        this.add(bmultiplicar);

        b7 = new JButton("7");
        b7.setBounds(10, 180, 70, 50);
        b7.addActionListener(this);
        this.add(b7);

        b8 = new JButton("8");
        b8.setBounds(90, 180, 70, 50);
        b8.addActionListener(this);
        this.add(b8);

        b9 = new JButton("9");
        b9.setBounds(170, 180, 70, 50);
        b9.addActionListener(this);
        this.add(b9);

        bmenos = new JButton("-");
        bmenos.setBounds(250, 180, 70, 50);
        bmenos.addActionListener(this);
        this.add(bmenos);

        b0 = new JButton("0");
        b0.setBounds(10, 240, 70, 50);
        b0.addActionListener(this);
        this.add(b0);

        bc = new JButton("C");
        bc.setBounds(90, 240, 70, 50);
        bc.addActionListener(this);
        this.add(bc);

        bigual = new JButton("=");
        bigual.setBounds(170, 240, 70, 50);
        bigual.addActionListener(this);
        this.add(bigual);

        bmais = new JButton("+");
        bmais.setBounds(250, 240, 70, 50);
        bmais.addActionListener(this);
        this.add(bmais);

        bhistorico = new JButton("Histórico");
        bhistorico.setBounds(10, 350, 320, 40);
        bhistorico.addActionListener(this);
        this.add(bhistorico);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new calculadora();
    }

    public void actionPerformed(ActionEvent e) {
        String n = "";

        if (e.getSource() == b1)
            n = "1";
        else if (e.getSource() == b2)
            n = "2";
        else if (e.getSource() == b3)
            n = "3";
        else if (e.getSource() == b4)
            n = "4";
        else if (e.getSource() == b5)
            n = "5";
        else if (e.getSource() == b6)
            n = "6";
        else if (e.getSource() == b7)
            n = "7";
        else if (e.getSource() == b8)
            n = "8";
        else if (e.getSource() == b9)
            n = "9";
        else if (e.getSource() == b0)
            n = "0";

        if (!n.isEmpty()) {
            if (cont == 1) {
                primeiro += n;
            } else {
                segundo += n;
            }
            visor.setText(primeiro + " " + sinal + " " + segundo);
        }

        else if (e.getSource() == bmais) {
            if (!primeiro.isEmpty()) {
                sinal = "+";
                cont = 2;
                visor.setText(primeiro + " " + sinal);
            }
        } else if (e.getSource() == bmenos) {
            if (!primeiro.isEmpty()) {
                sinal = "-";
                cont = 2;
                visor.setText(primeiro + " " + sinal);
            }
        } else if (e.getSource() == bmultiplicar) {
            if (!primeiro.isEmpty()) {
                sinal = "*";
                cont = 2;
                visor.setText(primeiro + " " + sinal);
            }
        } else if (e.getSource() == bdividir) {
            if (!primeiro.isEmpty()) {
                sinal = "/";
                cont = 2;
                visor.setText(primeiro + " " + sinal);
            }
        }

        else if (e.getSource() == bc) {
            visor.setText("");
            primeiro = "";
            segundo = "";
            sinal = "";
            cont = 1;
        }

        else if (e.getSource() == bigual) {
            calcularResultado();
        }

        else if (e.getSource() == bhistorico) {
            exibirHistorico();
        }
    }

    private void calcularResultado() {
        try {
            if (primeiro.isEmpty() || segundo.isEmpty() || sinal.isEmpty()) {
                throw new EntradaInvalidaException("Digite dois números antes de calcular!");
            }

            int num1 = Integer.parseInt(primeiro);
            int num2 = Integer.parseInt(segundo);

            if (sinal.equals("/") && num2 == 0) {
                throw new DivisaoPorZeroException();
            }

            int resultado = 0;
            switch (sinal) {
                case "+":
                    resultado = num1 + num2;
                    break;
                case "-":
                    resultado = num1 - num2;
                    break;
                case "*":
                    resultado = num1 * num2;
                    break;
                case "/":
                    resultado = num1 / num2;
                    break;
            }

            String operacao = primeiro + " " + sinal + " " + segundo + " = " + resultado;
            historico.add(operacao);
            visor.setText(String.valueOf(resultado));

            primeiro = "";
            segundo = "";
            sinal = "";
            cont = 1;

        } catch (DivisaoPorZeroException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de Divisão", JOptionPane.ERROR_MESSAGE);
            visor.setText("");
            primeiro = "";
            segundo = "";
            sinal = "";
            cont = 1;
        } catch (Exception e) {
            visor.setText(e.getMessage());
        }
    }

    private void exibirHistorico() {
        if (historico.isEmpty()) {
            historicoArea.setText("Nenhuma operação realizada ainda.");
        } else {
            historicoArea.setText(String.join("\n", historico));
        }
    }
}
