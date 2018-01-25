package ics.parkinggarageapp;

import java.util.Objects;

/**
 * An EntryStation is an implementation of <code>AutomatedParkingStation</code>.
 * An EntryStation will be the first AutomatedParkingStation in the parking
 * process. A ticket will be printed for each vehicle that parks.
 * <p>
 * Revision History:
 * <ul>
 * <li>2017-10-17: Initial version of EntryStation class</li>
 * <li>2017-11-10: Replace checkInVehicle() with processParkingTransaction() to
 * more closely follow DIP/Strategy Pattern</li>
 * <li>2017-11-14: Add unique identifier to differentiate between entry stations</li>
 * <li>2017-11-30: Add <code>TicketDatabase</code> to track cars and tickets</li>
 * <li>2017-12-12: Add and use <code>ExternalSpecFactory</code> in application</li>
 * </ul>
 * 
 * @author Isaac Stefanski, istefanski@my.wctc.edu
 * @version 1.04
 * @since 1.8
 */
public class EntryStation {
    private static int entryStationNum;
    private String entryStationID;
    private ParkingStationScreen screen = new ParkingStationScreen();
    private TicketPrinter ticketPrinter;
    private TicketDatabase ticketDB;
    private Camera camera;
    private ParkingGarage garage;
    private String welcomeMessage;
    private boolean armLowered = true;
    
    /**
     * Constructor to create an EntryStation object
     * 
     * @param garage a valid <code>ParkingGarage</code> that is not null
     * @param welcomeMessage a <code>String</code> containing a message the driver
     * will see when pulling up to the EntryStation
     * @param ticketDB a valid <code>TicketDatabase</code> that is not null
     */
    public EntryStation(ParkingGarage garage, String welcomeMessage, TicketDatabase ticketDB){
        entryStationNum++;
        setEntryStationID("EN" + entryStationNum);
        setParkingGarage(garage);
        setWelcomeMessage(welcomeMessage);
        setTicketDB(ticketDB);
        setCamera(ExternalSpecFactory.getCameraInstance());
    }
    
    /**
     * Creates and prints a ticket for the vehicle that is parking in the garage
     * and deducts one space from the number of available parking spaces
     * 
     * @param vehicle a valid <code>Vehicle</code> that is not null
     */
    public final void processParkingTransaction(Vehicle vehicle){
        displayOutputToScreen(welcomeMessage + "\n\nThere are " + garage.getEmptyParkingSpaces() + " spaces available.");
        camera.capture(vehicle.getLicensePlate());
        Ticket ticket = new Ticket(vehicle);
        setTicketPrinter(new TicketPrinter(ticket));
        ticketDB.addTicket(ticket, camera.getData());
        activatePrinter(ticketPrinter);
        raiseArm();
        vehicle.setTicket(ticket);
        garage.fillParkingSpace();
        lowerArm();
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
     * allow the vehicle to proceed and displays a "Please proceed" message
     */
    public final void raiseArm(){
        armLowered = false;
        displayOutputToScreen("Please proceed");
    }
    
    /**
     * Simulates the <code>AutomatedParkingStation</code> lowering the arm
     */
    public final void lowerArm(){
        armLowered = true;
    }
    
    public final static int getEntryStationNum() {
        return entryStationNum;
    }

    public final static void setEntryStationNum(int entryStationNum) throws IllegalArgumentException {
        if(entryStationNum >= 0){
            EntryStation.entryStationNum = entryStationNum;
        } else {
            throw new IllegalArgumentException("Invalid ID number");
        }
    }

    public final String getEntryStationID() {
        return entryStationID;
    }

    public final void setEntryStationID(String entryStationID) {
        if(entryStationID != null && entryStationID.length() > 0 && entryStationID.startsWith("EN")){
            this.entryStationID = entryStationID;
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

    public final String getWelcomeMessage() {
        return welcomeMessage;
    }

    public final void setWelcomeMessage(String welcomeMessage) throws IllegalArgumentException {
        if(welcomeMessage != null && welcomeMessage.length() > 0){
            this.welcomeMessage = welcomeMessage;
        } else {
            throw new IllegalArgumentException("Sorry, that welcome message is invalid");
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

    public final boolean isArmLowered() {
        return armLowered;
    }
    
    public final TicketDatabase getTicketDB() {
        return ticketDB;
    }

    public final void setTicketDB(TicketDatabase ticketDB) {
        if(ticketDB != null){
            this.ticketDB = ticketDB;
        } else {
            throw new IllegalArgumentException("Sorry, that is an invalid database");
        }
    }

    public final Camera getCamera() {
        return camera;
    }

    public final void setCamera(Camera camera) throws IllegalArgumentException {
        if(camera == null){
            throw new IllegalArgumentException("Camera cannot be null");
        }
        this.camera = camera;
    }

    public final TicketPrinter getTicketPrinter() {
        return ticketPrinter;
    }

    public final void setTicketPrinter(TicketPrinter ticketPrinter) throws IllegalArgumentException {
        if(ticketPrinter == null){
            throw new IllegalArgumentException("TicketPrinter cannot be null");
        }
        this.ticketPrinter = ticketPrinter;
    }
    
    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.entryStationID);
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
        final EntryStation other = (EntryStation) obj;
        if (!Objects.equals(this.entryStationID, other.entryStationID)) {
            return false;
        }
        return true;
    }

    /**
     * Returns basic information about the <code>EntryStation</code> object
     * 
     * @return a <code>String</code> with the garage name and entry station's ID
     */
    @Override
    public final String toString() {
        return "Garage: " + garage.getGarageName() + " Entry Station ID: " + entryStationID;
    }
}