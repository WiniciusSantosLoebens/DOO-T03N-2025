package Calculadora;
import java.awt.*;
import javax.swing.*;

public class Interface extends JFrame {

    private JTextField campo1, campo2, resultado;
    private JButton soma, subtrai, multiplica, divide;

    public Interface() {
        super("Calculadora");
        initComponents();
    }
 
    private void initComponents() {
        campo1 = new JTextField();
        campo2 = new JTextField();
        resultado = new JTextField();
        resultado.setEditable(false);
        resultado.setBackground(new Color(230, 230, 230));

        soma = new JButton("+");
        subtrai = new JButton("-");
        multiplica = new JButton("x");
        divide = new JButton("÷");

        JPanel painel = new JPanel(new GridLayout(5, 2, 5, 5));

        painel.add(new JLabel("Número 1:"));
        painel.add(campo1);
        painel.add(new JLabel("Número 2:"));
        painel.add(campo2);
        painel.add(soma);
        painel.add(subtrai);
        painel.add(multiplica);
        painel.add(divide);
        painel.add(new JLabel("Resultado:"));
        painel.add(resultado);

        add(painel);

        soma.addActionListener(new Operacoes("+", campo1, campo2, resultado));
        subtrai.addActionListener(new Operacoes("-", campo1, campo2, resultado));
        multiplica.addActionListener(new Operacoes("*", campo1, campo2, resultado));
        divide.addActionListener(new Operacoes("/", campo1, campo2, resultado));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

