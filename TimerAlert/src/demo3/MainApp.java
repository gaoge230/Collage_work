package demo3;

import java.awt.*;
import javax.swing.*;

public class MainApp {
        private static void createAndShowGUI() {
                JFrame mainFrame = new JFrame("����������");
                JLabel jLabel = new JLabel("����һ����ǩ", SwingConstants.CENTER);
                jLabel.setPreferredSize(new Dimension(400, 300));
    mainFrame.getContentPane().add(jLabel, BorderLayout.CENTER);
    
                mainFrame.pack();
                TrayFactory.createTray(mainFrame, "images/icon.png", "��ã�Java!\nHello,Java!");
                mainFrame.setVisible(true);
        }
    
        public static void main(String[] args) {
                String lf = UIManager.getSystemLookAndFeelClassName();
                try {
                        UIManager.setLookAndFeel(lf);
                } 
                catch(Exception e) {
                }
                
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                        createAndShowGUI();
                                }
    });
        }    
}

