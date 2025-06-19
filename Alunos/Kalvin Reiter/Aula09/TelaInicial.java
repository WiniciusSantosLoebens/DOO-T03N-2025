import java.awt.*;//Sistema de grid
import java.awt.event.*;//Sistema de eventos
import javax.swing.*;//Definir as margens
import javax.swing.border.EmptyBorder;//Definir as margens 

public class TelaInicial {
    
    //JFrame = janela
    //JButton = botão
    //JLabel = legenda
    //JTextField = campo
    //JPanel = painel

    public static int escolhaOp;
    public static Double resultado;
    public static Double num1;
    public static Double num2;
    
    public static void main(String[] args) {

        //Janela
        JFrame janela = new JFrame("Calculadora simples");//Criando a janela

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Janela fecha após clicar X
        janela.setSize(300, 300);//Tamanho da janela
        JPanel painel = new JPanel(new GridLayout(4, 1));//Criando um sistema de grid
        painel.setBorder(new EmptyBorder(20, 20, 20, 20));//Colocando as margens


        //Primeiro número
        JPanel painelPri = new JPanel(new FlowLayout(FlowLayout.LEFT));//Criando um painel FlowLayout para controlar o tamanho do campo

        JLabel priNum = new JLabel("Digite o primeiro número:");//Legenda
        painelPri.add(priNum); 

        JTextField campoPriNum = new JTextField();//Campo
        campoPriNum.setPreferredSize(new Dimension(100, 25));//Definindo o tamanho
        painelPri.add(campoPriNum);

        painel.add(painelPri);//Adicionando ao painel principal

        campoPriNum.addActionListener((ActionEvent e) -> {// Código que roda quando o Enter é pressionado no campo
            try {

                double num3 = Double.parseDouble(campoPriNum.getText());
                System.out.println("Primeiro número digitado: " + num3);//Controle de entrada

            }catch (NumberFormatException ex) {//Número inválido

                JOptionPane.showMessageDialog(null, "O número digitado é inválido, digite um número novamente.", "Erro", JOptionPane.ERROR_MESSAGE);//Mensagem de erro
            
            }
        });


        //Segundo número
        JPanel painelSeg = new JPanel(new FlowLayout(FlowLayout.LEFT));//Criando um painel FlowLayout para controlar o tamanho do campo

        JLabel segNum = new JLabel("Digite o segundo número:");//Legenda
        painelSeg.add(segNum); 

        JTextField campoSegNum = new JTextField();//Campo
        campoSegNum.setPreferredSize(new Dimension(100, 25));//Definindo o tamanho
        painelSeg.add(campoSegNum);

        painel.add(painelSeg);//Adicionando ao painel principal

        campoSegNum.addActionListener((ActionEvent e) -> {// Código que roda quando o Enter é pressionado no campo
            try {

                double num3 = Double.parseDouble(campoSegNum.getText());
                System.out.println("Segundo número digitado: " + num3);//Controle de entrada
                
            }catch (NumberFormatException ex) {//Número inválido

                JOptionPane.showMessageDialog(null, "O número digitado é inválido, digite um número novamente.", "Erro", JOptionPane.ERROR_MESSAGE);//Mensagem de erro
            
            }
        });


        //Operações
        JPanel painelOp = new JPanel(new FlowLayout(FlowLayout.LEFT));//Criando o painel dos botões

        JButton adi = new JButton("+");//Botão (+)
        painelOp.add(adi);

        JButton sub = new JButton("-");//Botão (-)
        painelOp.add(sub);

        JButton mult = new JButton("*");//Botão (*)
        painelOp.add(mult);

        JButton div = new JButton("/");//Botão (/)
        painelOp.add(div);
        
        painel.add(painelOp);//Adicionando ao painel principal

        
        //Resultado - preparando
        JLabel r = new JLabel("Resultado:");//Legenda
        JTextField campoResul = new JTextField();//Campo

        campoResul.setPreferredSize(new Dimension(100, 25)); //Definindo o tamanho
        campoResul.setEditable(false); //Impede o usuário de editar
        campoResul.setText(""); //Inicia vazio

        JPanel painelResul = new JPanel(new FlowLayout(FlowLayout.LEFT));//Criandoco painel de resultado
        painelResul.add(r);
        painelResul.add(campoResul);

        painel.add(painelResul);//Adicionando ao painel principal


        //Resultado - uso
        ActionListener escolha = (ActionEvent e) -> { //Criando um conduite
            try {

                //Pega as variáveis
                num1 = Double.valueOf(campoPriNum.getText());
                num2 = Double.valueOf(campoSegNum.getText());
                
                Object operacao = e.getSource();//Pega o clique do usuário
                
                if (operacao == adi) {//Adição

                    resultado = num1 + num2;
                    System.out.println("Operacao escolhida: soma(+)");//Controle de entrada

                } else if (operacao == sub) {//Subtração

                    resultado = num1 - num2;
                    System.out.println("Operacao escolhida: subtracao(-)");//Controle de entrada

                } else if (operacao == mult) {//Multiplicação

                    resultado = num1 * num2;
                    System.out.println("Operacao escolhida: multiplicacao(*)");//Controle de entrada

                } else if (operacao == div) {//Divisão

                    if (num2 == 0) {//Divisão por 0

                        JOptionPane.showMessageDialog(null, "É impossivel dividir algum número por 0, digite um número novamente, ou tente alguma outra operação.", "Erro", JOptionPane.ERROR_MESSAGE);//Mensagem de erro
                        return;

                    }

                    resultado = num1 / num2;
                    System.out.println("Operacao escolhida: divisao(/)");//Controle de entrada
                }
                
                campoResul.setText(String.valueOf(resultado));//Imprime o resultado
                
            } catch (NumberFormatException ex) {//Algum número não digitado

                JOptionPane.showMessageDialog(null, "É necessario 2 números para realizar as operações, digite o(s) número(s) restante(s).", "Erro", JOptionPane.ERROR_MESSAGE);//Mensagem de erro
            
            }
        };

        //Linkando botões
        adi.addActionListener(escolha);
        sub.addActionListener(escolha);
        mult.addActionListener(escolha);
        div.addActionListener(escolha);

        janela.add(painel);//Adicionando ao painel principal

        janela.setVisible(true);//Deixando a janela visível
    }
}