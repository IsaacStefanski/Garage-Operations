package ics.parkinggarageapp;

import java.util.Objects;
import javax.swing.JOptionPane;

/**
 * ParkingStationScreen is an implementation of <code>ParkingInfoOutput</code>
 * that simulates the screen on an <code>AutomatedParkingStation</code> by using
 * a GUI. The outputParkingInfo() method utilizes a <code>JOptionPane</code> to
 * display output.
 * <p>
 * Revision History:
 * <ul>
 * <li>2017-10-17: Initial version of ParkingStationScreen class</li>
 * <li>2017-11-14: Add overrides to equals(), hashCode(), and toString()</li>
 * </ul>
 * 
 * @author Isaac Stefanski, istefanski@my.wctc.edu
 * @version 1.01
 * @since 1.8
 */
public class ParkingStationScreen implements ParkingInfoOutput {
    private static int screenNum;
    private String screenID;
    
    /**
     * Constructs a ParkingStationScreen, incrementing screenNum and setting the ID
     */
    public ParkingStationScreen() {
        screenNum++;
        setScreenID("SC" + screenNum);
    }
    
    /**
     * Outputs a <code>String</code> to a <code>JOptionPane</code> to simulate
     * the screen on an <code>AutomatedParkingStation</code>
     * 
     * @param message a valid <code>String</code> that is not null with information
     * to display to the GUI / screen
     */
    @Override
    public final void outputParkingInfo(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
    
    public final static int getScreenNum() {
        return screenNum;
    }

    public final static void setScreenNum(int screenNum) throws IllegalArgumentException {
        if(screenNum >= 0){
            ParkingStationScreen.screenNum = screenNum;
        } else {
            throw new IllegalArgumentException("Invalid ID number");
        }
    }

    public final String getScreenID() {
        return screenID;
    }

    public final void setScreenID(String screenID) {
        if(screenID != null && screenID.length() > 0 && screenID.startsWith("SC")){
            this.screenID = screenID;
        } else {
            throw new IllegalArgumentException("Sorry, that ID is invalid");
        }
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.screenID);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParkingStationScreen other = (ParkingStationScreen) obj;
        if (!Objects.equals(this.screenID, other.screenID)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a <code>String</code> with the ID
     * 
     * @return a <code>String</code> with the ID
     */
    @Override
    public final String toString() {
        return "Screen ID: " + screenID;
    }
}