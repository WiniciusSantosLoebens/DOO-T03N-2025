package org.example;

import org.example.exception.CalculadoraException;
import org.example.model.OperacaoMatematica;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Calculadora extends JFrame {

    private final JTextField primeiroNumeroField;
    private final JTextField segundoNumeroField;
    private final JLabel resultadoLabel;


    public Calculadora() {

        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(null);


        JPanel painelEntrada = new JPanel(new GridLayout(2, 2, 10, 10));
        painelEntrada.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JLabel primeiroNumeroLabel = new JLabel("Primeiro número:");
        primeiroNumeroField = new JTextField(10);
        JLabel segundoNumeroLabel = new JLabel("Segundo número:");
        segundoNumeroField = new JTextField(10);
        
        painelEntrada.add(primeiroNumeroLabel);
        painelEntrada.add(primeiroNumeroField);
        painelEntrada.add(segundoNumeroLabel);
        painelEntrada.add(segundoNumeroField);


        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        

        String[] operacoes = {"+", "-", "×", "÷"};
        for (String operacao : operacoes) {
            JButton botao = new JButton(operacao);
            botao.setFont(new Font("Arial", Font.BOLD, 14));
            botao.addActionListener(e -> calcular(operacao));
            botao.setPreferredSize(new Dimension(50, 40));
            painelBotoes.add(botao);
        }


        JPanel painelResultado = new JPanel(new BorderLayout());
        painelResultado.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        

        resultadoLabel = new JLabel("Resultado: ");
        resultadoLabel.setHorizontalAlignment(JLabel.CENTER);
        resultadoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        painelResultado.add(resultadoLabel, BorderLayout.CENTER);
        

        JButton limparBotao = new JButton("Limpar");
        limparBotao.addActionListener(e -> {
            primeiroNumeroField.setText("");
            segundoNumeroField.setText("");
            resultadoLabel.setText("Resultado: ");
        });
        limparBotao.setPreferredSize(new Dimension(100, 40));
        painelBotoes.add(limparBotao);


        add(painelEntrada, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(painelResultado, BorderLayout.SOUTH);
    }


    private void calcular(String operacao) {
        try {

            double numero1 = validarEntrada(primeiroNumeroField.getText());
            double numero2 = validarEntrada(segundoNumeroField.getText());


            OperacaoMatematica operacaoMat = new OperacaoMatematica();
            operacaoMat.setPrimeiroNumero(numero1);
            operacaoMat.setSegundoNumero(numero2);
            operacaoMat.setOperacao(operacao);


            double resultado;
            switch (operacao) {
                case "+" -> resultado = numero1 + numero2;
                case "-" -> resultado = numero1 - numero2;
                case "×" -> resultado = numero1 * numero2;
                case "÷" -> {
                    if (numero2 == 0) {

                        throw new CalculadoraException("Erro: Divisão por zero não é permitida!");
                    }
                    resultado = numero1 / numero2;
                }
                default -> throw new CalculadoraException("Operação inválida!");
            }


            String resultadoFormatado;
            if (resultado == Math.floor(resultado)) {
                resultadoFormatado = String.format("%.0f", resultado);
            } else {
                resultadoFormatado = String.format("%.10g", resultado);
            }


            operacaoMat.setResultado(resultado);
            resultadoLabel.setText("Resultado: " + resultadoFormatado);


            salvarOperacao(operacaoMat);

        } catch (CalculadoraException e) {

            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de Cálculo", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, 
                "Erro inesperado: " + e.getMessage(), 
                "Erro do Sistema", 
                JOptionPane.ERROR_MESSAGE);
        }
    }


    private double validarEntrada(String input) throws CalculadoraException {
        if (input == null || input.trim().isEmpty()) {
            throw new CalculadoraException("Por favor, preencha todos os campos!");
        }
        
        try {

            return Double.parseDouble(input.replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new CalculadoraException("Valor inválido! Por favor, digite apenas números.");
        }
    }


    private void salvarOperacao(OperacaoMatematica operacao) {
        try {

            File diretorio = new File("historico");
            if (!diretorio.exists()) {
                diretorio.mkdir();
            }
            

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String dataFormatada = sdf.format(new Date());
            String nomeArquivo = "historico/operacao_" + dataFormatada + ".txt";
            

            try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
                writer.println("Data e Hora: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
                writer.println("Primeiro Número: " + operacao.getPrimeiroNumero());
                writer.println("Segundo Número: " + operacao.getSegundoNumero());
                writer.println("Operação: " + operacao.getOperacao());
                writer.println("Resultado: " + operacao.getResultado());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar operação: " + e.getMessage());
        }
    }


    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        SwingUtilities.invokeLater(() -> new Calculadora().setVisible(true));
    }
}
