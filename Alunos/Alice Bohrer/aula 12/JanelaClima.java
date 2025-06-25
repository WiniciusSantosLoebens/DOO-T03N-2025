package ui;

import javax.swing.JFrame;

public class JanelaClima extends JFrame {
    public JanelaClima() {
        super("Previsão do Tempo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(new PainelClima());
        
        pack(); 
        setLocationRelativeTo(null); 
        setResizable(false);
    }
}
