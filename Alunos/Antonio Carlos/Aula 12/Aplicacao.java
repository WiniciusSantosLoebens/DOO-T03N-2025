package ui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Aplicacao {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new JanelaClima().setVisible(true);
        });
    }
}
