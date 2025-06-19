package Calculadora;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Operacoes implements ActionListener {
    private String operacao;
    private JTextField campo1, campo2, resultado;

    public Operacoes(String operacao, JTextField campo1, JTextField campo2, JTextField resultado) {
        this.operacao = operacao;
        this.campo1 = campo1;
        this.campo2 = campo2;
        this.resultado = resultado;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double num1 = Double.parseDouble(campo1.getText());
            double num2 = Double.parseDouble(campo2.getText());
            double res = 0;

            switch (operacao) {
                case "+":
                    res = num1 + num2;
                    break;
                case "-":
                    res = num1 - num2;
                    break;
                case "*":
                    res = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        resultado.setText("Erro: divisão por 0");
                        return;
                    }
                    res = num1 / num2;
                    break;
            }

            resultado.setText(String.valueOf(res));
        } catch (NumberFormatException ex) {
            resultado.setText("Erro: número inválido");
        }
    }
}
