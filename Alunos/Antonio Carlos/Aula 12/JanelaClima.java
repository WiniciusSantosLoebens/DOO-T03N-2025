package ui;

import javax.swing.JFrame;

public class JanelaClima extends JFrame {
    public JanelaClima() {
        super("Previsão do Tempo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(new PainelClima());
        
        pack(); // Ajusta o tamanho da janela ao conteúdo
        setLocationRelativeTo(null); // Centraliza
        setResizable(false);
    }
}
