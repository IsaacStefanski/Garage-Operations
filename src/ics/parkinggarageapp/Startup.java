package ics.parkinggarageapp;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Isaac
 */
public class Startup {
    public static void main(String[] args) throws Exception {
        try {          
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Couldn't load System Look and Feel, continuing without");
        }        
        ParkingGarageOperationsWindow opsWindow = new ParkingGarageOperationsWindow();
        opsWindow.setVisible(true);
    }
}