import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        JFrame jf = new JFrame("Calculadora");

        String nome = JOptionPane.showInputDialog(jf,"Qual seu nome?", "Bem vindo", JOptionPane.PLAIN_MESSAGE);

        String num1 = JOptionPane.showInputDialog(nome + " digite primeiro valor: ");
        String num2 = JOptionPane.showInputDialog("Agora " + nome + " digite o segundo valor: ");

        int numero1 = Integer.parseInt(num1);
        int numero2 = Integer.parseInt(num2);

        String operacao = JOptionPane.showInputDialog("Escolha uma opção:  \n[1] + \n[2] - \n[3] * \n[4] /", JOptionPane.QUESTION_MESSAGE);
        int op = Integer.parseInt(operacao);

        switch (op) {
            case 1:
                JOptionPane.showMessageDialog(  null, numero1 + " + " + numero2 + " = " + (numero1 + numero2));
                break;
            case 2:
                JOptionPane.showMessageDialog(null, numero1 + " - " + numero2 + " = " + (numero1 - numero2));
                break;
            case 3:
                JOptionPane.showMessageDialog(null, numero1 + " * " + numero2 + " = " + (numero1 * numero2));
                break;
            case 4:
                if(numero2 == 0){
                    JOptionPane.showMessageDialog(null,"Erro operacao invalida!!","ATENÇÃO", JOptionPane.ERROR_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, numero1 + " / " + numero2 + " = " + (numero1 / numero2));
                } break;
                default:
                    JOptionPane.showMessageDialog(null,  "Opeção invalida");
        }
        System.exit(0);
    }
}