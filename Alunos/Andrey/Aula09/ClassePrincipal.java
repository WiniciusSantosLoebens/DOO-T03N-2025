package Main;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ClassePrincipal extends RuntimeException {

	private static Scanner scan = new Scanner(System.in);
	private static JFrame frame = new JFrame("Calculadora");
	
	
	public static void main(String[] args) {
		//cria pagina
		
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//cria painel para campos
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		
		//criar tela
		JLabel primeiroTexto = new JLabel("Digite primeiro numero");
		JTextField primeiroNumero = new JTextField();
		JLabel segundoTexto = new JLabel("Digite segundo numero");
		JTextField segundoNumero = new JTextField();
		JButton buttonMais = new JButton("+");
		JButton buttonMenos = new JButton("-");
		JButton buttonMulti = new JButton("*");
		JButton buttonDivi = new JButton("/");
		JLabel resposta = new JLabel("resposta");
		
		//add conteiners
		JPanel labelsPanel = new JPanel();
		labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS)); 
		labelsPanel.add(primeiroTexto);
		labelsPanel.add(primeiroNumero);
		labelsPanel.add(segundoTexto);
		labelsPanel.add(segundoNumero);
		labelsPanel.add(buttonMais);
		labelsPanel.add(buttonMenos);
		labelsPanel.add(buttonMulti);
		labelsPanel.add(buttonDivi);
		labelsPanel.add(resposta);
		panel.add(labelsPanel);
		
		buttonMais.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer num1 = Integer.parseInt(primeiroNumero.getText());
					Integer num2 = Integer.parseInt(segundoNumero.getText());
					Integer ope = 1;
					String resp = Double.toString(Calculadora(num1, num2, ope));
					resposta.setText(resp);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, 
							"Apenas numeros podem ser usados",
							"caracter invalido",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		buttonMenos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer num1 = Integer.parseInt(primeiroNumero.getText());
					Integer num2 = Integer.parseInt(segundoNumero.getText());
					Integer ope = 2;
					String resp = Double.toString(Calculadora(num1, num2, ope));
					resposta.setText(resp);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, 
							"Apenas numeros podem ser usados",
							"caracter invalido",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		buttonMulti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer num1 = Integer.parseInt(primeiroNumero.getText());
					Integer num2 = Integer.parseInt(segundoNumero.getText());
					Integer ope = 3;
					String resp = Double.toString(Calculadora(num1, num2, ope));
					resposta.setText(resp);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, 
							"Apenas numeros podem ser usados",
							"caracter invalido",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		buttonDivi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer num1 = Integer.parseInt(primeiroNumero.getText());
					Integer num2 = Integer.parseInt(segundoNumero.getText());
					Integer ope = 4;
					String resp = Double.toString(Calculadora(num1, num2, ope));
					if(resp != "Infinity") {
							resposta.setText(resp);
						}
					else {
						JOptionPane.showMessageDialog(frame, 
								"Nao pode dividir por 0",
								"Erro na Divisao",
								JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, 
							"Apenas numeros podem ser usados",
							"caracter invalido",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		frame.add(panel);
		
		frame.setVisible(true);

	}
	
	
	public static double Calculadora(double num1, double num2, Integer ope) {
		double resp = 0;
		switch (ope) {
		case 1: {
			resp = num1 + num2;
			break;
		}case 2: {
			resp = num1 - num2;
			break;
		}
		case 3: {
			resp = num1 * num2;
			break;
		}
		case 4: {
			try {
				resp = num1 / num2;
			} catch (ArithmeticException e) {
				resp = 999;
			}
			break;
		}
		default:
			throw new IllegalArgumentException("operação digitata incorreta");
		}
		return resp;
	}

}
