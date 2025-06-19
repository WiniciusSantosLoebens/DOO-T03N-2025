package Alunos.Gustavo_Baú.Aula09;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Aula09 {

    public static void main(String[] args) {
       
        JFrame janela = new JFrame("Minha Calculadora");
        janela.setSize(350, 250);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 2, 5, 5));
        
        JLabel label1 = new JLabel("Primeiro número:");
        JTextField campo1 = new JTextField();
        
        JLabel label2 = new JLabel("Segundo número:");
        JTextField campo2 = new JTextField();
        
        JButton botaoSoma = new JButton("+");
        JButton botaoSubtracao = new JButton("-");
        JButton botaoMultiplica = new JButton("*");
        JButton botaoDivide = new JButton("/");
        
        JLabel resultado = new JLabel("Resultado: ");
        
        painel.add(label1);
        painel.add(campo1);
        painel.add(label2);
        painel.add(campo2);
        painel.add(botaoSoma);
        painel.add(botaoSubtracao);
        painel.add(botaoMultiplica);
        painel.add(botaoDivide);
        painel.add(new JLabel());
        painel.add(resultado);
    
        janela.add(painel);
        
        botaoSoma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(campo1.getText());
                    double num2 = Double.parseDouble(campo2.getText());
                    resultado.setText("Resultado: " + (num1 + num2));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(janela, "Número inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        )  ;
        
        botaoSubtracao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(campo1.getText());
                    double num2 = Double.parseDouble(campo2.getText());
                    resultado.setText("Resultado: " + (num1 - num2));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(janela, "Número inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        botaoMultiplica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(campo1.getText());
                    double num2 = Double.parseDouble(campo2.getText());
                    resultado.setText("Resultado: " + (num1 * num2));
                } catch (Exception ex) {
                JOptionPane.showMessageDialog(janela, "Número inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        botaoDivide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(campo1.getText());
                    double num2 = Double.parseDouble(campo2.getText());
                    if (num2 == 0) {
                        JOptionPane.showMessageDialog(janela, "Não pode dividir por zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                    } else {
                        resultado.setText("Resultado: " + (num1 / num2));
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(janela, "Número inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        janela.setVisible(true);
    }
}
