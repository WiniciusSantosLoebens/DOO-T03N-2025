import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculadora extends JFrame implements ActionListener { private JTextField display; private String currentOperator = ""; private double firstNumber = 0; private boolean startNewNumber = true;

    public Calculadora() {
        setTitle("calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setFont(new Font("Montserrat", Font.BOLD, 24));
        display.setBounds(10, 10, 260, 50);
        add(display);

        JButton btn1 = new JButton("1");
        btn1.setBounds(10, 190, 60, 50);
        btn1.setFont(new Font("Montserrat", Font.PLAIN, 18));
        btn1.addActionListener(this);
        add(btn1);

        JButton btn2 = new JButton("2");
        btn2.setBounds(80, 190, 60, 50);
        btn2.setFont(new Font("Montserrat", Font.PLAIN, 18));
        btn2.addActionListener(this);
        add(btn2);

        JButton btn3 = new JButton("3");
        btn3.setBounds(150, 190, 60, 50);
        btn3.setFont(new Font("Montserrat", Font.PLAIN, 18));
        btn3.addActionListener(this);
        add(btn3);

        JButton btn4 = new JButton("4");
        btn4.setBounds(10, 130, 60, 50);
        btn4.setFont(new Font("Montserrat", Font.PLAIN, 18));
        btn4.addActionListener(this);
        add(btn4);

        JButton btn5 = new JButton("5");
        btn5.setBounds(80, 130, 60, 50);
        btn5.setFont(new Font("Montserrat", Font.PLAIN, 18));
        btn5.addActionListener(this);
        add(btn5);

        JButton btn6 = new JButton("6");
        btn6.setBounds(150, 130, 60, 50);
        btn6.setFont(new Font("Montserrat", Font.PLAIN, 18));
        btn6.addActionListener(this);
        add(btn6);


        JButton btn7 = new JButton("7");
        btn7.setBounds(10, 70, 60, 50);
        btn7.setFont(new Font("Montserrat", Font.PLAIN, 18));
        btn7.addActionListener(this);
        add(btn7);

        JButton btn8 = new JButton("8");
        btn8.setBounds(80, 70, 60, 50);
        btn8.setFont(new Font("Montserrat", Font.PLAIN, 18));
        btn8.addActionListener(this);
        add(btn8);

        JButton btn9 = new JButton("9");
        btn9.setBounds(150, 70, 60, 50);
        btn9.setFont(new Font("Montserrat", Font.PLAIN, 18));
        btn9.addActionListener(this);
        add(btn9);

        JButton btn0 = new JButton("0");
        btn0.setBounds(10, 250, 60, 50);
        btn0.setFont(new Font("Montserrat", Font.PLAIN, 18));
        btn0.addActionListener(this);
        add(btn0);

        JButton btnDiv = new JButton("/");
        btnDiv.setBounds(220, 70, 60, 50);
        btnDiv.setFont(new Font("Montserrat", Font.PLAIN, 18));
        btnDiv.addActionListener(this);
        add(btnDiv);

        JButton btnMul = new JButton("*");
        btnMul.setBounds(220, 130, 60, 50);
        btnMul.setFont(new Font("Montserrat", Font.PLAIN, 18));
        btnMul.addActionListener(this);
        add(btnMul);


        JButton btnSub = new JButton("-");
        btnSub.setBounds(220, 190, 60, 50);
        btnSub.setFont(new Font("Montserrat", Font.PLAIN, 18));
        btnSub.addActionListener(this);
        add(btnSub);


        JButton btnEq = new JButton("=");
        btnEq.setBounds(80, 250, 130, 50);
        btnEq.setFont(new Font("Montserrat", Font.PLAIN, 18));
        btnEq.addActionListener(this);
        add(btnEq);

        JButton btnAdd = new JButton("+");
        btnAdd.setBounds(220, 250, 60, 50);
        btnAdd.setFont(new Font("Montserrat", Font.PLAIN, 18));
        btnAdd.addActionListener(this);
        add(btnAdd);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        try {
            if ("0123456789".contains(cmd)) {
                if (startNewNumber) {
                    display.setText(cmd);
                    startNewNumber = false;
                } else {
                    String current = display.getText();
                    display.setText(current.equals("0") ? cmd : current + cmd);
                }
            } else if (cmd.equals("=")) {
                double value = Double.parseDouble(display.getText());
                calculate(value);
                currentOperator = "";
                startNewNumber = true;
            } else {
                double value = Double.parseDouble(display.getText());
                if (!currentOperator.isEmpty()) {
                    calculate(value);
                } else {
                    firstNumber = value;
                }
                currentOperator = cmd;
                startNewNumber = true;
            }
        } catch (NumberFormatException ex) {
            showError();
        }
    }

    private void calculate(double secondOperand) {
        try {
            if ("/".equals(currentOperator) && secondOperand == 0) {
                throw new ArithmeticException();
            }
            switch (currentOperator) {
                case "+": firstNumber += secondOperand; break;
                case "-": firstNumber -= secondOperand; break;
                case "*": firstNumber *= secondOperand; break;
                case "/": firstNumber /= secondOperand; break;
                default: firstNumber = secondOperand; break;
            }
            display.setText(String.valueOf(firstNumber));
        } catch (ArithmeticException ex) {
            showError();
        }
    }

    private void showError() {
        display.setText("Error");
        clearState();
        startNewNumber = true;
    }

    private void clearState() {
        firstNumber = 0;
        currentOperator = "";
    }

    public static void main(String[] args) {
        Calculadora calc = new Calculadora();
        calc.setVisible(true);
    }

}