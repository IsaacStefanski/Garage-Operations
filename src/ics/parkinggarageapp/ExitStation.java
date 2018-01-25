package ics.parkinggarageapp;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An ExitStation is an implementation of <code>AutomatedParkingStation</code>.
 * An ExitStation will be the last AutomatedParkingStation in the parking
 * process. A vehicle's ticket (Received at entry) must be inserted to calculate
 * the fee owed based on the amount of time parked. Fees are calculated using a
 * <code>ParkingFeeCalculator</code>. A <code>Receipt</code> is printed with
 * the date, time, fee, and vehicle ID.
 * <p>
 * Revision History:
 * <ul>
 * <li>2017-10-17: Initial version of ExitStation class</li>
 * <li>2017-11-10: Replace checkOutVehicle() with processParkingTransaction() to
 * more closely follow DIP/Strategy Pattern</li>
 * <li>2017-11-14: Add unique identifier to differentiate between exit stations</li>
 * </ul>
 * 
 * @author Isaac Stefanski, istefanski@my.wctc.edu
 * @version 1.02
 * @since 1.8
 */
public class ExitStation {
    private static int exitStationNum;
    private String exitStationID;
    private ParkingStationScreen screen = new ParkingStationScreen();
    private ReceiptPrinter receiptPrinter;
    private ParkingGarage garage;
    private String exitMessage;
    private ParkingFeeCalculator parkingFeeCalc;
    private ManagementReportGenerator reportGen;
    private boolean armLowered = true;
    
    /**
     * Constructor to create an ExitStation object
     * 
     * @param garage a valid <code>ParkingGarage</code> that is not null
     * @param exitMessage a <code>String</code> containing a message the driver
     * will see when pulling up to the ExitStation
     * @param parkingFeeCalc a valid <code>ParkingFeeCalculator</code> that is
     * not null, like <code>MinMaxFeeCalc</code>
     * @param reportGen a valid <code>ManagementReportGenerator</code> that is not null
     */
    public ExitStation(ParkingGarage garage, String exitMessage,
            ParkingFeeCalculator parkingFeeCalc, ManagementReportGenerator reportGen){
        exitStationNum++;
        setExitStationID("EX" + exitStationNum);
        setParkingGarage(garage);
        setExitMessage(exitMessage);
        setParkingFeeCalc(parkingFeeCalc);
        setReportGen(reportGen);
    }
    
    /**
     * Receives the vehicle's ticket from entry and prints a receipt with the
     * calculated fee for the time parked
     * 
     * @param vehicle a valid <code>Vehicle</code> that is not null
     */
    public final void processParkingTransaction(Vehicle vehicle) throws IllegalArgumentException, EncodingException {
        Ticket ticket = vehicle.getTicket();
        displayOutputToScreen(exitMessage);
        Receipt receipt = new Receipt(garage, ticket, parkingFeeCalc);
        receiptPrinter = new ReceiptPrinter(receipt);
        activatePrinter(receiptPrinter);
        raiseArm();
        garage.leaveParkingSpace();
        lowerArm();
        try {
            //Generate a report for management after the vehicle is checked out
            reportGen.updateTotals(ticket.getTimeParked(), receipt.calcTotalDue());
        } catch (Exception ex) {
        }
    }
    
    /**
     * Calls the screen output method with a message to display
     * 
     * @param message a <code>String</code> that is not null and contains the 
     * information to display on the <code>ParkingStationScreen</code>
     */
    public final void displayOutputToScreen(String message) {
        screen.outputParkingInfo(message);
    }

    /**
     * Calls the printer's performPrint() method to output information
     * 
     * @param printer a valid <code>Printer</code> that is not null, like a
     * <code>TicketPrinter</code> or <code>ReceiptPrinter</code>
     */
    public final void activatePrinter(Printer printer) {
        printer.performPrint();
    }
    
    /**
     * Simulates the <code>AutomatedParkingStation</code> raising the arm to
     * allow the vehicle to proceed and displays a message to remind the driver
     * to choose this garage to park at again
     */
    public final void raiseArm(){
        armLowered = false;
        displayOutputToScreen("See you next time!");
    }
    
    /**
     * Simulates the <code>AutomatedParkingStation</code> lowering the arm
     */
    public final void lowerArm(){
        armLowered = true;
    }
    
    public final static int getExitStationNum() {
        return exitStationNum;
    }

    public final static void setExitStationNum(int exitStationNum) throws IllegalArgumentException {
        if(exitStationNum >= 0){
            ExitStation.exitStationNum = exitStationNum;
        } else {
            throw new IllegalArgumentException("Invalid ID number");
        }
    }
    
    public final String getExitStationID() {
        return exitStationID;
    }

    public final void setExitStationID(String exitStationID) {
        if(exitStationID != null && exitStationID.length() > 0 && exitStationID.startsWith("EX")){
            this.exitStationID = exitStationID;
        } else {
            throw new IllegalArgumentException("Sorry, that ID is invalid");
        }
    }

    public final ParkingStationScreen getScreen() {
        return screen;
    }

    public final void setScreen(ParkingStationScreen screen) throws IllegalArgumentException {
        if(screen != null){
            this.screen = screen;
        } else {
            throw new IllegalArgumentException("Sorry, the screen is null");
        }
    }

    public final String getExitMessage() {
        return exitMessage;
    }

    public final void setExitMessage(String exitMessage) throws IllegalArgumentException {
        if(exitMessage != null && exitMessage.length() > 0){
            this.exitMessage = exitMessage;
        } else {
            throw new IllegalArgumentException("Sorry, that exit message is invalid");
        }
    }
    
    public final ParkingGarage getParkingGarage() {
        return garage;
    }

    public final void setParkingGarage(ParkingGarage garage) throws IllegalArgumentException {
        if(garage != null){
            this.garage = garage;
        } else {
            throw new IllegalArgumentException("Sorry, the garage is null");
        }
    }
    
    public final ParkingFeeCalculator getParkingFeeCalc() {
        return parkingFeeCalc;
    }

    public final void setParkingFeeCalc(ParkingFeeCalculator parkingFeeCalc) throws IllegalArgumentException {
        if(parkingFeeCalc != null){
            this.parkingFeeCalc = parkingFeeCalc;
        } else {
            throw new IllegalArgumentException("Sorry, you have attempted to use a null fee calculator");
        }
    }
    
    public final ManagementReportGenerator getReportGen() {
        return reportGen;
    }

    public final void setReportGen(ManagementReportGenerator reportGen) throws IllegalArgumentException {
        if(reportGen != null){
            this.reportGen = reportGen;
        } else {
            throw new IllegalArgumentException("Sorry, you have attempted to utilize a null report generator");
        }
    }
    
    public final boolean isArmLowered() {
        return armLowered;
    }

    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.exitStationID);
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
        final ExitStation other = (ExitStation) obj;
        if (!Objects.equals(this.exitStationID, other.exitStationID)) {
            return false;
        }
        return true;
    }

    /**
     * Returns basic information about the <code>ExitStation</code> object
     * 
     * @return a <code>String</code> with the garage name and exit station's ID
     */
    @Override
    public final String toString() {
        return "Garage: " + garage.getGarageName() + " Exit Station ID: " + exitStationID;
    }
}