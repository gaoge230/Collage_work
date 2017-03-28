package demo3;

import java.awt.*;
import java.awt.event.*;

public class TrayFactory {
        private TrayIcon trayIcon = null;
        private Component comp = null;
        private String trayImageName = null;
        private String tooltip = null;
        
        private TrayFactory(Component comp, String trayImageName, String tooltip) {
                this.comp = comp;
                this.trayImageName = trayImageName;
                this.tooltip = tooltip;
                
                init();
        }
        
        private void init() {
                if (SystemTray.isSupported()) {
                        SystemTray tray = SystemTray.getSystemTray();
                        Image image  = Toolkit.getDefaultToolkit().getImage(trayImageName);
                        PopupMenu popupMenu = new PopupMenu();
                        MenuItem restoreItem = new MenuItem("»¹Ô­"); 
                        MenuItem exitItem  = new MenuItem("ÍË³ö");
                        
                        restoreItem.addActionListener(new RestoreItemActionListener());                        
                        exitItem.addActionListener(new ExitItemActionListener());
                        
                        popupMenu.add(restoreItem);
                        popupMenu.addSeparator();
                        popupMenu.add(exitItem);
                        
                        trayIcon = new TrayIcon(image, tooltip,  popupMenu);
                        trayIcon.addMouseListener(new TrayIconMouseListener());
                        //trayIcon.addActionListener(new TrayIconActionListener());
                                                
                        try {
                                tray.add(trayIcon);
                        }
                        catch (AWTException e) {
                                System.err.println(e);
                        }
                }
                else {
                        //..........
                }
        }

        private class RestoreItemActionListener implements ActionListener {
                public void actionPerformed(ActionEvent ae) {
                        comp.setVisible(true);
                }
        }
                
        private class ExitItemActionListener implements ActionListener {
                public void actionPerformed(ActionEvent ae) {
                        System.exit(0);
                }
        }
        
        private class TrayIconMouseListener extends MouseAdapter {
                public void mousePressed(MouseEvent me) {
                        if (me.getButton() == MouseEvent.BUTTON1) {
                                comp.setVisible(true);
                        }
                }
        }

        public static void createTray(Component comp, String trayImageName, String tooltip) {
                new TrayFactory(comp, trayImageName, tooltip);
        }
}
